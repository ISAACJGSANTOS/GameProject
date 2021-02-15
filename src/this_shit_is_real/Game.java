package this_shit_is_real;

import this_shit_is_real.field.Field;
import this_shit_is_real.gameobjects.GameObjectsFactory;
import this_shit_is_real.gameobjects.Wait;
import this_shit_is_real.sounds.GameSounds;

public class Game {

    private Field field;
    private GamePlay gamePlay;

    public Game(int col, int row) { field = new Field(col,row); }


    // INIT EVERYTHING AND START StartMenu ---------------------------------------------------

    public void init() {
        gamePlay = new GamePlay(this);

        GameObjectsFactory factory = new GameObjectsFactory(field, gamePlay);
        GameSounds.startMenu.play(true);
        GameSounds.startMenu.setLoop(1000);

        StartMenu startMenu = new StartMenu(factory);

        while (startMenu.isMenuOn()) { System.out.println(""); }
        if (startMenu.endGame()) { quit(); }

        startMenu.deleteMenu();
        GameSounds.startMenu.close();
        play();
    }


    // PLAY THE GAME --------------------------------------------------------------------------

    public void play() {
        GameSounds.gameMusic.play(true);
        GameSounds.gameMusic.setLoop(1000);

        gamePlay.init();
        Wait.wait(500);
        gamePlay.start();

        GameSounds.gameMusic.close();

        if (gamePlay.getGameState() == "WIN" ) {
            gamePlay.delete();
            new WinMenu();
            GameSounds.win.play(true);
            GameSounds.win.setLoop(1000 );
        }
        else {
            gamePlay.delete();
            new GameOverMenu();
        }
    }

    public void quit() {
        System.exit(0);
    }

    public Field getField() {
        return field;
    }
}
