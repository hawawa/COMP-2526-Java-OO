import javafx.scene.Group;

/** 
 * A classic chess board for holding all squares.
 * Extends the Board class.
 * 
 * @author Chih-Hsi Chang
 * @version 2018
 */
public class BoardofClassic extends Board {
    /** Board size. */
    static final int BOARDSIZE = 8;
    /** Level size. */
    static final int LEVELSIZE = 1;
    /** Holding all squares. */
    private final Square[][] allSquare = new Square[BOARDSIZE][BOARDSIZE];

    /**
     * Constructs the classic board object.
     * @param squareSize
     *            the size of the squares.
     */
    public BoardofClassic(int squareSize) {
        super(LEVELSIZE);
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                allSquare[i][j] = new Square(squareSize, 0, i, j, 0);
            }
        }
        
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                getChildren().addAll(allSquare[i][j]);
            }
        }

    }
   
    
}
