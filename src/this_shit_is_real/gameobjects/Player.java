package this_shit_is_real.gameobjects;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import this_shit_is_real.field.FieldDirection;
import this_shit_is_real.field.FieldPosition;

public class Player extends GameObjects implements KeyboardHandler {

    private int score;
    private Keyboard keyboard;

    public Player(GameObjectsType type, FieldPosition pos){
        super(type, pos);
        keyboard = new Keyboard(this);
        init();
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

    public void hit(int bulletDamage){
        setHealth(getHealth() - bulletDamage);
    }

    public void shoot(){
        Bullets bullet = new Bullets(GameObjectsType.BULLET, getPos());
    }

    // KEYBOARD STAR -------------------------------------------------------------

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_LEFT:
                setCurrentDirection(FieldDirection.LEFT);
                break;
            case KeyboardEvent.KEY_RIGHT:
                setCurrentDirection(FieldDirection.RIGHT);
                break;
            case KeyboardEvent.KEY_SPACE:
                shoot();
                break;
        }
    }

    public void keyReleased(KeyboardEvent keyboardEvent) {}

    // KEYBOARD END --------------------------------------------------------------

}
