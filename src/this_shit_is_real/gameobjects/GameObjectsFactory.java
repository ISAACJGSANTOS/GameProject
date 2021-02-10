package this_shit_is_real.gameobjects;

import this_shit_is_real.GamePlay;
import this_shit_is_real.field.Field;

public class GameObjectsFactory {

    Field field;
    GamePlay gamePlay;

    public GameObjectsFactory(Field field, GamePlay gamePlay) {
        this.field = field;
        this.gamePlay = gamePlay;
    }

    public Enemies generateEnemy(int col, int row) {

        int random = (int) (Math.random() * 4);

        switch (random) {
            case 0:
                return new Enemies(GameObjectsType.VIRUS, field.makeFieldPosition(col, row));

            case 1:
                return new Enemies(GameObjectsType.VARIANT1, field.makeFieldPosition(col, row));

            case 2:
                return new Enemies(GameObjectsType.VARIANT2, field.makeFieldPosition(col, row));

            default:
                return new Enemies(GameObjectsType.VARIANT3, field.makeFieldPosition(col, row));
        }
    }

    public Player generatePlayer(int col, int row){
        return new Player(GameObjectsType.PLAYER, field.makeFieldPosition(col, row), gamePlay);
    }

    public Enemies generateBoss(int col, int row){
        return new Enemies(GameObjectsType.CREATOR, field.makeFieldPosition(col, row));
    }

    public Bullets generateBullets(int col, int row){
        return new Bullets(GameObjectsType.BULLET, field.makeFieldPosition(col, row));
    }

    public Barriers generateBarriers(int col, int row){
        return new Barriers(GameObjectsType.BARRIER, field.makeFieldPosition(col, row));
    }

    public Button generateButton(int col, int row){
        return new Button(GameObjectsType.MENU, field.makeFieldPosition(col, row));
    }
}
