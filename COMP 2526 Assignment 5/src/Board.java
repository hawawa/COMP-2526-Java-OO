import javafx.scene.Group;

/** 
 * A board for holding all squares.
 * Extends the Group class.
 * 
 * @author Chih-Hsi Chang
 * @version 2018
 */
public class Board extends Group {
    /** Board size. */
    static final int BOARDSIZE = 8;
    /** Holding all squares. */
    private final Square[][] allSquare = new Square[BOARDSIZE][BOARDSIZE];

    /**
     * Constructs the board object.
     * @param squareSize
     *            the size of the squares.
     */
    public Board(int squareSize) {
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                allSquare[i][j] = new Square(squareSize, i, j);
            }
        }
        
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                getChildren().addAll(allSquare[i][j]);
            }
        }
        
        
    }
    
    
}
