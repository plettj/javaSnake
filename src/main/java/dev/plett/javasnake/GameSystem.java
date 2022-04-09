package dev.plett.javasnake;

public class GameSystem {
    private final int foodSize;
    private final int[] windowOffset; // {x, y}: Pixels off from top-left that the game scene sits in
    private final int[] boardSize;    // {width, height}: Size of the game board, in units
    private final int unit;

    public GameSystem(int foodSize, int[] windowOffset, int[] boardSize,  int unit) {
        this.foodSize = foodSize;
        this.windowOffset = windowOffset; // Shallow copy is fine.
        this.boardSize = boardSize; // Shallow copy is fine.
        this.unit = unit;
    }

    public int getFoodSize() {
        return foodSize;
    }

    public int[] getWindowOffset() {
        return windowOffset;
    }

    public int[] boardSize() {
        return boardSize;
    }

    public int getUnit() {
        return unit;
    }
}
