package this_shit_is_real.gameobjects;

public class Player extends GameObjects{

    private int health;
    private int score;

    public void hit(int bulletDamage){
        this.health -= bulletDamage;
    }

    public void shoot(){
        Bullets bullet = new Bullets();
    }

}
