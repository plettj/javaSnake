package dev.plett.javasnake;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.List;

public class SnakeModel {
    private final ObjectProperty<List<Square>> body = new SimpleObjectProperty<>();
    private final ObjectProperty<Direction> direction = new SimpleObjectProperty<>();
    private final Direction[] keyPresses = new Direction[] {Direction.NO_DIRECTION, Direction.NO_DIRECTION};
    private int length;

    public SnakeModel() {
        resetSnake();
    }

    public void resetSnake() {
        length = 4;
        body.set(new ArrayList<>());
        body.get().add(new Square(0, 3));
        direction.set(Direction.RIGHT);
    }

    public ObjectProperty<List<Square>> getBodyProperty() {
        return body;
    }

    public List<Square> getBody() {
        return body.get();
    }

    /**
     * .moveBody() takes x and y coordinates, and moves the snake head towards them.
     *
     * @param add     Whether to increase the length of our snake by 1.
     */
    public void moveBody(int x, int y, boolean add) {
        if (add) {
            body.get().add(0, new Square(x, y));
        } else {
            int[] previousSquare = new int[]{x, y};
            int[] currentSquare;
            for (Square square : body.get()) {
                currentSquare = square.get();
                square.set(previousSquare[0], previousSquare[1]);
                previousSquare[0] = currentSquare[0];
                previousSquare[1] = currentSquare[1];
            }
        }
    }

    public Direction getDirection() {
        return direction.get();
    }

    public void setDirection(Direction direction) {
        if (keyPresses[0].equals(Direction.NO_DIRECTION)) {
            if (this.direction.getValue().ordinal() % 2 != direction.ordinal()) {
                keyPresses[0] = direction; // This was a legal move!
            }
        } else {
            if (keyPresses[0].ordinal() % 2 != direction.ordinal()) {
                keyPresses[1] = direction; // This was a legal move!
            }
        }
    }

    public void eatFood() {
        length += GameSystem.getInstance().getFoodSize();
    }

    public void update() {
        // Set our direction based on our keyPresses array
        if (!keyPresses[0].equals(Direction.NO_DIRECTION)) {
            this.direction.set(keyPresses[0]);
            keyPresses[0] = keyPresses[1];
            keyPresses[1] = Direction.NO_DIRECTION;
        }

        // Magic numbers time! This shifts our values by the correct amount based on our X and Y
        int newX = body.get().get(0).get()[0] + (direction.get().ordinal() - 1) % 2;
        int newY = body.get().get(0).get()[1] + (direction.get().ordinal() - 2) % 2;
        boolean add = length > body.get().size();

        // Perhaps eat some food, or collide with things
        if (newX < 0 || newX >= GameBoard.getInstance().getBoardSize()[0] || newY < 0 || newY >= GameBoard.getInstance().getBoardSize()[1]) {
            GameSystem.getInstance().resetGame();
            return;
        }
        for (int i = 0; i < this.body.get().size(); i++) {
            Square square = this.body.get().get(i);
            if (i < this.body.get().size() - 1 || add) { // So we don't consider colliding with tail that's about to go away
                if (newX == square.getX() && newY == square.getY()) {
                    GameSystem.getInstance().resetGame();
                    return;
                }
            }
        }
        if (GameBoard.getInstance().tryFood(newX, newY)) {
            eatFood(); // Grow, because we successfully ate a piece of food!
        }

        this.moveBody(newX, newY, add);
    }
}
