package stratego.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import stratego.model.*;
import stratego.view.*;

/**
 * StrategoAppController is the master of the game and controls all 
 * of the operations executed
 * @version 1.0
 * @author stamatiad.st@gmail.com
 */
public class StrategoAppController {
	public Board StrategoBoard;
	public  Turn turn;
	private static final int M = 8;
	private static final int N = 10;
	public List<Vector2D> moveLocations;
	private List<MovablePlayerToken> lostTokensFire = new ArrayList<MovablePlayerToken>();
	
	/**
	 * <b>post-condition</b>: Creates tha main game controller.
	 */
	public StrategoAppController(){
		moveLocations = new ArrayList<Vector2D>();
	}
	/**
	 * <b>post-condition</b>: Initializes the StrategoAppController:
	 * 1. Creates a Board
	 * 2. Creates two players
	 * 3. Creates the Viewer for the game
	 */
	public void start(){
		//Initialize board:
		StrategoBoard = new Board(M, N);
		//Insert Board's background tokens (Grass, Rock):
		//Easy peasy, lemon squeezy:
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
		
		//Initialize Turn based system between the players:
		turn = new Turn(Fire, Ice);
		
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
		final StrategoAppViewer viewport = new StrategoAppViewer(this, StrategoBoard, turn);
		//Render viewport:
		viewport.display();
		
	}
	
	/**
	 * Insert Token reference to Board.
	 * @param b Board Object.
	 * @param t Input Token reference.
	 * @param row Insert position in Board.
	 * @param col Insert position in Board.
	 */
	public void insertToken2Board(Board b, Token t, int row, int col){
		if(t instanceof PlayerToken){
			PlayerToken ct = (PlayerToken)t;
			ct.setRow(row);
			ct.setCol(col);
		}
		int index = row * this.N + col;
		b.BoardTokens[index] = t;
	}
	
	/**
	 * <b>pre-condition</b>: The board to be initialized and players to be existent. 
	 * Get the reference for Board Token in input position.
	 * @param b
	 * @param row
	 * @param col
	 * @return
	 */
	public Token getReference2Token(Board b, int row, int col){
		int index = row * this.N + col;
		return b.BoardTokens[index];
	}
	
	/**
	 * Implements Scout moving model.
	 * @param board
	 * @param sct
	 * @return
	 */
	public List<Vector2D> movementSystemScout(Board board, Scout sct){
		List<Vector2D> sctMovements = new ArrayList<Vector2D>();
		//move in each direction until hitting something:
		int x = sct.getCol();
		int y = sct.getRow();
		//Blocked by:
		//1.rock (non-inclusive)
		//2.same player token (non-inclusive)
		//3.other player token (inclusive)
		//4.Board boundaries (non-inclusive)
		boolean blocked = false;
		//Negative Y:
		int ny = y;
		while(!blocked){
			ny--;
			if(isInsideBoard(new Vector2D(ny,x))){
				Token bt = board.getToken(ny, x);
				if(hitsRock(new Vector2D(ny,x))){
					blocked = true;
				}else if(bt.getOwn() == this.getTurn().side()){
					blocked = true;
				}else if(bt.getOwn() == this.getTurn().otherSide()){
					sctMovements.add(new Vector2D(ny,x));
					blocked = true;
				}else{
					sctMovements.add(new Vector2D(ny,x));
				}
			}else{
				blocked = true;
			}
		}
		blocked = false;
		//Positive Y:
		ny = y;
		while(!blocked){
			ny++;
			if(isInsideBoard(new Vector2D(ny,x))){
				Token bt = board.getToken(ny, x);
				if(hitsRock(new Vector2D(ny,x))){
					blocked = true;
				}else if(bt.getOwn() == this.getTurn().side()){
					blocked = true;
				}else if(bt.getOwn() == this.getTurn().otherSide()){
					sctMovements.add(new Vector2D(ny,x));
					blocked = true;
				}else{
					sctMovements.add(new Vector2D(ny,x));
				}
			}else{
				blocked = true;
			}
		}
		blocked = false;
		//Negative X:
		int nx = x;
		while(!blocked){
			nx--;
			if(isInsideBoard(new Vector2D(y, nx))){
				Token bt = board.getToken(y, nx);
				if(hitsRock(new Vector2D(y, nx))){
					blocked = true;
				}else if(bt.getOwn() == this.getTurn().side()){
					blocked = true;
				}else if(bt.getOwn() == this.getTurn().otherSide()){
					sctMovements.add(new Vector2D(y, nx));
					blocked = true;
				}else{
					sctMovements.add(new Vector2D(y, nx));
				}
			}else{
				blocked = true;
			}
		}
		blocked = false;
		//Positive X:
		nx = x;
		while(!blocked){
			nx++;
			if(isInsideBoard(new Vector2D(y, nx))){
				Token bt = board.getToken(y, nx);
				if(hitsRock(new Vector2D(y, nx))){
					blocked = true;
				}else if(bt.getOwn() == this.getTurn().side()){
					blocked = true;
				}else if(bt.getOwn() == this.getTurn().otherSide()){
					sctMovements.add(new Vector2D(y, nx));
					blocked = true;
				}else{
					sctMovements.add(new Vector2D(y, nx));
				}
			}else{
				blocked = true;
			}
		}
		
		return sctMovements;
	}
	
