package stratego.model;

public class Mage extends MovablePlayerToken implements Token{

	/**
	 * <b>postcondition</b>: Constructs Dragon Player Token
	 * @param own owner of the token
	 */
	public Mage(Object own){
		super("Mage", own);
		this.rank = 9;
	}
}
