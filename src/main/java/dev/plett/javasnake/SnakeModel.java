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
        length = 1;
        body.set(new ArrayList<>());
        body.get().add(new Square(3, 3));
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

        // Not implemented properly yet! Right now it just changes the head's location.

        body.get().get(0).set(x, y);
    }

    public Direction getDirection() {
        return direction.get();
    }

    public void setDirection(Direction direction) {
        // This needs to get complicated in order to create the illusion of controller forgiveness
        // Adds to the first direction slot; then the second; otherwise, replaces the second.
        // Don't do any of these things if the move is not legal (eg. trying to go DOWN from UP).

        if (keyPresses[0].equals(Direction.NO_DIRECTION)) {
            // Now we check if this is even a legal move
            if (this.direction.getValue().ordinal() % 2 != direction.ordinal()) {
                keyPresses[0] = direction;
            }
        } else {
            // Now we check if this is even a legal move COMPARED TO keyPresses[0]
            if (keyPresses[0].ordinal() % 2 != direction.ordinal()) {
                keyPresses[1] = direction;
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
        int newX = getBody().get(0).get()[0] + (direction.get().ordinal() - 1) % 2;
        int newY = getBody().get(0).get()[1] + (direction.get().ordinal() - 2) % 2;
        boolean add = length > getBody().size();

        this.moveBody(newX, newY, add);
    }
}
