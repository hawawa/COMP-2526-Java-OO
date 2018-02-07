
/**
 * 
 */

/**
 * @author Chang
 *
 */
public class Board {
    final int boardSize = 8;
    final Square allSquare[][] = new Square[boardSize][boardSize];
    Player blackPlayer;
    Player whitePlayer;

    
    public Board(Player blackPlayer, Player whitePlayer) {
        
        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                allSquare[i][j] = new Square(i, j);
            }
        }
        
        this.blackPlayer = blackPlayer;
        this.whitePlayer = whitePlayer;
        reSet();
        
    }
    
    public void reSet() {
        blackPlayer.reSetPieces();
        whitePlayer.reSetPieces();
    }
    

    public Square[][] getSquare(){
        return allSquare;
    }

    public int getBoardSize() {
        return boardSize;
    }
    
}
