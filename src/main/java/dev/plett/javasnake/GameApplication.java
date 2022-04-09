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
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameApplication extends Application {
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();

        StackPane holder = new StackPane();
        Canvas canvas = new Canvas(1080, 720);
        GraphicsContext context = canvas.getGraphicsContext2D();

        holder.getChildren().add(canvas);
        root.getChildren().add(holder);

        holder.setStyle("-fx-background-color: #000000"); // Background colour
        context.setFill(Color.valueOf("#FFFFFF")); // Snake colour

        Scene scene = new Scene(root, 1080, 720); // canvasScene!

        new GameSystem(canvas, scene, 4, new int[] {0, 0}, new int[] {12, 12}, 25);
        //GameSystem system = GameSystem.getInstance(); // THIS << IS HOW TO GET THE GAME SYSTEM

        SnakeModel snakeModel = new SnakeModel();
        SnakeController snakeController = new SnakeController(snakeModel);
        SnakeView snakeView = new SnakeView(snakeModel);

        new GameLoop(snakeController, snakeView);

        stage.setScene(scene);
        stage.show();

        // Start the game!
        GameLoop.getInstance().play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
