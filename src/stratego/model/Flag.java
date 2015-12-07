package stratego.model;

/**
 * Implements the Flag player token.
 * @author steve
 *
 */
public class Flag extends ImmovablePlayerToken implements Token{
	
	public Flag(Object own){
		super("Flag",own);
	}

}
