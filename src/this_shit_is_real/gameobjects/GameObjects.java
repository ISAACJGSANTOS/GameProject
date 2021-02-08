package this_shit_is_real.gameobjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import this_shit_is_real.field.Field;
import this_shit_is_real.field.FieldPosition;

public class GameObjects {

    private FieldPosition pos;
    private Picture image;
    private int health;

    public GameObjects (FieldPosition pos) {
        this.pos = pos;
    }

    public int getHealth() {
        return health;
    }

    public Picture getImage() {
        return image;
    }

    public FieldPosition getPos() {
        return pos;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
