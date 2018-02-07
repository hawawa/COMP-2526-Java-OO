import javafx.scene.paint.Color;

/**
 * 
 */

/**
 * @author Chang
 *
 */
public class Player {
    Piece[] allPiece = new Piece[16];
    Color team;
    
    public Player(Color team) {
        this.team = team;
        reSetPieces();
    }
    
    public void reSetPieces(){
        int pawnPosition;
        int otherPosition;
        if(team == Color.BLACK) {
            pawnPosition = 1;
            otherPosition = 0;
        } else {
            pawnPosition = 6;
            otherPosition = 7;
        }

        int i;
        for(i = 0; i < 8; i++) {
            allPiece[i] = new Pawn(i, pawnPosition, team);
        }

        allPiece[i] = new Rook(i - 8, otherPosition, team);
        i++;
        allPiece[i] = new Knight(i - 8, otherPosition, team);
        i++;
        allPiece[i] = new Bishop(i - 8, otherPosition, team);
        i++;
        allPiece[i] = new Queen(i - 8, otherPosition, team);
        i++;
        allPiece[i] = new King(i - 8, otherPosition, team);
        i++;
        allPiece[i] = new Bishop(i - 8, otherPosition, team);
        i++;
        allPiece[i] = new Knight(i - 8, otherPosition, team);
        i++;
        allPiece[i] = new Rook(i - 8, otherPosition, team);
        
        
    }
    
    public boolean isAnyOccupied(int currentXPosition, int currentYPosition) {
        
        for(int i = 0; i < 16; i++) {
            if(allPiece[i].isOccupied(currentXPosition, currentYPosition)) {
                return true;
            }
        }
        return false;
    }
    
    public Piece[] getPiece() {
        return allPiece;
    }
    
}
