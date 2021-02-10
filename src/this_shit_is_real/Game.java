package this_shit_is_real;

import this_shit_is_real.field.Field;
import this_shit_is_real.gameobjects.GameObjectsFactory;

public class Game {

    private Field field;
    private StartMenu startMenu;
    private GamePlay gamePlay;

    public Game(int col, int row){
        field = new Field(col,row);
        init();
    }

    public void init() {
//        GameObjectsFactory factory = new GameObjectsFactory(field);
//        startMenu = new StartMenu(factory,this, getField());
    }

    public void play() {
        gamePlay = new GamePlay(this);
        gamePlay.start();
    }

    public Field getField() {
        return field;
    }

}
