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
    /** serial Version UID. */
    private static final long serialVersionUID = 1L;
    
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
        possiblePositions();
    }
    
    /**
     * Gets piece image.
     * @return Image
     *            the piece image.
     */
    public Image getPieceImage() {
        resetTeam();
        return getTeam() == Color.BLACK ? IMAGEBLACK : IMAGEWHITE;
    }
    
    /**
     * Generates legal position.
     * 
     */
    public void possiblePositions() {
        setPossiblePositions(new Position[8][8]);
        int move = getTeam() == Color.BLACK ? 1 : -1;
        //move 1
        setPossiblePositions(new Position(getPosition().getxPosition(), getPosition().getyPosition() + move), 0, 0);
        
        //move 2
        if (isFirstMove()) {
            setPossiblePositions(new Position(getPosition().getxPosition(), getPosition().getyPosition() + move * 2), 0, 1);
        }
        
        //capture
        if (getPosition().getxPosition() != 7) {
            setPossiblePositions(new Position(getPosition().getxPosition() + 1, getPosition().getyPosition() + move), 1, 0);
        }
        if (getPosition().getxPosition() != 0) {
            setPossiblePositions(new Position(getPosition().getxPosition() - 1, getPosition().getyPosition() + move), 2, 0);
        }

    }
    
}
