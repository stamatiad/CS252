package stratego.model;

/**
 * Implements the Slayer Player Token
 * @author steve
 *
 */
public class Slayer extends MovablePlayerToken implements Token {

	/**
	 * <b>postcondition</b>: Constructs Slayer Player Token
	 * @param own owner of the token
	 */
	public Slayer(Object own){
		super("Slayer", own);
		this.rank = 1;
	}
	
	/**
	 * Overrides Slayer's special attack towards Dragon.
	 */
	public String attackTo(PlayerToken trg){
		String outcome = super.attackTo(trg);
		//if attack on Dragon, win:
		if(trg instanceof Dragon){
			outcome = new String("won");
		}
		return outcome;
	}
}
