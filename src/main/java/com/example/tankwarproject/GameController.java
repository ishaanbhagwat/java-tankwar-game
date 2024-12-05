package com.example.tankwarproject;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    private Pane gamePane;
    private Scene gameScene;

    private GameMap gameMap;
    private PlayerTank playerTank;
    private List<Missile> missiles;
    private List<AITank> aiTanks;

    private long lastAIMovementTime = 0;
    private long lastAIFiringTime = 0;
    private PlayerInputHandler inputHandler;

    private int playerLives = 3; // Track player's lives
    private int aiLives;
    private int playerHealth = 100; // Player health
    private Text livesText;
    private Text aiLivesText;
    private ProgressBar healthBar;

    public GameController(Pane gamePane) {
        this.gamePane = gamePane;
        this.gameScene = new Scene(gamePane);
        this.gameMap = GameMap.getInstance();
        this.missiles = new ArrayList<>();
        this.aiTanks = new ArrayList<>();
        this.inputHandler = new PlayerInputHandler();
    }

    public Scene getGameScene() {
        return gameScene;
    }

    public void startNewGame() {
        initializeUI();
        initializePlayerTank();
        initializeAITanks();
        aiLives = aiTanks.size();
        gameMap.initialize(gamePane);
        inputHandler.handleInput(gameScene, playerTank, gameMap, missiles);
        startGameLoop();
    }

    public void startGameLoop() {
        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;

            @Override
            public void handle(long now) {
                // Main game update (60 FPS)
                if (now - lastUpdate >= 16_666_667) {
                    updateGame();
                    lastUpdate = now;
                }

                // AI Tank Movement (Every 3 seconds)
                if (now - lastAIMovementTime >= 500_000_000L) {
                    for (AITank aiTank : aiTanks) {
                        aiTank.moveRandomly(gameMap);
                    }
                    lastAIMovementTime = now;
                }

                // AI Tank Firing (Every 1.5 seconds)
                if (now - lastAIFiringTime >= 1_000_000_000L) {
                    for (AITank aiTank : aiTanks) {
                        aiTank.fireMissileBurst(missiles);
                    }
                    lastAIFiringTime = now;
                }
            }
        };
        timer.start();
    }

    private void updateGame() {
        if(playerTank.isDestroyed()) {
            playerLives--; // Decrease life on death
            gamePane.getChildren().remove(playerTank.getRepresentation());
            if (playerLives > 0) {
                respawnPlayerTank();
            }
            else {
                gameOver();
                return;
            }
        }
        if(aiTanks.isEmpty()) {
            victory();
            return;
        }

        List<Missile> toRemove = new ArrayList<>();
        for (Missile missile : missiles) {
            missile.move();
            checkMissileCollision(missile, toRemove);
        }

        // Remove missiles from the game pane and the missiles list
        for (Missile missile : toRemove) {
            gamePane.getChildren().remove(missile.getRepresentation());
        }
        missiles.removeAll(toRemove);

        // Remove destroyed tanks
        aiTanks.removeIf(aiTank -> {
            if (aiTank.isDestroyed()) {
                aiLives--;
                gamePane.getChildren().remove(aiTank.getRepresentation());
                return true; // Remove from list
            }
            return false;
        });

        updateUI(); // Update lives and health display
        render(); // Render updates
    }

    private void render() {
        for (Missile missile : missiles) {
            if (!gamePane.getChildren().contains(missile.getRepresentation())) {
                gamePane.getChildren().add(missile.getRepresentation());
            }
        }
    }

    private void initializePlayerTank() {
        playerTank = new PlayerTank(350, 200, 300, Direction.UP);
        gamePane.getChildren().add(playerTank.getRepresentation());
    }

    private void initializeAITanks() {
        AITank aiTank1 = new AITank(400, 300, 100, Direction.DOWN);
        AITank aiTank2 = new AITank(500, 400, 100, Direction.UP);
        aiTanks.add(aiTank1);
        aiTanks.add(aiTank2);

        for (AITank aiTank : aiTanks) {
            gamePane.getChildren().add(aiTank.getRepresentation());
        }
    }

    private void checkMissileCollision(Missile missile, List<Missile> toRemove) {
        // Check collisions with walls
        for (Wall wall : gameMap.getWalls()) {
            if (missile.intersects(wall.getBounds())) {
                toRemove.add(missile);
                return;
            }
        }

        // Check collision with tanks
        if (missile.getSourceTank() != null) {
            if (missile.intersects(playerTank.getBounds()) && missile.getSourceTank() != playerTank) {
                playerTank.takeDamage(missile.getDamage());
                playerHealth = playerTank.getHealth();
                if (playerTank.isDestroyed()) {
                    new Explosion(playerTank.getX(), playerTank.getY(), gamePane); // Trigger explosion
                }
                toRemove.add(missile);
                return;
            }
            for (AITank aiTank : aiTanks) {
                if (missile.intersects(aiTank.getBounds()) && missile.getSourceTank() != aiTank) {
                    aiTank.takeDamage(100);
                    if (aiTank.isDestroyed()) {
                        new Explosion(aiTank.getX(), aiTank.getY(), gamePane); // Trigger explosion
                    }
                    toRemove.add(missile);
                    return;
                }
            }
        }
    }

    private void respawnPlayerTank() {
        playerTank = new PlayerTank(350, 200, 300, Direction.UP); // Reset player tank
        gamePane.getChildren().add(playerTank.getRepresentation());
        playerHealth = 100; // Reset health

        inputHandler.handleInput(gameScene, playerTank, gameMap, missiles);
    }

    private void gameOver() {
        gamePane.getChildren().clear(); // Clear the game pane
        Text gameOverText = new Text("Game Over!");
        gameOverText.setX(200); // Position the text
        gameOverText.setY(200);
        gameOverText.setFont(Font.font("Arial", 50));
        gameOverText.setStyle("-fx-fill: white;");
        gamePane.getChildren().add(gameOverText); // Show Game Over text
    }

    private void victory() {
        gamePane.getChildren().clear(); // Clear the game pane
        Text gameOverText = new Text("Victory!");
        gameOverText.setX(200); // Position the text
        gameOverText.setY(200);
        gameOverText.setFont(Font.font("Arial", 50));
        gameOverText.setStyle("-fx-fill: white;");
        gamePane.getChildren().add(gameOverText); // Show Game Over text
    }

    private void initializeUI() {
        // Display player lives at the bottom left
        livesText = new Text("Lives: " + playerLives);
        livesText.setStyle("-fx-font-size: 18; -fx-fill: green;"); // Optional: Style the text
        livesText.setX(10); // Set to bottom-left corner
        livesText.setY(gamePane.getHeight() - 20); // Set Y position near the bottom
        gamePane.getChildren().add(livesText);

        aiLivesText = new Text("AI Lives: " + aiLives);
        aiLivesText.setStyle("-fx-font-size: 18; -fx-fill: green;"); // Optional: Style the text
        aiLivesText.setX(200);
        aiLivesText.setY(gamePane.getHeight() - 20); // Set Y position near the bottom
        gamePane.getChildren().add(aiLivesText);

        // Display player health bar at the bottom right
        healthBar = new ProgressBar(playerHealth / 100.0);
        healthBar.setLayoutX(gamePane.getWidth() - healthBar.getWidth() - 10); // Set to bottom-right corner
        healthBar.setLayoutY(gamePane.getHeight() - 40); // Set Y position near the bottom
        healthBar.setPrefWidth(200);// Set preferred width
        healthBar.setStyle("-fx-fill: green;");
        gamePane.getChildren().add(healthBar);
    }

    private void updateUI() {
        livesText.setText("Lives: " + playerLives); // Update lives text
        aiLivesText.setText("AI Lives: " + aiLives);
        healthBar.setProgress(playerHealth / 100.0); // Update health bar

        // Re-position UI elements if the game pane size changes (optional for resizing)
        livesText.setY(gamePane.getHeight() - 20);
        aiLivesText.setY(gamePane.getHeight() - 20);
        healthBar.setLayoutX(gamePane.getWidth() - healthBar.getWidth() - 10);
        healthBar.setLayoutY(gamePane.getHeight() - 40);
    }
}
