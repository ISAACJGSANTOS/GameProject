package this_shit_is_real;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import this_shit_is_real.sounds.GameSounds;

public class GameOverMenu implements KeyboardHandler {

    private final Keyboard keyboard;

    public GameOverMenu() {
        keyboard = new Keyboard(this);
        createMenu();
    }

    // START GAME OVER MENU ------------------------------------------------------------------------------

    public void createMenu(){
        Picture gameOver = new Picture();
        gameOver.load("media/GAMEOVER_Final.png");
        gameOver.translate(10, 10);
        gameOver.draw();
        GameSounds.gameOver.play(true);

        activateKeyboard();
    }


    // KEYBOARD ------------------------------------------------------------------------------------------

    public void activateKeyboard() {
        KeyboardEvent spaceButton = new KeyboardEvent();
        spaceButton.setKey(KeyboardEvent.KEY_Q);
        spaceButton.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(spaceButton);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        System.exit(0);
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) { }
}