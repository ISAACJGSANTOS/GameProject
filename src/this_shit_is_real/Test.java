package this_shit_is_real;

import this_shit_is_real.field.Field;
import this_shit_is_real.field.FieldPosition;
import this_shit_is_real.gameobjects.*;

public class Test {

    public static void main(String[] args) {
        Game game = new Game(21,26);

        GameObjectsFactory factory = new GameObjectsFactory(game.getField());

        Player player = factory.generatePlayer(game.getField().getCols()/2, game.getField().getRows() - 4);
        Enemies boss = factory.generateBoss(game.getField().getCols() / 2, game.getField().getRows() - (game.getField().getRows() - 3));
        Barriers barrier = factory.generateBarriers((game.getField().getCols()/ 2) + (game.getField().getCols() / 4),  game.getField().getRows() - 8);
        //Enemies enemy= factory.generateEnemy(4, 10);

        System.out.println((int) 5 / 2);
    }

}
