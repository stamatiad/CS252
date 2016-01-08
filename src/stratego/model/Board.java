package stratego.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Board model, responsible to hold token locations
 * during gameplay (for close interaction with game Viewer).
 * @author stamatiad.st@gmail.com
 *
 */
public class Board {
	private int M, N;
	public final Token[] BoardTokens = new Token[80];
	public Token grassToken;
	public Token rockToken;
	public List<PlayerToken> convertedTokens = new ArrayList<PlayerToken>();
	
	/**
	 * Constructs and initializes an empty game Board.
	 * <b>post-condition</b>: Constructs and initializes an empty game Board.
	 * @param M Board rows (vertical dimension).
	 * @param N Board columns (Horizontal dimension).
	 */
	public Board(int M, int N){
		this.M = M;
		this.N = N;
		
		this.grassToken = new Grass();
		this.rockToken = new Rock();
	}
	
	/**
	 * Return the reference to the token in Board position(r,c).
	 * <b>pre-condition</b>: The position must be inside game Board limits.
	 * <b>post-condition</b>: Return the reference to the token in Board position(r,c).
	 * @param row Row in the Board.
	 * @param col Column in the Board.
	 * @return Reference to token in that Board location.
	 */
	public Token getToken(int row, int col){
		int index = row * this.N + col;
		return this.BoardTokens[index];
	}
	
	/**
	 * <b>post-condition</b>: Get Board number of rows.
	 * @return Board number of rows.
	 */
	public int getM(){
		return this.M;
	}
	
	/**
	 * <b>post-condition</b>: Get Board number of columns.
	 * @return Board number of columns.
	 */
	public int getN(){
		return this.N;
	}
	
	/**
	 * Check if PlayerToken is converted once during the game 
	 * (Sorceress special power).
	 * <b>post-condition</b>: boolean true if input Token is
	 * converted once.
	 * @param tkn The Token to check for conversion.
	 * @return boolean true if input Token is
	 * converted once.
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
