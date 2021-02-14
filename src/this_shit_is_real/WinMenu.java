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
import this_shit_is_real.sounds.GameSounds;

public class WinMenu implements KeyboardHandler {

    private Keyboard keyboard;
    private Game game;
    private Field field;
    private int selected;
    private KeyboardEvent spaceButton;
    private boolean winMenu;
    private boolean endGame;
    private Picture winMenuW;

    public WinMenu(Game game, Field field) {
        this.game = game;
        this.field = field;
        this.selected = 0;
        keyboard = new Keyboard(this);

        winMenu = true;
        endGame = false;
        createMenu();
    }

    public Picture getPic() {
        return winMenuW;
    }

    public void createMenu(){
        winMenuW = new Picture();
        winMenuW.load("media/Won_final-01.png");
        winMenuW.translate(10, 10);
        winMenuW.draw();

        activateKeyboard();
    }

    public void activateKeyboard() {

        spaceButton = new KeyboardEvent();
        spaceButton.setKey(KeyboardEvent.KEY_SPACE);
        spaceButton.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(spaceButton);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
       System.exit(0);
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public boolean isMenuOn () {
        return winMenu;
    }

    public boolean endGame() {
        return endGame;
    }
}