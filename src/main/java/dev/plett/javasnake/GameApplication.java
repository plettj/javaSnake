package dev.plett.javasnake;


/* Things I need to implement:
 * (0) <<DONE 1.0>> Implement the Square class and Direction enum
 * (1) <<DONE 1.0>> Snake Model to represent the snake itself
 * (2) Snake View to know how to print our model to the screen
 * (3) Snake Controller to interpret and relay the user inputs
 * (4) Game Application to start the game and set up our canvases
 * (5) Game Timeline to run the game loops
 * (6) Game System to hold canvases, know game-wide conventions, and handle game states
 */

import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class GameApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(1080, 720);
        GraphicsContext context = canvas.getGraphicsContext2D();

        GameSystem system = new GameSystem(
                canvas,
                4,
                new int[] {50, 50},
                new int[] {10, 10},
                25
        );

        // system needs to include our canvases so the view and controllers can interact with them

        SnakeModel snakeModel = new SnakeModel(system);
        SnakeController snakeController = new SnakeController(snakeModel, system);
        SnakeView snakeView = new SnakeView(snakeModel, system);
    }
}
