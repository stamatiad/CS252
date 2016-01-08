package stratego.model;

/**
 * Defines the abstract class of Tokens owned by the Board
 * (and not the Players).
 * @author stamatiad.st@gmail.com
 *
 */
public abstract class BackgroundToken implements Token {

	public Object own;
	public String name;
	
	/**
	 * Constructor of the Tokens that are handled by the
	 * game controller and belong to the board. PlayerTokens 
	 * can interact, but Players have no control over them.
	 * <b>post-condition</b>: Creates a Token owned by the 
	 * game Board.
	 * @param name The name of the Token.
	 */
	public BackgroundToken(String name){
		this.own = "Board";
		this.name = name;
	}
	
	/**
	 * <b>post-condition</b>: Get the owner of the Token.
	 * @return The owner of the Token (Must be Board).
	 */
	public Object getOwn(){
		return this.own;
	}

	/**
	 * <b>post-condition</b>: Get the name of the Token.
	 * @return The name of the Token.
	 */
	public String getName(){
		return this.name;
	}

}
