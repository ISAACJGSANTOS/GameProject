package this_shit_is_real;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import this_shit_is_real.field.Field;
import this_shit_is_real.field.FieldDirection;
import this_shit_is_real.field.FieldPosition;
import this_shit_is_real.gameobjects.*;
import this_shit_is_real.sounds.GameSounds;

import java.util.concurrent.CopyOnWriteArrayList;

public class GamePlay {

    private CopyOnWriteArrayList<GameObjects> gameObjects;
    private Field field;
    private int totalEnemies = 32;
    private final int E_ROWS = 4;
    private GameObjectsFactory factory;
    private int enemyMovement = 16;
    private int enemyMoves;
    private final int SPEED = 300;
    private int enemySpeed;
    private CopyOnWriteArrayList<Bullets> bullets;
    private int counter;
    private Player player;
    private Text score;
    private CopyOnWriteArrayList<Heart> lifes;
    private String gameState = "OFF";

    public GamePlay(Game game) {
        field = game.getField();
        bullets = new CopyOnWriteArrayList<>();
        enemySpeed = 1;
        counter = 0;
        lifes = new CopyOnWriteArrayList<>();
    }


    // INSTANTIATE OBJECTS ------------------------------------------------------

    public void init() {

        // Background
        Picture pic = new Picture();
        pic.load("media/Game_final-01.png");
        pic.translate(10, 10);
        pic.draw();

        // Score
        score = new Text(362, 620, "0000");
        score.setColor(Color.WHITE);
        score.grow(20, 20);
        score.draw();

        factory = new GameObjectsFactory(field, this);
        gameObjects = new CopyOnWriteArrayList<GameObjects> ();
        int col = field.getCols();
        int row = field.getRows();

        // Player
        player = factory.generatePlayer((int) col / 2, (int) row - 6);
        gameObjects.add(player);

        // Lifes
        for (int i = player.getLifes() - 1; i >= 0; i--) {
            lifes.add(factory.generateHeart((col / 6) + i * 3, row - 3));
        }

        // Barriers
        for (int i = 0; i < 3; i++) {
            gameObjects.add(factory.generateBarriers(i * (col / 4) + (col / 4), row - 9));
        }

        // Boss
        Enemies boss = factory.generateBoss((int) col / 2, (int) row - ((int) row - 6));
        gameObjects.add(boss);
        boss.setCurrentDirection(FieldDirection.RIGHT);

        // 32 enemies (8 per row, 4 per col)
        int x = (totalEnemies / E_ROWS) * 2;

        for (int i = 1; i < x; i++) {
            for (int j = 10; j <= 10 + E_ROWS * 2; j++) {
                if (i % 2 != 0 && j % 2 != 0) {
                    gameObjects.add(factory.generateEnemy(i, j));
                }
            }
        }
    }

    // START -------------------------------------------------------------------

    public void start() {

        // Future development needed: enemies movement accelerates as the player scores
        gameState = "ON";
        enemySpeed = 1;
        enemyMovement = (int) enemyMovement / enemySpeed;
        enemyMoves = enemyMovement;

        // Main while of the game
        while (gameState == "ON") {
            counter++;

            Wait.wait(SPEED / 2);

            if ( gameObjects.size() > 4 ) { moveEnemies(); }

            moveBullets();

            Wait.wait(SPEED / 2);

            if ( counter % 5 == 0 && gameObjects.size() > 4){ enemyShoot(); }

            checkCollision();

            if (player.getLifes() <= 0) { gameState = "GAME_OVER"; }
            if (totalEnemies < 0) { gameState = "WIN"; }
        }
    }

    // MOVEMENT ---------------------------------------------------------------

    private void moveEnemies() {

        // ENEMIES
        if (enemyMoves < 0) { enemyMoves = enemyMovement; }

        for (int i = 0; i < gameObjects.size(); i++) {
            GameObjects o = gameObjects.get(i);
            if ( o instanceof Enemies & !(o instanceof Boss) ) {

                Enemies e = (Enemies) o;

                if (!e.isDead()) {
                    if (enemyMoves > enemyMovement / 2) { e.move(FieldDirection.RIGHT, enemySpeed); }
                    else if (enemyMoves > 0) { e.move(FieldDirection.LEFT, enemySpeed); }
                    else { e.move(FieldDirection.DOWN, enemySpeed); }
                }
            }
        }

        enemyMoves--;

        // BOSS
        if (gameObjects.get(4) instanceof Boss){ try {

            Enemies boss = (Boss) gameObjects.get(4);

            if (!boss.isDead()) {
                int col = boss.getPos().getCol();

                switch (boss.getCurrentDirection()) {
                    case RIGHT:
                        if (col == field.getCols() - 2) {
                            boss.setCurrentDirection(FieldDirection.LEFT);
                            break;
                        }
                    case LEFT:
                        if (col == 0) {
                            boss.setCurrentDirection(FieldDirection.DOWN);
                            break;
                        }
                    case DOWN:
                        if (col == 0) {
                            boss.setCurrentDirection(FieldDirection.RIGHT);
                            break;
                        }
                }
                boss.move(boss.getCurrentDirection(), enemySpeed);
            }
        } catch (Exception e) {
            System.out.println(" problemo boss... "); } }
    }

