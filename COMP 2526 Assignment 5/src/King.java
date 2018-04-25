import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/** 
 * A king piece.
 * 
 * @author Chih-Hsi Chang
 * @version 2018
 */
public class King extends Piece { 
    
    private static final long serialVersionUID = 1L;
    
    /** The name of the piece. */
    static final String NAME = "king";
    /** The path of black image. */
    static final String LOCATIONBLACK = File.separator + "image" + File.separator + NAME + "_black.png";
    /**  The path of white image. */
    static final String LOCATIONWHITE = File.separator + "image" + File.separator + NAME + "_white.png";
    /** Image of black. */
    static final Image IMAGEBLACK = new Image(LOCATIONBLACK);
    /** Image of white. */
    static final Image IMAGEWHITE = new Image(LOCATIONWHITE);

    /**
     * Constructs the King object.
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
    public King(int xPosition, int yPosition, Color team, 
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
        
        int i = 0;
        int j = 0;
        Position possible = new Position(getPosition().getxPosition(), getPosition().getyPosition());
        for(int x = -1; x <= 1; x++) {
            for(int y = -1; y <= 1; y++) {

                if(x == 0 && y == 0) {
                    continue;
                }
                
                possible = new Position(getPosition().getxPosition(), getPosition().getyPosition());

                possible.changexPosition(x);
                possible.changeyPosition(y);
                if(possible.getxPosition() < 0 || possible.getxPosition() > 7 
                        || possible.getyPosition() < 0 || possible.getyPosition() > 7) {
                    continue;
                }
                setPossiblePositions(new Position(possible.getxPosition(), possible.getyPosition()), i, j);
                i++;


                
            }
        }
        
    }
    
    
}
