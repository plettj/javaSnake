package dev.plett.javasnake;

import java.util.List;

public class SnakeView {
    private final SnakeModel snake;
    private final GameSystem system;

    public SnakeView(SnakeModel snake, GameSystem system) {
        this.snake = snake;
        this.system = system;
    }

    public void drawSnake() {
        List<Square> body = snake.getBody();
        for (Square point: body) {

        }
    }
}
