package stratego.model;

public class Knight extends MovablePlayerToken implements Token{

	/**
	 * <b>postcondition</b>: Constructs Dragon Player Token
	 * @param own owner of the token
	 */
	public Knight(Object own){
		super("Knight", own);
		this.rank = 8;
	}
}
