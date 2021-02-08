package this_shit_is_real.gameobjects;

import this_shit_is_real.field.FieldPosition;

public class Player extends GameObjects{

    private int health;
    private int score;

    public Player(FieldPosition pos){
        super(pos);
    }

    public void hit(int bulletDamage){
        this.health -= bulletDamage;
    }

    public void shoot(){
        Bullets bullet = new Bullets(getPos());
    }

}
