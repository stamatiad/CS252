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
            this.insertToken2Board(StrategoBoard,StrategoBoard.grassToken,row,col);
		}
		
		/*this.insertToken2Board(this.grassToken,3,0);
		this.insertToken2Board(this.grassToken,3,1);
		this.insertToken2Board(this.grassToken,4,0);
		this.insertToken2Board(this.grassToken,4,1);
		*/
		this.insertToken2Board(StrategoBoard,StrategoBoard.rockToken,3,2);
		this.insertToken2Board(StrategoBoard,StrategoBoard.rockToken,3,3);
		this.insertToken2Board(StrategoBoard,StrategoBoard.rockToken,4,2);
		this.insertToken2Board(StrategoBoard,StrategoBoard.rockToken,4,3);
		/*
		this.insertToken2Board(this.grassToken,3,4);
		this.insertToken2Board(this.grassToken,3,5);
		this.insertToken2Board(this.grassToken,4,4);
		this.insertToken2Board(this.grassToken,4,5);
		*/
		this.insertToken2Board(StrategoBoard,StrategoBoard.rockToken,3,6);
		this.insertToken2Board(StrategoBoard,StrategoBoard.rockToken,3,7);
		this.insertToken2Board(StrategoBoard,StrategoBoard.rockToken,4,6);
		this.insertToken2Board(StrategoBoard,StrategoBoard.rockToken,4,7);
		/*
		this.insertToken2Board(this.grassToken,3,8);
		this.insertToken2Board(this.grassToken,3,9);
		this.insertToken2Board(this.grassToken,4,8);
		this.insertToken2Board(this.grassToken,4,9);
		*/
		
		//Initialize tokens and players:
		Player Fire = new Fire("steve");
		Player Ice = new Ice("john");
		
		//Shuffle and insert players' tokens on board:
		Collections.shuffle(Fire.tokens, new Random(0));
		Collections.shuffle(Ice.tokens, new Random(0));
		

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
	/*public static boolean tokenAction(Board board, Token src, Token trg){
		//Determine if action is valid (is permitted):
		if(src instanceof ImmovableToken){
			return true;
		}
		if(src instanceof MovableToken){
			//Get available positions for movement/attack:
			MovableToken mtkn = (MovableToken)src;
			Vector2D[] movements = mtkn.getMovement();
			
			//Check if action is possible:
			
			
			StrategoAppController.moveToken(board, src, trg);
			return true;
		}
		return false;
	}
	
	public static void moveToken(Board board, Token src, Token trg){
		//Since movement can only be done on a grass token:
		//Swap tokens:
		int tmpc = trg.getCol();
		int tmpr = trg.getRow();
		//Update:
		board.insertToken(trg, src.getRow(), src.getCol());
		board.insertToken(src, tmpr, tmpc);
	}
	public static void attackToken(Board board, Token src, Token trg){
		//
		
	}*/
}
