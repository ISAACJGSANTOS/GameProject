package this_shit_is_real;

public class ThisShitIsReal {
    public static void main(String[] args) {

        Game game = new Game(25,41);

        game.play();

        GamePlay gamePlay = new GamePlay(game);
        gamePlay.init();
    }
}
