package stratego.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the basic (common to all subclasses) functionality of movable
 * player's tokens.
 * @author stamatiad.st@gmail.com
 *
 */
public abstract class MovablePlayerToken extends PlayerToken implements Token {
	
	public List<Vector2D> movePattern = new ArrayList<Vector2D>();
	public int savesPerformed;
	
	public MovablePlayerToken(String name, Object own){
		super(name,own);
		
		this.movePattern.add(new Vector2D(-1,0));
		this.movePattern.add(new Vector2D(1,0));
		this.movePattern.add(new Vector2D(0,-1));
		this.movePattern.add(new Vector2D(0,1));
		
		this.savesPerformed = 0;
	}
	
	/**
	 * <b>post-condition</b>: Returns the (relative) movement pattern of the token.
	 * @return Array of token class movement locations (Y,X)
	 */
	public List<Vector2D> getMovePattern(){
		List<Vector2D> loc = new ArrayList<Vector2D>();
		//return absolute board position:
		for(int i=0 ; i<this.movePattern.size(); i++){
			loc.add(new Vector2D(this.getRow()+this.movePattern.get(i).y,this.getCol()+this.movePattern.get(i).x));
		}
		
		return loc;
	}

	/**
	 * <b>post-condition</b>: Performs an attack to trg PlayerToken
	 * @param trg
	 * @return
	 */
	public String attackTo(PlayerToken trg){
		String outcome = null;
		if(trg.rank < this.rank){
			outcome = new String("won");
		}else if(trg.rank > this.rank){
			outcome = new String("lost");
		}else if(trg.rank == this.rank){
			outcome = new String("tie");
		}
		//if attack on Trap, lose:
		if(trg instanceof Trap){
			outcome = new String("lost");
		}
		return outcome;
	}
	
	/**
	 * <b>post-condition</b>: Get Token saves performed
	 * by the Token.
	 * @return Token saves performed by the Token.
	 */
	public int getSavesPerformed(){
		return this.savesPerformed;
	}
	
	/**
	 * Increments Token's save counter. If maximum saves are exceeded,
	 * no increment is taking place and returns false.
	 * <b>pre-condition</b>: Token performed less than twice a save.
	 * <b>post-condition</b>: Token's save counter increments.
	 * @return boolean true if incremented, else false.
	 */
	public boolean savedToken(){
		if(this.savesPerformed<=2){
			this.savesPerformed++;
			return true;
		}
		return false;
	}
}
