package stratego.model;

public class Scout extends MovablePlayerToken implements Token {

	/**
	 * <b>postcondition</b>: Constructs Dragon Player Token
	 * @param own owner of the token
	 */
	public Scout(Object own){
		super("Scout", own);
		this.rank = 2;
	}
}
