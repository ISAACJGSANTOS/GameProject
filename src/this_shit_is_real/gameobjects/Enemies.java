package this_shit_is_real.gameobjects;

import this_shit_is_real.GamePlay;
import this_shit_is_real.field.FieldDirection;
import this_shit_is_real.field.FieldPosition;

public class Enemies extends GameObjects {

    private String name;
    private boolean damageOnTouch;
    private GameObjectsFactory factory;
    private GamePlay gamePlay;

    public Enemies (GameObjectsType type, FieldPosition[] pos, GamePlay gamePlay) {
        super(type, pos);
        this.gamePlay = gamePlay;
        this.factory = gamePlay.getFactory();
    }

    public boolean damageOnTouch(){
        return damageOnTouch;
    }

    public void hit(int bulletDamage){
       setHealth(getHealth() - bulletDamage);
    }

    public void shoot(){
        int row = getPos().getRow() + 1;
        int col = getPos().getCol();
        Bullets bullet = factory.generateBullets(col, row);
        bullet.setCurrentDirection(FieldDirection.DOWN);
        gamePlay.addBullet(bullet);
    }


}
