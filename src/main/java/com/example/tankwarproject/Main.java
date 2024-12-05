package com.example.tankwarproject;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tank War");

        MenuView menuView = new MenuView(primaryStage);
        menuView.show();
    }
}
