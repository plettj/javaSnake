package dev.plett.javasnake;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.util.Arrays;
import java.util.List;

public class GameBoard {
    private static GameBoard instance;

    private final int[] windowOffset; // {x, y}: Pixels off from top-left that the game scene sits in
    private final int[] boardSize; // {width, height}: Size of the game board, in units
    private int[] foodSpot = new int[] {0, 0}; // {x, y}: Location of the current food, in units

    private Rectangle boardShape;

    public static GameBoard getInstance() {
        return instance;
    }

    public GameBoard(int[] windowOffset, int[] boardSize, Color color) {
        instance = this;
        this.windowOffset = Arrays.copyOf(windowOffset, windowOffset.length); // Deep copy!
        this.boardSize = Arrays.copyOf(boardSize, boardSize.length); // Shallow copy is fine.
        this.makeBoardShape(color);
        this.setFood();
    }

    private void makeBoardShape(Color color) {
        int unit = GameSystem.getInstance().getUnit();
        boardShape = new Rectangle(
                this.windowOffset[0] - unit * 0.2,
                this.windowOffset[1] - unit * 0.2,
                unit * (this.boardSize[0] + 0.4),
                unit * (this.boardSize[1] + 0.4)
        );
        boardShape.setStrokeType(StrokeType.OUTSIDE);
        boardShape.setStroke(color);
        boardShape.setStrokeWidth(unit / 4.0);
        boardShape.setFill(Color.TRANSPARENT);
    }

    public Rectangle getBoardShape() {
        return this.boardShape;
    }

    public int[] getWindowOffset() {
        return windowOffset;
    }

    public int[] getBoardSize() {
        return boardSize;
    }

    public void drawFood() {
        GraphicsContext context = GameSystem.getInstance().getCanvas().getGraphicsContext2D();

        int unit = GameSystem.getInstance().getUnit();

        context.fillRect(this.windowOffset[0] + (this.foodSpot[0] + 0.1) * unit, this.windowOffset[1] + (this.foodSpot[1] + 0.1) * unit, unit * 0.8, unit * 0.8);
    }

    public void setFood() {
        SnakeModel model = GameSystem.getInstance().getSnakeController().getModel(); // This is rather convoluted. Is that ok?!

        // Do not allow food to spawn on our snake!
        if (model.getBody().size() < boardSize[0] * boardSize[1]) {
            boolean unsafeFoodSpot = true;
            while (unsafeFoodSpot) {
                this.foodSpot[0] = (int) (Math.random() * this.boardSize[0]);
                this.foodSpot[1] = (int) (Math.random() * this.boardSize[1]);
                unsafeFoodSpot = false;
                for (Square square: model.getBody()) {
                    if (this.foodSpot[0] == square.getX() && this.foodSpot[1] == square.getY()) {
                        unsafeFoodSpot = true;
                        break;
                    }
                }
            }
        } else {
            System.out.println("YOU BEAT SNAKE! CONGRATULATIONS!");
            GameSystem.getInstance().resetGame();
        }
    }

    /**
     * @return true if snake is eating the food
     */
    public boolean tryFood(int x, int y) {
        if (foodSpot[0] == x && foodSpot[1] == y) {
            setFood();
            return true;
        } else {
            return false;
        }
    }
}
