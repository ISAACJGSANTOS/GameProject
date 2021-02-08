package this_shit_is_real;

import this_shit_is_real.gameobjects.*;

public class Test {

    public static void main(String[] args) {
        Game game = new Game(30,50);

        GameObjectsFactory factory = new GameObjectsFactory(game.getField());

        Player player = factory.generatePlayer(2, 3);
        Enemies boss = factory.generateBoss(4, 5);
        Barriers barrier = factory.generateBarriers(6, 8);
        Enemies enemy= factory.generateEnemy(4, 10);
    }

}
