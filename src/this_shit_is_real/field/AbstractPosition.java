package this_shit_is_real.field;

/*
   This class represents the abstract & logic position of each Game Object.
   It should not be instantiated, since it serves to simplify the code of its subclass: FieldPosition
 */

public abstract class AbstractPosition {

    private int col;
    private int row;
    private Field field;
    private boolean original;

    public AbstractPosition(int col, int row, Field field) {
        this.col = col;
        this.row = row;
        this.field = field;
        original = false;
    }

    // MOVEMENT LOGIC ----------------------------------------------------------------------
    public void moveInDirection(FieldDirection direction, int distance) {
        switch (direction) {
            case UP:
                moveUp(distance);
                break;
            case DOWN:
                moveDown(distance);
                break;
            case LEFT:
                moveLeft(distance);
                break;
            case RIGHT:
                moveRight(distance);
                break;
        }
    }

    // Let's make sure that we don't go out of the field
    public void moveUp(int dist) {
        int maxRowsUp = Math.min (dist, getRow()); // Checks if the movement is bigger than the limit of the field
        setPos(getCol(), getRow() - maxRowsUp); // Calls the method that changes the abstract position
    }

    public void moveDown(int dist) {
        int maxRowsDown = Math.min (dist, getField().getRows() - (getRow() + 1));
        setPos(getCol(), getRow() + maxRowsDown);
    }

    public void moveLeft(int dist) {
        int maxRowsLeft = Math.min (dist, getCol());
        setPos(getCol() - maxRowsLeft, getRow());
    }

    public void moveRight(int dist) {
        int maxRowsRight = Math.min (dist, getField().getCols() - (getCol() + 1));
        setPos(getCol() + maxRowsRight, getRow());
    }

    public void setPos(int col, int row) { // Changes the abstract position of the object (not it's representation)
        this.col = col;
        this.row = row;
        if (original) { show(0); }
    }


    // OTHER ------------------------------------------------------------------------
    public boolean equals(FieldPosition pos) {
        return this.col == pos.getCol() && this.row == pos.getRow();
    }
    public void show(int img){} // Although it is only filled on subclass, we need to create it here in order be able to call it on setPos().

    // GETTERS
    public Field getField() { return field; }
    public int getCol() { return col; }
    public int getRow() { return row; }
    public boolean isOriginal() {
        return original;
    }

    // SETTERS
    public void setOriginal(boolean original) { this.original = original; }

}
