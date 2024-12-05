package com.example.tankwarproject;
import javafx.scene.Scene;


import java.util.List;

public class PlayerInputHandler {
    public void handleInput(Scene scene, Tank playerTank, GameMap gameMap, List<Missile> missiles) {
        scene.setOnKeyPressed(event -> {
            // Only update direction or fire missile when appropriate
            switch (event.getCode()) {
                case UP, DOWN, LEFT, RIGHT -> {
                    playerTank.setDirection(Direction.valueOf(event.getCode().name()));
                    playerTank.move(gameMap);  // Move tank when a direction key is pressed
                }
                case SPACE -> playerTank.fireMissile(missiles);  // Fire missile when SPACE is pressed
            }
        });
    }
}
