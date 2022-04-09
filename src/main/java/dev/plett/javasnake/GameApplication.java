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
        /* Initializing values of our game world */
        int[] size = new int[] {1080, 720}; // {width, height} of the full window, in pixels
        int[] boardSize = new int[] {30, 14}; // {width, height} of the board, in units
        //Color backgroundColor = Color.valueOf("#000000"); // Wasn't able to get the syntax to work when doing holder.setStyle();
        Color snakeColor = Color.valueOf("#FFFFFF");

        int unit = (size[0] / boardSize[0] > size[1] / boardSize[1]) ? size[1] / (boardSize[1] + 3) : size[0] / (boardSize[0] + 3);

        int[] boardOffset = new int[] {
                size[0] / 2 - boardSize[0] * unit / 2,
                size[1] / 2 - boardSize[1] * unit / 2
        };

        Pane root = new Pane();

        StackPane holder = new StackPane();
        Canvas canvas = new Canvas(size[0], size[1]);
        GraphicsContext context = canvas.getGraphicsContext2D();

        holder.getChildren().add(canvas);
        root.getChildren().add(holder);

        holder.setStyle("-fx-background-color: #000000");
        context.setFill(snakeColor);
        context.setStroke(snakeColor);

        Scene scene = new Scene(root, size[0], size[1]); // canvasScene!

        SnakeModel snakeModel = new SnakeModel();
        SnakeController snakeController = new SnakeController(snakeModel);
        SnakeView snakeView = new SnakeView(snakeModel);

        new GameSystem(canvas, scene, snakeController, 4, unit);
        new GameBoard(boardOffset, boardSize, snakeColor);
        //GameSystem system = GameSystem.getInstance(); // THIS << IS HOW TO GET THE GAME SYSTEM

        snakeController.createListeners();
        new GameLoop(snakeController, snakeView);

        root.getChildren().add(GameBoard.getInstance().getBoardShape());

        stage.setScene(scene);
        stage.show();

        // Start the game!
        GameLoop.getInstance().play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
