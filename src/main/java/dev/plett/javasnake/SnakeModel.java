package dev.plett.javasnake;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.List;

public class SnakeModel {
    private final ObjectProperty<List<Square>> body = new SimpleObjectProperty<>();
    private final ObjectProperty<Direction> direction = new SimpleObjectProperty<>();
    private int length;

    public SnakeModel() {
        length = 1;
        body.set(new ArrayList<>());
        body.get().add(new Square(0, 0));
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
        this.direction.set(direction);
    }

    public void eatFood() {
        length += GameSystem.getInstance().getFoodSize();
    }

    public void update() {
        // Magic numbers time! This shifts our values by the correct amount based on our X and Y
        int newX = getBody().get(0).get()[0] + (direction.get().ordinal() - 1) % 2;
        int newY = getBody().get(0).get()[1] + (direction.get().ordinal() - 2) % 2;
        boolean add = length > getBody().size();

        this.moveBody(newX, newY, add);
    }
}
