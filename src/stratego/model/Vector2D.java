package stratego.model;

/**
 * Implements basic 2D vector class.
 * <b>postcondition</b>: Constructs a 2D vector container.
 * @author steve
 *
 */
public class Vector2D {
	
	public int x , y;

	public Vector2D(int y, int x){
		this.x = x;
		this.y = y;
	}
	
	public int x(){
		return this.x;
	}
	
	public int y(){
		return this.y;
	}
		   
}
