package dev.plett.javasnake;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameSystem {
    private static GameSystem instance;

    private final Canvas canvas;
    private final Scene scene;
    private final int foodSize;
    private final int[] windowOffset; // {x, y}: Pixels off from top-left that the game scene sits in
    private final int[] boardSize;    // {width, height}: Size of the game board, in units
    private final int unit;

    public static GameSystem getInstance() {
        return instance;
    }

    public GameSystem(Canvas canvas, Scene scene, int foodSize, int[] windowOffset, int[] boardSize, int unit) {
        instance = this;
        this.canvas = canvas;
        this.scene = scene;
        this.foodSize = foodSize;
        this.windowOffset = windowOffset; // Shallow copy is fine.
        this.boardSize = boardSize; // Shallow copy is fine.
        this.unit = unit;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public Scene getScene() {
        return scene;
    }

    public GraphicsContext getGraphicsContext() {
        return canvas.getGraphicsContext2D();
    }

    public int getFoodSize() {
        return foodSize;
    }

    public int[] getWindowOffset() {
        return windowOffset;
    }

    public int[] boardSize() {
        return boardSize;
    }

    public int getUnit() {
        return unit;
    }
}
