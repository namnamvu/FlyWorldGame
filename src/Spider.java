/**
 * Spider.java
 * A program to create spider in the game and create their movement set
 */
import java.io.IOException;
import javax.imageio.ImageIO;

public class Spider extends Predator{

    protected static final String imgFile = "/img/spider.png";

    /**
     * Creates a new Frog object.<br>
     * The image file for a frog is frog.jpg<br>
     *
     * @param loc a GridLocation
     * @param fw the FlyWorld the spider is in
     */
    public Spider(GridLocation loc, FlyWorld fw)
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

    @Override
    public boolean eatsFly() {
        return this.location == world.getFlyLocation();
    }

    @Override
    public String toString() {
        String s = "Spider in world:  " + this.world + "  at location (" + this.location.getRow() + ", " + this.location.getCol() + ")";
        return s;
    }

    /**
     * This method generates all move that a frog can go to
     * It can go either south, east, north, west but cannot go in
     * the location that another predator has occupied
     * 
     */
    @Override
    public GridLocation[] generateLegalMoves() {
        
        int currRow = location.getRow();
        int currCol = location.getCol();
        int flyRow = world.getFlyLocation().getRow();
        int flyCol  = world.getFlyLocation().getCol();
        GridLocation[] temp = new GridLocation[2];
        int count = 0;

        // Check NORTH
        if (currRow > flyRow && (!(world.world[currRow - 1][currCol].hasPredator()))){
            temp[count++] = world.world[currRow -1][currCol];
        }
        // Check South
        if (currRow < flyRow && (!(world.world[currRow + 1][currCol].hasPredator()))){ 
            temp[count++] = world.world[currRow +1][currCol];
        }
        // Check East
        if (currCol > flyCol && (!(world.world[currRow][currCol - 1].hasPredator()))){
            temp[count++] = world.world[currRow][currCol -1];
        }
        // Check West
        if (currCol < flyCol && (!(world.world[currRow][currCol + 1].hasPredator()))){
            temp[count++] = world.world[currRow][currCol +1];
        }
        
        GridLocation[] legalMove = new GridLocation[count];
        for (int i = 0, k = 0; i < 2; i++){
            if (temp[i] != null){
                legalMove[k++] = temp[i];
            }
        }
        return legalMove;
    }
}
