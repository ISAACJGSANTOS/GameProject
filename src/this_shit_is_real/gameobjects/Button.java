package this_shit_is_real.gameobjects;
import this_shit_is_real.field.FieldPosition;

public class Button extends GameObjects{

    GameObjectsType type;

    public Button(GameObjectsType type, FieldPosition[] pos){
        super(type, pos);
        this.type = type;
    }

}
