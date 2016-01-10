package stratego.controller;

import java.util.Collections;
import java.util.Random;

import junit.framework.*;
import stratego.model.Board;
import stratego.model.Fire;
import stratego.model.Ice;
import stratego.model.Player;
import stratego.view.StrategoAppViewer;

public class JavaTest extends TestCase{

	/**
	 * Setup a custom game environment to test things.
	 */
	protected void setUp(){
		final StrategoAppController game = new StrategoAppController(){

			@Override
			public void start(){
				int M = 12;
				int N = 2;
				//Initialize board:
				StrategoBoard = new Board(M, N);
				//Insert Board's background tokens (Grass):
				for(int i=0 ; i<M*N ; i++){
					int row = i / N;
		            int col = i % N;
		            this.insertToken2Board(StrategoBoard,StrategoBoard.grassToken,row,col);            	
				}

				//Initialize tokens and players:
				Player Fire = new Fire("steve");
				Player Ice = new Ice("john");
				
				//Initialize Turn based system between the players:
				turn = new Turn(Fire, Ice);
				
				//Shuffle and insert players' tokens on board:

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
				final StrategoAppViewer viewport = new StrategoAppViewer(this, StrategoBoard, turn);
				//Render viewport:
				viewport.display();
			}
			
		};
		
		game.start();
	}
}
