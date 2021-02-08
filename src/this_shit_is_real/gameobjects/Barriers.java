package this_shit_is_real.gameobjects;

import this_shit_is_real.field.FieldPosition;

public class Barriers extends GameObjects{

    private int health;

    public Barriers (FieldPosition pos){
        super(pos);
    }

    public void hit(int bulletDamage){
        this.health -= bulletDamage;
    }

    public void hide(){

    }

}