	/**
	 * Implements Dragon moving/attack model.
	 * @param board
	 * @param drg
	 * @return
	 */
	public List<Vector2D> powerMovementSystemFlight(Board board, Dragon drg){
		List<Vector2D> drgMovements = new ArrayList<Vector2D>();
		//move in each direction skipping first obstacle:
		int x = drg.getCol();
		int y = drg.getRow();

		boolean mustLand = false;
		boolean obstacle = false;
		List<Vector2D> safeLanding = new ArrayList<Vector2D>();
		List<Vector2D> riskyLanding = new ArrayList<Vector2D>();
		List<Vector2D> attackAfterLanding = new ArrayList<Vector2D>();
		//Negative Y:
		int ny = y;
		while(!mustLand){
			ny--;
			if(isInsideBoard(new Vector2D(ny,x))){
				Token bt = board.getToken(ny, x);
				/*if(!isInsideBoard(new Vector2D(ny,x))){
					mustLand = true;
				}else */
				if(hitsRock(new Vector2D(ny,x))){
					riskyLanding.add(new Vector2D(ny,x));
					obstacle = true;
				}else if(bt instanceof PlayerToken){
					riskyLanding.add(new Vector2D(ny,x));
					obstacle = true;
				}else if(obstacle){
					riskyLanding.add(new Vector2D(ny,x));
					safeLanding.addAll(riskyLanding);
					mustLand = true;
				}else{
					//If no better position is found, glide down with pride (like a true dragon):
					safeLanding.add(new Vector2D(ny,x));
				}
			}else{
				//Hit the end of the board:
				mustLand = true;
			}
		}
				
		mustLand = false;
		obstacle = false;
		riskyLanding = new ArrayList<Vector2D>();
		//Positive Y:
		ny = y;
		while(!mustLand){
			ny++;
			if(isInsideBoard(new Vector2D(ny,x))){
				Token bt = board.getToken(ny, x);
				if(hitsRock(new Vector2D(ny,x))){
					riskyLanding.add(new Vector2D(ny,x));
					obstacle = true;
				}else if(bt instanceof PlayerToken){
					riskyLanding.add(new Vector2D(ny,x));
					obstacle = true;
				}else if(obstacle){
					riskyLanding.add(new Vector2D(ny,x));
					safeLanding.addAll(riskyLanding);
					mustLand = true;
				}else{
					safeLanding.add(new Vector2D(ny,x));
				}
			}else{
				//Hit the end of the board:
				mustLand = true;
			}
		}

		mustLand = false;
		obstacle = false;
		riskyLanding = new ArrayList<Vector2D>();
		//Negative X:
		int nx = x;
		while(!mustLand){
			nx--;
			if(isInsideBoard(new Vector2D(y,nx))){
				Token bt = board.getToken(y,nx);
				if(hitsRock(new Vector2D(y,nx))){
					riskyLanding.add(new Vector2D(y,nx));
					obstacle = true;
				}else if(bt instanceof PlayerToken){
					riskyLanding.add(new Vector2D(y,nx));
					obstacle = true;
				}else if(obstacle){
					riskyLanding.add(new Vector2D(y,nx));
					safeLanding.addAll(riskyLanding);
					mustLand = true;
				}else{
					safeLanding.add(new Vector2D(y,nx));
				}
			}else{
				//Hit the end of the board:
				mustLand = true;
			}
		}

		mustLand = false;
		obstacle = false;
		riskyLanding = new ArrayList<Vector2D>();
		//Positive X:
		nx = x;
		while(!mustLand){
			nx++;
			if(isInsideBoard(new Vector2D(y,nx))){
				Token bt = board.getToken(y,nx);
				if(hitsRock(new Vector2D(y,nx))){
					riskyLanding.add(new Vector2D(y,nx));
					obstacle = true;
				}else if(bt instanceof PlayerToken){
					riskyLanding.add(new Vector2D(y,nx));
					obstacle = true;
				}else if(obstacle){
					riskyLanding.add(new Vector2D(y,nx));
					safeLanding.addAll(riskyLanding);
					mustLand = true;
				}else{
					safeLanding.add(new Vector2D(y,nx));
				}
			}else{
				//Hit the end of the board:
				mustLand = true;
			}
		}
		
		//Define additional attack options for each landing position:
		for(int k=0 ; k< safeLanding.size() ; k++){
			attackAfterLanding.addAll(positionsAttackOptions(board,new Vector2D(safeLanding.get(k).y,safeLanding.get(k).x)));
		}
		
		drgMovements.addAll(safeLanding);
		drgMovements.addAll(attackAfterLanding);

		return drgMovements;
	}
	
