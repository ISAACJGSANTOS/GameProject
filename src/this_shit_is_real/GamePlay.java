package this_shit_is_real;

import this_shit_is_real.field.Field;
import this_shit_is_real.gameobjects.*;

public class GamePlay {

    private GameObjects[] gameObjects;
    private Field field;
    private final int TOTAL_ENEMIES = 32;
    private final int E_ROWS = 4;
    private GameObjectsFactory factory;


    public GamePlay(Game game) {
        field = game.getField();
    }

    public void init() {
        factory = new GameObjectsFactory(field, this);

        GameObjects[] gameObjects = new GameObjects[TOTAL_ENEMIES + 4];

        int row = field.getRows();
        int col = field.getCols();

        Player player = factory.generatePlayer((int) col / 2, (int) row - 4);
        gameObjects[0] = player;
        Enemies boss = factory.generateBoss((int) col / 2, (int) row - ((int) row - 3));
        gameObjects[1] = boss;

        for (int i = 0; i < (int) col; i++) {
            if (i == (int) col / 4) {
                gameObjects[2] = factory.generateBarriers(i, row - 8);
            } else if (i == ((int) col / 2) + ((int) col / 4)) {
                gameObjects[3] = factory.generateBarriers(i, row - 8);
            }
        }

        // 32 enemies (8 per row, 4 per col)
        int x = (TOTAL_ENEMIES / E_ROWS) * 2;
        int index = 4;

        for (int i = (int) (col - x) / 2; i < (int) x + (col - x) / 2; i++) {
            for (int j = 7; j <= 9 + E_ROWS; j++) {
                if (i % 2 != 0 && j % 2 != 0) {
                    gameObjects[index] = factory.generateEnemy(i, j);
                    index++;
                }
            }
        }
    }

    public GameObjectsFactory getFactory() {
        return factory;
    }

    public void start() {
    }
}
