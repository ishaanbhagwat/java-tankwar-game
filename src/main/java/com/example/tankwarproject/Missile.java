package com.example.tankwarproject;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Missile {
    private double x;
    private double y;
    private Direction direction;
    private double speed = 5;
    private int damage = 25;
    private ImageView representation;
    private Rectangle bounds;
    private final Tank sourceTank;

    // Static image resources for each direction
    private static final Image upImage = new Image(Missile.class.getResource("/com/example/TankWarProject/missileU.gif").toExternalForm());
    private static final Image downImage = new Image(Missile.class.getResource("/com/example/TankWarProject/missileD.gif").toExternalForm());
    private static final Image leftImage = new Image(Missile.class.getResource("/com/example/TankWarProject/missileL.gif").toExternalForm());
    private static final Image rightImage = new Image(Missile.class.getResource("/com/example/TankWarProject/missileR.gif").toExternalForm());


    public Missile(double x, double y, Direction direction, Tank sourceTank) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.sourceTank = sourceTank;
        
        // Initialize visual representation
        this.representation = new ImageView(getImageForDirection(direction));
        this.representation.setX(x);
        this.representation.setY(y);
        this.representation.setFitWidth(10);
        this.representation.setFitHeight(10);

        // Initialize bounds for collision detection
        this.bounds = new Rectangle(x, y, 10, 10);
    }

    public void move() {
        switch (direction) {
            case UP -> y -= speed;
            case DOWN -> y += speed;
            case LEFT -> x -= speed;
            case RIGHT -> x += speed;
        }
        representation.setX(x);
        representation.setY(y);
        bounds.setX(x);
        bounds.setY(y);
    }

    public ImageView getRepresentation() {
        return representation;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public int getDamage() {
        return damage;
    }

    // Helper method to get the correct image based on direction
    private Image getImageForDirection(Direction direction) {
        return switch (direction) {
            case UP -> upImage;
            case DOWN -> downImage;
            case LEFT -> leftImage;
            case RIGHT -> rightImage;
        };
    }

    public boolean isOutOfBounds(double width, double height) {
        return x < 0 || y < 0 || x > width || y > height;
    }

    public boolean intersects(Rectangle otherBounds) {
        return bounds.getBoundsInParent().intersects(otherBounds.getBoundsInParent());
    }

    public Tank getSourceTank() {
        return sourceTank;
    }
}
