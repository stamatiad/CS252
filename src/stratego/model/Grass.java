package stratego.model;

/**
 * Implements the Grass, Board token (where player movement
 * is allowed).
 * @author steve
 *
 */
public class Grass extends BackgroundToken implements Token{
	
	public Grass(){
		super("Grass");
	}
	
	/*public Grass(int row, int col){
		super("Grass", row, col);
	}*/

}
