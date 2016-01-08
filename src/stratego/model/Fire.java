package stratego.model;

/**
 * Implements the Player Fire model
 * @author steve
 *
 */
public class Fire extends Player implements PlayerInterface{
	
	/**
	 * <b>post-condition</b>: Constructs the Player Fire in the game.
	 * @param name The name of the Player that plays as Fire.
	 */
	public Fire(String name){
		super("Fire", name);

	}
	
}
