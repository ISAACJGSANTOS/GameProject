package this_shit_is_real.gameobjects;

import this_shit_is_real.field.FieldPosition;

public class Barriers extends GameObjects{

    public Barriers (GameObjectsType type, FieldPosition[] pos){
        super(type, pos);
    }

    public void hit(int bulletDamage){
        setHealth(getHealth() - bulletDamage);
    }

    public void hide(){

    }

}
