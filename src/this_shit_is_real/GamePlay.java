package this_shit_is_real;

import this_shit_is_real.field.Field;
import this_shit_is_real.field.FieldDirection;
import this_shit_is_real.gameobjects.*;

import java.util.ArrayList;
import java.util.LinkedList;

public class GamePlay {

    private GameObjects[] gameObjects;
    private Field field;
    private final int TOTAL_ENEMIES = 32;
    private final int E_ROWS = 4;
    private GameObjectsFactory factory;
    private int enemyMovement = 16;
    private int enemyMovs;
    private final int SPEED = 300;
    private int enemySpeed;
    private ArrayList<Bullets> bullets;
    private int counter;

    public GamePlay(Game game) {
        field = game.getField();
        bullets = new ArrayList<>();
        enemySpeed = 1;
        counter = 0;
    }

    public void init() {
        factory = new GameObjectsFactory(field, this);

        gameObjects = new GameObjects[TOTAL_ENEMIES + 5];

        int row = field.getRows();
        int col = field.getCols();

        Player player = factory.generatePlayer((int) col / 2, (int) row - 4);
        gameObjects[0] = player;

        // Barriers
        for (int i = 0; i < 3; i++) {
            gameObjects[i + 1] = factory.generateBarriers(i * (col / 4) + (col / 4), row - 8);
        }

        Enemies boss = factory.generateBoss((int) col / 2, (int) row - ((int) row - 3));
        gameObjects[4] = boss;
        boss.setCurrentDirection(FieldDirection.RIGHT);

        // 32 enemies (8 per row, 4 per col)
        int x = (TOTAL_ENEMIES / E_ROWS) * 2;
        int index = 5;

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
        enemySpeed = 1;
        enemyMovement = (int) enemyMovement / enemySpeed;
        enemyMovs = enemyMovement;

        while (true) {
            counter++;
            Wait.wait(SPEED);
            moveEnemies();
            moveBullets();
            if ( counter % 5 == 0){ enemyShoot(); }
            checkCollision();
        }
    }

    private void moveEnemies() {

        // ENEMIES
        if (enemyMovs < 0) { enemyMovs = enemyMovement; }
        for (int i = 5; i < gameObjects.length; i++) {

            if (enemyMovs > enemyMovement / 2) { gameObjects[i].move(FieldDirection.RIGHT, enemySpeed); }
            else if (enemyMovs > 0) { gameObjects[i].move(FieldDirection.LEFT, enemySpeed); }
            else { gameObjects[i].move(FieldDirection.DOWN, enemySpeed); }
        }
        enemyMovs--;

        // BOSS
        Enemies boss = (Enemies) gameObjects[4];
        int col = boss.getPos().getCol();

        switch (boss.getCurrentDirection()) {
            case RIGHT: if (col == field.getCols() - 2) { boss.setCurrentDirection(FieldDirection.LEFT); break; }
            case LEFT: if (col == 0) { boss.setCurrentDirection(FieldDirection.DOWN); break; }
            case DOWN: if (col == 0) { boss.setCurrentDirection(FieldDirection.RIGHT); break; }
        }   boss.move(boss.getCurrentDirection(), enemySpeed);


    }

    private void moveBullets() {
        for(Bullets bullet : bullets){
            bullet.move(bullet.getCurrentDirection(), 1);
        }
    }

    private void enemyShoot(){
        int i = (int) (Math.random() * TOTAL_ENEMIES + 5);

        Enemies enemy = (Enemies) gameObjects[i];
        enemy.shoot();
    }


    private void checkCollision(){

        Bullets[] removedBullets = new Bullets[bullets.size()];

      //---------------Its necessary to review this part.

        for(Bullets bullet : bullets){
            if(bullet.getCurrentDirection() == FieldDirection.DOWN){
                for(int i = 0; i < 5; i++){
                    if (bullet.getPos().equals(gameObjects[i].getPos())){
                        //Make damage on objects
                        System.out.println("sim");
                        bullet.getPos().hide();
                        for(Bullets r : removedBullets){
                            if(r == null){
                                r = bullet;
                            }
                        }
                    }
                }
            }
        }
    }

    public void addBullet (Bullets bullet) {
        bullets.add(bullet);
    }
    public void removeBullet (Bullets bullet) { bullets.remove(bullet); }
}
