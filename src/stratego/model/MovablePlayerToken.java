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
	
	private List<Vector2D> movePattern = new ArrayList<Vector2D>();
			
	public MovablePlayerToken(String name, String own){
		super(name,own);
		/*this.row = -1;
		this.col = -1;
		this.own = "Game";
		this.name = "";*/
		
		this.movePattern.add(new Vector2D(-1,0));
		this.movePattern.add(new Vector2D(1,0));
		this.movePattern.add(new Vector2D(0,-1));
		this.movePattern.add(new Vector2D(0,1));
		
	}
	
	/**
	 * Returns the (relative) movement pattern of the token.
	 * @return Array of token class movement locations (Y,X)
	 */
	public List<Vector2D> getMovePattern(){
		return this.movePattern;
	}
	/*
	public void setOwn(String own){
		this.own = own;
	}
	
	public void setName(String name){
		this.name = name;
	}
	*/
}
