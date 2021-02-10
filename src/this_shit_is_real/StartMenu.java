package this_shit_is_real;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import this_shit_is_real.field.Field;
import this_shit_is_real.gameobjects.Button;
import this_shit_is_real.gameobjects.GameObjectsFactory;

public class StartMenu implements KeyboardHandler {

    private Keyboard keyboard;
    private Game game;
    private Field field;
    private Button startButton;
    private Button muteButton;
    private Button quitButton;
    private int count = 0;

    public StartMenu(GameObjectsFactory factory, Game game, Field field) {
        keyboard = new Keyboard(this);
        this.game = game;
        this.field = field;
        createMenu(factory);
    }

    public void createMenu(GameObjectsFactory factory){
        startButton = factory.generateButton(10, 10);
        muteButton = factory.generateButton(10, 15);
        quitButton = factory.generateButton(10,20);
        activateKeyboard();
    }

    public void activateKeyboard() {
        KeyboardEvent downButton = new KeyboardEvent();
        downButton.setKey(KeyboardEvent.KEY_DOWN);
        downButton.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent upButton = new KeyboardEvent();
        upButton.setKey(KeyboardEvent.KEY_UP);
        upButton.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent spaceButton = new KeyboardEvent();
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
                if((count >= 0 && count<2)){
                    count++;
                    System.out.println(count);
                }
                System.out.println("KeyDown Clicked");
                changeImage();
                break;

            case KeyboardEvent.KEY_UP:
                if (!(count <1)){
                    count--;
                    System.out.println(count);
                }
                System.out.println("KeyUp Clicked");
                changeImage();
                break;

            case KeyboardEvent.KEY_SPACE:
                /*
                * se o count for igual a 1 -> startGame
                * se o count for igual a 2 -> mute
                * se o count for igual a 3 -> quit
                */
                System.out.println("SPACE Clicked");
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }

    public void changeImage(){
        switch (count){
            case 0:{
               /*
               image startButton selected
               image muteButton unselected
               image quitButton unselected
                */
            }
            case 1:{
                /*
               image muteButton selected
               image startButton unselected
               image quitButton unselected
                */
            }
            case 2:{
                /*
               image quitButton selected
               image startButton unselected
               image muteButton unselected
                */
            }
        }
    }
}
