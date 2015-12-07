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
	public String own;
	public String name;
	
	/*public PlayerToken(){
		this.row = -1;
		this.col = -1;
		this.own = "Game";
		this.name = "Blank";
	}*/
	
	public PlayerToken(String name, String own){
		//this();
		this.name = name;
		this.own = own;
		this.rank = 0;
		this.row = -1;
		this.col = -1;
	}
	
	public void setRow(int row){
		this.row = row;
	}
	
	public void setCol(int col){
		this.col = col;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getCol(){
		return this.col;
	}
	
	public String getOwn(){
		return this.own;
	}
	/*
	public void setOwn(String own){
		this.own = own;
	}
	*/
	public String getName(){
		return this.name;
	}
	/*
	public void setName(String name){
		this.name = name;
	}
	*/
}
