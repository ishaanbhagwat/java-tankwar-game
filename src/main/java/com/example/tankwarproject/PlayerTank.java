package com.example.tankwarproject;

import javafx.scene.image.Image;

public class PlayerTank extends Tank {

    // Static image resources for each direction
    private static final Image upImage = new Image(Tank.class.getResource("/com/example/TankWarProject/tankU.gif").toExternalForm());
    private static final Image downImage = new Image(Tank.class.getResource("/com/example/TankWarProject/tankD.gif").toExternalForm());
    private static final Image leftImage = new Image(Tank.class.getResource("/com/example/TankWarProject/tankL.gif").toExternalForm());
    private static final Image rightImage = new Image(Tank.class.getResource("/com/example/TankWarProject/tankR.gif").toExternalForm());

    public PlayerTank(int x, int y, int health, Direction direction) {
        super(x, y, health, direction);
    }

    @Override
    protected Image getImageForDirection(Direction direction) {
        return switch (direction) {
            case UP -> upImage;
            case DOWN -> downImage;
            case LEFT -> leftImage;
            case RIGHT -> rightImage;
        };
    }

    // PlayerTank-specific behavior can be added here if needed
}
