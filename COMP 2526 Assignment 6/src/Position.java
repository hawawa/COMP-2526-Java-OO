import java.io.Serializable;


/** 
 * For saving both x and y positions.
 * Implements the Serializable.
 * 
 * @author Chih-Hsi Chang
 * @version V2
 */
public class Position implements Serializable {
    /** serial Version UID. */
    private static final long serialVersionUID = 2L;
    
    /** The position of x. */
    private int xPosition;
    /** The position of y. */
    private int yPosition;
    /** The level of z. */
    private int zLevel;
    
    
    /**
     * Constructs the Position object.
     * @param xPosition
     *            the position of x.
     * @param yPosition
     *            the position of y.
     * @param zLevel
     *            the level of z.       
     */
    public Position(int xPosition, int yPosition, int zLevel) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.zLevel = zLevel;
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
     * Gets z level.
     * @return int
     *            the z level.
     */
    public int getzLevel() {
        return zLevel;
    }

    /**
     * Sets z level.
     * @param zLevel
     *            the z level for setting.
     */
    public void setzLevel(int zLevel) {
        this.zLevel = zLevel;
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
     * Moves z level.
     * @param z
     *            the z level for moving.
     */
    public void changezLevel(int z) {
        this.zLevel = this.zLevel + z;
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
            if (xPosition == anotherPosition.getxPosition() 
                    && yPosition == anotherPosition.getyPosition()
                    && zLevel == anotherPosition.getzLevel()
                    ) {
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
     * @param zLevel
     *            the level of z.  
     * @return boolean
     *            if equals.
     */
    public boolean equals(int xPosition, int yPosition, int zLevel) {
        Position aPosition = new Position(xPosition, yPosition, zLevel);
        return equals(aPosition);
    }

}
