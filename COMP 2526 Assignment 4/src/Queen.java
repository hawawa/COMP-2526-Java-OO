import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * 
 */

/**
 * @author Chang
 *
 */
public class Queen extends Piece{ 
    final static String name = "queen";
    final static String locationBlack = File.separator + "image" + File.separator + name + "_black.png";
    final static String locationWhite = File.separator + "image" + File.separator + name + "_white.png";
    final static Image imageBlack = new Image(locationBlack);
    final static Image imageWhite = new Image(locationWhite);
    
    public Queen(int xPosition, int yPosition, Color team){
        super(team == Color.BLACK ? imageBlack : imageWhite, name, xPosition, yPosition, team);
    }
}
