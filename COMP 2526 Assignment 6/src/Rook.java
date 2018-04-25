import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/** 
 * A rook piece.
 * 
 * @author Chih-Hsi Chang
 * @version 2018
 */
public class Rook extends Piece { 
    
    private static final long serialVersionUID = 2L;
    
    /** The name of the piece. */
    static final String NAME = "rook";
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
    public Rook(int xPosition, int yPosition, int zLevel, Color team, 
            int squareSize, int pieceSize, int shift, int boardBorder) {
        super(team == Color.BLACK ? IMAGEBLACK : IMAGEWHITE, NAME,
                xPosition, yPosition, zLevel, team, squareSize, pieceSize, shift, boardBorder);
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
        resetPossiblePositions();
        
        int i = 0;
        int j = 0;
        Position possible;
        
        for(int x = -1; x <= 1; x++) {
            for(int y = -1; y <= 1; y++) {
                for(int z = -1; z <= 1; z++) {
                    
                    if((x == 0 && y == 0 & z == 0) || (x != 0 && y != 0 ) || (y != 0 && z != 0 ) || (x != 0 && z != 0 )) {
                        continue;
                    }
                    possible = new Position(getPosition().getxPosition(), getPosition().getyPosition(), getPosition().getzLevel());

                    for(;;) {
                        possible.changexPosition(x);
                        possible.changeyPosition(y);
                        possible.changezLevel(z);
                        if(possible.getxPosition() < 0 || possible.getxPosition() > 7 
                                || possible.getyPosition() < 0 || possible.getyPosition() > 7
                                || possible.getzLevel() < 0 || possible.getzLevel() > 2
                                ) {
                            if(j != 0) {
                                i++;
                                j = 0;
                            }
                            break;
                        }
                        setPossiblePositions(new Position(possible.getxPosition(), possible.getyPosition(), possible.getzLevel()), i, j);
                        j++;
                    }
                    
                }
            }
        }
        
    }
    
    
}
