package this_shit_is_real;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import this_shit_is_real.gameobjects.Button;
import this_shit_is_real.gameobjects.GameObjectsFactory;
import static this_shit_is_real.sounds.GameSounds.*;

public class StartMenu implements KeyboardHandler {

    private final Keyboard keyboard;
    private Button[] buttons;
    private int selected;
    private Picture pic = new Picture();
    private KeyboardEvent downButton;
    private KeyboardEvent upButton;
    private KeyboardEvent spaceButton;
    private boolean menuOn;
    private boolean endGame;
    private boolean musicOn;

    // INIT AND CREATE MENU ----------------------------------------------------------------

    public StartMenu(GameObjectsFactory factory) {
        this.selected = 0;
        musicOn = true;
        buttons = new Button[3];
        keyboard = new Keyboard(this);

        menuOn = true;
        endGame = false;
        createMenu(factory);
    }

    public void createMenu(GameObjectsFactory factory){
        pic.load("media/Menu_background_final-01.png");
        pic.translate(10, 10);
        pic.draw();

        for (int i = 0; i < buttons.length; i++) { buttons[i] = factory.generateButton(9, 17 + i * 5); }
        changeImage();

        activateKeyboard();

    }

    // KEYBOARD ----------------------------------------------------------------------------

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
                if (selected < buttons.length - 1) { selected ++; changeImage(); button.play(true);}
                break;

            case KeyboardEvent.KEY_UP:
                if (selected > 0) { selected --; changeImage(); button.play(true); }
                break;
            case KeyboardEvent.KEY_SPACE:
                switch (selected) {

                    case 1: endGame = true;
                    case 0: menuOn = false; break;
                    case 2: muteMusic();
                }
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {}

    public void deleteMenu() {
        pic.delete();
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].getPos().hide();
            buttons[i] = null;
        }
        keyboard.removeEventListener(upButton);
        keyboard.removeEventListener(downButton);
        keyboard.removeEventListener(spaceButton);
    }


    // OTHER -------------------------------------------------------------------------

    private void muteMusic(){
        if(musicOn) {
            startMenu.stop();
            musicOn = false;
        } else {
            startMenu.play(true);
            musicOn = true;
        }
    }

    public void changeImage() {
        for (int i = 0; i < buttons.length; i++) {

            Button b = buttons[i];

            if ( b.equals(buttons[selected]) ) {
                b.getPos().show(1 + i * 2);
            } else {
                b.getPos().show(i * 2);
            }
        }
    }


    // GETTERS ---------------------------------------------------------------

    public boolean isMenuOn () {
        return menuOn;
    }
    public boolean endGame() {
        return endGame;
    }
}