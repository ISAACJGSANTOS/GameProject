package this_shit_is_real;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import this_shit_is_real.field.Field;
import this_shit_is_real.gameobjects.Button;
import this_shit_is_real.gameobjects.GameObjectsFactory;

public class StartMenu implements KeyboardHandler {

    private Keyboard keyboard;
    private Game game;
    private Field field;
    private Button[] buttons;
    private int selected;
    private Picture pic;
    private KeyboardEvent downButton;
    private KeyboardEvent upButton;
    private KeyboardEvent spaceButton;
    private boolean menuOn;
    private boolean endGame;

    public StartMenu(GameObjectsFactory factory, Game game, Field field) {
        this.game = game;
        this.field = field;
        this.selected = 0;
        buttons = new Button[3];
        keyboard = new Keyboard(this);

        menuOn = true;
        endGame = false;
        createMenu(factory);
    }

    public Picture getPic() {
        return pic;
    }

    public void createMenu(GameObjectsFactory factory){
        pic = new Picture();
        pic.load("media/menu_background-01.png");
        pic.translate(10, 10);
        pic.draw();

        for (int i = 0; i < buttons.length; i++) { buttons[i] = factory.generateButton(9, 23 + i * 5); }
        buttons[selected].getPos().setColor(Color.RED);
        // buttons[selected].getPos().show(1);
        changeImage();

        activateKeyboard();
    }

    public void activateKeyboard() {
        downButton = new KeyboardEvent();
        downButton.setKey(KeyboardEvent.KEY_DOWN);
        downButton.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        upButton = new KeyboardEvent();
        upButton.setKey(KeyboardEvent.KEY_UP);
        upButton.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        spaceButton = new KeyboardEvent();
        spaceButton.setKey(KeyboardEvent.KEY_SPACE);
        spaceButton.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(downButton);
        keyboard.addEventListener(upButton);
        keyboard.addEventListener(spaceButton);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_DOWN:
                if (selected < buttons.length - 1) { selected ++; changeImage(); }
                break;

            case KeyboardEvent.KEY_UP:
                if (selected > 0) { selected --; changeImage(); }
                break;
            case KeyboardEvent.KEY_SPACE:
                switch (selected) {

                    case 1: endGame = true;
                    case 0: menuOn = false; break;
                    case 2: // game.mute();
                }
        }
    }

    public void changeImage() {

        for (int i = 0; i < buttons.length; i++) {

            Button b = buttons[i];

            if ( b.equals(buttons[selected]) ) {
                b.getPos().show(1 + i * 2);
            } else {
                b.getPos().show(0 + i * 2);
            }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {}

    public void deleteMenu() {
        pic.delete();
        for (Button b: buttons) {
            b.getPos().hide();
            b = null;
        }
        keyboard.removeEventListener(upButton);
        keyboard.removeEventListener(downButton);
        keyboard.removeEventListener(spaceButton);
    }

    public boolean isMenuOn () {
        return menuOn;
    }

    public boolean endGame() {
        return endGame;
    }
}