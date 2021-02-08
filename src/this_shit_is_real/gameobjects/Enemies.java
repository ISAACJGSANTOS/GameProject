package this_shit_is_real.gameobjects;

import this_shit_is_real.field.FieldPosition;

public class Enemies extends GameObjects {

    private String name;
    private boolean damageOnTouch;

    public Enemies (GameObjectsType type, FieldPosition pos){
        super(type, pos);
    }

    public boolean damageOnTouch(){
        return damageOnTouch;
    }

    public void hit(int bulletDamage){
       setHealth(getHealth() - bulletDamage);
    }

    public void shoot(){
        Bullets bullet = new Bullets(GameObjectsType.BULLET, getPos());
    }


}
