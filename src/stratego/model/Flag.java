package stratego.model;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import stratego.controller.*;

/**
 * Implements the Flag player token.
 * @author steve
 *
 */
public class Flag extends ImmovablePlayerToken implements Token{
	
	public int row;
	public int col;
	public String own;
	//public ImageIcon icon;
	
	public Flag(){
		super();
		this.own = "Game";
		//this.rank = 0;
		//this.setIcon(getIconImage());
	}
	public Flag(int row, int col){
		this();
		this.row = row;
		this.col = col;
	}
	
	/*private ImageIcon getIconImage() { //image for flag
        try {
            return new ImageIcon(ImageIO.read(getClass().getResource("/resources/images/flagR.png")).
                    getScaledInstance(100, 80, Image.SCALE_SMOOTH)); //image
        } catch (IOException ex) {
            Logger.getLogger(MovePiece.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }*/
}
