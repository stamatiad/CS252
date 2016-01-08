package stratego.model;

/**
 * Defines the most abstract class for game tokens
 * owned by Players (and not the Board).
 * @author steve
 *
 */
public abstract class PlayerToken implements Token {
	
	public int row;
	public int col;
	public int rank;
	public Object own;
	public String name;
	
	/**
	 * Constructor defining the basic attributes of a
	 * Token owned by a Player (and not the Board).
	 * @param name
	 * @param own
	 */
	public PlayerToken(String name, Object own){
		//this();
		this.name = name;
		this.own = own;
		this.rank = 0;
		this.row = -1;
		this.col = -1;
	}
	
	/**
	 * Set the row of the Token as per Board positioning. Token
	 * can be placed outside of the board. That is interpreted as
	 * a defeated/lost Token in battle.
	 * <b>post-condition</b>: Changes Token's row.
	 * @param row The row to set this Token
	 */
	public void setRow(int row){
		this.row = row;
	}
	
	/**
	 * Set the column of the Token as per Board positioning. Token
	 * can be placed outside of the board. That is interpreted as
	 * a defeated/lost Token in battle.
	 * <b>post-condition</b>: Changes Token's column.
	 * @param col The column to set this Token
	 */
	public void setCol(int col){
		this.col = col;
	}
	
	/**
	 * <b>post-condition</b>: Get the current row of the Token position. 
	 * @return The row as per Board positioning.
	 */
	public int getRow(){
		return this.row;
	}
	
	/**
	 * <b>post-condition</b>: Get the current column of the Token position. 
	 * @return The column as per Board positioning.
	 */
	public int getCol(){
		return this.col;
	}
	
	/**
	 * <b>post-condition</b>: Get the Player that owns this Token.
	 * @return The owner of the Token.
	 */
	public Object getOwn(){
		return this.own;
	}
	
	/**
	 * <b>post-condition</b>: Setting the owner of the Token. 
	 * Useful for converting Tokens
	 * between sides.
	 * @param own the new Player that owns the token.
	 */
	public void setOwn(Player own){
		this.own = own;
	}
	
	/**
	 * <b>post-condition</b>: Get the name of the Token (almost equivalent as a toString
	 * of the Token's class).
	 * @return The name of the Token.
	 */
	public String getName(){
		return this.name;
	}

}
