package stratego.model;

/**
 * Implements the Player Ice model
 * @author stamatiad.st@gmail.com
 *
 */
public class Ice extends Player implements PlayerInterface {
	
	/**
	 * <b>post-condition</b>: Constructs the Player Fire in the game
	 * @param name
	 */
	public Ice(String name){
		super("Ice", name);
	}

}
