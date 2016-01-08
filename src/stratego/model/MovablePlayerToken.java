package stratego.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the basic (common to all subclasses) functionality of movable
 * player's tokens.
 * @author steve
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
	 * Returns the (relative) movement pattern of the token.
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
	 * Performs an attack to trg PlayerToken
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
	
	public int getSavesPerformed(){
		return this.savesPerformed;
	}
	
	public boolean savedToken(){
		if(this.savesPerformed<=2){
			this.savesPerformed++;
			return true;
		}
		return false;
	}
}
