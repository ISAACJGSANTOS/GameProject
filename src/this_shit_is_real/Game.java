package this_shit_is_real;

import this_shit_is_real.field.Field;

public class Game {

    private Field field;
    private StartMenu startMenu;
    private GamePlay gamePlay;

    public Game(int col, int row){
        field = new Field(col,row);
        init();
    }

    public void init() {
        startMenu = new StartMenu(this);
    }

    public void play() {
        gamePlay = new GamePlay(this);
        gamePlay.start();
    }

    public Field getField() {
        return field;
    }

}
