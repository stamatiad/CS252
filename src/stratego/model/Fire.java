package stratego.model;

/**
 * Implements the Player Fire model
 * @author steve
 *
 */
public class Fire extends Player implements PlayerInterface{
	
	/**
	 * <b>postcondition</b>: Constructs the Player Fire in the game
	 * @param name
	 */
	public Fire(String name){
		super(name);
	}
	
	public void initializePlayerTokens(){
		//Create tokens and put them on list:
		tokens.add( new Dragon("Fire") );
		tokens.add( new Slayer("Fire") );
		
	}
}
