package com.example.tankwarproject;

import javafx.scene.image.Image;

import java.util.List;

public class AITank extends Tank {
    private static final Image aiUpImage = new Image(AITank.class.getResource("/com/example/TankWarProject/AITankU.png").toExternalForm());
    private static final Image aiDownImage = new Image(AITank.class.getResource("/com/example/TankWarProject/AITankD.png").toExternalForm());
    private static final Image aiLeftImage = new Image(AITank.class.getResource("/com/example/TankWarProject/AITankL.png").toExternalForm());
    private static final Image aiRightImage = new Image(AITank.class.getResource("/com/example/TankWarProject/AITankR.png").toExternalForm());

    public AITank(int x, int y, int health, Direction direction) {
        super(x, y, health, direction);
    }

    public void moveRandomly(GameMap gameMap) {
        Direction[] directions = Direction.values();
        setDirection(directions[(int) (Math.random() * directions.length)]);
        move(gameMap);
    }

    @Override
    protected Image getImageForDirection(Direction direction) {
        return switch (direction) {
            case UP -> aiUpImage;
            case DOWN -> aiDownImage;
            case LEFT -> aiLeftImage;
            case RIGHT -> aiRightImage;
        };
    }

    public void fireMissileBurst(List<Missile> missiles) {
        for (int i = 0; i < 3; i++) {
            fireMissile(missiles);
        }
    }

}