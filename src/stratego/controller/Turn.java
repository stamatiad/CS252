package stratego.controller;

import stratego.model.*;

public class Turn {
	public Player p1,p2;
	public Player activePlayer;
	public Player winner;
	
	public Turn(Player p1, Player p2){
		this.p1 = p1;
		this.p2 = p2;
		this.activePlayer = p1;
		this.winner = null;
	}
	
	public Player side(){
		return this.activePlayer;
	}
	
	public Player otherSide(){
		if( this.activePlayer == this.p1){
			return this.p2;
		}else{
			return this.p1;
		}
	}
	
	public void flip(){
		if( this.activePlayer == this.p1){
			this.activePlayer = this.p2;
		}else{
			this.activePlayer = this.p1;
		}
	}
	
	public void setWinner(Player winner){
		this.winner = winner;
	}
	
	public Player getWinner(){
		return this.winner;
	}
}
