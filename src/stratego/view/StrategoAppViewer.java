package stratego.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;

import stratego.controller.*;
import stratego.model.*;

/**
 * StrategoAppViewer handles all the viewing aspects of the game.
 * @author stamatiad.st@gmail.com
 */
public class StrategoAppViewer {
	//private JPanel panel;
	private StrategoAppController controller;
	public Turn turn;
	private int M, N;
	public Board board;
	private final JButton[][] buttons = new JButton[8][10];
	private HashMap iconsBuffer = new HashMap(39);
	private int selectedToken;
	private boolean selectedSpecialPower;
	public JFrame viewFrame;
	private List<Vector2D> highlighter = new ArrayList<Vector2D>();
	private List<MovablePlayerToken> lostTokensFire = new ArrayList<MovablePlayerToken>();
	private List<MovablePlayerToken> lostTokensIce = new ArrayList<MovablePlayerToken>();
	private final JButton[][] buttonsLostTokensFire = new JButton[8][3];
	private final JButton[][] buttonsLostTokensIce = new JButton[8][3];
	
	/**
	 * <b>pre-condition</b>: A valid game StrategoAppController and Board
	 * must be provided.
	 * <b>post-condition</b>: Creates and initializes the
	 * Viewer for the game.
	 * @param controller The Controller of the game.
	 * @param board The Board of the game.
	 * @param turn The Turn controlling module of the game.
	 */
	public StrategoAppViewer(StrategoAppController controller, Board board, Turn turn){
		this.controller = controller;
		this.board = board;
		this.turn = turn;
		this.M = board.getM();
		this.N = board.getN();
		this.selectedToken = -1;
		
		//Preload token Icons:
		//Names sorted with ranking:
		iconsBuffer.put("rDragon.png", getIconImage("rDragon.png"));
		iconsBuffer.put("bDragon.png", getIconImage("bDragon.png"));
		iconsBuffer.put("rMage.png", getIconImage("rMage.png"));
		iconsBuffer.put("bMage.png", getIconImage("bMage.png"));
		iconsBuffer.put("rKnight.png", getIconImage("rKnight.png"));
		iconsBuffer.put("bKnight.png", getIconImage("bKnight.png"));
		iconsBuffer.put("rBeastRider.png", getIconImage("rBeastRider.png"));
		iconsBuffer.put("bBeastRider.png", getIconImage("bBeastRider.png"));
		iconsBuffer.put("rSorceress.png", getIconImage("rSorceress.png"));
		iconsBuffer.put("bSorceress.png", getIconImage("bSorceress.png"));
		iconsBuffer.put("rBeast.png", getIconImage("rBeast.png"));
		iconsBuffer.put("bBeast.png", getIconImage("bBeast.png"));
		iconsBuffer.put("rElf.png", getIconImage("rElf.png"));
		iconsBuffer.put("bElf.png", getIconImage("bElf.png"));
		iconsBuffer.put("rDwarf.png", getIconImage("rDwarf.png"));
		iconsBuffer.put("bDwarf.png", getIconImage("bDwarf.png"));
		iconsBuffer.put("rScout.png", getIconImage("rScout.png"));
		iconsBuffer.put("bScout.png", getIconImage("bScout.png"));
		iconsBuffer.put("rSlayer.png", getIconImage("rSlayer.png"));
		iconsBuffer.put("bSlayer.png", getIconImage("bSlayer.png"));
		iconsBuffer.put("rTrap.png", getIconImage("rTrap.png"));
		iconsBuffer.put("bTrap.png", getIconImage("bTrap.png"));
		iconsBuffer.put("rFlag.png", getIconImage("rFlag.png"));
		iconsBuffer.put("bFlag.png", getIconImage("bFlag.png"));
		iconsBuffer.put("rHidden.png", getIconImage("rHidden.png"));
		iconsBuffer.put("bHidden.png", getIconImage("bHidden.png"));
		iconsBuffer.put("Grass.png", getIconImage("Grass.png"));
		iconsBuffer.put("Rock.png", getIconImage("Rock.png"));
		
		//Special Icons
		iconsBuffer.put("srDragon.png", getIconImage("srDragon.png"));
		iconsBuffer.put("sbDragon.png", getIconImage("sbDragon.png"));
		iconsBuffer.put("srKnight.png", getIconImage("srKnight.png"));
		iconsBuffer.put("sbKnight.png", getIconImage("sbKnight.png"));
		iconsBuffer.put("srBeastRider.png", getIconImage("srBeastRider.png"));
		iconsBuffer.put("sbBeastRider.png", getIconImage("sbBeastRider.png"));
		iconsBuffer.put("srSorceress.png", getIconImage("srSorceress.png"));
		iconsBuffer.put("sbSorceress.png", getIconImage("sbSorceress.png"));
		iconsBuffer.put("srElf.png", getIconImage("srElf.png"));
		iconsBuffer.put("sbElf.png", getIconImage("sbElf.png"));
		
		//Empty background Token:
		iconsBuffer.put("Token.png", getIconImage("Token.png"));


	}

