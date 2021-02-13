package this_shit_is_real.gameobjects;

import this_shit_is_real.GamePlay;
import this_shit_is_real.field.Field;
import this_shit_is_real.field.FieldDirection;
import this_shit_is_real.field.FieldPosition;

public class GameObjectsFactory {

    Field field;
    GamePlay gamePlay;

    public GameObjectsFactory(Field field, GamePlay gamePlay) {
        this.field = field;
        this.gamePlay = gamePlay;
    }

    public Enemies generateEnemy(int col, int row) {

        int random = (int) (Math.random() * 3);

        switch (random) {
            case 0:
                return new Enemies(GameObjectsType.VIRUS, createFieldArray (col, row, GameObjectsType.VIRUS), gamePlay);

            case 1:
                return new Enemies(GameObjectsType.VARIANT1, createFieldArray (col, row, GameObjectsType.VARIANT1), gamePlay);

            default:
                return new Enemies(GameObjectsType.VARIANT2, createFieldArray (col, row, GameObjectsType.VARIANT2), gamePlay);
        }
    }

    public Player generatePlayer(int col, int row){
        return new Player(GameObjectsType.PLAYER, createFieldArray (col, row, GameObjectsType.PLAYER), gamePlay);
    }

    public Enemies generateBoss(int col, int row){
        return new Boss(GameObjectsType.CREATOR, createFieldArray (col, row, GameObjectsType.CREATOR), gamePlay);
    }

    public Bullets generateBullets(int col, int row){
        return new Bullets(GameObjectsType.BULLET, createFieldArray (col, row, GameObjectsType.BULLET));
    }
    public Bullets generateBulletsEnemy(int col, int row){
        return new Bullets(GameObjectsType.BULLET, createFieldArray (col, row, GameObjectsType.VIRUS));
    }

    public Barriers generateBarriers(int col, int row){
        return new Barriers(GameObjectsType.BARRIER, createFieldArray (col, row, GameObjectsType.BARRIER));
    }

    public Button generateButton(int col, int row){
        return new Button(GameObjectsType.MENU, createFieldArray (col, row, GameObjectsType.MENU));
    }

    public Heart generateHeart(int col, int row){
        return new Heart(GameObjectsType.HEART, createFieldArray (col, row, GameObjectsType.HEART));
    }

    public FieldPosition[] createFieldArray (int col, int row, GameObjectsType type) {

        int size = type.getSize();
        FieldPosition[] pos = new FieldPosition[size];

        for (int i = 0; i < size; i++) {
            pos[i] = field.makeFieldPosition(col, row);

            // Let's set the final position for all the positions && add an image only to the first position
            switch (i) {
                case 0: pos[i].init(type.getPaths()); pos[i].setOriginal(true); break;
                case 3: pos[i].moveInDirection(FieldDirection.RIGHT, 1);
                case 2: pos[i].moveInDirection(FieldDirection.DOWN, 1); break;
                case 1: pos[i].moveInDirection(FieldDirection.RIGHT, 1);
            }
        }
        return pos;
    }
}
