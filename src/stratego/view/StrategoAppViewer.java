package stratego.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class StrategoAppViewer {
	private JPanel panel;
	private int M, N;
	private Board StrategoBoard;
	//private List<String> iconsList = new ArrayList<String>();
	//private final List<JButton> buttons = new ArrayList<JButton>();
	private final JButton[][] buttons = new JButton[8][10];
	//private ImageIcon[] iconsBuffer = new ImageIcon[28];
	private HashMap iconsBuffer = new HashMap(28);
	//private final List<Token> list = new ArrayList<Token>();
	private int selectedToken;
	
	
	
	public StrategoAppViewer(Board StrategoBoard){
		this.StrategoBoard = StrategoBoard;
		this.M = StrategoBoard.getM();
		this.N = StrategoBoard.getN();
		this.selectedToken = -1;
		
		//Preload token Icons:
		iconsBuffer.put("rDragon.png", getIconImage("rDragon.png"));
		iconsBuffer.put("bDragon.png", getIconImage("bDragon.png"));
		iconsBuffer.put("rSlayer.png", getIconImage("rSlayer.png"));
		iconsBuffer.put("bSlayer.png", getIconImage("bSlayer.png"));
		iconsBuffer.put("Grass.png", getIconImage("Grass.png"));
		iconsBuffer.put("Rock.png", getIconImage("Rock.png"));
		/*
		iconsBuffer[0] = getIconImage("rDragon.png");
		iconsBuffer[0] = getIconImage("bDragon.png");
		iconsBuffer[0] = getIconImage("rMage.png");
		iconsBuffer[0] = getIconImage("bMage.png");
		iconsBuffer[0] = getIconImage("rKnight.png");
		iconsBuffer[0] = getIconImage("bKnight.png");
		iconsBuffer[0] = getIconImage("rBeastRider.png");
		iconsBuffer[0] = getIconImage("bBeastRider.png");
		iconsBuffer[0] = getIconImage("rSorceress.png");
		iconsBuffer[0] = getIconImage("bSorceress.png");
		iconsBuffer[0] = getIconImage("rBeast.png");
		iconsBuffer[0] = getIconImage("bBeast.png");
		iconsBuffer[0] = getIconImage("rElf.png");
		iconsBuffer[0] = getIconImage("bElf.png");
		iconsBuffer[0] = getIconImage("rDwarf.png");
		iconsBuffer[0] = getIconImage("bDwarf.png");
		iconsBuffer[0] = getIconImage("rScout.png");
		iconsBuffer[0] = getIconImage("bScout.png");
		*/
		//Names sorted with ranking:
		/*this.iconsList.add("Dragon.png"); //0
		this.iconsList.add("Mage.png");
		this.iconsList.add("Knight.png");
		this.iconsList.add("BeastRider.png");
		this.iconsList.add("Sorceress.png");
		this.iconsList.add("Beast.png");
		this.iconsList.add("Elf.png");
		this.iconsList.add("Dwarf.png");
		this.iconsList.add("Scout.png");
		this.iconsList.add("Slayer.png");
		this.iconsList.add("Trap.png");
		this.iconsList.add("Flag.png");
		this.iconsList.add("Hidden.png");//12
		this.iconsList.add("Grass.png");//13
		this.iconsList.add("Rock.png");*/
	}

	private JButton getGridButton(int r, int c) {
        //int index = r * N + c;
        //return this.buttons.get(index);
		return this.buttons[r][c];
    }
	
	/*private Token createGridButton(Token tkn, final int row, final int col) {
        //final Token b = new Token("r" + row + ",c" + col);
		//final Token b = new Dragon(row, col);
		//int row = tkn.getRow();
		//int col = tkn.getCol();
		tkn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            	//int row = tkn.getRow();
        		//int col = tkn.getCol();
            	Token gb = StrategoAppViewer.this.getGridButton(row, col);
                System.out.println("r" + row + ",c" + col
                    + " " + (tkn == gb)
                    + " " + (tkn.equals(gb)));
            }
        });
		//Override method will still work?
        //return b;
    }*/
	
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
    
        
	/*private void updateGridListeners() {
        for(int i=0 ; i<this.StrategoBoard.tokens.size() ; i++){
        	Token tkn = this.StrategoBoard.tokens.get(i);
        	final int row = tkn.getRow();
        	final int col = tkn.getCol();
        	tkn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                	System.out.println("r"+row + " c"+col);
                	//check if selected and toggle (Ctrl):
                	//Check if other selected for action:(ctrl: src and trg)
                	//Action -> move
                	//Action -> Attack
                	//highlight with green for movement and red for no movement(Ctrl+view):
                	
                	
                	
                }
            });
        }
		
		//Override method will still work?
        //return b;
    }*/
	
	private JPanel createGridPanel() {
        JPanel p = new JPanel(new GridLayout(this.M, this.N));
        //Initialize icons in buttons:
        for (int i = 0; i < this.M * this.N; i++) {
            int row = i / this.N;
            int col = i % this.N;
            JButton gb = createGridButton(row, col);
            Token tkn = this.StrategoBoard.getToken(row,col);
            if(tkn instanceof BackgroundToken){
            	toggleButton(gb, false);
            } else {
            	toggleButton(gb, true);
            }
            gb.setIcon(this.getTokenIcon(tkn));
            //this.buttons.add(gb);
            this.buttons[row][col] = gb;
            p.add(gb);
            //p.add(tkn);
        }
        
        return p;
    }
	/**
	 * Toggle button for non-interaction with the player
	 * @param b
	 * @param toggle toggle value (enable, disable)
	 */
	private void toggleButton(JButton b, boolean toggle){
		b.setBorderPainted( toggle );
        b.setFocusPainted( toggle );
	}
	
	private void updateGrid() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < this.M * this.N; i++) {
            int row = i / this.N;
            int col = i % this.N;
            //JButton gb = this.buttons.get(i);
            JButton gb = this.buttons[row][col];
            Token tkn = this.StrategoBoard.getToken(row,col);
            if(tkn instanceof BackgroundToken){
            	toggleButton(gb, false);
            } else {
            	toggleButton(gb, true);
            }
            gb.setIcon(this.getTokenIcon(tkn));
            //this.buttons.add(gb,i);
            //p.add(gb);
            //p.add(tkn);
        }
		long endTime = System.currentTimeMillis();
		System.out.println("updateGrid() " + (endTime - startTime) + " milliseconds");
    }
	
	private ImageIcon getTokenIcon(Token tkn){
		String filename = new String();
		if(tkn.getOwn().equals("Player1")){
			filename = filename.concat("r");
		}
		if(tkn.getOwn().equals("Player2")){
			filename = filename.concat("b");
		}
		filename = filename.concat(tkn.getName());
		filename = filename.concat(".png");
		return (ImageIcon)iconsBuffer.get(filename);
		//return getIconImage(filename);
		
		/*if(tkn.getOwn().equals("Game")){
			if(tkn.getClass().equals(Grass.class)){
				return getIconImage(filename.concat(this.iconsList.get(13)));
	        }
			if(tkn.getClass().equals(Rock.class)){
				return getIconImage(filename.concat(this.iconsList.get(14)));
	        }
        }
		if(tkn.getOwn().equals("Fire")){
			filename.concat("r");
		}
		if(tkn.getOwn().equals("Ice")){
			filename.concat("b");
		}*/
		//Get proper icon for token class:
		//return null;
		
	}
	
	private ImageIcon getIconImage(String filename) { //image from file
        try {
        	//URL url = getClass().getResource("/resources/images/"+filename);
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
		//Check if clicked button is previously selected:
		int i = row * this.N + col;
		if(this.selectedToken == -1){
			this.buttons[row][col].setBorder(BorderFactory.createLineBorder(Color.YELLOW,3));
			this.selectedToken = i;
		}
		else if(this.selectedToken == i){
			this.buttons[row][col].setBorder(BorderFactory.createLineBorder(Color.black,0));
			this.selectedToken = -1;
		} else {
			int r = this.selectedToken / this.N;
            int c = this.selectedToken % this.N;
			//Check if the target is a token to interact with:
            long startTime = System.currentTimeMillis();
			//boolean rslt = StrategoAppController.tokenAction(this.StrategoBoard, this.StrategoBoard.getToken(r,c), this.StrategoBoard.getToken(row,col));
			long endTime = System.currentTimeMillis();
			System.out.println("tokenAction() " + (endTime - startTime) + " milliseconds");
			//Token tkn = this.StrategoBoard.getToken(row,col);
			
			if(true){
				this.buttons[r][c].setBorder(BorderFactory.createLineBorder(Color.black,0));
				this.selectedToken = -1;
			}else{
				this.buttons[r][c].setBorder(BorderFactory.createLineBorder(Color.black,0));
				this.buttons[row][col].setBorder(BorderFactory.createLineBorder(Color.YELLOW,3));
				this.selectedToken = i;
			}

			this.updateGrid();
		}
	}
	
	
	public void display() {
        JFrame f = new JFrame("GridButton");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(createGridPanel());
        this.updateGrid();
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        //StrategoAppController.tokenAction(this.StrategoBoard, this.StrategoBoard.getToken(0,0), this.StrategoBoard.getToken(0,1));
        //this.updateGrid();
    }
}
