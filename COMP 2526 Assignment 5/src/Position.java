import java.io.Serializable;

/** 
 * For saving both x and y positions.
 * Implements the Serializable.
 * 
 * @author Chih-Hsi Chang
 * @version 2018
 */
public class Position implements Serializable {
    /** serial Version UID. */
    private static final long serialVersionUID = 1L;
    
    /** The position of x. */
    private int xPosition;
    /** The position of y. */
    private int yPosition;
    
    /**
     * Constructs the Position object.
     * @param xPosition
     *            the position of x.
     * @param yPosition
     *            the position of y.     
     */
    public Position(int xPosition, int yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    /**
     * Gets x position.
     * @return int
     *            the x position.
     */
    public int getxPosition() {
        return xPosition;
    }

    /**
     * Sets x position.
     * @param xPosition
     *            the x position for setting.
     */
    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    /**
     * Gets y position.
     * @return int
     *            the y position.
     */
    public int getyPosition() {
        return yPosition;
    }

    /**
     * Sets y position.
     * @param yPosition
     *            the y position for setting.
     */
    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }
    
    /**
     * Moves x position.
     * @param x
     *            the x position for moving.
     */
    public void changexPosition(int x) {
        this.xPosition = this.xPosition + x;
    }
    
    /**
     * Moves y position.
     * @param y
     *            the y position for moving.
     */
    public void changeyPosition(int y) {
        this.yPosition = this.yPosition + y;
    }
    
    /**
     * Check if equals to another position.
     * @param anObject
     *            the Position for checking.
     * @return boolean
     *            if equals.
     */
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (anObject instanceof Position) {
            Position anotherPosition = (Position) anObject;
            if (xPosition == anotherPosition.getxPosition() && yPosition == anotherPosition.getyPosition()) {
                return true;
            }
            
        }
        return false;
    }
    
    /**
     * Check if equals to another position.
     * @param xPosition
     *            the x position.
     * @param yPosition
     *            the y position.
     * @return boolean
     *            if equals.
     */
    public boolean equals(int xPosition, int yPosition) {
        Position aPosition = new Position(xPosition, yPosition);
        return equals(aPosition);
    }

}
