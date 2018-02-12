import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/** 
 * An application holding a board, players and a moving control.
 * 
 * @author Chih-Hsi Chang
 * @version 2018
 */
public class Game extends Application {
    /** Square size. */
    static final int SQUARESIZE = 50;
    /** Piece size. */
    static final int PIECESIZE = 40;
    /** Shift for pieces. */
    static final int SHIFT = 5;
    /** The number of pieces. */
    static final int PIECENUMBER = 16;
    
    /** A board. */
    private Board board;
    /** Players. */
    private Player[] twoPlayers = new Player[2];
    /** Group of all images. */
    private Group root;
    /** All pieces. */
    private Piece[][] allPiece = new Piece[2][PIECENUMBER];
    /** Current held piece. */
    private Piece currentPiece;
    /** Current team color. */
    private Color currentTeam;

    /**
     * Displays a board and pieces for moving.
     * 
     * @param primaryStage
     *            a Stage
     */
    public void start(Stage primaryStage) {
        reSetGame();
        board = new Board(SQUARESIZE);
      
        allPiece[0] = twoPlayers[0].getPiece();
        allPiece[1] = twoPlayers[1].getPiece(); 
        
        root = new Group(board, twoPlayers[0], twoPlayers[1]);
        
        final int appWidth = 400;
        final int appHeight = 400;
        Scene scene = new Scene(root, appWidth, appHeight, Color.WHITE);
        scene.setOnMousePressed(this::processMousePress);

        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * Reset the game.
     * 
     */
    public void reSetGame() {
        twoPlayers[0] = new Player(Color.BLACK, SQUARESIZE, PIECESIZE, SHIFT);
        twoPlayers[1] = new Player(Color.WHITE, SQUARESIZE, PIECESIZE, SHIFT);
        currentTeam = Color.BLACK;
    }
    
    /**
     * Reset the pieces.
     * 
     */
    public void reSetPieces() {
        twoPlayers[0].reSetPieces();
        twoPlayers[1].reSetPieces();
        currentTeam = Color.BLACK;
    }
    
    /**
     * Modifies the position of pieces when user click on the screen.
     * 
     * @param event
     *            invoked this method
     */
    public void processMousePress(MouseEvent event) {
        int choiceXPosition = (int) event.getX() / SQUARESIZE;
        int choiceYPosition = (int) event.getY() / SQUARESIZE;

        int i;
        int ni;
        if (currentTeam == Color.BLACK) {
            i = 0;
            ni = 1;
        } else {
            i = 1;
            ni = 0;
        }
        
        if (twoPlayers[i].isAnyOccupied(choiceXPosition, choiceYPosition)) {
            //choice
            currentPiece = null;
            for (int j = 0; j < PIECENUMBER; j++) {
                if (allPiece[i][j].isOccupied(choiceXPosition, choiceYPosition)) {
                    allPiece[i][j].setFitHeight(PIECESIZE + SHIFT);
                    allPiece[i][j].setFitWidth(PIECESIZE + SHIFT);
                    currentPiece = allPiece[i][j];
                } else {
                    allPiece[i][j].setFitHeight(PIECESIZE);
                    allPiece[i][j].setFitWidth(PIECESIZE);
                }
            }
        } else if (currentPiece != null) {
            //move
            currentPiece.setxPosition(choiceXPosition);
            currentPiece.setyPosition(choiceYPosition);
            currentPiece.setFitHeight(PIECESIZE);
            currentPiece.setFitWidth(PIECESIZE);
            
            //eat
            for (int j = 0; j < PIECENUMBER; j++) {
                if (allPiece[ni][j].isOccupied(choiceXPosition, choiceYPosition)) {
                    allPiece[ni][j].setxPosition(100);
                    allPiece[ni][j].setyPosition(100);
                }
            }
            
            //next team
            currentPiece = null;
            if (currentTeam == Color.BLACK) {
                currentTeam = Color.WHITE;
            } else {
                currentTeam = Color.BLACK;
            }
            
        }
        

    }
    

}
