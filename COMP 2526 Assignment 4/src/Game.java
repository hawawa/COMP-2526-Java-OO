import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/** 
 * An application holding board, players and moving control.
 * 
 * @author Chih-Hsi Chang
 * @version 2017
 */
public class Game extends Application {
    /** Square size. */
    static final int squareSize = 50;
    static final int pieceSize = 40;
    static final int shift = 5;
    
    Board board;
    Player twoPlayers[] = new Player[2];
    Group pieceG;
    Group squareG;
    Group root;

    Piece allPiece[][] = new Piece[2][16];
    Piece currentPiece;
    Color currentTeam;

    /**
     * Read name from user.
     * @param person
     *            the person for displaying.
     */
    public void start(Stage primaryStage) {
        reSet();
        board = new Board(twoPlayers[0], twoPlayers[1]);

        squareG = drawSquare();
        
        allPiece[0] = twoPlayers[0].getPiece();
        allPiece[1] = twoPlayers[1].getPiece(); 

        Group pieceG = new Group();
        
        for(int i = 0; i < 2; i++) {
            for(int j = 0; j < 16; j++) {
                pieceG.getChildren().addAll(allPiece[i][j]);
            }
        }


        
        
        
        
        
        root = new Group(squareG, pieceG);
        
        
        final int appWidth = 400;
        final int appHeight = 400;
        Scene scene = new Scene(root, appWidth, appHeight, Color.WHITE);
        scene.setOnMousePressed(this::processMousePress);

        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    private Group drawSquare() {
        int boardSize = board.getBoardSize();
        Group squareG = new Group();
        for(int i = 0; i < boardSize ;i++) {
            for(int j = 0; j < boardSize ;j++) {
                squareG.getChildren().addAll(board.getSquare()[i][j]);
            }
        }
        return squareG;
        
    }

    
    public void reSet() {
        twoPlayers[0] = new Player(Color.BLACK);
        twoPlayers[1] = new Player(Color.WHITE);
        currentTeam = Color.BLACK;
    }
    
    public void processMousePress(MouseEvent event) {
        int choiceXPosition = (int)event.getX() / squareSize;
        int choiceYPosition = (int)event.getY() / squareSize;

        int i;
        int ni;
        if(currentTeam == Color.BLACK) {
            i = 0;
            ni = 1;
        } else {
            i = 1;
            ni = 0;
        }
        
        if (twoPlayers[i].isAnyOccupied(choiceXPosition, choiceYPosition)) {
            currentPiece = null;
            for(int j = 0; j < 16; j++) {
                if(allPiece[i][j].isOccupied(choiceXPosition, choiceYPosition)) {
                    allPiece[i][j].setFitHeight(pieceSize + 5);
                    allPiece[i][j].setFitWidth(pieceSize + 5);
                    currentPiece = allPiece[i][j];
                } else {
                    allPiece[i][j].setFitHeight(pieceSize);
                    allPiece[i][j].setFitWidth(pieceSize);
                }
            }
        } else if(currentPiece != null) {
            currentPiece.setxPosition(choiceXPosition);
            currentPiece.setyPosition(choiceYPosition);
            currentPiece.setFitHeight(pieceSize);
            currentPiece.setFitWidth(pieceSize);
            
            for(int j = 0; j < 16; j++) {
                if(allPiece[ni][j].isOccupied(choiceXPosition, choiceYPosition)) {
                    allPiece[ni][j].setxPosition(100);
                    allPiece[ni][j].setyPosition(100);
                }
            }
            
            
            currentPiece = null;
            if(currentTeam == Color.BLACK) {
                currentTeam = Color.WHITE;
            } else {
                currentTeam = Color.BLACK;
            }
            
        }
        

    }
    

}
