/**
* Fly.java
* A program to create fly in the game and create its movement set
*/
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Contains several methods that aid in the<br>
 * display and movement of Mosca
 */
public class Fly
{
    protected static final String imgFile = "/img/fly.png";
    
    protected GridLocation location;

    protected FlyWorld world;

    protected BufferedImage image;

    
    /**
     * Creates a new Fly object.<br>
     * The image file for a fly is fly.jpg<br>
     *
     * @param loc a GridLocation
     * @param fw the FlyWorld the fly is in
     */
    public Fly(GridLocation loc, FlyWorld fw)
    {
        location = loc;
        world = fw;

        try
        {
            image = ImageIO.read(getClass().getResource(imgFile));
        }
        catch (IOException ioe)
        {
            System.out.println("Unable to read image file: " + imgFile);
            System.exit(0);
        }
        location.setFly(this);
    }

    /**
     * @return BufferedImage, the image of the fly
     */
    public BufferedImage getImage()
    {
    return image;
    }

    /**
     * @return GridLocation, the location of the fly
     */
    public GridLocation getLocation()
    {
    return location;
    }

    /**
     * @return boolean, always false, Mosca is not a predator
     */
    public boolean isPredator()
    {
    return false;
    }

    /**
    * Returns a string representation of this Fly showing
    * the location coordinates and the world.
    *
    * @return the string representation
    */
    public String toString(){
        String s = "Fly in world:  " + this.world + "  at location (" + this.location.getRow() + ", " + this.location.getCol() + ")";
        return s;
    }
    
    /**
     * This method <strong>updates</strong> the fly's location in<br>
     * the <strong>world</strong><br>
     * The fly can move in one of the four cardinal (N, S, E, W) directions.<br>
     * You need to make sure that the <strong>updated</strong> location<br>
     * is within the bounds of the world before<br>
     * changing the location of the fly<br>
     *
     * @param direction one of the four cardinal directions<br>
     *        Given as constants in FlyWorldGUI<br><br>
     *        They are:<br>
     *        FlyWorldGUI.NORTH<br>
     *        FlyWorldGUI.SOUTH<br>
     *        FlyWorldGUI.EAST<br>
     *        FlyWorldGUI.WEST<br>
     */
    public void update(int direction)
    {
    // FILL IN
        int currRow = location.getRow();
        int currCol = location.getCol();
        // Move North
        if (direction == FlyWorldGUI.NORTH && currRow - 1 >= 0){
            currRow -= 1;
        // Move South
        }else if (direction == FlyWorldGUI.SOUTH && currRow + 1 < world.getNumRows()){
            currRow += 1;
        // Move East
        }else if (direction == FlyWorldGUI.EAST && currCol + 1 < world.getNumCols()){
            currCol += 1;
        // Move West
        }else if (direction == FlyWorldGUI.WEST && currCol - 1 >= 0){
            currCol -= 1;
        }
        location.removeFly();
        location = world.world[currRow][currCol];
        location.setFly(this);
        
    }
}