	/**
	 * Fetch requested button reference from the playing board.
	 * @param r Row of requested button in Board.
	 * @param c Column of requested button in Board.
	 * @return
	 */
	private JButton getGridButton(int r, int c) {
        //return this.buttons.get(index);
		return this.buttons[r][c];
    }
	
	/**
	 * Creates a Token button in the playing Board.
	 * @param row Row of button in Board.
	 * @param col Column of button in Board.
	 * @return
	 */
	private JButton createGridButton(final int row, final int col) {
        final JButton b = new JButton();
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton gb = StrategoAppViewer.this.getGridButton(row, col);
                StrategoAppViewer.this.selectToken(row, col);
                System.out.println("r" + row + ",c" + col
                    + " " + (b == gb)
                    + " " + (b.equals(gb)));
            }
        });
        return b;
	}
	
	/**
	 * Create the panel for the game Board.
	 * @return the JPanel reflecting the Board.
	 */
	private JPanel createGridPanel() {
        JPanel p = new JPanel(new GridLayout(this.M, this.N));
        //Initialize icons in buttons:
        for (int i = 0; i < this.M * this.N; i++) {
            int row = i / this.N;
            int col = i % this.N;
            JButton gb = createGridButton(row, col);
            gb.setPreferredSize(new Dimension(92, 80));
            this.buttons[row][col] = gb;
            p.add(gb);
        }
        return p;
    }
	
	/**
	 * Create button for the lost Tokens in the battlefield.
	 * @param row Row of button in Board.
	 * @param col Column of button in Board.
	 * @return the JButton.
	 */
	private JButton createRestoreGridButton(final int row, final int col) {
        final JButton b = new JButton();
        b.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton gb = StrategoAppViewer.this.getGridButton(row, col);
                //Select Lost Token:
                //StrategoAppViewer.this.selectLostToken(row, col);
                System.out.println("r" + row + ",c" + col
                    + " " + (b == gb)
                    + " " + (b.equals(gb)));
            }
        });
        return b;
	}
	
	/**
	 * Creates the panel holding the lost Tokens of the game.
	 * @param name
	 * @return the JPanel.
	 */
	private JPanel createScoreGridPanel(String name) {
		int M = 8;
		int N = 3;
        JPanel p = new JPanel(new GridLayout(M,N));
        //Initialize icons in buttons:
        for (int i = 0; i < M * N; i++) {
            int row = i / N;
            int col = i % N;
            JButton gb = createRestoreGridButton(row, col);
            gb.setPreferredSize(new Dimension(92, 80));
            gb.setIcon((ImageIcon)iconsBuffer.get("Token.png"));
            gb.setDisabledIcon((ImageIcon)iconsBuffer.get("Token.png"));
            toggleClickableButton(gb, false);
            p.add(gb);
            if(name.equals("Fire")){
            	buttonsLostTokensFire[row][col] = gb;            	
            }else{
            	buttonsLostTokensIce[row][col] = gb;
            }
            
        }
        return p;
    }
	
	/**
	 * Toggle button for non-interaction with the player.
	 * @param b The JButton to toggle on/off.
	 * @param toggle toggle boolean value (enable, disable).
	 */
	private void toggleClickableButton(JButton b, boolean toggle){
		b.setBorderPainted( toggle );
        b.setFocusPainted( toggle );
        b.setEnabled(toggle);
	}
	
	/**
	 * Toggles highlight of the currently selected Token on the Board.
	 * @param b Selected button.
	 * @param toggle Highlight toggle boolean value.
	 */
	private void toggleSelectButton(JButton b, boolean toggle){
		if(toggle){
			b.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3));
		}else{
			b.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		}
	}
	
	/**
	 * Toggles movement or special power highlight for the currently
	 * selected Token on the Board.
	 * @param b The JButton that the currently selected Token can act upon.
	 * @param toggle The action toggle boolean value.
	 */
	private void toggleHighlightButton(JButton b, boolean toggle){
		if(toggle){
			if(this.selectedSpecialPower){
				b.setBorder(BorderFactory.createLineBorder(Color.CYAN,3));
			}else{
				b.setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
			}
		}else{
			b.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		}
	}
	
	/**
	 * Resets button state based on activePlayer etc.
	 * @param pos The position on the Board that Token is to be reseted.
	 */
	private void resetButtonState(Vector2D pos){
		int row = pos.y;
		int col = pos.x;
		//JButton gb = this.buttons.get(i);
		int r = this.selectedToken / this.N;
        int c = this.selectedToken % this.N;
        JButton gb = this.buttons[row][col];
        Token tkn = this.board.getToken(row,col);
        //If on a selected token state enable everything:
    	//Based on turn, conceal other player's tokens:
        if (tkn instanceof BackgroundToken){
        	gb.setIcon(this.getTokenIcon(tkn));
        	gb.setDisabledIcon(this.getTokenIcon(tkn));
        	toggleClickableButton(gb, false);
        }else if(tkn.getOwn().getClass().equals(this.controller.getTurn().side().getClass())){
        	if(this.selectedSpecialPower && tkn instanceof SpecialMovablePlayerToken){
        		PlayerToken stkn = (SpecialMovablePlayerToken)tkn;
        		if(stkn.getRow()==r && stkn.getCol()==c){
                	gb.setIcon(this.getPlayerTokenSpecialIcon(stkn));
                	gb.setDisabledIcon(this.getPlayerTokenSpecialIcon(stkn));
                	toggleClickableButton(gb, true);        		
            	}
        	}else{
	        	gb.setIcon(this.getTokenIcon(tkn));
	        	gb.setDisabledIcon(this.getTokenIcon(tkn));
	        	toggleClickableButton(gb, true);
	        	if( r==row && c==col){
	        		toggleSelectButton(this.buttons[row][col], true);
	        	}else{
	        		toggleHighlightButton(this.buttons[row][col], false);	        		
	        	}
	        	
        	}
        }else{
        	gb.setIcon(this.getPlayerTokenHiddenIcon((PlayerToken)tkn));
        	gb.setDisabledIcon(this.getPlayerTokenHiddenIcon((PlayerToken)tkn));
        	toggleClickableButton(gb, false);
        	//einai aparethth h parakatw grammh?
        	//toggleHighlightButton(this.buttons[row][col], false);
        }     
        
	}
	
	/**
	 * Highlights and enables movement to valid buttons/locations.
	 */
	private void toggleApplyHighlighter(){
		System.out.println("Highlighter contains:");
		for(int i=0;i<this.highlighter.size();i++){
			System.out.println("R "+ (this.highlighter.get(i).y+1) +" C "+ (this.highlighter.get(i).x+1) );
		}
		if(this.highlighter.size()>0){
			//highlight any previous movements:
			for(int i=0;i<this.highlighter.size();i++){
				//this.buttons[highlighter.get(ii).y][highlighter.get(ii).x].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
				toggleHighlightButton(this.buttons[this.highlighter.get(i).y][this.highlighter.get(i).x], true);
				toggleClickableButton(this.buttons[this.highlighter.get(i).y][this.highlighter.get(i).x], true);
				//edw 8elw ena function gia to default state anti gia toggle:
			}
		}else{
			//de-highlight any previous movements (reset player tokens to they default state):
			for(int i=0;i<this.highlighter.size();i++){
				//this.buttons[highlighter.get(ii).y][highlighter.get(ii).x].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
				resetButtonState(new Vector2D(this.highlighter.get(i).y, this.highlighter.get(i).x));
				//toggleHighlightButton(this.buttons[highlighter.get(i).y][highlighter.get(i).x], false);
				//toggleClickableButton(this.buttons[highlighter.get(i).y][highlighter.get(i).x], false);
				//edw 8elw ena function gia to default state anti gia toggle:
			}
			//Reset highlighter array:
			//this.highlighter = new ArrayList<Vector2D>();
		}
		
		
	}
	
	/**
	 * Reveal and disable all board Tokens (At game end).
	 */
	private void disableAllButtons(){
		for (int i = 0; i < this.M * this.N; i++) {
            int row = i / this.N;
            int col = i % this.N;
            JButton gb = this.buttons[row][col];
            Token tkn = this.board.getToken(row,col);
            gb.setIcon(this.getTokenIcon(tkn));
        	gb.setDisabledIcon(this.getTokenIcon(tkn));
        	toggleClickableButton(gb, false);
        	toggleSelectButton(gb, false);
        	toggleHighlightButton(gb, false);
        }
	}
	
	private void updateGrid() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < this.M * this.N; i++) {
            int row = i / this.N;
            int col = i % this.N;
            resetButtonState(new Vector2D(row,col));
        }
		
		//If in selected state, change accordingly:
		if(this.selectedToken>=0){
			toggleApplyHighlighter();
		}		
		
		//Update Lost Tokens in both side panels:
		//For Player1:
		int ctr = 0;
		ctr = 0;
		lostTokensFire.clear();
		for(int k=0;k<this.controller.getTurn().p1.tokens.size();k++){
			int r = ctr / 3;
	        int c = ctr % 3;
			if( (this.controller.getTurn().p1.tokens.get(k).getRow() < 0) ){
				if(this.controller.getTurn().p1.tokens.get(k) instanceof MovablePlayerToken){
			      //Distribute the Lost Tokens in each jPanel:
					lostTokensFire.add((MovablePlayerToken)this.controller.getTurn().p1.tokens.get(k));
					buttonsLostTokensFire[r][c].setIcon(this.getTokenIcon(this.controller.getTurn().p1.tokens.get(k)));
					buttonsLostTokensFire[r][c].setDisabledIcon(this.getTokenIcon(this.controller.getTurn().p1.tokens.get(k)));
					ctr++;
				}
			}else{
				buttonsLostTokensFire[r][c].setIcon((ImageIcon)iconsBuffer.get("Token.png"));
				buttonsLostTokensFire[r][c].setDisabledIcon((ImageIcon)iconsBuffer.get("Token.png"));
			}		
		}
		//For Player2:
		ctr = 0;
		lostTokensIce.clear();
		for(int k=0;k<this.controller.getTurn().p2.tokens.size();k++){
			int r = ctr / 3;
	        int c = ctr % 3;
			if( (this.controller.getTurn().p2.tokens.get(k).getRow() < 0) ){
	        	lostTokensIce.add((MovablePlayerToken)this.controller.getTurn().p2.tokens.get(k));
				buttonsLostTokensIce[r][c].setIcon(this.getTokenIcon(this.controller.getTurn().p2.tokens.get(k)));
				buttonsLostTokensIce[r][c].setDisabledIcon(this.getTokenIcon(this.controller.getTurn().p2.tokens.get(k)));
				ctr++;
			}else{
				buttonsLostTokensIce[r][c].setIcon((ImageIcon)iconsBuffer.get("Token.png"));
				buttonsLostTokensIce[r][c].setDisabledIcon((ImageIcon)iconsBuffer.get("Token.png"));
			}
		}
	
		long endTime = System.currentTimeMillis();
		System.out.println("updateGrid() " + (endTime - startTime) + " milliseconds");
    }
	
	/**
	 * Fetch from cache and apply the appropriate JButton ImageIcon for the input Token.
	 * @param tkn The input Token to get a new ImageIcon.
	 * @return The new ImageIcon.
	 */
	private ImageIcon getTokenIcon(Token tkn){
		String filename = new String();
		if(tkn.getOwn() instanceof Fire){
			filename = filename.concat("r");
		}
		if(tkn.getOwn() instanceof Ice){
			filename = filename.concat("b");
		}
		filename = filename.concat(tkn.getName());
		filename = filename.concat(".png");
		return (ImageIcon)iconsBuffer.get(filename);		
	}
	
	/**
	 * Fetch from cache and apply the hidden token icon for the input Token.
	 * @param tkn The input Token to get a new ImageIcon.
	 * @return The new ImageIcon.
	 */
	private ImageIcon getPlayerTokenHiddenIcon(PlayerToken tkn){
		String filename = new String();
		if(tkn.getOwn() instanceof Fire){
			filename = filename.concat("r");
		}
		if(tkn.getOwn() instanceof Ice){
			filename = filename.concat("b");
		}
		filename = filename.concat("Hidden");
		filename = filename.concat(".png");
		return (ImageIcon)iconsBuffer.get(filename);		
	}
	
	/**
	 * Fetch from cache and apply the special power token icon for the input Token.
	 * @param tkn The input Token to get a new ImageIcon.
	 * @return The new ImageIcon.
	 */
	private ImageIcon getPlayerTokenSpecialIcon(PlayerToken tkn){
		String filename = new String();
		if(tkn.getOwn() instanceof Fire){
			filename = filename.concat("sr");
		}
		if(tkn.getOwn() instanceof Ice){
			filename = filename.concat("sb");
		}
		filename = filename.concat(tkn.getName());
		filename = filename.concat(".png");
		return (ImageIcon)iconsBuffer.get(filename);		
	}
	
	
	/**
	 * Fetch from disk the input image. To be used as ImageIcon
	 * in Tokens later on.
	 * @param filename The filename of the queried image.
	 * @return  The input image as a ImageIcon.
	 */
	private ImageIcon getIconImage(String filename) { //image from file
        try {
        	URL url = getClass().getResource("/resources/images/"+filename);
            return new ImageIcon(ImageIO.read(getClass().getResource("/resources/images/"+filename)).
                    getScaledInstance(100, 80, Image.SCALE_SMOOTH)); //image
        } catch (IOException ex) {
            Logger.getLogger(MovePiece.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
	
	/**
	 * Highlights (toggle) the selected token on the board
	 * Also handles src trg tokens' actions
	 * @param row The clicked token row
	 * @param col THe clicked token column
	 */
	private void selectToken(int row, int col){
		//List<Vector2D> moveLocations = new ArrayList<Vector2D>();
		//Check if clicked button is previously selected:
		int i = row * this.N + col;
		Token tkn = this.board.getToken(row,col);
		boolean isSpecial = false;
		//Check if token has special powers:
		if(tkn instanceof Dragon || tkn instanceof Knight || tkn instanceof Elf || tkn instanceof Sorceress || tkn instanceof BeastRider){
			isSpecial = true;
		}
		if(this.selectedToken == -1){
			//highlight selected token:
			toggleSelectButton(this.buttons[row][col], true);
			//this.buttons[row][col].setBorder(BorderFactory.createLineBorder(Color.YELLOW,3));
			this.selectedToken = i;
			
			this.highlighter = this.controller.tokenSelection(this.board, tkn);
			//highlight/enable selected token possible movement locations:
			//toggleApplyHighlighter(true);
			/*for(int ii=0;ii<highlighter.size();ii++){
				//this.buttons[highlighter.get(ii).y][highlighter.get(ii).x].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));
				toggleHighlightButton(this.buttons[highlighter.get(ii).y][highlighter.get(ii).x], true);
				toggleClickableButton(this.buttons[highlighter.get(ii).y][highlighter.get(ii).x], true);
			}*/
			
		}else if((this.selectedToken == i) && (isSpecial) ){
			//if special token cycle through: no selected, selected, special:
			if(this.selectedSpecialPower){
				toggleSelectButton(this.buttons[row][col], false);
				this.selectedToken = -1;
				this.selectedSpecialPower = false;
			}else{
				this.highlighter = this.controller.tokenSpecialSelection(this.board, tkn);
				//this.buttons[row][col].setBorder(BorderFactory.createLineBorder(Color.black,0));
				toggleSelectButton(this.buttons[row][col], true);
				//toggleApplyHighlighter(false);
				this.selectedSpecialPower = true;
			}
			
		}else if((this.selectedToken == i) && (!isSpecial) ){
			//this.buttons[row][col].setBorder(BorderFactory.createLineBorder(Color.black,0));
			toggleSelectButton(this.buttons[row][col], false);
			this.selectedToken = -1;
			//toggleApplyHighlighter(false);
		}else {
			int r = this.selectedToken / this.N;
            int c = this.selectedToken % this.N;
			//Check if the target is a token to interact with:
            long startTime = System.currentTimeMillis();
            boolean rslt = false;
            if(this.selectedSpecialPower){
            	rslt = this.controller.tokenAction(this.board, this.board.getToken(r,c), this.board.getToken(row,col), new Vector2D(row,col),this, true);
            	this.selectedSpecialPower = false;
            }else{
            	rslt = this.controller.tokenAction(this.board, this.board.getToken(r,c), this.board.getToken(row,col), new Vector2D(row,col),this, false);
            }
			
			long endTime = System.currentTimeMillis();
			System.out.println("tokenAction() " + (endTime - startTime) + " milliseconds");
			//Token tkn = this.board.getToken(row,col);
			
			if(rslt){
				//If tokenAction was successful:
				//this.buttons[r][c].setBorder(BorderFactory.createLineBorder(Color.black,0));
				//toggleSelectButton(this.buttons[r][c], false);
				this.selectedToken = -1;
				//toggleApplyHighlighter(false);
				//Check if winning condition:
				if(this.controller.getTurn().getWinner()!=null){
					//Game ended, disable all tokens...
					disableAllButtons();
				}
			}else{
				//If tokenAction was unsuccessful(or user intended otherwise):
				//this.buttons[r][c].setBorder(BorderFactory.createLineBorder(Color.black,0));
				//toggleSelectButton(this.buttons[r][c], true);
				//this.buttons[row][col].setBorder(BorderFactory.createLineBorder(Color.YELLOW,3));
				toggleHighlightButton(this.buttons[row][col], true);
				this.selectedToken = i;
				this.highlighter = this.controller.tokenSelection(this.board, tkn);
				System.out.println("FALSE tokenAction()");
			}

			//this.updateGrid();
		}
		this.updateGrid();
	}
	
	/**
	 * Experimental feature: handle properly the resizing of the game window, so as the
	 * Board and Tokens to maintain their aspect ratio.
	 * @param innerPanel JPanel containing the JButtons for each game Token, needed to
	 *  maintain constant aspect ratio.
	 * @param container The containing JPanel that can be resized freely. 
	 */
	private static void resizePreview(JPanel innerPanel, JPanel container) {
        int w = container.getWidth();
        int h = container.getHeight();
        int size =  Math.min(w, h);
        innerPanel.setPreferredSize(new Dimension(920, 656));
        container.revalidate();
    }
	
/**
 * The main StrategoAppViewer function that draws and refreshes the gaming
 *  board and side panels.
 */
	public void display() {
        viewFrame = new JFrame("GridButton");
        
        final JPanel containerPanel = new JPanel(new GridBagLayout());
        final JPanel FirePanel = new JPanel(new GridBagLayout());
        final JPanel IcePanel = new JPanel(new GridBagLayout());
        final JPanel boardPanel = createGridPanel();
        FirePanel.setBorder(BorderFactory.createLineBorder(Color.RED,0));
        IcePanel.setBorder(BorderFactory.createLineBorder(Color.BLUE,0));
        FirePanel.setVisible(true);
        IcePanel.setVisible(true);
        
        containerPanel.add(FirePanel.add(createScoreGridPanel("Fire")));
        containerPanel.add(boardPanel);
        containerPanel.add(IcePanel.add(createScoreGridPanel("Ice")));
        
        viewFrame.getContentPane().add(containerPanel);
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //viewFrame.setSize(920, 656);
        this.updateGrid();
        viewFrame.pack();
        viewFrame.setLocationRelativeTo(null);
        viewFrame.setVisible(true);
        
    }
}
