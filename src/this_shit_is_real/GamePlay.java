package this_shit_is_real;

import this_shit_is_real.field.Field;
import this_shit_is_real.gameobjects.*;

public class GamePlay {

    private GameObjects[] gameObjects;
    private Field field;
    private int totalEnemies = 51;


    public GamePlay(Game game) {
    }

    public void init() {
        GameObjectsFactory factory = new GameObjectsFactory(field);

        GameObjects[] gameObjects = new GameObjects[totalEnemies + 3];

        for (int i = 0; i < gameObjects.length; i++) {
            if (i == 0) {
                Player player = factory.generatePlayer(0, 0);
                gameObjects[i] = player;
            } else if (i == 1) {
                Enemies boss = factory.generateBoss(0, 0);
                gameObjects[i] = boss;
            } else if (i < 4) {
                gameObjects[i] = factory.generateBarriers(0, 0);
            } else {
                gameObjects[i] = factory.generateEnemy(0, 0);
            }
            System.out.println(gameObjects[i].toString());
        }
    }

    public void start() {
    }
}
