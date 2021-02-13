package this_shit_is_real.gameobjects;

import this_shit_is_real.field.FieldPosition;

public class Heart extends GameObjects {

    GameObjectsType type;

    public Heart(GameObjectsType type, FieldPosition[] pos) {
        super(type, pos);
        this.type = type;
    }

}
