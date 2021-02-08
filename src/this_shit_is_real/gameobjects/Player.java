package this_shit_is_real.gameobjects;

import this_shit_is_real.field.FieldPosition;

public class Player extends GameObjects{

    private int score;

    public Player(GameObjectsType type, FieldPosition pos){
        super(type, pos);
    }

    public void hit(int bulletDamage){
        setHealth(getHealth() - bulletDamage);
    }

    public void shoot(){
        Bullets bullet = new Bullets(GameObjectsType.BULLET, getPos());
    }

}
