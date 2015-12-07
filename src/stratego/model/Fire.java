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
		super("Fire", name);

	}
	
	/*public void initializePlayerTokens(){
		//Create tokens and put them on list:
		tokens.add( new Dragon(this.pname) );
		tokens.add( new Slayer(this.pname) );
		
	}*/
}
