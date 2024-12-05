package com.example.tankwarproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MenuView {
    private Stage stage;
    public MenuView(Stage stage) {
        this.stage = stage;
    }

    public void show() {
        // Title Text
        Text title = new Text("Tank War");
        title.setFont(Font.font("Arial", 50));
        title.setStyle("-fx-fill: white;");

        // Start Button
        // Start button
        Button startButton = new Button("Start Game");
        startButton.setFont(Font.font("Arial", 20));
        startButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
        startButton.setOnAction(event -> startGame());

        // VBox
        // Layout for the menu
        VBox layout = new VBox(40, title, startButton); // Add spacing between elements
        layout.setStyle("-fx-alignment: center; -fx-background-color: black;");
        layout.setPrefSize(650, 850);

        Scene menuScene = new Scene(layout);
        stage.setScene(menuScene);

        stage.show();
    }

    private void startGame() {
        Pane gamePane = new Pane();
        gamePane.setStyle("-fx-background-color: black;");
        gamePane.setPrefSize(650, 850);
        GameController gameController = new GameController(gamePane);
        gameController.startNewGame();
        stage.setScene(gameController.getGameScene());
        stage.show();
    }
}
