import javafx.scene.Group;
import javafx.scene.paint.Color;

/** 
 * A player for holding all pieces.
 * Extends the Group class.
 * 
 * @author Chih-Hsi Chang
 * @version 2018
 */
public class Player extends Group {
    /** The number of pieces. */
    static final int PIECENUMBER = 16;
    
    /** Team color. */
    private final Color team;
    /** Square size. */
    private final int squareSize;
    /** Piece size. */
    private final int pieceSize;
    /** Shift for pieces. */
    private final int shift;
    /** All pieces. */
    private Piece[] allPiece = new Piece[PIECENUMBER];
    
    /**
     * Constructs the square object.
     * @param team
     *            the size of the squares.
     * @param squareSize
     *             the size of the squares.
     * @param pieceSize
     *            the size of piece.     
     * @param shift
     *            for shifting piece. 
     */
    public Player(Color team, int squareSize, int pieceSize, int shift) {
        this.team = team;
        this.squareSize = squareSize;
        this.pieceSize = pieceSize;
        this.shift = shift;
        reSetPieces();
        
        for (int i = 0; i < PIECENUMBER; i++) {
            getChildren().addAll(allPiece[i]);
        }
        
    }
    
    /**
     * Reset the pieces.
     * 
     */
    public void reSetPieces() {
        int pawnPosition;
        int otherPosition;
        if (team == Color.BLACK) {
            pawnPosition = 1;
            otherPosition = 0;
        } else {
            pawnPosition = 6;
            otherPosition = 7;
        }

        int i;
        for (i = 0; i < 8; i++) {
            allPiece[i] = new Pawn(i, pawnPosition, team, squareSize, pieceSize, shift);
        }

        allPiece[i] = new Rook(i - 8, otherPosition, team, squareSize, pieceSize, shift);
        i++;
        allPiece[i] = new Knight(i - 8, otherPosition, team, squareSize, pieceSize, shift);
        i++;
        allPiece[i] = new Bishop(i - 8, otherPosition, team, squareSize, pieceSize, shift);
        i++;
        allPiece[i] = new Queen(i - 8, otherPosition, team, squareSize, pieceSize, shift);
        i++;
        allPiece[i] = new King(i - 8, otherPosition, team, squareSize, pieceSize, shift);
        i++;
        allPiece[i] = new Bishop(i - 8, otherPosition, team, squareSize, pieceSize, shift);
        i++;
        allPiece[i] = new Knight(i - 8, otherPosition, team, squareSize, pieceSize, shift);
        i++;
        allPiece[i] = new Rook(i - 8, otherPosition, team, squareSize, pieceSize, shift);
        
    }
    
    /**
     * Checks if any piece is on the current position.
     * @param currentXPosition
     *            current position of x.
     * @param currentYPosition
     *            current position of y.
     * @return boolean
     *            if any piece is occupied.
     */
    public boolean isAnyOccupied(int currentXPosition, int currentYPosition) {
        
        for (int i = 0; i < PIECENUMBER; i++) {
            if (allPiece[i].isOccupied(currentXPosition, currentYPosition)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns all pieces.
     * @return Piece[]
     *            all pieces.
     */
    public Piece[] getPiece() {
        return allPiece;
    }
    
}
