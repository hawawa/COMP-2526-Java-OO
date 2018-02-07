import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * 
 */

/**
 * @author Chang
 *
 */
public class Square extends Rectangle{
    static final int squareSize = 50;
    int xPosition;
    int yPosition;
    
    public Square(int xPosition, int yPosition){
        super(xPosition * squareSize, yPosition * squareSize, squareSize, squareSize);
        this.xPosition = xPosition;
        this.xPosition = xPosition;
        if((xPosition + yPosition) % 2 ==0) {
            setFill(Color.GRAY);
        } else {
            setFill(Color.WHITE);
        }
        
        
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

   

    
    
}
