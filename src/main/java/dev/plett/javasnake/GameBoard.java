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
    private final int[] boardSize;    // {width, height}: Size of the game board, in units

    private Rectangle boardShape;

    public static GameBoard getInstance() {
        return instance;
    }

    public GameBoard(int[] windowOffset, int[] boardSize, Color color) {
        instance = this;
        this.windowOffset = Arrays.copyOf(windowOffset, windowOffset.length); // Deep copy!
        this.boardSize = Arrays.copyOf(boardSize, boardSize.length); // Shallow copy is fine.
        this.makeBoardShape(color);
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

    public int[] boardSize() {
        return boardSize;
    }
}
