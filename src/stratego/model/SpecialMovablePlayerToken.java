package stratego.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the special Tokens class  functionality of movable
 * player's tokens.
 * @author steve
 *
 */
public abstract class SpecialMovablePlayerToken extends MovablePlayerToken implements Token {
			
	public SpecialMovablePlayerToken(String name, Object own){
		super(name,own);
	}

	public String specialAttackTo(PlayerToken trg){
		String outcome = null;
		outcome = attackTo(trg);

		return outcome;
	}
}
