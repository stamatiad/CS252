package stratego.model;

public class BeastRider extends MovablePlayerToken implements Token {

	/**
	 * <b>postcondition</b>: Constructs Dragon Player Token
	 * @param own owner of the token
	 */
	public BeastRider(Object own){
		super("BeastRider", own);
		this.rank = 7;
	}
}