	/**
	 * Implements Speed special movement/attack model.
	 * @param board
	 * @param mtkn
	 * @return
	 */
	public List<Vector2D> powerMovementSystemSpeed(Board board, MovablePlayerToken mtkn){
		List<Vector2D> movements = new ArrayList<Vector2D>();
		List<Vector2D> extraMovements = new ArrayList<Vector2D>();
		//Make consecutive movements:
		int x = mtkn.getCol();
		int y = mtkn.getRow();
		
		movements.add(new Vector2D(y-1,x));
		movements.add(new Vector2D(y+1,x));
		movements.add(new Vector2D(y,x-1));
		movements.add(new Vector2D(y,x+1));
		
		movements = validInBoard(movements);
		movements = validInPlayer(board, movements);
		
		for (int k=0 ; k<movements.size(); k++){
			if(board.getToken(movements.get(k).y, movements.get(k).x).getOwn()!=this.getTurn().otherSide()){
				extraMovements.add(new Vector2D(movements.get(k).y-1,movements.get(k).x));
				extraMovements.add(new Vector2D(movements.get(k).y+1,movements.get(k).x));
				extraMovements.add(new Vector2D(movements.get(k).y,movements.get(k).x-1));
				extraMovements.add(new Vector2D(movements.get(k).y,movements.get(k).x+1));				
			}
		}
		
		extraMovements = validInBoard(extraMovements);
		extraMovements = validInPlayer(board, extraMovements);
		
		movements.addAll(extraMovements);

		return movements;		
	}
	
	/**
	 * Implements Range special attack model. 
	 * @param board
	 * @param mtkn
	 * @return
	 */
	public List<Vector2D> powerMovementSystemRange(Board board, MovablePlayerToken mtkn){
		List<Vector2D> movements = new ArrayList<Vector2D>();

		int x = mtkn.getCol();
		int y = mtkn.getRow();
		
		for(int i = -2 ; i <=2 ; i++){
			for(int j = -2 ; j <=2 ; j++){
				movements.add(new Vector2D(y+i,x+j));
			}
		}
		
		movements = validInBoard(movements);
		movements = validInPlayer(board, movements);
		
		//Because this system implements ranged attack,
		//valid targets are only enemy tokens:
		movements = validForAttack(board, movements);
		
		return movements;	
	}
	
