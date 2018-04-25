import java.io.Serializable;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/** 
 * A piece for drawing and holding positions.
 * Extends the ImageView.
 * Implements the Serializable.
 * 
 * @author Chih-Hsi Chang
 * @version 2018
 */
public abstract class Piece extends ImageView implements Serializable {
    /** serial Version UID. */
    private static final long serialVersionUID = 1L;
    
    /** The position of this piece. */
    private Position position;
    /** Square size. */
    private final int squareSize;
    /** Piece size. */
    private final int pieceSize;
    /** Shift for pieces. */
    private final int shift;
    /** Team color. */
    private transient Color team;
    /** String type of team color. */
    private String team_String;
    /** Name of piece. */
    private String name;
    /** Live or not. */
    private boolean live;
    /** Possible Positions. */
    private Position[][] possiblePositions = new Position[8][8];
    /** First move or not. */
    private boolean firstMove = true;
    
    /**
     * Constructs the Piece object.
     * @param image
     *            the image for drawing.
     * @param name
     *            the name of piece.
     * @param xPosition
     *            the position of x.
     * @param yPosition
     *            the position of y.     
     * @param team
     *            the team.     
     * @param squareSize
     *            the size of square.
     * @param pieceSize
     *            the size of piece.     
     * @param shift
     *            for shifting piece.
     */
    Piece(Image image, String name, int xPosition, int yPosition, Color team,
            int squareSize, int pieceSize, int shift) {
        super(image);
        this.squareSize = squareSize;
        this.pieceSize = pieceSize;
        this.shift = shift;
        setFitHeight(pieceSize);
        setFitWidth(pieceSize);
        setX(shift + xPosition * squareSize);
        setY(shift + yPosition * squareSize);
        this.name = name;
        this.position = new Position(xPosition, yPosition);
        this.team = team;
        resetTeam();
        this.live = true;

    }

    /**
     * Checks if the piece is on the current position.
     * @param currentPosition
     *            position for checking.
     * @return boolean
     *            if it is occupied.
     */
    public boolean isOccupied(Position currentPosition) {
        return isVisible() && this.position.equals(currentPosition); 
    }
    
    /**
     * Checks if the piece is on the current position.
     * @param currentXPosition
     *            current position of x.
     * @param currentYPosition
     *            current position of y.
     * @return boolean
     *            if it is occupied.
     */
    /*
    public boolean isOccupied(int currentXPosition, int currentYPosition) {
        return isVisible() && this.position.equals(currentXPosition, currentYPosition); 
    }
    */
    
    /**
     * Gets the position.
     * @return Position
     *            position.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets the position.
     * @param position
     *            the position for setting.
     */
    public void setPosition(Position position) {
        this.position = position;
        firstMove = false;
        super.setX(shift + position.getxPosition() * squareSize);
        super.setY(shift + position.getyPosition() * squareSize);
        possiblePositions();
    }

    /**
     * Gets the team color.
     * @return Color
     *            team color.
     */
    public Color getTeam() {
        resetTeam();
        return team;
    }

    /**
     * Gets if it is alive.
     * @return boolean
     *            if it is alive.
     */
    public boolean isLive() {
        return live;
    }

    /**
     * Sets if it is alive.
     * @param live
     *            alive or not.
     */
    public void setLive(boolean live) {
        this.live = live;
    }
    
    /**
     * Generates legal position.
     * Override by each piece object.
     * 
     */
    public void possiblePositions() {
    }

    /**
     * Gets the possible positions.
     * @return Position[][]
     *            possible Positions.
     */
    public Position[][] getPossiblePositions() {
        return possiblePositions;
    }

    /**
     * Sets all possible positions.
     * @param possiblePositions
     *            possible Positions.
     */
    public void setPossiblePositions(Position[][] possiblePositions) {
        this.possiblePositions = possiblePositions;
    }
    
    /**
     * Sets possible positions.
     * @param possible
     *            possible Position.
     * @param i
     *            number first dimension.
     * @param j
     *            number second dimension.
     */
    public void setPossiblePositions(Position possible, int i, int j) {
        this.possiblePositions[i][j] = possible;
    }

    /**
     * Gets if first move.
     * @return boolean
     *            if it is first move.
     */
    public boolean isFirstMove() {
        return firstMove;
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
     * Get piece image.
     * Override by each type of pieces.
     * @return Image
     *            the Image.
     */
    public abstract Image getPieceImage();
    
    /**
     * Get set piece image.
     * @return Image
     *            the Image.
     */
    public Image getSetPieceImage() {
        getPieceImage();
        setFitHeight(pieceSize);
        setFitWidth(pieceSize);
        setX(shift + position.getxPosition() * squareSize);
        setY(shift + position.getyPosition() * squareSize);
        setVisible(live);
        return getPieceImage();
    }

}
