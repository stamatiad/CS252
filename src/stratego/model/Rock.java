package stratego.model;
/**
 * Implements the Rock, Board token (where player movement
 * is not allowd).
 * @author steve
 *
 */
public class Rock extends BackgroundToken implements Token{

	public Rock(){
		super("Rock");
	}
	
	/*public Rock(String own, int row, int col){
		super("Rock", own, row, col);
	}
	*/
}
