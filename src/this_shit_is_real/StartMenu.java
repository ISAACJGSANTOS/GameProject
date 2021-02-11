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
import this_shit_is_real.gameobjects.Wait;

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

    public StartMenu(GameObjectsFactory factory, Game game, Field field) {
        this.game = game;
        this.field = field;
        this.selected = 0;
        buttons = new Button[3];
        keyboard = new Keyboard(this);

        createMenu(factory);
    }

    public void createMenu(GameObjectsFactory factory){
        pic = new Picture(field.getWidth(), field.getHeight());
        pic.load("media/test_menu.jpg");
        pic.draw();
        pic.translate(10, 10);

        for (int i = 0; i < buttons.length; i++) { buttons[i] = factory.generateButton(9, 23 + i * 5); }
        buttons[selected].getPos().setColor(Color.RED);
        buttons[selected].getPos().show();

        activateKeyboard();
    }

    public void resetMenu(){
        pic.delete();
        for (Button b: buttons) {
            b.getPos().hide();
        }
        keyboard.removeEventListener(upButton);
        keyboard.removeEventListener(downButton);
        keyboard.removeEventListener(spaceButton);
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
                    case 0:
                        resetMenu();
                        Wait.wait(20);
                        game.play();
                        break;
                    case 1: // game.quit(); break;
                    case 2: // game.mute();
                }
        }
    }

    public void changeImage() {

        for (Button b : buttons) {

            if ( b.equals(buttons[selected]) ) {
                b.getPos().setColor(Color.RED);
            } else {
                b.getPos().setColor(Color.WHITE);
            }
            b.getPos().show();
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {}
}