package this_shit_is_real.gameobjects;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.pictures.Picture;
import this_shit_is_real.field.Field;
import this_shit_is_real.field.FieldDirection;
import this_shit_is_real.field.FieldPosition;

public class GameObjects {

    private FieldPosition[] myPos;
    private Picture image;
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

    // Let's move all the object positions
    public void move (FieldDirection direction, int distance) {
        for (FieldPosition pos : myPos) {
            pos.moveInDirection(direction, distance);
        }
    }

    public void kill() {
        dead = true;
        for (FieldPosition pos : myPos) { pos.hide(); }
    }

    public void hit(int damage){
        health -= damage;
    }

    public boolean isDead() { return dead; }
    public Picture getImage() { return image; }
    public FieldPosition getPos() { return myPos[0]; }
    public FieldDirection getCurrentDirection() { return currentDirection; }
    public int getHealth() { return health; }
    public FieldPosition[] getAllPos() { return myPos; }
    public int getDamage() { return damage; }
    public GameObjectsType getType() { return type; }

    public void setCurrentDirection(FieldDirection currentDirection) { this.currentDirection = currentDirection; }
    public void setHealth(int health) { this.health = health; }
    public void setDamage(int damage) { this.damage = damage; }

    @Override
    public String toString() { return getClass().getSimpleName(); }
}
