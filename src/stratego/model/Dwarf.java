package stratego.model;

public class Dwarf extends MovablePlayerToken implements Token {

	/**
	 * <b>postcondition</b>: Constructs Dragon Player Token
	 * @param own owner of the token
	 */
	public Dwarf(Object own){
		super("Dwarf", own);
		this.rank = 3;
	}
}
