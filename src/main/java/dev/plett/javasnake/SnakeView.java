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

        GraphicsContext context = GameSystem.getInstance().getCanvas().getGraphicsContext2D();

        int unit = GameSystem.getInstance().getUnit();
        int[] offset = GameBoard.getInstance().getWindowOffset();

        for (int i = 0; i < body.size(); i++) {
            Square square = body.get(i);
            if (i < body.size() - 1) {
                Square nextSquare = body.get(i + 1);
                // Magic number stuff again! This is to draw our rectangles correctly
                double xExtra = (nextSquare.getX() - square.getX()) * 0.155;
                double yExtra = (nextSquare.getY() - square.getY()) * 0.155;
                context.fillRect(
                        offset[0] + (square.getX() + 0.15 + xExtra - Math.abs(xExtra)) * unit,
                        offset[1] + (square.getY() + 0.15 + yExtra - Math.abs(yExtra)) * unit,
                        unit * (0.7 + Math.abs(xExtra) * 2),
                        unit * (0.7 + Math.abs(yExtra) * 2)
                );
            } else {
                context.fillRect(offset[0] + (square.getX() + 0.15) * unit, offset[1] + (square.getY() + 0.15) * unit, unit * 0.7, unit * 0.7);
            }
        }
    }
}
