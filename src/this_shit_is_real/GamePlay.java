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
    private Picture pic;
    private Text score;
    private CopyOnWriteArrayList<Heart> lifes;
    private String gameState;
    private WinMenu winMenu;
    private GameOverMenu gameOverMenu;


    public GamePlay(Game game) {
        field = game.getField();
        bullets = new CopyOnWriteArrayList<>();
        enemySpeed = 1;
        counter = 0;
        lifes = new CopyOnWriteArrayList<>();
        gameState = "OFF";
        winMenu = new WinMenu(factory, game, field);
        gameOverMenu = new GameOverMenu(factory, game, field);
    }

    public void init() {

        // Background
        pic = new Picture();
        pic.load("media/Game_background-01.png");
        pic.translate(10, 10);
        pic.draw();

        // Score
        score = new Text(362, 783, "0000");
        score.setColor(Color.WHITE);
        score.grow(20, 20);
        score.draw();

        factory = new GameObjectsFactory(field, this);

        gameObjects = new CopyOnWriteArrayList<GameObjects> ();

        int col = field.getCols();
        int row = field.getRows();

        // Player
        player = factory.generatePlayer((int) col / 2, (int) row - 8);
        gameObjects.add(player);

        // Lifes
        for (int i = player.getLifes() - 1; i >= 0; i--) {
            lifes.add(factory.generateHeart((col / 6) + i * 3, row - 3));
        }

        // Barriers
        for (int i = 0; i < 3; i++) {
            gameObjects.add(factory.generateBarriers(i * (col / 4) + (col / 4), row - 12));
        }

        Enemies boss = factory.generateBoss((int) col / 2, (int) row - ((int) row - 6));
        gameObjects.add(boss);
        boss.setCurrentDirection(FieldDirection.RIGHT);

        // 32 enemies (8 per row, 4 per col)
        int x = (totalEnemies / E_ROWS) * 2;

        for (int i = 1; i < x; i++) {
            for (int j = 10; j <= 12 + E_ROWS; j++) {
                if (i % 2 != 0 && j % 2 != 0) {
                    gameObjects.add(factory.generateEnemy(i, j));
                }
            }
        }
    }

    // START -------------------------------------------------------------------

    public void start() {
        gameState = "ON";

        enemySpeed = 1;
        enemyMovement = (int) enemyMovement / enemySpeed;
        enemyMoves = enemyMovement;

        while (gameState == "ON") {
            counter++;


            Wait.wait(SPEED / 2);

            if ( gameObjects.size() > 4 ) { moveEnemies(); }

            moveBullets();

            Wait.wait(SPEED / 2);

            if ( counter % 8 == 0){ enemyShoot(); }

            checkCollision();

            if (player.getLifes() <= 0) { gameState = "GAME_OVER"; }
            else if (totalEnemies < 0) { gameState = "WIN"; }

        }

        if (gameState == "WIN" ) {
            System.out.println("WIN!");
            winMenu.createMenu(factory);
        }
        else { System.out.println("GAME OVER!"); }
        gameOverMenu.createMenu(factory);
    }


    // MOVEMENT ---------------------------------------------------------------

    private void moveEnemies() {

        // ENEMIES
        if (enemyMoves < 0) { enemyMoves = enemyMovement; }

        for (int i = 5; i < gameObjects.size(); i++) {

            Enemies e = (Enemies) gameObjects.get(i);

            if (!e.isDead()) {
                if (enemyMoves > enemyMovement / 2) { e.move(FieldDirection.RIGHT, enemySpeed); }
                else if (enemyMoves > 0) { e.move(FieldDirection.LEFT, enemySpeed); }
                else { e.move(FieldDirection.DOWN, enemySpeed); }
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
        for(Bullets bullet : bullets) {
            bullet.move(bullet.getCurrentDirection(), 1);
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

        for (GameObjects o1 : gameObjects) {
            for (GameObjects o2 : gameObjects) {
                if (!o1.isDead() && !o2.isDead() && comparePos(o1, o2)) { o1.hit(o2.getDamage()); }
            }
        }

        for(Bullets b : bullets){
            if(b.getCurrentDirection() == FieldDirection.DOWN){
                if(b.getPos().getRow() == field.getRows() - 1){ b.kill(); }
                for(int i = 0; i < 4; i++){
                    GameObjects o = gameObjects.get(i);
                    if (!o.isDead() && comparePos(b, o)) { hit(b, o); }
                }
            } else {
                if(b.getPos().getRow() == 0){ b.kill(); }
                for(int i = 1; i < gameObjects.size(); i++){
                    GameObjects o = gameObjects.get(i);
                    if (!o.isDead() && comparePos(b, o)) { hit(b, o); }
                }
            }
        }

        // KILL THEM

        for (GameObjects o : gameObjects) {
            if(!o.isDead() && o.getHealth() <= 0) {
                if(o instanceof Enemies){
                    player.addScore(60);
                    changeScore();
                    totalEnemies -= 1;
                    System.out.println(player.getScore());
                    o.kill();
                } else if(o instanceof Player){
                    player.setHealth(player.getOriginalHealth());
                    player.reduceLifes();
                } else {
                    o.kill();
                }
            }
        }
        try {
            gameObjects.removeIf(enemy -> (enemy instanceof Enemies & enemy.isDead()));
            bullets.removeIf(bullet -> (bullet.isDead()));
        } catch (Exception e) {}
    }

    private boolean comparePos (GameObjects o1, GameObjects o2) {
        if (o1.getPos() == o2.getPos()) { return false; }

        FieldPosition[] allPos1 = o1.getAllPos();
        FieldPosition[] allPos2 = o2.getAllPos();

        for (FieldPosition pos1 : allPos1) {
            for (FieldPosition pos2 : allPos2) {
                if (pos1.equals(pos2)) { return true; }
            }
        }
        return false;
    }

    private void hit (Bullets b, GameObjects o) {
        b.hit(o.getDamage());
        o.hit(b.getDamage());
        b.kill();
        System.out.println(o.getType() + ": " + o.getHealth());
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

}
