package stratego.model;

/**
 * ImmovablePlayerToken extends PlayerToken.
 * Immovable tokens have limited functionality.
 * @author steve
 *
 */
public abstract class ImmovablePlayerToken extends PlayerToken implements Token {
	
	/*public ImmovableToken(){
		super();
	}*/
	
	public ImmovablePlayerToken(String name, String own){
		super(name, own);
	}
	
	
	/*public int row;
	public int col;
	public String own;
	public String name;
	
	public ImmovableToken(){
		this.row = -1;
		this.col = -1;
		this.own = "Game";
		this.name = "";
	}
	
	public ImmovableToken(String name, String own, int r, int c){
		this();
		this.name = name;
		this.own = own;
		this.row = r;
		this.col = c;
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
	
	public void setOwn(String own){
		this.own = own;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	 */
}
