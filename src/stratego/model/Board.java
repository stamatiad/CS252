package stratego.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Board Model, responsible to hold token locations
 * during gameplay (for close interaction with game Viewer).
 * @author steve
 *
 */
public class Board {
	private int M, N;
	//public final List<Token> tokens = new ArrayList<Token>(80);
	public final Token[] BoardTokens = new Token[80];
	public Token grassToken;
	public Token rockToken;
	public List<PlayerToken> convertedTokens = new ArrayList<PlayerToken>();
	
	/**
	 * Constracts and initializes an empty game Board.
	 * <b>postcondition</b>: Constracts and initializes an empty game Board.
	 * @param M
	 * @param N
	 */
	public Board(int M, int N){
		this.M = M;
		this.N = N;
		
		this.grassToken = new Grass();
		this.rockToken = new Rock();
		
		
	}
	
	
	
	/*public void insertBoardToken(Token t, int row, int col){
		if(t instanceof BackgroundToken){
			int index = row * this.N + col;
			this.BoardTokens[index] = t;
		}else{
			//Throw exception:
		}
	}*/
	/**
	 * Return the reference to the token in Board position
	 * (r,c).
	 * @param row
	 * @param col
	 * @return reference to token in that Board location.
	 */
	public Token getToken(int row, int col){
		int index = row * this.N + col;
		//return this.tokens.get(index);
		return this.BoardTokens[index];
	}
	
	public int getM(){
		return this.M;
	}
	
	public int getN(){
		return this.N;
	}
	
	/**
	 * Check if PlayerToken is converted once during the game.
	 * @param tkn
	 */
	public boolean isConverted(PlayerToken tkn){
		boolean output = false;
		for(int k=0 ; k<this.convertedTokens.size() ; k++){
			//Check by reference:
			if(tkn == this.convertedTokens.get(k)){
				output = true;
			}else{
				output = false;
			}
		}
		return output;
	}
}
