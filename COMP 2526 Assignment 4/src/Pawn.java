import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/** 
 * A pawn piece.
 * 
 * @author Chih-Hsi Chang
 * @version 2018
 */
public class Pawn extends Piece { 
    /** The name of the piece. */
    static final String NAME = "pawn";
    /** The path of black image. */
    static final String LOCATIONBLACK = File.separator + "image" + File.separator + NAME + "_black.png";
    /**  The path of white image. */
    static final String LOCATIONWHITE = File.separator + "image" + File.separator + NAME + "_white.png";
    /** Image of black. */
    static final Image IMAGEBLACK = new Image(LOCATIONBLACK);
    /** Image of white. */
    static final Image IMAGEWHITE = new Image(LOCATIONWHITE);

    /**
     * Constructs the Pawn object.
     * @param xPosition
     *            the position of x.
     * @param yPosition
     *            the position of y.     
     * @param team
     *            the team.     
     * @param squareSize
     *            the size of square.
     * @param pieceSize
     *            the size of piece.     
     * @param shift
     *            for shifting piece.
     */
    public Pawn(int xPosition, int yPosition, Color team, 
            int squareSize, int pieceSize, int shift) {
        super(team == Color.BLACK ? IMAGEBLACK : IMAGEWHITE, NAME,
                xPosition, yPosition, team, squareSize, pieceSize, shift);
    }
}
