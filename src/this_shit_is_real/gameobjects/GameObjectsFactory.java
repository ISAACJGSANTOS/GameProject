package this_shit_is_real.gameobjects;

import this_shit_is_real.field.Field;

import java.util.Random;

public class GameObjectsFactory {

    Field field;

    public GameObjectsFactory(Field field) {
        this.field = field;
    }

    public Enemies generateEnemy(int col, int row) {

        int random = (int) (Math.random() * 4);

        switch (random) {
            case 0:
                return new Enemies(EnemyType.VIRUS, field.makeFieldPosition(col, row));

            case 1:
                return new Enemies(EnemyType.VARIANT1, field.makeFieldPosition(col, row));

            case 2:
                return new Enemies(EnemyType.VARIANT2, field.makeFieldPosition(col, row));

            default:
                return new Enemies(EnemyType.VARIANT3, field.makeFieldPosition(col, row));
        }
    }

    public Player generatePlayer(int col, int row){
        return new Player(field.makeFieldPosition(col, row));
    }

    public Enemies generateBoss(int col, int row){
        return new Enemies(EnemyType.CREATOR, field.makeFieldPosition(col, row));
    }

    public Bullets generateBullets(int col, int row){
        return new Bullets(field.makeFieldPosition(col, row));
    }

    public Barriers generateBarriers(int col, int row){
        return new Barriers(field.makeFieldPosition(col, row));
    }
}
