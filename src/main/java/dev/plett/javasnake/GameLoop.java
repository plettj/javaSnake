package dev.plett.javasnake;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

public class GameLoop {
    private static GameLoop instance;

    private boolean paused = true;

    public static GameLoop getInstance() {
        return instance;
    }

    public GameLoop(SnakeController snakeController, SnakeView snakeView) {
        instance = this;
        Timeline timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(
                Duration.millis(100),
                actionEvent -> {
                    if (GameLoop.getInstance().isPlaying()) {
                        snakeController.newFrame();

                        Canvas canvas = GameSystem.getInstance().getCanvas();
                        canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

                        snakeView.drawSnake();
                    }
                }
        );
        timeline.setCycleCount(Timeline.INDEFINITE);

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    public void togglePause() {
        paused = !paused;
    }

    public void play() {
        paused = false;
    }

    public void pause() {
        paused = true;
    }

    public boolean isPlaying() {
        return !paused;
    }
}
