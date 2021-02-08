package this_shit_is_real.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Color;

public enum GameObjectsType {

    VIRUS("China", 10, Color.YELLOW),
    VARIANT1("England", 30, Color.ORANGE),
    VARIANT2("SouthAfrica", 50, Color.PINK),
    VARIANT3("Brazil", 70, Color.MAGENTA),
    CREATOR("Boss", 150, Color.RED),
    PLAYER("Player", 200, Color.GREEN),
    BARRIER("Protection", 200, Color.GRAY),
    BULLET("Bullet", 1, Color.BLUE);

    private String name;
    private int health;
    private Color color;

    GameObjectsType(String name, int health, Color color) {
        this.name = name;
        this.health = health;
        this.color = color;
    }
}
