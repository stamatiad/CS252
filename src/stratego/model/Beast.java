package stratego.model;

public class Beast extends MovablePlayerToken implements Token {

	/**
	 * <b>postcondition</b>: Constructs Dragon Player Token
	 * @param own owner of the token
	 */
	public Beast(Object own){
		super("Beast", own);
		this.rank = 5;
	}
}
