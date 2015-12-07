package stratego.model;

/**
 * Defines abstract class for the Player model
 */
import java.util.ArrayList;
import java.util.List;

public abstract class Player {
	public String name;
	//public String clan; 
	public List<Token> tokens = new ArrayList<Token>(30);
	
	public Player(String name){
		this.name = name;
		//this.clan = clan;
		this.initializePlayerTokens();
	}
	
	public void initializePlayerTokens(){}	
}
