package dev.plett.javasnake;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class SnakeView {
    private final SnakeModel model;

    public SnakeView(SnakeModel snake) {
        this.model = snake;
    }

    public void drawSnake() {
        List<Square> body = model.getBody();

        Canvas canvas = GameSystem.getInstance().getCanvas();
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        int unit = GameSystem.getInstance().getUnit();
        int[] offset = GameSystem.getInstance().getWindowOffset();
        for (Square point: body) {
            context.fillRect(offset[0] + model.getBody().get(0).get()[0] * unit, offset[1] + model.getBody().get(0).get()[1] * unit, unit, unit);
        }
    }
}
