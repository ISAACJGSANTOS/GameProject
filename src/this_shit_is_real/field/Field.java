package this_shit_is_real.field;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/*
   This class represents the logic grid of the game (cols vs rows) and its representation on the screen (the rectangle).
 */

public class Field {

    private static final int PADDING = 10;  // Standard - not to change.
    private static final int CELL_SIZE = 30;  // We can change this one - the larger the number, the larger the columns and rows.
    private Rectangle field;  // Our representation on the screen

    public Field(int cols, int rows) {
        int width = cols * CELL_SIZE;
        int height = rows * CELL_SIZE;
        field = new Rectangle(PADDING, PADDING, width, height);
        init();
    }

    public void init() {
        field.setColor(Color.BLACK);
        field.fill();
    }

    // Here we create the individual positions for each object
    public FieldPosition makeFieldPosition(int col, int row) {
        return new FieldPosition (col, row, this);
    }

    // Translation from abstract position to pixels
    public int rowToYinPixels(int row) {
        return PADDING + row * CELL_SIZE;
    }
    public int columnToXinPixels(int column) {
        return PADDING + column * CELL_SIZE;
    }

    // GETTERS
    public int getCols() {
        return field.getWidth() / CELL_SIZE;
    }
    public int getRows() {
        return field.getHeight() / CELL_SIZE;
    }
    public int getWidth() {
        return field.getWidth();
    }
    public int getHeight() {
        return field.getHeight();
    }
    public int getCellSize() {
        return CELL_SIZE;
    }

}
