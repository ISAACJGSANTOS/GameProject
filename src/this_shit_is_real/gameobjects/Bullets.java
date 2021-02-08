package this_shit_is_real.gameobjects;

import this_shit_is_real.field.FieldPosition;

public class Bullets extends GameObjects{

    private int bulletDamage;
    private GameObjects ally;
    private boolean damageOnTouch;

    public Bullets(FieldPosition pos){
        super(pos);
    }

    public boolean damageOnTouch(){
        return damageOnTouch;
    }

    public int hit(int bulletDamage){
        return bulletDamage;
    }

}
