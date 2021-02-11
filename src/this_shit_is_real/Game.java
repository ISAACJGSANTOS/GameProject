package this_shit_is_real;

import this_shit_is_real.field.Field;
import this_shit_is_real.gameobjects.GameObjectsFactory;
import this_shit_is_real.gameobjects.Wait;

public class Game {

    private Field field;
    private StartMenu startMenu;
    private GamePlay gamePlay;

    public Game(int col, int row){
        field = new Field(col,row);

    }

    public void init() {
        gamePlay = new GamePlay(this);
        GameObjectsFactory factory = new GameObjectsFactory(field, gamePlay);
        // startMenu = new StartMenu(factory,this, getField());

        play();
    }

    public void play() {
        gamePlay.init();
        Wait.wait(500);
        gamePlay.start();
    }

    public Field getField() {
        return field;
    }

}
