package this_shit_is_real.gameobjects;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import this_shit_is_real.GamePlay;
import this_shit_is_real.field.FieldDirection;
import this_shit_is_real.field.FieldPosition;

public class Player extends GameObjects implements KeyboardHandler {

    private int score;
    private Keyboard keyboard;
    private GameObjectsFactory factory;
    private GamePlay gamePlay;
    private Bullets bullet;
    private int lifes;

    public Player(GameObjectsType type, FieldPosition[] pos, GamePlay game){
        super(type, pos);
        keyboard = new Keyboard(this);
        gamePlay = game;
        factory = gamePlay.getFactory();
        init();
        score = 0;
        lifes = 3;
    }

    public void init() {
        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent shoot = new KeyboardEvent();
        shoot.setKey(KeyboardEvent.KEY_SPACE);
        shoot.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(left);
        keyboard.addEventListener(right);
        keyboard.addEventListener(shoot);
    }

    public void shoot(){
        int row = getPos().getRow() - 1;
        int col = getPos().getCol();
        Bullets bullet = factory.generateBullets(col, row);
        bullet.setCurrentDirection(FieldDirection.UP);
        bullet.setDamage(getDamage());
        gamePlay.addBullet(bullet);
    }

    // KEYBOARD STAR -------------------------------------------------------------

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        FieldPosition pos = getPos();
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                setCurrentDirection(FieldDirection.LEFT);
                move(getCurrentDirection(),1);
                break;
            case KeyboardEvent.KEY_RIGHT:
                setCurrentDirection(FieldDirection.RIGHT);
                move(getCurrentDirection(),1);
                break;
            case KeyboardEvent.KEY_SPACE:
                shoot();
                break;
        }
    }

    public void keyReleased(KeyboardEvent keyboardEvent) {}

    // KEYBOARD END --------------------------------------------------------------

    public int getScore() {
        return score;
    }

    public void addScore(int add) {
        score += add;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }
}
