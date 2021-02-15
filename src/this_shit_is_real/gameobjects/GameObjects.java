package this_shit_is_real.gameobjects;

import this_shit_is_real.field.FieldDirection;
import this_shit_is_real.field.FieldPosition;

public class GameObjects {

    private FieldPosition[] myPos;
    private int health;
    private GameObjectsType type;
    private FieldDirection currentDirection;
    private int damage;
    private boolean dead;

    public GameObjects (GameObjectsType type, FieldPosition[] pos) {
        this.type = type;
        myPos = pos;
        damage = type.getDamage();
        health = type.getHealth();
        dead = false;
    }


    // MOVING OBJECTS ----------------------------------------------------------------------

    public void move (FieldDirection direction, int distance) {
        for (int i = 0; i < myPos.length; i++) {
            myPos[i].moveInDirection(direction, distance);
        }
    }


    // KILLING OBJECTS & MAKE THEM DISAPPEAR....  ;)

    public void kill() {
        dead = true;
        for (int i = 0; i < myPos.length; i++) { myPos[i].hide(); }
    }


    // GETTERS --------------------------------------------------------------------------

    public boolean isDead() { return dead; }
    public FieldPosition getPos() { return myPos[0]; }
    public FieldDirection getCurrentDirection() { return currentDirection; }
    public int getHealth() { return health; }
    public FieldPosition[] getAllPos() { return myPos; }
    public int getDamage() { return damage; }
    public GameObjectsType getType() { return type; }


    // SETTERS --------------------------------------------------------------------------

    public void hit(int damage){
        health -= damage;
    }
    public void setCurrentDirection(FieldDirection currentDirection) { this.currentDirection = currentDirection; }
    public void setHealth(int health) { this.health = health; }
    public void setDamage(int damage) { this.damage = damage; }

    @Override
    public String toString() { return getClass().getSimpleName(); }
}
