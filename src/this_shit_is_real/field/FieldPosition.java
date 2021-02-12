package this_shit_is_real.field;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

/*
   This class represents the logic position of the GameObject and its representation on the field.
   The main logic of the abstract position is on the SuperClass: AbstractPosition.
 */

public class FieldPosition extends AbstractPosition {

    private Rectangle rectangle;
    private Field field;
    private Color color;
    private Picture image;
    private String[] paths;

    public FieldPosition(int col, int row, Field field) {
        super(col, row, field);
        this.field = field;
    }

    // This method is called by the GameObject (the owner of FieldPosition) to show the object on canvas for the first time
    public void init (String[] paths) {
        this.paths = paths;
        image = new Picture();
        image.translate(field.columnToXinPixels(getCol()), field.rowToYinPixels(getRow()));

        /*
        this.color = color;
        rectangle = new Rectangle(field.columnToXinPixels(getCol()), field.rowToYinPixels(getRow()), field.getCellSize(),field.getCellSize());
        show(); */
    }

    public void setPath(String[] paths) {
        this.paths = paths;
    }

    @Override
    public void show(int img) {  // Soon to be adjusted to image
        // rectangle.setColor(color);
        // rectangle.fill();
        image.load(paths[img]);
        image.draw();
    }
    public void hide() { /* rectangle.delete(); */ image.delete(); }   // Soon to be adjusted to image

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

        // rectangle.translate(xFinal - xStart, yFinal - yStart);  // This method of Rectangle receives ONLY the movement in pixels (= FP - CP)
        image.translate(xFinal - xStart, yFinal - yStart);  // This method of Rectangle receives ONLY the movement in pixels (= FP - CP)
    }
}
