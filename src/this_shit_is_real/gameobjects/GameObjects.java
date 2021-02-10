package this_shit_is_real.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import this_shit_is_real.field.Field;
import this_shit_is_real.field.FieldDirection;
import this_shit_is_real.field.FieldPosition;

public class GameObjects {

    private FieldPosition[] myPos;
    private Picture image;
    private int health;
    private GameObjectsType type;
    private FieldDirection currentDirection;

    public GameObjects (GameObjectsType type, FieldPosition pos) {
        this.type = type;
        myPos = new FieldPosition[type.getSize()];

        System.out.println(type);
        init(pos);
    }

    public void init (FieldPosition pos) {
        for (int i = 0; i < myPos.length; i++) {
            myPos[i] = pos;
            myPos[i].init(type.getColor());  // Ask FieldPosition to create the rectangle or image

            // Let's set the final position for all the positions
            switch (i) {
                case 1: case 3: myPos[i].moveInDirection(FieldDirection.RIGHT, 1); break;
                case 2:
                    myPos[i].moveInDirection(FieldDirection.DOWN, 1);
                    myPos[i].moveInDirection(FieldDirection.LEFT, 1);
            }
        }
    }

    // Let's move all the object positions
    public void move(FieldDirection direction, int distance) {
        for (FieldPosition pos : myPos) {
            pos.moveInDirection(direction, distance);
        }
    }

    public Picture getImage() {
        return image;
    }
    public FieldPosition getPos() {
        return myPos[0];
    }
    public FieldDirection getCurrentDirection() {
        return currentDirection;
    }
    public int getHealth() {
        return health;
    }
    public FieldPosition[] getMyPos() {
        return myPos;
    }

    public void setCurrentDirection(FieldDirection currentDirection) {
        this.currentDirection = currentDirection;
    }
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
