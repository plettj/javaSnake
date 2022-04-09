package dev.plett.javasnake;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class GameSystem {
    private static GameSystem instance;

    private final Canvas canvas;
    private final Scene scene;
    private final int foodSize;
    private final int unit;

    public static GameSystem getInstance() {
        return instance;
    }

    public GameSystem(Canvas canvas, Scene scene, int foodSize, int unit) {
        instance = this;
        this.canvas = canvas;
        this.scene = scene;
        this.foodSize = foodSize;
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

    public int getUnit() {
        return unit;
    }
}
