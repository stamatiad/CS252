package stratego.model;

/**
 * Implements the Dragon Player Token
 * @author steve
 *
 */
public class Dragon extends SpecialMovablePlayerToken implements Token{
	
	/**
	 * <b>postcondition</b>: Constructs Dragon Player Token
	 * @param own owner of the token
	 */
	public Dragon(Object own){
		super("Dragon", own);
		this.rank = 10;
	}
}
