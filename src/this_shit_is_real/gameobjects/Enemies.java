package this_shit_is_real.gameobjects;

import this_shit_is_real.field.FieldPosition;

public class Enemies extends GameObjects {

    private String name;
    private int health;
    private boolean damageOnTouch;

    public Enemies (EnemyType enemyType, FieldPosition pos){
        super(pos);
    }

    public boolean damageOnTouch(){
        return damageOnTouch;
    }

    public void hit(int bulletDamage){
        this.health -= bulletDamage;
    }

    public void shoot(){
        Bullets bullet = new Bullets(getPos());
    }


}
