/**
 * FlyWorld.java
 * A program to initiate the world of the game and call function to move the fly/predators
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.awt.Color;

/**
 * Contains information about the world (i.e., the grid of squares)<br>
 * and handles most of the game play work that is NOT GUI specific
 */
public class FlyWorld
{
    protected int numRows;
    protected int numCols;

    protected GridLocation [][] world;

    protected GridLocation start;
    protected GridLocation goal;
    
    protected Fly mosca;
    protected Scanner scan;

    // All places is possible except start and home
    protected Predator[] predators;


    /**
     * Reads a file containing information about<br>
     * the grid setup.  Initializes the grid<br>
     * and other instance variables for use by<br>
     * FlyWorldGUI and other pieces of code.
     *
     *@param fileName the file containing the world grid information
     */
    public FlyWorld(String fileName){
        // Load the world file from the resources
        try {
            // Use the class loader to locate the file inside the .app bundle
            Scanner scan = new Scanner(getClass().getClassLoader().getResourceAsStream(fileName));
            assignValueToWorld(scan);
        } catch (Exception e) {
            System.err.println("File does not exist or could not be loaded: " + fileName);
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    // public FlyWorld(String fileName){
    //     // Try/catch the file
    //     assignValueToWorld(checkFile(fileName));
    // }

    /**
     * Create new GridLocation in every space of 2D arrayList and set value
     * 
     * @param input String from file
     */
    public void assignValueToWorld(Scanner scan){
        String input = scan.nextLine();
        createWorld(input);
        // int countSpider = 0;
        // int countFrog = 0;
        int count = 0;
        for (int i = 0; i< numRows; i++){
            input = scan.nextLine();
            for (int j = 0; j< numCols; j++){
                world[i][j] = new GridLocation(i, j);
                if (input.charAt(j) == 's'){
                    setStartLocation(i, j);
                    mosca = new Fly(world[i][j], this);
                }else if (input.charAt(j) == 'h'){
                    setHomeLocation(i, j);
                }else if (input.charAt(j) == 'f'){
                    // countFrog += 1;
                    // setFrogLocation(i,j,countFrog);
                    predators[count++] = new Frog(world[i][j], this);
                }else if (input.charAt(j) == 'a'){
                    // countSpider += 1;
                    predators[count++] = new Spider(world[i][j], this);
                    // setSpiderLocation(i, j, countSpider);
                }
            }
        }
    }

    /**
     * Set start square and mosca at start
     * 
     * @param i row
     * @param j column
     */
    public void setStartLocation (int i, int j){
        world[i][j].setBackground(Color.GREEN);
        start = world[i][j];
    }
    /**
     * Set home square 
     * 
     * @param i row
     * @param j column
     */
    public void setHomeLocation(int i, int j){
        world[i][j].setBackground(Color.RED);
        goal = world[i][j];
    }
    /**
     * Check if file exist
     * 
     * @param fileName take in fileName
     */
    public Scanner checkFile (String fileName){
        scan = new Scanner(System.in);
        try {
            scan = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist");
            System.exit(1);
        }
        return scan;
    }

    /**
     * Set numRows and numCols
     * 
     * @param input First string from the file
     */
    public void createWorld(String input){
        String[] firstLine =input.split(" ");
        this.numRows = Integer.parseInt(firstLine[0]);
        this.numCols = Integer.parseInt(firstLine[1]);
        world = new GridLocation[this.numRows][this.numCols];
        predators = new Predator[(numRows*numCols) - 2];
    }

    /**
     * @return int, the number of rows in the world
     */
    public int getNumRows(){
        return numRows;
    }

    /** 
     * @return int, the number of columns in the world
     */
    public int getNumCols(){
        return numCols;
    }

    /**
     * Deterimes if a specific row/column location is<br>
     * a valid location in the world (i.e., it is not out of bounds)
     *
     * @param r a row
     * @param c a column
     *
     * @return boolean
     */
    public boolean isValidLoc(int r, int c){
        // FILL IN
        if (r < numRows && r >= 0 && c < numCols && c >= 0){
            return true;
        }
        return false; // THIS LINE IS JUST SO CODE COMPILES
    }

    /**
     * Returns a specific location based on the given row and column
     *
     * @param r the row
     * @param c the column
     *
     * @return GridLocation
     */
    public GridLocation getLocation(int r, int c){
        return world[r][c];
        }

    /**
     * @return FlyWorldLocation, the location of the fly in the world
     */
    public GridLocation getFlyLocation(){
        return mosca.getLocation();
    }

    /**
     * Moves the fly in the given direction (if possible)
     * Checks if the fly got home or was eaten
     *
     * @param direction the direction, N,S,E,W to move
     *
     * @return int, determines the outcome of moving fly<br>
     *              there are three possibilities<br>
     *              1. fly is at home, return ATHOME (defined in FlyWorldGUI)<br>
     *              2. fly is eaten, return EATEN (defined in FlyWorldGUI)<br>
     *              3. fly not at home or eaten, return NOACTION (defined in FlyWorldGUI)
     */
    public int moveFly(int direction){
        // FILL IN
        mosca.update(direction);
        if (mosca.location.equals(goal)){
            return FlyWorldGUI.ATHOME;
        }
        for (Predator p : this.predators){
            if (p != null && p.eatsFly()){
                return FlyWorldGUI.EATEN;
            }   
        }
        return FlyWorldGUI.NOACTION; 
    }

    /**
     * Moves all predators. After it moves a predator, checks if it eats fly
     *
     * @return boolean, return true if any predator eats fly, false otherwise
     */
    public boolean movePredators(){
        // FILL IN
        for (Predator p : predators){
            if (p != null){
                p.update();
                if (p.eatsFly()){
                    return true;
                }
            }
        }
        return false; // THIS LINE IS JUST SO CODE COMPILES
    }
}
