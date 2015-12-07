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
	//Is this the best way?
	private StrategoAppController controller = new StrategoAppController();
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
	
	private JPanel createGridPanel() {
        JPanel p = new JPanel(new GridLayout(this.M, this.N));
        //Initialize icons in buttons:
        for (int i = 0; i < this.M * this.N; i++) {
            int row = i / this.N;
            int col = i % this.N;
            JButton gb = createGridButton(row, col);
            /*Token tkn = this.StrategoBoard.getToken(row,col);
            if(tkn instanceof BackgroundToken){
            	toggleButton(gb, false);
            } else {
            	toggleButton(gb, true);
            }
            gb.setIcon(this.getTokenIcon(tkn));
            //this.buttons.add(gb);
             */
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
		List<Vector2D> moveLocations = new ArrayList<Vector2D>();
		//Check if clicked button is previously selected:
		int i = row * this.N + col;
		if(this.selectedToken == -1){
			//highlight selected token:
			this.buttons[row][col].setBorder(BorderFactory.createLineBorder(Color.YELLOW,3));
			this.selectedToken = i;
			Token tkn = this.StrategoBoard.getToken(row,col);
			moveLocations = this.controller.tokenSelection(this.StrategoBoard, tkn);
			//highlight selected token possible movement locations:
			for(int ii=0;ii<moveLocations.size();ii++){
				this.buttons[moveLocations.get(ii).y][moveLocations.get(ii).x].setBorder(BorderFactory.createLineBorder(Color.GREEN,3));				
			}
			
		}
		else if(this.selectedToken == i){
			this.buttons[row][col].setBorder(BorderFactory.createLineBorder(Color.black,0));
			this.selectedToken = -1;
		} else {
			int r = this.selectedToken / this.N;
            int c = this.selectedToken % this.N;
			//Check if the target is a token to interact with:
            long startTime = System.currentTimeMillis();
			boolean rslt = this.controller.tokenAction(this.StrategoBoard, this.StrategoBoard.getToken(r,c), this.StrategoBoard.getToken(row,col),this);
			long endTime = System.currentTimeMillis();
			System.out.println("tokenAction() " + (endTime - startTime) + " milliseconds");
			//Token tkn = this.StrategoBoard.getToken(row,col);
			
			if(rslt){
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
