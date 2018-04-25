import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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
    /** Turn. */
    private Turn gameTurn;
    /** Group of all images. */
    private Group root;
    /** All pieces. */
    private Piece[][] allPiece = new Piece[2][PIECENUMBER];
    /** Current held piece. */
    private Piece currentPiece;
    /** Choice position for event. */
    private Position choicePosition;
    /** Legal position for event. */
    private Position[] legalPositions = new Position[64];
    /** Save Button. */
    private Button saveButton;
    /** Load Button. */
    private Button loadButton;
    /** New Button. */
    private Button newButton;
    /** Team color. */
    private Rectangle turnTeam;

    /**
     * Displays a board and pieces for moving.
     * 
     * @param primaryStage
     *            a Stage
     */
    public void start(Stage primaryStage) {
        reSetGame();
        board = new Board(SQUARESIZE);
      
        allPiece[0] = gameTurn.getTwoPlayers()[0].getAllPieces();
        allPiece[1] = gameTurn.getTwoPlayers()[1].getAllPieces(); 
        root = new Group(board, gameTurn.getTwoPlayers()[0], gameTurn.getTwoPlayers()[1]);
        

        //Turn
        Label turn = new Label("Turn:");
        turnTeam = new Rectangle(SQUARESIZE, SQUARESIZE);
        turnTeam.setFill(gameTurn.getCurrentTeam());
        HBox turnPart = new HBox(turn, turnTeam);
        turnPart.setAlignment(Pos.CENTER);
        turnPart.setSpacing(10);
        
        //Save, load
        saveButton = new Button("Save");
        loadButton = new Button("Load");
        //saveButton.setOnAction(this::processColorButton);
        newButton = new Button("New");
        //saveButton.setOnAction(this::processColorButton);
                
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(new ExtensionFilter("Turn files", "*.turn"));
        
        saveButton.setOnAction(e -> {
            File file = chooser.showSaveDialog(primaryStage);
            if (file != null) {
                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                    out.writeObject(gameTurn);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        });
        
        loadButton.setOnAction(e -> {
            File file = chooser.showOpenDialog(primaryStage);
            if (file != null) {
                try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                    //Turn loadedGameTurn = (Turn) in.readObject();
                    
                    gameTurn = (Turn) in.readObject();
                    
                    root.getChildren().remove(1,3);
                    gameTurn.resetImage();
                    root.getChildren().addAll(gameTurn.getTwoPlayers()[0],gameTurn.getTwoPlayers()[1]);
                     
                    turnTeam.setFill(gameTurn.getCurrentTeam());
                    
                    allPiece[0] = gameTurn.getTwoPlayers()[0].getAllPieces();
                    allPiece[1] = gameTurn.getTwoPlayers()[1].getAllPieces(); 
                  
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }

        });
               
                

        HBox controlPart = new HBox(saveButton, loadButton);
        controlPart.setAlignment(Pos.CENTER);
        controlPart.setSpacing(10);
        
        //center
        VBox centerPart = new VBox(turnPart, controlPart);
        centerPart.setAlignment(Pos.CENTER);
        centerPart.setSpacing(10);
        centerPart.setPrefSize(4 * SQUARESIZE, 2 * SQUARESIZE);
        centerPart.setStyle("-fx-border-color: gray;-fx-border-color: black");

        VBox blackTeam = new VBox();
        blackTeam.setPrefSize(2 * SQUARESIZE, 2 * SQUARESIZE);
        blackTeam.setStyle("-fx-background-color: black;-fx-border-color: black");
        VBox whiteTeam = new VBox();
        whiteTeam.setPrefSize(2 * SQUARESIZE, 2 * SQUARESIZE);
        whiteTeam.setStyle("-fx-background-color: white;-fx-border-color: black");

        
        HBox controlBoard = new HBox(blackTeam, centerPart, whiteTeam);

        
        
        
        VBox allObject = new VBox(root, controlBoard);

        
        
        final int appWidth = 400;
        final int appHeight = 500;
        Scene scene = new Scene(allObject, appWidth, appHeight, Color.WHITE);
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
        gameTurn = new Turn();
        gameTurn.getTwoPlayers()[0] = new Player(Color.BLACK, SQUARESIZE, PIECESIZE, SHIFT);
        gameTurn.getTwoPlayers()[1] = new Player(Color.WHITE, SQUARESIZE, PIECESIZE, SHIFT);
        gameTurn.setCurrentTeam(Color.BLACK);
    }
    
    /**
     * Reset the pieces.
     * 
     */
    public void reSetPieces() {
        gameTurn.getTwoPlayers()[0].reSetPieces();
        gameTurn.getTwoPlayers()[1].reSetPieces();
        gameTurn.setCurrentTeam(Color.BLACK);
    }
    
    /**
     * Modifies the position of pieces when user click on the screen.
     * 
     * @param event
     *            invoked this method
     */
    public void processMousePress(MouseEvent event) {
        choicePosition = new Position((int) event.getX() / SQUARESIZE, (int) event.getY() / SQUARESIZE);
        
        int i;
        int ni;
        if (gameTurn.getCurrentTeam() == Color.BLACK) {
            i = 0;
            ni = 1;
        } else {
            i = 1;
            ni = 0;
        }
        
        if (gameTurn.getTwoPlayers()[i].isAnyOccupied(choicePosition)) {
            //choice
            currentPiece = null;
            for (int j = 0; j < PIECENUMBER; j++) {
                if (allPiece[i][j].isOccupied(choicePosition)) {
                    allPiece[i][j].setFitHeight(PIECESIZE + SHIFT);
                    allPiece[i][j].setFitWidth(PIECESIZE + SHIFT);
                    currentPiece = allPiece[i][j];
                } else {
                    allPiece[i][j].setFitHeight(PIECESIZE);
                    allPiece[i][j].setFitWidth(PIECESIZE);
                }
            }
            
            //Generate legal positions
            legalPositions = new Position[64];
            int count = 0;
            for (int m = 0; m < 8 && currentPiece.getPossiblePositions()[m][0] != null; m++) {
                for (int n = 0; n < 8 && currentPiece.getPossiblePositions()[m][n] != null; n++) {
                    if (gameTurn.getTwoPlayers()[i].isAnyOccupied(currentPiece.getPossiblePositions()[m][n])) {
                        break;
                    }
                    legalPositions[count] = currentPiece.getPossiblePositions()[m][n];
                    count++;
                    if (gameTurn.getTwoPlayers()[ni].isAnyOccupied(currentPiece.getPossiblePositions()[m][n])) {
                        break;   
                    }
                }
            }
            
            //Pawn
            if (currentPiece instanceof Pawn) {
                legalPositions = new Position[64];
                count = 0;
                if (currentPiece.getPossiblePositions()[0][0] != null) {
                    if ( !gameTurn.getTwoPlayers()[i].isAnyOccupied(currentPiece.getPossiblePositions()[0][0])
                            && !gameTurn.getTwoPlayers()[ni].isAnyOccupied(currentPiece.getPossiblePositions()[0][0]) ) {
                        legalPositions[count] = currentPiece.getPossiblePositions()[0][0];
                        count++;
                    }
                }
                if (currentPiece.getPossiblePositions()[0][1] != null) {
                    if ( !gameTurn.getTwoPlayers()[i].isAnyOccupied(currentPiece.getPossiblePositions()[0][1])
                            && !gameTurn.getTwoPlayers()[ni].isAnyOccupied(currentPiece.getPossiblePositions()[0][1]) ) {
                        legalPositions[count] = currentPiece.getPossiblePositions()[0][1];
                        count++;
                    }
                }
                
                if (currentPiece.getPossiblePositions()[1][0] != null) {
                    if ( gameTurn.getTwoPlayers()[ni].isAnyOccupied(currentPiece.getPossiblePositions()[1][0]) ) {
                        legalPositions[count] = currentPiece.getPossiblePositions()[1][0];
                        count++;
                    }
                }
                if (currentPiece.getPossiblePositions()[2][0] != null) {
                    if ( gameTurn.getTwoPlayers()[ni].isAnyOccupied(currentPiece.getPossiblePositions()[2][0]) ) {
                        legalPositions[count] = currentPiece.getPossiblePositions()[2][0];
                        count++;
                    }
                }
                
            }
                               
        } else if (currentPiece != null) {
            //if legal
            boolean legal = false;
            for (int p = 0; p < 64 && legalPositions[p] != null; p++) {
                if (choicePosition.equals(legalPositions[p])) {
                    legal = true;
                    break;
                }
            }
            
            if (legal) {
                //move
                currentPiece.setPosition(choicePosition);
                currentPiece.setFitHeight(PIECESIZE);
                currentPiece.setFitWidth(PIECESIZE);
                
                //capture
                for (int j = 0; j < PIECENUMBER; j++) {
                    if (allPiece[ni][j].isOccupied(choicePosition)) {
                        allPiece[ni][j].setVisible(false);
                        allPiece[ni][j].setLive(false);
                    }
                }
                
                //next team
                currentPiece = null;
                if (gameTurn.getCurrentTeam() == Color.BLACK) {
                    gameTurn.setCurrentTeam(Color.WHITE);
                } else {
                    gameTurn.setCurrentTeam(Color.BLACK);
                }
                turnTeam.setFill(gameTurn.getCurrentTeam());
            }
            
        }
        
    }
    


}
