package this_shit_is_real.gameobjects;

public enum EnemyType {

    VIRUS("China", 10),
    VARIANT1("England", 30),
    VARIANT2("SouthAfrica", 50),
    VARIANT3("Brazil", 70),
    CREATOR("Boss", 150);

    private String name;
    private int health;

    EnemyType(String name, int health) {
        this.name = name;
        this.health = health;
    }
}
