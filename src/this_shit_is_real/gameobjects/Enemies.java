package this_shit_is_real.gameobjects;

import this_shit_is_real.GamePlay;
import this_shit_is_real.field.FieldDirection;
import this_shit_is_real.field.FieldPosition;
import this_shit_is_real.sounds.GameSounds;

public class Enemies extends GameObjects {

    private GameObjectsFactory factory;
    private GamePlay gamePlay;

    public Enemies (GameObjectsType type, FieldPosition[] pos, GamePlay gamePlay) {
        super(type, pos);
        this.gamePlay = gamePlay;
        this.factory = gamePlay.getFactory();
    }


    // OTHER ---------------------------------------------------------------------

    public void shoot(){
        int row = getPos().getRow() + 1;
        int col = getPos().getCol();
        Bullets bullet = factory.generateBulletsEnemy(col, row);
        bullet.setCurrentDirection(FieldDirection.DOWN);
        bullet.setDamage(getDamage());
        gamePlay.addBullet(bullet);
        int rand = (int) (Math.random() * 10);
        if(rand < 5) {
            GameSounds.coronaVirus.play(true);
        }
    }

}
