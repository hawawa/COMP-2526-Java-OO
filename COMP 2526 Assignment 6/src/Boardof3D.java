import javafx.scene.Group;
import javafx.scene.shape.Line;

/** 
 * A 3D chess board for holding all squares.
 * Extends the Board class.
 * 
 * @author Chih-Hsi Chang
 * @version 2018
 */
public class Boardof3D extends Board {
    /** Board size. */
    static final int BOARDSIZE = 8;
    /** Level size. */
    static final int LEVELSIZE = 3;
    /** Holding all squares. */
    private final Square[][][] allSquare = new Square[BOARDSIZE][BOARDSIZE][LEVELSIZE];
    /** Holding all board borders. */
    private final Line[] allBorders = new Line[LEVELSIZE - 1];
    

    /**
     * Constructs the 3D board object.
     * @param squareSize
     *            the size of the squares.
     */
    public Boardof3D(int squareSize, int boardBorder) {
        super(LEVELSIZE);
        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                for (int k = 0; k < LEVELSIZE; k++) {
                    allSquare[i][j][k] = new Square(squareSize, boardBorder, i, j, k);
                    getChildren().addAll(allSquare[i][j][k]);
                }
            }
        }
        for (int l = 0; l < LEVELSIZE - 1; l++) {
            allBorders[l]= new Line((BOARDSIZE * squareSize + boardBorder) * (l + 1) - (boardBorder + 1) / 2, 0 
                                  , (BOARDSIZE * squareSize + boardBorder) * (l + 1) - (boardBorder + 1) / 2, BOARDSIZE * squareSize);
            allBorders[l].setStrokeWidth(boardBorder);
            getChildren().addAll(allBorders[l]);
        }
        
    }
    
    
}
