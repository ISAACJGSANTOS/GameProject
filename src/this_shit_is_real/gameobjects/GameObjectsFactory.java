package this_shit_is_real.gameobjects;

import java.util.Random;

public class GameObjectsFactory {

    public Enemies generateEnemy() {
        int random = (int) (Math.random() * 4);

        switch (random) {
            case 0:
                return new Enemies(EnemyType.VIRUS);

            case 1:
                return new Enemies(EnemyType.VARIANT1);

            case 2:
                return new Enemies(EnemyType.VARIANT2);

            default:
                return new Enemies(EnemyType.VARIANT3);
        }
    }

    public Player generatePlayer(){
        return new Player();
    }

    public Enemies generateBoss(){
        return new Enemies(EnemyType.CREATOR);
    }

    public Bullets generateBullets(){
        return new Bullets();
    }

    public Barriers generateBarriers(){
        return new Barriers();
    }
}
