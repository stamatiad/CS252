package stratego.model;

/**
 * Implements basic 2D vector class for utilizing elements
 * positioning during game.
 * <b>postcondition</b>: Constructs a 2D vector container.
 * @author stamatiad.st@gmail.com
 *
 */
public class Vector2D {
	
	public int x , y;

	/**
	 * Constructor for the Vector2D object.
	 * @param y The Row/Y value.
	 * @param x The Column/X value.
	 */
	public Vector2D(int y, int x){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get the Column/X value of the vector.
	 * @return The Column/X value.
	 */
	public int x(){
		return this.x;
	}
	
	/**
	 * Get the Row/Y value of the vector.
	 * @return The Row/Y value.
	 */
	public int y(){
		return this.y;
	}
		   
}
