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
    private static final long serialVersionUID = 2L;
    
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
    public Pawn(int xPosition, int yPosition, int zLevel, Color team, 
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
        int move = getTeam() == Color.BLACK ? 1 : -1;
        
        
        //move 
        if(getPosition().getyPosition() + move <= 7 && getPosition().getyPosition() + move >= 0) {
            //Same level 0
            //move 1
            setPossiblePositions(new Position(getPosition().getxPosition(), getPosition().getyPosition() + move, getPosition().getzLevel()), 0, 0);
            //move 2
            if(isFirstMove()) {
                setPossiblePositions(new Position(getPosition().getxPosition(), getPosition().getyPosition() + move * 2, getPosition().getzLevel()), 0, 1);
            }
            
            //Up and down level 1 2
            if(getPosition().getzLevel() == 0) {
                //move up 1
                setPossiblePositions(new Position(getPosition().getxPosition(), getPosition().getyPosition() + move, getPosition().getzLevel() + 1), 1, 0);
                //move up 2
                if (isFirstMove()) {
                    setPossiblePositions(new Position(getPosition().getxPosition(), getPosition().getyPosition() + move * 2, getPosition().getzLevel() + 2), 1, 1);
                }
            } else if(getPosition().getzLevel() == 1){
                //move up 1
                setPossiblePositions(new Position(getPosition().getxPosition(), getPosition().getyPosition() + move, getPosition().getzLevel() + 1), 1, 0);
                //move down 1
                setPossiblePositions(new Position(getPosition().getxPosition(), getPosition().getyPosition() + move, getPosition().getzLevel() - 1), 2, 0);
            } else {
                //move down 1
                setPossiblePositions(new Position(getPosition().getxPosition(), getPosition().getyPosition() + move, getPosition().getzLevel() - 1), 2, 0);
            }
        }
        
        //Capture
        if(getPosition().getyPosition() + move <= 7 && getPosition().getyPosition() + move >= 0) {
            //Same level 3 4
            //Left 3
            if (getPosition().getxPosition() != 0) {
                setPossiblePositions(new Position(getPosition().getxPosition() - 1, getPosition().getyPosition() + move, getPosition().getzLevel()), 3, 0);
            }
            //Right 4
            if (getPosition().getxPosition() != 7) {
                setPossiblePositions(new Position(getPosition().getxPosition() + 1, getPosition().getyPosition() + move, getPosition().getzLevel()), 4, 0);
            }
            
            //Up and down level 5 6 7 8
            if(getPosition().getzLevel() == 0) {
                //up
                //Left 5
                if (getPosition().getxPosition() != 0) {
                    setPossiblePositions(new Position(getPosition().getxPosition() - 1, getPosition().getyPosition() + move, getPosition().getzLevel() + 1), 5, 0);
                }
                //Right 6
                if (getPosition().getxPosition() != 7) {
                    setPossiblePositions(new Position(getPosition().getxPosition() + 1, getPosition().getyPosition() + move, getPosition().getzLevel() + 1), 6, 0);
                }
            } else if(getPosition().getzLevel() == 1){
                //up
                //Left 5
                if (getPosition().getxPosition() != 0) {
                    setPossiblePositions(new Position(getPosition().getxPosition() - 1, getPosition().getyPosition() + move, getPosition().getzLevel() + 1), 5, 0);
                }
                //Right 6
                if (getPosition().getxPosition() != 7) {
                    setPossiblePositions(new Position(getPosition().getxPosition() + 1, getPosition().getyPosition() + move, getPosition().getzLevel() + 1), 6, 0);
                }
                //Down
                //Left 7
                if (getPosition().getxPosition() != 0) {
                    setPossiblePositions(new Position(getPosition().getxPosition() - 1, getPosition().getyPosition() + move, getPosition().getzLevel() - 1), 7, 0);
                }
                //Right 8
                if (getPosition().getxPosition() != 7) {
                    setPossiblePositions(new Position(getPosition().getxPosition() + 1, getPosition().getyPosition() + move, getPosition().getzLevel() - 1), 8, 0);
                }    
            } else {
                //Down
                //Left 7
                if (getPosition().getxPosition() != 0) {
                    setPossiblePositions(new Position(getPosition().getxPosition() - 1, getPosition().getyPosition() + move, getPosition().getzLevel() - 1), 7, 0);
                }
                //Right 8
                if (getPosition().getxPosition() != 7) {
                    setPossiblePositions(new Position(getPosition().getxPosition() + 1, getPosition().getyPosition() + move, getPosition().getzLevel() - 1), 8, 0);
                }   
            }
        }
        
    }
    
}
