package this_shit_is_real.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Color;

public enum GameObjectsType {

    VIRUS("China", 10, Color.YELLOW, 1),
    VARIANT1("England", 30, Color.ORANGE, 1),
    VARIANT2("SouthAfrica", 50, Color.PINK, 1),
    VARIANT3("Brazil", 70, Color.MAGENTA, 1),
    CREATOR("Boss", 150, Color.RED, 4),
    PLAYER("Player", 200, Color.GREEN, 1),
    BARRIER("Protection", 200, Color.GRAY, 2),
    BULLET("Bullet", 1, Color.BLUE, 1);
    MENU("Button", 100, Color.WHITE, 2);

    private String name;
    private int health;
    private Color color;
    private int size;

    GameObjectsType(String name, int health, Color color, int size) {
        this.name = name;
        this.health = health;
        this.color = color;
        this.size = size;
    }

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }
}