	/**
	 * <b>pre-condition</b>: The Token to exist in Board.  
	 * Computes valid movement locations for the token in board.
	 * @param board
	 * @param tkn
	 * @return
	 */
	public List<Vector2D> tokenSelection(Board board, Token tkn){
		//List<Vector2D> moveLocations = new ArrayList<Vector2D>();
		this.moveLocations = new ArrayList<Vector2D>();
		//Determine if action is valid (is permitted):
		if(tkn instanceof MovablePlayerToken){
			//Get available positions for movement/attack
			//based on token class:
			MovablePlayerToken mtkn = (MovablePlayerToken)tkn;
			//Special movement pattern for scouts, based on state of the board:
			if(mtkn instanceof Scout){
				this.moveLocations = movementSystemScout(board, (Scout)mtkn);
			}else{
				//Get ABSOLUTE board locations:
				this.moveLocations = mtkn.getMovePattern();
				//Vector2D pos = new Vector2D(mtkn.getRow(),mtkn.getCol());
				//Check what locations are valid on the board:
				this.moveLocations = validInBoard(this.moveLocations);
				this.moveLocations = validInPlayer(board, this.moveLocations);
			}
			

		}
		//else return empty valid move locations:
		return this.moveLocations;
	}
	
	/**
	 * <b>pre-condition</b>: Token to be on board and belong to special Tokens.
	 * Computes valid movement/attack locations for the token in board.
	 * @param board
	 * @param tkn
	 * @return
	 */
	public List<Vector2D> tokenSpecialSelection(Board board, Token tkn){
		this.moveLocations = new ArrayList<Vector2D>();
		//Check if token has special powers:
		boolean isSpecial = false;
		if(tkn instanceof Dragon || tkn instanceof Knight || tkn instanceof Elf || tkn instanceof Sorceress || tkn instanceof BeastRider){
			isSpecial = true;
		}
		if(isSpecial){
			//Get available positions for movement/attack
			//based on token class:
			MovablePlayerToken mtkn = (MovablePlayerToken)tkn;
			//Special movement pattern for each Special Token, based on state of the board:
			if(mtkn instanceof Dragon){
				this.moveLocations = powerMovementSystemFlight(board, (Dragon)mtkn);
			}
			if(mtkn instanceof Knight || mtkn instanceof BeastRider){
				this.moveLocations = powerMovementSystemSpeed(board, mtkn);
			}
			if(mtkn instanceof Elf || mtkn instanceof Sorceress){
				this.moveLocations = powerMovementSystemRange(board, mtkn);
			}

			//Vector2D pos = new Vector2D(mtkn.getRow(),mtkn.getCol());
			//Check what locations are valid on the board:
			this.moveLocations = validInBoard(this.moveLocations);
			this.moveLocations = validInPlayer(board, this.moveLocations);
			
		}
		//else return empty valid move locations:
		return this.moveLocations;
	}
	
	/**
	 * Return true if position falls inside the board bounds. Else returns false.
	 * @param pos The Vector2D of the queried position on board.
	 * @return true if inside the board bounds. Else returns false.
	 */
	public boolean isInsideBoard(Vector2D pos){
		if( pos.x<0 | pos.x>N-1 ){
			return false;
		}
		if( pos.y<0 | pos.y>M-1 ){
			return false;
		}
		return true;
	}
	
