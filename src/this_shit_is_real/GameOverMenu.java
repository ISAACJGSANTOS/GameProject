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

public class GameOverMenu implements KeyboardHandler {

    private Keyboard keyboard;
    private Game game;
    private Field field;
    private int selected;
    private Picture gameOver;
    private KeyboardEvent spaceButton;
    private boolean gameOverOn;
    private boolean endGame;

    public GameOverMenu(Game game, Field field) {
        this.game = game;
        this.field = field;
        this.selected = 0;
        keyboard = new Keyboard(this);

        gameOverOn = true;
        endGame = false;
        createMenu();
    }

    public Picture getPic() {
        return gameOver;
    }

    public void createMenu(){
        gameOver = new Picture();
        gameOver.load("media/GAMEOVER_Final.png");
        gameOver.translate(10, 10);
        gameOver.draw();

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
        return gameOverOn;
    }

    public boolean endGame() {
        return endGame;
    }
}