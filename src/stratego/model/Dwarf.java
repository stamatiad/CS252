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
	
	/**
	 * Overrides Dwarf's special attack towards Trap. 
	 */
	public String attackTo(PlayerToken trg){
		String outcome = super.attackTo(trg);
		//if attack on Trap, lose:
		if(trg instanceof Trap){
			outcome = new String("won");
		}
		return outcome;
	}
}
