package stratego.model;

public class Elf extends MovablePlayerToken implements Token {

	/**
	 * <b>postcondition</b>: Constructs Dragon Player Token
	 * @param own owner of the token
	 */
	public Elf(Object own){
		super("Elf", own);
		this.rank = 4;
	}
}
