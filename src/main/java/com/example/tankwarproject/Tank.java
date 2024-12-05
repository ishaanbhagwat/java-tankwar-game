package com.example.tankwarproject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.util.List;

public abstract class Tank {
    private int health;
    private int x;
    private int y;
    private Direction direction;
    private ImageView representation;
    private Rectangle bounds;

    // Static image resources for each direction
    private static final Image upImage = new Image(Tank.class.getResource("/com/example/TankWarProject/tankU.gif").toExternalForm());
    private static final Image downImage = new Image(Tank.class.getResource("/com/example/TankWarProject/tankD.gif").toExternalForm());
    private static final Image leftImage = new Image(Tank.class.getResource("/com/example/TankWarProject/tankL.gif").toExternalForm());
    private static final Image rightImage = new Image(Tank.class.getResource("/com/example/TankWarProject/tankR.gif").toExternalForm());

    public Tank(int x, int y, int health, Direction direction) {
        this.health = health;
        this.direction = direction;
        this.x = x;
        this.y = y;

        // Initialize the ImageView with the appropriate direction image
        this.representation = new ImageView(getImageForDirection(direction));
        this.representation.setX(x);
        this.representation.setY(y);
        this.representation.setFitHeight(50);
        this.representation.setFitWidth(50);

        // Initialize the bounds for collision detection (same size as the tank)
        this.bounds = new Rectangle(x, y, 50, 50);
    }

    public void move(GameMap gameMap) {
        int currentRow = (int) bounds.getY() / 50;
        int currentCol = (int) bounds.getX() / 50;
        // Calculate the potential new grid position based on direction
        int targetRow = currentRow;
        int targetCol = currentCol;

        // Determine the new target posit   ion based on the current direction
        switch (direction) {
            case UP -> targetRow--;
            case DOWN -> targetRow++;
            case LEFT -> targetCol--;
            case RIGHT -> targetCol++;
        }

        // Check for boundaries: Ensure the target position is within the grid limits
        if (targetRow < 0 || targetRow >= gameMap.getRows() || targetCol < 0 || targetCol >= gameMap.getCols()) {
            return; // Prevent movement if outside the grid
        }

        // Check for collision with walls: Get the value of the target grid cell
        int tileType = gameMap.getMapGrid()[targetRow][targetCol];

        // If the tile is a wall (indestructible or destructible), prevent movement
        if (tileType == 1 || tileType == 2) {
            return; // Block movement if the target cell has a wall
        }

        // If there is no wall, move the tank to the new position
        double newX = targetCol * gameMap.getTileSize();
        double newY = targetRow * gameMap.getTileSize();
        // Update the bounds and representation of the tank

        bounds.setX(newX);
        bounds.setY(newY);
        representation.setX(newX);
        representation.setY(newY);
    }

    // Updates the direction and changes the image
    public void setDirection(Direction direction) {
        this.direction = direction;
        representation.setImage(getImageForDirection(direction));
    }

    // Damage
    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            health = 0;
            System.out.println("Tank destroyed!");
        }
    }

    public boolean isDestroyed() {
        return this.health <= 0;
    }

    // Fire Missile
    public void fireMissile(List<Missile> missiles) {
        double missileX = bounds.getX() + bounds.getWidth() / 2 - 5;
        double missileY = bounds.getY() + bounds.getHeight() / 2 - 5;

        switch (direction) {
            case UP -> missileY -= 20;   // Spawn slightly above the tank
            case DOWN -> missileY += 20; // Spawn slightly below the tank
            case LEFT -> missileX -= 20; // Spawn slightly to the left of the tank
            case RIGHT -> missileX += 20; // Spawn slightly to the right of the tank
        }

        Missile missile = new Missile(missileX, missileY, this.direction, this);
        missiles.add(missile);
    }

    // Getters and setters
    public int getX() {
        return (int) bounds.getX();
    }

    public void setX(int x) {
        bounds.setX(x);
        representation.setX(x);
    }

    public int getY() {
        return (int) bounds.getY();
    }

    public void setY(int y) {
        bounds.setY(y);
        representation.setY(y);
    }

    public int getHealth() {
        return health;
    }

    public Direction getDirection() {
        return direction;
    }

    public ImageView getRepresentation() {
        return representation;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    protected abstract Image getImageForDirection(Direction direction);
}