    private void moveBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move(bullets.get(i).getCurrentDirection(), 1);
        }
    }


    // SHOOTING ---------------------------------------------------------------

    private void enemyShoot(){

        int i = (int) (Math.random() * (gameObjects.size() - 4) + 4);

        Enemies enemy = (Enemies) gameObjects.get(i);
        enemy.shoot();
    }


    // COLLISIONS ---------------------------------------------------------------

    private void checkCollision(){

        // Collisions between enemies, barriers and player
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObjects o1 = gameObjects.get(i);

            for (int j = 0; j < gameObjects.size(); j++) {
                GameObjects o2 = gameObjects.get(j);
                if (i == 0 && o2 instanceof Enemies && o1.getPos().getRow() <= o2.getPos().getRow() ) { o1.hit(200); }
                if (!o1.isDead() && !o2.isDead() && comparePos(o1, o2)) { o1.hit(o2.getDamage()); }
            }
        }

        // Collisions of bullets with other objects

        // Enemy Bullets
        for (int j = 0; j < bullets.size(); j++) {
            Bullets b = bullets.get(j);
            if(b.getCurrentDirection() == FieldDirection.DOWN){
                if(b.getPos().getRow() == field.getRows() - 1){ b.kill(); }

                for(int i = 0; i < 4; i++){
                    GameObjects o = gameObjects.get(i);
                    if (!o.isDead() && comparePos(b, o)) { hit(b, o); }
                }

            // Player bullets
            } else {
                if(b.getPos().getRow() == 0){ b.kill(); }

                for(int i = 1; i < gameObjects.size(); i++){
                    GameObjects o = gameObjects.get(i);
                    if (!o.isDead() && comparePos(b, o)) { hit(b, o); GameSounds.hitEnemy.play(true);}
                }
            }
        }

        // Killing Game Objects
        for (int i = 0; i < gameObjects.size(); i++) {
            GameObjects o = gameObjects.get(i);
            if(!o.isDead() && o.getHealth() <= 0) {
                if(o instanceof Enemies){
                    player.addScore(60);
                    changeScore();
                    totalEnemies -= 1;
                    o.kill();

                } else if(o instanceof Player){
                    player.setHealth(player.getOriginalHealth());
                    GameSounds.hitPlayer.play(true);
                    player.reduceLifes();

                } else {
                    o.kill();
                }
            }
        }

        // Cleaning lists
        try {
            gameObjects.removeIf(enemy -> (enemy instanceof Enemies & enemy.isDead()));
            bullets.removeIf(bullet -> (bullet.isDead()));
        } catch (Exception ignored) {}
    }

    // COLLISIONS SUPPORT  ----------------------------------------------------

    // Comparing positions
    private boolean comparePos (GameObjects o1, GameObjects o2) {
        if (o1.getPos() == o2.getPos()) { return false; }

        FieldPosition[] allPos1 = o1.getAllPos();
        FieldPosition[] allPos2 = o2.getAllPos();

        for (int i = 0; i < allPos1.length; i++) {
            for (int j = 0; j < allPos2.length; j++) {
                if (allPos1[i].equals(allPos2[j])) { return true; }
            }
        }
        return false;
    }

    // Bullet hit
    private void hit (Bullets b, GameObjects o) {
        b.hit(o.getDamage());
        o.hit(b.getDamage());
        b.kill();
    }


    // GRAPHICS ---------------------------------------------------------------

    private void changeScore () {

        if ( player.getScore() < 99 ) { score.setText("00" + player.getScore()); }
        else if ( player.getScore() < 999 ) { score.setText("0" + player.getScore()); }
        else { score.setText("" + player.getScore()); }
    }

    public void minusLife () {
        lifes.get(0).kill();
        lifes.remove(0);
    }


    // OTHER ---------------------------------------------------------------

    public void addBullet (Bullets bullet) { bullets.add(bullet); }
    public GameObjectsFactory getFactory() { return factory; }
    public String getGameState() {
        return gameState;
    }

    public void delete() {
        score.delete();

        gameObjects.clear();
        lifes.clear();
        bullets.clear();

        player.killListeners();
        player.getPos().hide();
        player = null;
    }
}
