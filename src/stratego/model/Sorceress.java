package stratego.model;

public class Sorceress extends MovablePlayerToken implements Token {

	/**
	 * <b>postcondition</b>: Constructs Dragon Player Token
	 * @param own owner of the token
	 */
	public Sorceress(Object own){
		super("Sorceress", own);
		this.rank = 6;
	}
}
