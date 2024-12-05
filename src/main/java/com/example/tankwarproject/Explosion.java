package com.example.tankwarproject;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Explosion {
    private List<Image> explosionFrames;
    private ImageView representation;
    private Pane gamePane;

    public Explosion(double x, double y, Pane gamePane) {
        this.gamePane = gamePane;
        this.explosionFrames = loadExplosionFrames();
        this.representation = new ImageView(explosionFrames.get(0));
        this.representation.setX(x);
        this.representation.setY(y);
        this.representation.setFitWidth(50);
        this.representation.setFitHeight(50);
        gamePane.getChildren().add(representation);
        playAnimation();
    }

    private List<Image> loadExplosionFrames() {
        List<Image> frames = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            String imagePath = "/com/example/TankWarProject/" + i + ".gif";
            frames.add(new Image(getClass().getResource(imagePath).toExternalForm()));
        }
        return frames;
    }

    private void playAnimation() {
        Timeline animation = new Timeline();
        for (int i = 0; i < explosionFrames.size(); i++) {
            Image frame = explosionFrames.get(i);
            animation.getKeyFrames().add(new KeyFrame(Duration.millis(i * 100), e -> representation.setImage(frame)));
        }
        animation.setOnFinished(e -> gamePane.getChildren().remove(representation)); // Remove after animation
        animation.play();
    }
}
