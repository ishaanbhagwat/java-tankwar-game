package com.example.tankwarproject;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class GameMap {
    private static GameMap instance;
    private final int tileSize = 50; // Size of each tile in pixels
    private int rows;
    private int cols;

    private final List<Wall> walls = new ArrayList<>();

    private final int[][] mapGrid = {
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
    };

    private GameMap() {
        rows = mapGrid.length;
        cols = mapGrid[0].length;
    }

    public static synchronized GameMap getInstance() {
        if (instance == null) {
            instance = new GameMap();
        }
        return instance;
    }

    public void initialize(Pane gameArea) {
        renderMap(gameArea);
    }

    private void renderMap(Pane gamePane) {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int tileType = mapGrid[row][col];
                if (tileType == 1) {
                    Wall wall = renderWall(row, col);
                    gamePane.getChildren().add(wall.getRepresentation());
                }
            }
        }
    }

    private Wall renderWall(int row, int col) {
        Wall wall = new Wall(col * tileSize, row * tileSize, false);
        walls.add(wall);
        return wall;
    }

    // Getters and Setters
    public List<Wall> getWalls() {
        return new ArrayList<>(walls); // Return a copy of the walls list
    }

    public int[][] getMapGrid() {
        return mapGrid;
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
