package stratego.model;

/**
 * ImmovablePlayerToken extends PlayerToken.
 * Immovable tokens have limited functionality.
 * @author steve
 *
 */
public abstract class ImmovablePlayerToken extends PlayerToken implements Token {
	
	public ImmovablePlayerToken(String name, Object own){
		super(name, own);
	}

}
