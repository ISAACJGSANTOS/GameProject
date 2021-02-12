package this_shit_is_real.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Color;

public enum GameObjectsType {

    VIRUS("China", 10, Color.YELLOW, 1, 50, new String[]{"media/virus1.png"}),
    VARIANT1("England", 30, Color.ORANGE, 1, 100, new String[]{"media/virus2.png"}),
    VARIANT2("SouthAfrica", 50, Color.PINK, 1, 150, new String[]{"media/virus3.png"}),
    CREATOR("Boss", 150, Color.RED, 4, 250, new String[]{"media/boss_Bat.png"}),
    PLAYER("Player", 200, Color.GREEN, 1, 20, new String[]{"media/playerM_normal.png", "media/playerM_throw.png"}),
    BARRIER("Protection", 200, Color.GRAY, 2, 1, new String[]{"media/barriers_trump-01.png"}),
    BULLET("Bullet", 1, Color.BLUE, 1, 1, new String[]{"media/syringe.png"}),
    MENU("Button", 100, Color.WHITE, 1, 1, new String[]{"media/M_button_start-01.png", "media/M_button_start_selected-01.png", "media/M_button_quit-01.png", "media/M_button_quit_selected-01.png", "media/M_button_mute-01.png", "media/M_button_mute_selected-01.png"});

    private String name;
    private int health;
    private Color color;
    private int size;
    private int damage;
    private String paths[];

    GameObjectsType(String name, int health, Color color, int size, int damage, String[] paths) {
        this.name = name;
        this.health = health;
        this.color = color;
        this.size = size;
        this.damage = damage;
        this.paths = paths;
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
    public String[] getPaths() {
        return paths;
    }
}
