package this_shit_is_real.gameobjects;

public class Barriers extends GameObjects{

    private int health;

    public void hit(int bulletDamage){
        this.health -= bulletDamage;
    }

    public void hide(){

    }

}
