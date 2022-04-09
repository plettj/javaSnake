package dev.plett.javasnake;

public class Square {
    private int x;
    private int y;

    public Square(int x, int y) {
        set(x, y);
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] get() {
        return new int[] {x, y};
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
