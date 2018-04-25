import javafx.scene.Group;

/** 
 * A board class for different chess boards.
 * Extends the Board class.
 * 
 * @author Chih-Hsi Chang
 * @version 2018
 */
public class Board extends Group{
    /** Level size. */
    final int LEVELSIZE;
    

    /**
     * Constructs the board object.
     * @param levelSize
     *            the size of levels.
     */
    public Board(int levelSize) {
        this.LEVELSIZE = levelSize;
    }

    /**
     * Gets the level size.
     * @return LEVELSIZE
     *            LEVELSIZE.
     */
    public int getLEVELSIZE() {
        return LEVELSIZE;
    }
    
    
    
    
}
