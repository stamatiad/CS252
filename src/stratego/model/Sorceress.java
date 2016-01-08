package stratego.model;

public class Sorceress extends SpecialMovablePlayerToken implements Token {

	/**
	 * <b>postcondition</b>: Constructs Dragon Player Token
	 * @param own owner of the token
	 */
	public Sorceress(Object own){
		super("Sorceress", own);
		this.rank = 6;
	}
	
	/**
	 * Sorceress special attack is Conversion. Can only win lower ranked tokens.
	 * Can not lose with Conversion attack.
	 * @param trg target PlayerToken.
	 * @return Outcome of the attack as String.
	 */
	public String specialAttackTo(PlayerToken trg){
		String outcome = null;
		if(trg.rank < this.rank){
			outcome = new String("convert");
		}else{
			outcome = new String("nothing");
		}
		//if attack on Trap/Flag nothing happens:
		if(trg instanceof Trap || trg instanceof Flag){
			outcome = new String("nothing");
		}

		return outcome;
	}
}
