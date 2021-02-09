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

        int row = field.getRows();
        int col = field.getCols();

        Player player = factory.generatePlayer((int) col / 2, (int) row - 4);
        gameObjects[0] = player;
        Enemies boss = factory.generateBoss((int) col / 2, (int) row - ((int) row - 3));
        gameObjects[1] = boss;

        for (int i = 0; i < field.getCols(); i++) {
            if (i == (int) col / 4) {
                gameObjects[2] = factory.generateBarriers(i, row - 8);
            } else if (i == ((int) col / 2) + ((int) col / 4)) {
                gameObjects[3] = factory.generateBarriers(i, row - 8);
            }
        }
        /*for (int i = 0; i < field.getCols(); i++){
            if()

                gameObjects[i] = factory.generateEnemy(0, 0);

            System.out.println(gameObjects[i].toString()):}*/
}

    public void start() {
    }
}
