package this_shit_is_real;

import this_shit_is_real.field.Field;
import this_shit_is_real.field.FieldDirection;
import this_shit_is_real.gameobjects.*;

import java.util.ArrayList;

public class GamePlay {

    private GameObjects[] gameObjects;
    private Field field;
    private final int TOTAL_ENEMIES = 32;
    private final int E_ROWS = 4;
    private GameObjectsFactory factory;
    private final int ENEMY_MOVEMENTS = 16;
    private int enemyMovs;
    private final int NORMAL_SPEED = 300;
    private int enemySpeed;
    private ArrayList<Bullets> bullets;

    public GamePlay(Game game) {
        field = game.getField();
        enemyMovs = ENEMY_MOVEMENTS;
        enemySpeed = NORMAL_SPEED;
        bullets = new ArrayList<Bullets>();
    }

    public void init() {
        factory = new GameObjectsFactory(field, this);

        gameObjects = new GameObjects[TOTAL_ENEMIES + 4];

        int row = field.getRows();
        int col = field.getCols();

        Player player = factory.generatePlayer((int) col / 2, (int) row - 4);
        gameObjects[0] = player;
        Enemies boss = factory.generateBoss((int) col / 2, (int) row - ((int) row - 3));
        gameObjects[1] = boss;
        boss.setCurrentDirection(FieldDirection.RIGHT);

        // Barriers
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

        for (int i = 1; i < x; i++) {
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

        while (true) {

            Wait.wait(enemySpeed);
            moveEnemies();

            Wait.wait(NORMAL_SPEED - enemySpeed);
            moveBullets();

        }

    }

    private void moveEnemies() {

        // ENEMIES
        if (enemyMovs < 0) { enemyMovs = ENEMY_MOVEMENTS; }
        for (int i = 4; i < gameObjects.length; i++) {

            if (enemyMovs > ENEMY_MOVEMENTS / 2) { gameObjects[i].move(FieldDirection.RIGHT, 1); }
            else if (enemyMovs > 0) { gameObjects[i].move(FieldDirection.LEFT, 1); }
            else { gameObjects[i].move(FieldDirection.DOWN, 1); }
        }
        enemyMovs--;

        // BOSS
        Enemies boss = (Enemies) gameObjects[1];
        int col = boss.getPos().getCol();

        switch (boss.getCurrentDirection()) {
            case RIGHT: if (col == field.getCols() - 2) { boss.setCurrentDirection(FieldDirection.LEFT); break; }
            case LEFT: if (col == 0) { boss.setCurrentDirection(FieldDirection.DOWN); break; }
            case DOWN: if (col == 0) { boss.setCurrentDirection(FieldDirection.RIGHT); break; }
        }   boss.move(boss.getCurrentDirection(), 1);


    }

    private void moveBullets() {

    }

    public void addBullet (Bullets bullet) {
        bullets.add(bullet);
    }
    public void removeBullet (Bullets bullet) { bullets.remove(bullet); }
}
