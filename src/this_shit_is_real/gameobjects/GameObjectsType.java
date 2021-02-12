package this_shit_is_real.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Color;

public enum GameObjectsType {

    VIRUS("China", 10, Color.YELLOW, 1, 50),
    VARIANT1("England", 30, Color.ORANGE, 1, 100),
    VARIANT2("SouthAfrica", 50, Color.PINK, 1, 150),
    VARIANT3("Brazil", 70, Color.MAGENTA, 1, 200),
    CREATOR("Boss", 150, Color.RED, 4, 250),
    PLAYER("Player", 200, Color.GREEN, 1, 20),
    BARRIER("Protection", 200, Color.GRAY, 2, 1),
    BULLET("Bullet", 1, Color.BLUE, 1, 1),
    MENU("Button", 100, Color.WHITE, 1, 1);

    private String name;
    private int health;
    private Color color;
    private int size;
    private int damage;

    GameObjectsType(String name, int health, Color color, int size, int damage) {
        this.name = name;
        this.health = health;
        this.color = color;
        this.size = size;
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
    public Color getColor() {
        return color;
    }
    public int getHealth() {
        return health;
    }
    public int getSize() {
        return size;
    }
}
