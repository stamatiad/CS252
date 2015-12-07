package stratego.model;

/**
 * Implements the Player Ice model
 * @author steve
 *
 */
public class Ice extends Player implements PlayerInterface {
	
	/**
	 * <b>postcondition</b>: Constructs the Player Fire in the game
	 * @param name
	 */
	public Ice(String name){
		super(name);
	}
	
	public void initializePlayerTokens(){
		//Create tokens and put them on list:
		tokens.add( new Dragon("Ice") );
		tokens.add( new Slayer("Ice") );
		
	}
}