/**
 * Frog.java
 * A program to create frogs in the game and create their movement set
 */
import java.io.IOException;
import javax.imageio.ImageIO;



/**
 * Handles display, movement, and fly eating capabalities for frogs
 */
public class Frog extends Predator
{
    protected static final String imgFile = "/img/frog.png";
    /**
     * Creates a new Frog object.<br>
     * The image file for a frog is frog.jpg<br>
     *
     * @param loc a GridLocation
     * @param fw the FlyWorld the frog is in
     */
    public Frog(GridLocation loc, FlyWorld fw)
    {
    // FILL IN
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
        location.setPredator(this);
    }

    /**
    * Returns a string representation of this Frog showing
    * the location coordinates and the world.
    *
    * @return the string representation
    */
    public String toString(){
        String s = "Frog in world:  " + this.world + "  at location (" + this.location.getRow() + ", " + this.location.getCol() + ")";
        return s;
    }

    /**
     * This method helps determine if a frog is in a location<br>
     * where it can eat a fly or not. A frog can eat the fly if it<br>
     * is on the same square as the fly or 1 spaces away in<br>
     * one of the cardinal directions
     *
     * @return boolean true if the fly can be eaten, false otherwise
     */ 
    public boolean eatsFly()
    {
        // FILL IN
        if (this.location == world.getFlyLocation()){
            return true;
        }
        for (GridLocation loc : generateLegalMoves()){
            if (loc == world.getFlyLocation()){
                return true;
            }
        }
        return false; // THIS LINE IS JUST SO CODE COMPILES
    }

    /**
     * This method generates all move that a frog can go to
     * It can go either south, east, north, west but cannot go in
     * the location that another predator has occupied
     */
    @Override
    public GridLocation[] generateLegalMoves()
    {
        // FILL IN
        GridLocation[] temp = new GridLocation[4];
        int currRow = location.getRow();
        int currCol = location.getCol();
        int count = 0;

        // Check NORTH
        if (world.isValidLoc(currRow -1, currCol)){
            if (!(world.world[currRow -1][currCol].hasPredator())){
            count += 1;
            temp[0] = world.world[currRow -1][currCol];
            }
        }
        // Check South
        if (world.isValidLoc(currRow +1, currCol)){ 
            if (!(world.world[currRow +1][currCol].hasPredator())){
            count += 1;
            temp[1] = world.world[currRow +1][currCol];
            }
        }
        // Check West
        if (world.isValidLoc(currRow, currCol -1)){
            if (!(world.world[currRow][currCol -1].hasPredator())){
            count += 1;
            temp[2] = world.world[currRow][currCol -1];
            }
        }
        // Check East
        if (world.isValidLoc(currRow, currCol + 1)){
            if (!(world.world[currRow][currCol +1].hasPredator())){
            count += 1;
            temp[3] = world.world[currRow][currCol +1];
            }
        }
        
        GridLocation[] legalMove = new GridLocation[count];
        int k = 0;
        for (int i = 0; i < 4; i++){
            if (temp[i] != null){
                legalMove[k] = temp[i];
                k++;
            }
        }
        return legalMove; // THIS LINE IS JUST SO CODE COMPILES
    }   
}
