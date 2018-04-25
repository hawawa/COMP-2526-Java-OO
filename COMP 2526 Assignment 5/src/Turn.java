import java.io.Serializable;

import javafx.scene.paint.Color;

/** 
 * A turn for saving players and current turn.
 * Implements the Serializable.
 * 
 * @author Chih-Hsi Chang
 * @version 2018
 */
public class Turn implements Serializable {
    /** serial Version UID. */
    private static final long serialVersionUID = 1L;
    
    /** Players. */
    private Player[] twoPlayers = new Player[2];
    /** Current team color. */
    private transient Color currentTeam;
    /** String type of current team color. */
    private String currentTeam_String;
    
    
    /**
     * Returns two players.
     * @return Player[]
     *            all Players.
     */
    public Player[] getTwoPlayers() {
        return twoPlayers;
    }
    
    /**
     * Sets two players.
     * @param twoPlayers
     *            two Players.
     */
    public void setTwoPlayers(Player[] twoPlayers) {
        this.twoPlayers = twoPlayers;
    }
    
    /**
     * Returns current team color.
     * @return Color
     *            current team Color.
     */
    public Color getCurrentTeam() {
        if (this.currentTeam == null) {
            if (currentTeam_String.equals("BLACK")) {
                this.currentTeam = Color.BLACK;
            } else {
                this.currentTeam = Color.WHITE;
            }
        }
        return this.currentTeam;
    }
    
    /**
     * Sets current team color.
     * @param currentTeam
     *            current team Color.
     */
    public void setCurrentTeam(Color currentTeam) {
        if (currentTeam == Color.BLACK) {
            this.currentTeam_String = "BLACK";
        } else {
            this.currentTeam_String = "WHITE";
        }

        this.currentTeam = currentTeam;
    }

    /**
     * Returns string type of current team color.
     * @return String
     *            string type of current team Color.
     */
    public String getCurrentTeam_String() {
        return currentTeam_String;
    }
    
    /**
     * Resets images for each players when loading.
     */
    public void resetImage() {
        for (int i = 0; i < 2; i++) {
            twoPlayers[i].resetImage();
        }
    }

    
    
    
    
}