	/**
	 * Return true if position falls on top of rocks, on board. Else returns false. Does not
	 * check if position is inside board bounds!
	 * <b>pre-condition</b>: The position must be inside board bounds.
	 * @param pos The Vector2D of the queried position on board.
	 * @return true if position falls on top of rocks. Else returns false.
	 */
	public boolean hitsRock(Vector2D pos){
		if( (2 < pos.y && pos.y < 5) && ((1 < pos.x & pos.x < 4)|(5 < pos.x & pos.x < 8)) ){
			return true;
        }
		return false;
	}
	
	/**
	 * Check if Board position is valid by game terms.
	 * @param loc the query location List (Vector2D).
	 * @return The valid locations only.
	 */
	public List<Vector2D> validInBoard(List<Vector2D> loc){
		//remove locations if they fall outside of the board bounds:
		for (int i=loc.size()-1 ; i>=0 ; i--){
			if(!isInsideBoard(new Vector2D(loc.get(i).y, loc.get(i).x))){
				loc.remove(i);
			}else if(hitsRock(new Vector2D(loc.get(i).y, loc.get(i).x))){
				loc.remove(i);
			}
		}
		
		return loc;
	}
	
	/**
	 * Filter valid movements in order to not conflict with same player Tokens.
	 * @param board
	 * @param loc
	 * @return
	 */
	public List<Vector2D> validInPlayer(Board board, List<Vector2D> loc){
		//remove locations if they fall on same player tokens:
		for (int i=loc.size()-1 ; i>=0 ; i--){
			//Smarter way to check:
			//Not valid location if same player token overlap.
			if(board.getToken(loc.get(i).y,loc.get(i).x).getOwn() == this.getTurn().side()){
				loc.remove(i);
			}
		}
		return loc;
	}
	
	/**
	 * Valid classic (cross-like) attack positions for each position in input list,
	 * that enemy Token exists.
	 * @param board
	 * @param loc
	 * @return
	 */
	public List<Vector2D> validForAttack(Board board, List<Vector2D> loc){
		for (int i=loc.size()-1 ; i>=0 ; i--){
			if(!(board.getToken(loc.get(i).y,loc.get(i).x).getOwn() == this.getTurn().otherSide())){
				loc.remove(i);
			}
		}
		return loc;
	}
	
	/**
	 * Checks if input position has valid attack options:
	 * @param loc query position.
	 * @return List with possible attack options.
	 */
	public List<Vector2D> positionsAttackOptions(Board board, Vector2D pos){
		List<Vector2D> attackPositions = new ArrayList<Vector2D>();
		
		int y = pos.y;
		int x = pos.x;
		
		//Add the clasic attack positions:
		attackPositions.add(new Vector2D(y-1,x));
		attackPositions.add(new Vector2D(y+1,x));
		attackPositions.add(new Vector2D(y,x-1));
		attackPositions.add(new Vector2D(y,x+1));
		
		attackPositions = validInBoard(attackPositions);
		
		for (int k=0;k<attackPositions.size();k++){
			if( !(board.getToken(attackPositions.get(k).y,attackPositions.get(k).x).getOwn() == this.getTurn().otherSide()) ){
				attackPositions.remove(k);
			}			
		}
		
		return attackPositions;
	}
	
