package dev.plett.javasnake;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameSystem {
    private static GameSystem instance;

    private final Canvas canvas;
    private final Scene scene;
    private final SnakeController snakeController;
    private final int foodSize;
    private final int unit;

    public static GameSystem getInstance() {
        return instance;
    }

    public GameSystem(Canvas canvas, Scene scene, SnakeController snakeController, int foodSize, int unit) {
        instance = this;
        this.canvas = canvas;
        this.scene = scene;
        this.snakeController = snakeController;
        this.foodSize = foodSize;
        this.unit = unit;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Scene getScene() {
        return scene;
    }

    public SnakeController getSnakeController() {
        return snakeController;
    }

    public int getFoodSize() {
        return foodSize;
    }

    public int getUnit() {
        return unit;
    }

    public void resetGame() {
        this.snakeController.getModel().resetSnake();
        GameBoard.getInstance().setFood();
        GameLoop.getInstance().play();
    }
}
