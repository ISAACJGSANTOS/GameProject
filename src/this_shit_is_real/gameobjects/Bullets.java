package this_shit_is_real.gameobjects;

public class Bullets extends GameObjects{

    private int bulletDamage;
    private GameObjects ally;
    private boolean damageOnTouch;

    public boolean damageOnTouch(){
        return damageOnTouch;
    }

    public int hit(int bulletDamage){
        return bulletDamage;
    }

}