	/**
	 * Performs an action from source token to the target token.
	 * Actions can be:
	 * 1. Nothing (action not permitted)
	 * 2. Move (valid movement action)
	 * 3. Attack (valid attack action)
	 * @param src The source token
	 * @param trg The target token
	 * @return The outcome of the action was successful. False: no action whatsoever.
	 */
	public  boolean tokenAction(Board board, Token src, Token trg, Vector2D trgloc,  StrategoAppViewer viewer, boolean specialPower){
		//List<Vector2D> moveLocations = new ArrayList<Vector2D>();
		//Determine if action is valid (is permitted):
		//Token action is already validated by the viewer, but the controller ought to check:
		//(in case viewer is not used):
		boolean found = false;
		//Get available positions for movement/attack:
		if(specialPower){
			this.moveLocations = tokenSpecialSelection(board, src);
		}else{
			this.moveLocations = tokenSelection(board, src);
		}
		
		for(int i=0; i<this.moveLocations.size();i++){
			if((trgloc.y == this.moveLocations.get(i).y)&&(trgloc.x == this.moveLocations.get(i).x)){
				found = true;
				break;
			}
		}
		//if no valid movement/token detected: do not execute action:
		if(!found){return false;}
		//Now that the movement is permitted:
		//Check what token action to perform:
		if(trg instanceof BackgroundToken){
			//Grass is the only move-onto background token:
			this.moveToken(board, (MovablePlayerToken)src, trgloc);
		}else{
			//else this is an attack:
			this.attackToken(board, (MovablePlayerToken)src, (PlayerToken)trg, specialPower);
		}
		
		MovablePlayerToken saviour = null;
		//Check if a token moved in enemy's back row:
		
		for(int k=0 ; k<getTurn().side().tokens.size() ; k++){
			if(getTurn().side().pname == "Fire"){
				if( (getTurn().side().tokens.get(k).getRow() == 0) ){
					saviour = (MovablePlayerToken)getTurn().side().tokens.get(k);
				}
			}else{
				if( (getTurn().side().tokens.get(k).getRow() == 7) ){
					saviour = (MovablePlayerToken)getTurn().side().tokens.get(k);
				}					
			}
		}	
		//Scouts can not save tokens:
		if(saviour instanceof Scout){
			saviour = null;
		}else if(getTurn().side().getTotalSaves() > 2){
			//Can not save more than twice:
			saviour = null;
		}else if( (saviour!=null) && saviour.savedToken()){
			//Can not save more than twice:
			//Get a random Token to save:
			for(int k=0;k<getTurn().side().tokens.size();k++){
				if(getTurn().side().tokens.get(k).getCol() < 0){
					if(getTurn().side().tokens.get(k) instanceof MovablePlayerToken){
						if(saveToken(board, (MovablePlayerToken)getTurn().side().tokens.get(k))){
							break;
						}						
					}
				}
			}
		}
		
		
		
		
		//if player turn completed successfully, check if winning condition:
		getTurn().setWinner(doWeHaveAWinner(board,getTurn()));
		//Else change sides:
		if(getTurn().getWinner()==null){
			getTurn().flip();
		}else{
			endGame(getTurn());
		}
		
		return true;

	}
	
