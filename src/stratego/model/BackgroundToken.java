package stratego.model;

/**
 * Defines the abstract class of Tokens owned by the Board
 * (and not the Players).
 * @author steve
 *
 */
public abstract class BackgroundToken implements Token {

	//public int row;
	//public int col;
	public String own;
	public String name;
	
	/*public BackgroundToken(){
		//this.row = -1;
		//this.col = -1;
		this.own = "Board";
		this.name = "Blank";
	}*/
	
	public BackgroundToken(String name){
		this.own = "Board";
		this.name = name;
		//this.row = r;
		//this.col = c;
	}
	/*
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
	*/
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
