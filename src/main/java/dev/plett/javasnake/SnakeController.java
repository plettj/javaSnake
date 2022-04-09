package dev.plett.javasnake;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class SnakeController {
    private final SnakeModel model;

    public SnakeController(SnakeModel model) {
        this.model = model;
        this.createListeners();
    }

    private void createListeners() {
        Scene scene = GameSystem.getInstance().getScene();
        scene.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKeypress);
    }

    public void newFrame() {
        // Consider collisions....
        // Make a complex decision based on the cached keypress events about which direction to go....
        model.update();
    }

    private void handleKeypress(KeyEvent event) {
        if (GameLoop.getInstance().isPlaying()) { // Game is not paused
            KeyCode keyCode = event.getCode();
            switch (keyCode.getName()) {
                case "Left", "A" -> model.setDirection(Direction.LEFT);
                case "Up", "W" -> model.setDirection(Direction.UP);
                case "Right", "D" -> model.setDirection(Direction.RIGHT);
                case "Down", "S" -> model.setDirection(Direction.DOWN);
                case "Enter", "Space" -> GameLoop.getInstance().togglePause();
            }
            System.out.print("keyCode is: " + keyCode.getCode() + "  |||  Name is: " + keyCode.getName() + "\n");
        }
    }
}
