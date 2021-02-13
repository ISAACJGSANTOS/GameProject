package this_shit_is_real;

import this_shit_is_real.field.Field;
import this_shit_is_real.gameobjects.GameObjectsFactory;
import this_shit_is_real.gameobjects.Wait;
import this_shit_is_real.sounds.GameSounds;

public class Game {

    private Field field;
    private StartMenu startMenu;
    private GamePlay gamePlay;
    private GameOverMenu gameOverMenu;


    public Game(int col, int row){
        field = new Field(col,row);
        System.out.println("Field :" + field.getHeight());
    }

    public void init() {
        gamePlay = new GamePlay(this);

        GameObjectsFactory factory = new GameObjectsFactory(field, gamePlay);
        GameSounds.startMenu.play(true);
        GameSounds.startMenu.setLoop(1000);

        startMenu = new StartMenu(factory,this, getField());
        gameOverMenu = new GameOverMenu(factory,this, getField());

        while (startMenu.isMenuOn()) { System.out.println(""); }
        if (startMenu.endGame()) { quit(); }

        startMenu.deleteMenu();
        GameSounds.startMenu.close();
        play();
    }

    public void play() {
        GameSounds.gameMusic.play(true);
        GameSounds.gameMusic.setLoop(1000);

        gamePlay.init();
        Wait.wait(500);
        gamePlay.start();


        GameSounds.gameMusic.close();

    }

    public void quit() {
        System.exit(0);
    }

    public Field getField() {
        return field;
    }
}
