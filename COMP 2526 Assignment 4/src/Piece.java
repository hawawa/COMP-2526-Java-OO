import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * 
 */

/**
 * @author Chang
 *
 */
public class Piece extends ImageView{
    int xPosition;
    int yPosition;
    static final int squareSize = 50;
    static final int pieceSize = 40;
    static final int shift = 5;

    Color team;
    String name;
    boolean live;
    
    Piece(Image image, String name, int xPosition, int yPosition, Color team){
        super(image);
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

    public boolean isOccupied(int currentXPosition, int currentYPosition){
        if(xPosition == currentXPosition && yPosition == currentYPosition) {
            return true;
        }else {
            return false;
        }
        
    }
    
    public Color getColor() {
        return team;
    }

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
        super.setX(shift + xPosition * squareSize);
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
        super.setY(shift + yPosition * squareSize);
    }

    public Color getTeam() {
        return team;
    }

    public void setTeam(Color team) {
        this.team = team;
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

}
