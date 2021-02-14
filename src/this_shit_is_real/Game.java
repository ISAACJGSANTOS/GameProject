package this_shit_is_real;

import this_shit_is_real.field.Field;
import this_shit_is_real.gameobjects.GameObjectsFactory;
import this_shit_is_real.gameobjects.Wait;
import this_shit_is_real.sounds.GameSounds;

public class Game {

    private Field field;
    private StartMenu startMenu;
    private GamePlay gamePlay;


    public Game(int col, int row) {
        field = new Field(col,row);
    }

    public void init() {
        gamePlay = new GamePlay(this);

        GameObjectsFactory factory = new GameObjectsFactory(field, gamePlay);
        GameSounds.startMenu.play(true);
        GameSounds.startMenu.setLoop(1000);

        startMenu = new StartMenu(factory,this, getField());

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

        if (gamePlay.getGameState() == "WIN" ) {
            System.out.println("WIN!");
            gamePlay.delete();
            new WinMenu(this, field);
            GameSounds.win.play(true);
            GameSounds.win.setLoop(1000 );
        }
        else {
            System.out.println("LOST!");
            gamePlay.delete();
            new GameOverMenu(this, field);

        }


    }

    public void quit() {
        System.exit(0);
    }

    public Field getField() {
        return field;
    }
}
