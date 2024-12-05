package com.example.tankwarproject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Wall {
    private int x;
    private int y;
    private boolean destructible;
    private ImageView representation;
    private Rectangle bounds; // Rectangle for collision detection
    private static final Image indestructibleWall = new Image(Wall.class.getResource("/com/example/TankWarProject/wall.png").toExternalForm());

    public Wall(int x, int y, boolean destructible) {
        this.x = x;
        this.y = y;
        this.destructible = destructible;
        this.representation = new ImageView(indestructibleWall);
        this.representation.setX(x);
        this.representation.setY(y);
        this.representation.setFitHeight(50);
        this.representation.setFitWidth(50);

        // Initialize the bounds for collision detection (same size as the wall)
        this.bounds = new Rectangle(x, y, 50, 50);
    }

    public boolean isDestructible() {
        return destructible;
    }

    public ImageView getRepresentation() {
        return representation;
    }

    public Rectangle getBounds() {
        return bounds;
    }

}
