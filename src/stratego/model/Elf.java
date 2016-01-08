package stratego.model;

public class Elf extends SpecialMovablePlayerToken implements Token {

	/**
	 * <b>postcondition</b>: Constructs Dragon Player Token
	 * @param own owner of the token
	 */
	public Elf(Object own){
		super("Elf", own);
		this.rank = 4;
	}
	
	/**
	 * Elf special attack with arrows. Can only win lower ranked tokens.
	 * Can not lose with ranged attack.
	 * @param trg target PlayerToken.
	 * @return Outcome of the attack as String.
	 */
	public String specialAttackTo(PlayerToken trg){
		String outcome = null;
		if(trg.rank < this.rank){
			outcome = new String("wonNotMove");
		}else{
			outcome = new String("nothing");
		}

		return outcome;
	}
}
