import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/** 
 * A square for drawing.
 * Extends the Rectangle class.
 * 
 * @author Chih-Hsi Chang
 * @version 2018
 */
public class Square extends Rectangle {
    
    /**
     * Constructs the square object.
     * @param squareSize
     *            the size of the squares.
     * @param xPosition
     *            the position of x.
     * @param yPosition
     *            the position of y.     
     */
    public Square(int squareSize, int xPosition, int yPosition) {
        super(xPosition * squareSize, yPosition * squareSize, squareSize, squareSize);
        if ((xPosition + yPosition) % 2 == 0) {
            setFill(Color.GRAY);
        } else {
            setFill(Color.WHITE);
        }
        
    }
    
}
