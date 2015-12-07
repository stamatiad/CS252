package stratego.model;

/**
 * Defines abstract class for the Player model
 */
import java.util.ArrayList;
import java.util.List;

public abstract class Player {
	public String name;
	public String pname;
	//public String clan; 
	public List<Token> tokens = new ArrayList<Token>(30);
	
	public Player(String pname, String name){
		this.pname = pname;
		this.name = name;
		//this.clan = clan;
		this.initializePlayerTokens();
	}
	
	public void initializePlayerTokens(){
		//Create tokens and put them on list:
		tokens.add( new Dragon(this) );
		tokens.add( new Mage(this) );
		for (int i=0 ; i<2 ; i++){
			tokens.add( new Knight(this) );
		}
		for (int i=0 ; i<3 ; i++){
			tokens.add( new BeastRider(this) );
		}
		for (int i=0 ; i<2 ; i++){
			tokens.add( new Sorceress(this) );
		}
		for (int i=0 ; i<2 ; i++){
			tokens.add( new Beast(this) );
		}
		for (int i=0 ; i<2 ; i++){
			tokens.add( new Elf(this) );
		}
		for (int i=0 ; i<5 ; i++){
			tokens.add( new Dwarf(this) );
		}
		for (int i=0 ; i<4 ; i++){
			tokens.add( new Scout(this) );
		}
		tokens.add( new Slayer(this) );
		//Immovable player tokens:
		for (int i=0 ; i<6 ; i++){
			tokens.add( new Trap(this) );
		}
		tokens.add( new Flag(this) );
	}	
}
