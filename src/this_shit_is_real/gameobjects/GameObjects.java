package this_shit_is_real.gameobjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import this_shit_is_real.field.Field;
import this_shit_is_real.field.FieldDirection;
import this_shit_is_real.field.FieldPosition;

public class GameObjects {

    private FieldPosition pos;
    private Picture image;
    private int health;
    private GameObjectsType type;
    private FieldDirection currentDirection;

    public GameObjects (GameObjectsType type, FieldPosition pos) {
        this.pos = pos;
        this.type = type;

        pos.init(type.getColor());  // Ask FieldPosition to create the rectangle or image
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public Picture getImage() {
        return image;
    }

    public FieldPosition getPos() {
        return pos;
    }

    public FieldDirection getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(FieldDirection currentDirection) {
        this.currentDirection = currentDirection;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
