package stratego.controller;

import stratego.model.*;

/**
 * Turn is the class that handles the players' sequence.
 * @author stamatiad.st@gmail.com
 */
public class Turn {
	public Player p1,p2;
	public Player activePlayer;
	public Player winner;
	
	/**
	 * Constructor for the Turn controlling module.
	 * <b>pre-condition</b>: Players must be provided. 
	 * <b>post-condition</b>: Constructs the game turn-based
	 * system.
	 * @param p1 The first Player.
	 * @param p2 The second Player.
	 */
	public Turn(Player p1, Player p2){
		this.p1 = p1;
		this.p2 = p2;
		this.activePlayer = p1;
		this.winner = null;
	}
	
	/**
	 * <b>post-condition</b>: Returns the Player that is active.
	 * @return the Player that is currently playing.
	 */
	public Player side(){
		return this.activePlayer;
	}
	
	/**
	 * <b>post-condition</b>: Returns the Player that is not active.
	 * @return the Player that is not currently playing.
	 */
	public Player otherSide(){
		if( this.activePlayer == this.p1){
			return this.p2;
		}else{
			return this.p1;
		}
	}
	
	/**
	 * <b>post-condition</b>: Flips the sequence from Player1 to Player2.
	 */
	public void flip(){
		if( this.activePlayer == this.p1){
			this.activePlayer = this.p2;
		}else{
			this.activePlayer = this.p1;
		}
	}
	
	/**
	 * <b>post-condition</b>: Sets a Player as the winner of the game.
	 * @param winner The Player that won the game.
	 */
	public void setWinner(Player winner){
		this.winner = winner;
	}
	
	/**
	 * <b>post-condition</b>: Gets the Player that is set as a winner:
	 * @return The winner Player.
	 */
	public Player getWinner(){
		return this.winner;
	}
}
