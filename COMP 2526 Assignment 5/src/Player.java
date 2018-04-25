import java.io.Serializable;

import javafx.scene.Group;
import javafx.scene.paint.Color;

/** 
 * A player for holding all pieces.
 * Extends the Group class.
 * Implements the Serializable.
 * 
 * @author Chih-Hsi Chang
 * @version 2018
 */
public class Player extends Group implements Serializable {
    /** serial Version UID. */
    private static final long serialVersionUID = 1L;
    /** The number of pieces. */
    private static final int PIECENUMBER = 16;
    
    /** Team color. */
    private transient Color team;
    /** String type of team color. */
    private String team_String;
    /** Square size. */
    private final int squareSize;
    /** Piece size. */
    private final int pieceSize;
    /** Shift for pieces. */
    private final int shift;
    /** All pieces. */
    private Piece[] allPieces = new Piece[PIECENUMBER];
    
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
        resetTeam();
        this.squareSize = squareSize;
        this.pieceSize = pieceSize;
        this.shift = shift;
        
        reSetPieces();
        resetImage();
        
    }
    
    /**
     * Reset the pieces.
     * 
     */
    public void reSetPieces() {
        int pawnPosition;
        int otherPosition;
        resetTeam();
        if (team == Color.BLACK) {
            pawnPosition = 1;
            otherPosition = 0;
        } else {
            pawnPosition = 6;
            otherPosition = 7;
        }

        int i;
        for (i = 0; i < 8; i++) {
            allPieces[i] = new Pawn(i, pawnPosition, team, squareSize, pieceSize, shift);
        }

        allPieces[i] = new Rook(i - 8, otherPosition, team, squareSize, pieceSize, shift);
        i++;
        allPieces[i] = new Knight(i - 8, otherPosition, team, squareSize, pieceSize, shift);
        i++;
        allPieces[i] = new Bishop(i - 8, otherPosition, team, squareSize, pieceSize, shift);
        i++;
        allPieces[i] = new Queen(i - 8, otherPosition, team, squareSize, pieceSize, shift);
        i++;
        allPieces[i] = new King(i - 8, otherPosition, team, squareSize, pieceSize, shift);
        i++;
        allPieces[i] = new Bishop(i - 8, otherPosition, team, squareSize, pieceSize, shift);
        i++;
        allPieces[i] = new Knight(i - 8, otherPosition, team, squareSize, pieceSize, shift);
        i++;
        allPieces[i] = new Rook(i - 8, otherPosition, team, squareSize, pieceSize, shift);
        
    }
    
    /**
     * Checks if any piece is on the current position.
     * @param currentPosition
     *            current Position.
     * @return boolean
     *            if any piece is occupied.
     */
    public boolean isAnyOccupied(Position currentPosition) {
        
        for (int i = 0; i < PIECENUMBER; i++) {
            if (allPieces[i].isOccupied(currentPosition)) {
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
    public Piece[] getAllPieces() {
        return allPieces;
    }
    
    /**
     * Resets team and team_String for serialization.
     */
    public void resetTeam() {
        if (team == Color.BLACK) {
            this.team_String = "BLACK";
        } else if (team == Color.WHITE) {
            this.team_String = "WHITE";
        } else {
            if (team_String.equals("BLACK")) {
                this.team = Color.BLACK;
            } else {
                this.team = Color.WHITE;
            }
        }
    }
    
    /**
     * Resets images for all pieces when loading.
     */
    public void resetImage() {
        for (int i = 0; i < PIECENUMBER; i++) {
            allPieces[i].setImage(allPieces[i].getSetPieceImage());
            getChildren().addAll(allPieces[i]);
        }
    }

    
    
}
