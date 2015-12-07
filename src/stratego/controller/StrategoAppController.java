package stratego.controller;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.*;

import stratego.model.*;
import stratego.view.*;

/**
 * StrategoAppController is the master of the game and controls all 
 * of the operations executed
 * @version 1.0
 * @author stamatiad.st@gmail.com
 */
public class StrategoAppController {
	private Board StrategoBoard;
	private static final int M = 8;
	private static final int N = 10;
	//public Token[] Player1Tokens = new Token[30];
	//public Token[] Player2Tokens = new Token[30];
	//public List<Token> BoardTokens = new ArrayList<Token>;
	
	public StrategoAppController(){
		
	}
	/**
	 * start method initializes the StrategoAppController:
	 * 1. Creates a Board
	 * 2. Creates two players
	 * 3. Creates the Viewer for the game
	 */
	public void start(){
		//Initialize board:
		StrategoBoard = new Board(M, N);
		//Insert Board's background tokens:
		//Easy peasy, lemon squeezy
		for(int i=0 ; i<this.M*this.N ; i++){
			int row = i / this.N;
            int col = i % this.N;
            if( (2 < row && row < 5) && ((1 < col & col < 4)|(5 < col & col < 8)) ){
            	this.insertToken2Board(StrategoBoard,StrategoBoard.rockToken,row,col);
            }else{
            	this.insertToken2Board(StrategoBoard,StrategoBoard.grassToken,row,col);            	
            }
		}

		//Initialize tokens and players:
		Player Fire = new Fire("steve");
		Player Ice = new Ice("john");
		
		//Shuffle and insert players' tokens on board:
		Collections.shuffle(Fire.tokens, new Random(0));
		Collections.shuffle(Ice.tokens, new Random(1));
		
		for (int i = 0 ; i < Fire.tokens.size(); i++){
			int index = M * N - i - 1;
			int row = index / N;
            int col = index % N;
			this.insertToken2Board(StrategoBoard,Fire.tokens.get(i),row,col);
		}
		for (int i = 0 ; i < Ice.tokens.size(); i++){
			int index = i;
			int row = index / N;
            int col = index % N;
			this.insertToken2Board(StrategoBoard,Ice.tokens.get(i),row,col);
		}

		//Create viewport:
		final StrategoAppViewer viewport = new StrategoAppViewer(StrategoBoard);
		viewport.display();
		/*Token t1 = StrategoBoard.getToken(0,0);
		Token t2 = StrategoBoard.getToken(0,1);
		StrategoBoard.insertToken(t2,0,0);
		StrategoBoard.insertToken(t1,0,1);
*/
		
	}
	
	public void insertToken2Board(Board b, Token t, int row, int col){
		if(t instanceof PlayerToken){
			PlayerToken ct = (PlayerToken)t;
			ct.setRow(row);
			ct.setCol(col);
		}
		int index = row * this.N + col;
		b.BoardTokens[index] = t;
	}
	
	public Token getReference2Token(Board b, int row, int col){
		int index = row * this.N + col;
		//return this.tokens.get(index);
		return b.BoardTokens[index];
	}
	public List<Vector2D> tokenSelection(Board board, Token tkn){
		List<Vector2D> moveLocations = new ArrayList<Vector2D>();
		//Determine if action is valid (is permitted):
		if(tkn instanceof MovablePlayerToken){
			//Get available positions for movement/attack
			//based on token class:
			MovablePlayerToken mtkn = (MovablePlayerToken)tkn;
			//Get ABSOLUTE board locations:
			moveLocations = mtkn.getMovePattern();
			
			//Vector2D pos = new Vector2D(mtkn.getRow(),mtkn.getCol());
			//Check what locations are valid on the board:
			moveLocations = insideBoard(moveLocations);

		}
		//else return empty valid move locations:
		return moveLocations;
	}
	
	public List<Vector2D> insideBoard(List<Vector2D> loc){
		//remove locations if they fall outside of the board bounds:
		for (int i=loc.size()-1 ; i>=0 ; i--){
			//int rsltx = pos.x + loc.get(i).x;
			//int rslty = pos.y + loc.get(i).y;
			if( loc.get(i).x<0 | loc.get(i).x>N ){
				loc.remove(i);
			}
			if( loc.get(i).y<0 | loc.get(i).y>M ){
				loc.remove(i);
			}
		}
		return loc;
	}
	/**
	 * Performs an action fron source token to the target token.
	 * Actions can be:
	 * 1. Nothing (action not permited)
	 * 2. Move (valid movement action)
	 * 3. Attack (valid attack action)
	 * @param src The source token
	 * @param trg The target token
	 * @return The outcome of the action was successful. False: no action whatsoever.
	 */
	public  boolean tokenAction(Board board, Token src, Token trg, StrategoAppViewer viewer){
		List<Vector2D> moveLocations = new ArrayList<Vector2D>();
		//Determine if action is valid (is permitted):
		moveLocations = tokenSelection(board, src);
		
		if(moveLocations.size()>0){
			//Get available positions for movement/attack:
			MovablePlayerToken msrc = (MovablePlayerToken)src;
			MovablePlayerToken mtrg = (MovablePlayerToken)trg;
			
			//Check what token action to perform:
			//this.moveToken(board, msrc, mtrg);
			
			//Since movement can only be done on a movable token:
			//We trust controller that is a movable token:

			return true;
		}
		return false;
	}
	
	public void moveToken(Board board, MovablePlayerToken src, MovablePlayerToken trg){
		//Swap tokens:
		int tmpc = trg.getCol();
		int tmpr = trg.getRow();
		//Update:
		this.insertToken2Board(board, trg, src.getRow(), src.getCol());
		this.insertToken2Board(board,src, tmpr, tmpc);
	}
	public void attackToken(Board board, MovablePlayerToken src, PlayerToken trg){
		//
		
	}
}

