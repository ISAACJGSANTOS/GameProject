package this_shit_is_real.field;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;

/*
   This class represents the logic position of the GameObject and its representation on the field.
   The main logic of the abstract position is on the SuperClass: AbstractPosition.
 */

public class FieldPosition extends AbstractPosition {

    private Rectangle rectangle;
    private Field field;
    private Color color;

    public FieldPosition(int col, int row, Field field) {
        super(col, row, field);
        this.field = field;
    }

    // This method is called by the GameObject (the owner of FieldPosition) to show the object on canvas for the first time
    public void init (Color color) {
        // Until we have the images, we'll use rectangles to represent the objects
        this.color = color;
        rectangle = new Rectangle(field.columnToXinPixels(getCol()), field.rowToYinPixels(getRow()), field.getCellSize(),field.getCellSize());
        show();
    }

    @Override
    public void show() {  // Soon to be adjusted to rectangles
        rectangle.setColor(color);
        rectangle.fill();
    }
    public void hide() { rectangle.delete(); }   // Soon to be adjusted to rectangles

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void moveInDirection(FieldDirection direction, int distance) {  // The parameters receive the abstract distance (in rows or columns, NOT in pixels)

        // Let's save the current position in pixels (CP)
        int xStart = field.columnToXinPixels(getCol());
        int yStart = field.rowToYinPixels(getRow());

        // Let's change the abstract position (row and columns)
        super.moveInDirection(direction, distance);

        // Let's save the final position in pixels (FP)
        int xFinal = field.columnToXinPixels(getCol());
        int yFinal = field.rowToYinPixels(getRow());

        rectangle.translate(xFinal - xStart, yFinal - yStart);  // This method of Rectangle receives ONLY the movement in pixels (= FP - CP)
    }
}
