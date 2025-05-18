/**
 * Predator.java
 * An abstact class to provide a common functionality to all predator in the game
 */
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Handles display, movement, and fly eating capabalities for frogs
 */
public abstract class Predator
{
    protected GridLocation location;

    protected FlyWorld world;

    protected BufferedImage image;

    /**
     * @return BufferedImage the image of the frog
     */
    public BufferedImage getImage()
    {
    return image;
    }

    /**
     * @return GridLocation the location of the frog
     */
    public GridLocation getLocation()
    {
    return location;
    }

    /**
     * @return boolean, always true
     */
    public boolean isPredator()
    {
    return true;
    }

    public abstract GridLocation[] generateLegalMoves();

    /**
     * This method updates the frog's position.<br>
     * It should randomly select one of the legal locations(if there any)<br>
     * and set the frog's location to the chosen updated location.
     */
    public void update(){
        GridLocation[] move = generateLegalMoves();
        int rand = new Random().nextInt(move.length);
        if (move.length != 0){
            location.removePredator();
            location = move[rand];
            location.setPredator(this);
        }
    }

    /**
     * This method helps determine if a frog is in a location<br>
     * where it can eat a fly or not. A frog can eat the fly if it<br>
     * is on the same square as the fly or 1 spaces away in<br>
     * one of the cardinal directions
     *
     * @return boolean true if the fly can be eaten, false otherwise
     */ 
    public abstract boolean eatsFly();

    /**
    * Returns a string representation of this Frog showing
    * the location coordinates and the world.
    *
    * @return the string representation
    */
    public abstract String toString();
}
