package dev.plett.javasnake;

public class SnakeController {
    private final SnakeModel model;
    private final GameSystem system;

    public SnakeController(SnakeModel model, GameSystem system) {
        this.model = model;
        this.system = system;
        this.createListeners();
    }

    private void createListeners() {

    }
}