	/**
	 * Saves the lost (input) Token and places it back to the
	 * board in the first empty position. If no empty position
	 * Token is not saved.
	 * @param board
	 * @param tkn
	 * @return
	 */
	public boolean saveToken(Board board, MovablePlayerToken tkn){
		//Check if input Token is indeed killed;
		if(tkn.getCol()>=0){
			return false;
		}
		//Move the selected Token into the board (if possible!):
		if(getTurn().side().pname.equals("Fire")){
			//find the first empty spot:
			for (int k=0;k<30;k++){
				int r = k / 10 + 5;
		        int c = k % 10;
				if(board.getToken(r, c) instanceof Grass){
					this.insertToken2Board(board, tkn, r, c);
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Converts the input PlayerToken to the other side. The particular token can not
	 * be converted back, till the end of the game.
	 * @param board
	 * @param tkn PlayerToken to be converted.
	 */
	public void convertToken(Board board, PlayerToken tkn){
		//Check if PlayerToken is converted previously:
		boolean isconverted = board.isConverted(tkn);
		if(!isconverted){
			//Come to the dark side, we have cookies..
			tkn.setOwn(this.getTurn().side());
			this.getTurn().side().tokens.add(tkn);
			board.convertedTokens.add(tkn);
		}
	}
	
	public void takeToken(Board board, PlayerToken tkn){
		int tmpc = tkn.getCol();
		int tmpr = tkn.getRow();
		//if a token loses in an attack, move it away from board:
		tkn.setCol(-1);
		tkn.setRow(-1);
		//place in its place plain, ol' vanilla grass:
		this.insertToken2Board(board, StrategoBoard.grassToken, tmpr, tmpc);
	}
	
	public void moveToken(Board board, MovablePlayerToken src, Vector2D trgloc){
		//A move happens only between a movablePlayerToken and Grass token:
		
		//Swap tokens:
		int tmpc = src.getCol();
		int tmpr = src.getRow();
		//Update position of source token:
		this.insertToken2Board(board, src, trgloc.y, trgloc.x);
		//fill the space with grass:
		this.insertToken2Board(board, StrategoBoard.grassToken, tmpr, tmpc);
		
	}
	
	public void attackToken(Board board, MovablePlayerToken src, PlayerToken trg, boolean specialPower){
		//An attack happens only between PlayerTokens:
		//perform attack method of source token:
		String outcome = "";
		//No need for an extra attack method, just interpret differently the results.
		if(specialPower && (src instanceof SpecialMovablePlayerToken)){
			SpecialMovablePlayerToken scltkn = (SpecialMovablePlayerToken)src;
			outcome = scltkn.specialAttackTo(trg);
		}else{
			outcome = src.attackTo(trg);
		}
		if (outcome.equals("won")){
			//Save location:
			int tmpc = trg.getCol();
			int tmpr = trg.getRow();
			//Remove token that lost from the board:
			takeToken(board, trg);
			//move token that won to its place:
			this.moveToken(board, src, new Vector2D(tmpr, tmpc));
		}else if(outcome.equals("lost")){
			//Remove token that lost from the board:
			takeToken(board, src);
		}else if(outcome.equals("tie")){
			//remove both tokens from the board:
			takeToken(board, src);
			takeToken(board, trg);
		}else if(outcome.equals("wonNotMove")){
			//Remove token that lost from the board:
			takeToken(board, trg);
		}else if(outcome.equals("nothing")){
			//Nothing happens in the board.
		}else if(outcome.equals("convert")){
			//Target Token is converted to the other side (probably
			//due to cookies)..
			convertToken(board,trg);
		}
	}
	
	/**
	 * Check if next move is impossible. 
	 * <b>pre-condition</b>: Must be called after a tokenAction have been made. 
	 * Consequently check if nonactive player has available moves
	 * @return boolean if true.
	 */
	public boolean isThereNextMovePossible(Board board, Turn turn){
		boolean outcome = true;
		//Check if nonactive Player has any movements available to play:
		Player nonactive = null;
		if(turn.activePlayer == turn.p1){
			nonactive = turn.p2;
		}else{
			nonactive = turn.p1;
		}
		
		for(int i=0;i<nonactive.tokens.size();i++){
			if(tokenSelection(board, nonactive.tokens.get(i)).size() > 0){
				outcome = true;
				break;
			}
		}
		
		return outcome;
	}
	
	/**
	 * Check if dropped flag. 
	 * <b>pre-condition</b>: Must be called after a tokenAction
	 * have been made. 
	 * Consequently check if non-active player lost its Flag.
	 * @return boolean if true.
	 */
	public boolean isThereDropedFlag(Turn turn){
		//
		boolean outcome = false;
		Player nonactive = null;
		if(turn.activePlayer == turn.p1){
			nonactive = turn.p2;
		}else{
			nonactive = turn.p1;
		}
		//Check if previous movement got flag out of the board:
		if( (nonactive.getFlag().getCol()<0) ){
			outcome = true;
		}

		return outcome;
	}
	
	/**
	 * Check if winning condition.
	 * @return the Player that wins, or NULL.
	 */
	public Player doWeHaveAWinner(Board board, Turn turn){
		Player winner = null;
		boolean outcome = false;
		
		if(isThereDropedFlag(turn)){
			//active player wins
			return turn.activePlayer;
		}
		
		if(!isThereNextMovePossible(board, turn)){
			//active player wins
			return turn.activePlayer;			
		}
		
		return winner;
	}
	
	/**
	 * End the Game.
	 */
	public void endGame(Turn turn){
		System.out.println("Player "+turn.getWinner().name+" won!");	
	}
	
	public Turn getTurn(){
		return this.turn;
	}
}

