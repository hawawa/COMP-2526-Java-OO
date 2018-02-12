import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/** 
 * A piece for drawing and holding positions.
 * 
 * @author Chih-Hsi Chang
 * @version 2018
 */
public class Piece extends ImageView {
    /** The position of x. */
    private int xPosition;
    /** The position of y. */
    private int yPosition;
    /** Square size. */
    private final int squareSize;
    /** Piece size. */
    private final int pieceSize;
    /** Shift for pieces. */
    private final int shift;
    /** Team color. */
    private Color team;
    /** Name of piece. */
    private String name;
    /** Live or not. */
    private boolean live;
    
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
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.team = team;
        this.live = true;

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
    public boolean isOccupied(int currentXPosition, int currentYPosition) {
        return xPosition == currentXPosition && yPosition == currentYPosition; 
    }
    
    /**
     * Gets the x position.
     * @return integer
     *            position of x.
     */
    public int getxPosition() {
        return xPosition;
    }

    /**
     * Sets the position of x.
     * @param xPosition
     *            the position of x.
     */
    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
        super.setX(shift + xPosition * squareSize);
    }

    /**
     * Gets the y position.
     * @return integer
     *            position of y.
     */
    public int getyPosition() {
        return yPosition;
    }

    /**
     * Sets the position of y.
     * @param yPosition
     *            the position of y.
     */
    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
        super.setY(shift + yPosition * squareSize);
    }

    /**
     * Gets the team color.
     * @return Color
     *            team color.
     */
    public Color getTeam() {
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

}
