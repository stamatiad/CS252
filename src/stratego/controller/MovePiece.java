package stratego.controller;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.net.URL;

/**
 *
 * @author Thanos
 */
public class MovePiece extends JFrame{
    private boolean iconSelected;
    private JButton selectedButton;
     public MovePiece() throws IOException {
        //this.setResizable(false);
        this.setTitle("MovePiece Demo");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardListener cl= new CardListener();
        JPanel panel= new JPanel();
        panel.setLayout(new GridLayout(3,3));
         for (int i = 0; i < 9; i++) {
             JButton b=new JButton();
             if(i==0){ //init joker
                b.setIcon(getImageJock());
                b.setName(JOKERNAME);
                //b.setBorder(BorderFactory.createLineBorder(Color.YELLOW,5));
             }else{ //init background
                b.setIcon(getImageBack());
             }
             b.setBorder(BorderFactory.createLineBorder(Color.black));
             b.addMouseListener(cl); 
             panel.add(b);
         }
        add(panel);
        iconSelected=false;
        
    }
    private ImageIcon getImageBack(){ //image for background
        try{
            return  new ImageIcon(ImageIO.read(getClass().getResource("/resources/images/bg_green.png"))); //image
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    private ImageIcon getImageJock() { //image for joker
        try {
        	URL url = getClass().getResource("/resources/images/joker.png");
            return new ImageIcon(ImageIO.read(getClass().getResource("/resources/images/joker.png")).
                    getScaledInstance(100, 80, Image.SCALE_SMOOTH)); //image
        } catch (IOException ex) {
            Logger.getLogger(MovePiece.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
    private class CardListener implements MouseListener { 
        @Override
        public void mouseClicked(MouseEvent e) {
        	
            JButton but = ((JButton) e.getSource());
            if(iconSelected && !but.equals(selectedButton)){ // move(swap) buttons
                but.setIcon(selectedButton.getIcon());
                but.setName(JOKERNAME);
                //but.setBorder(BorderFactory.createLineBorder(Color.YELLOW,5));
                selectedButton.setIcon(getImageBack());
                selectedButton.setBorder(BorderFactory.createLineBorder(Color.black));
                selectedButton.setName(null);
                selectedButton=but;
                iconSelected=false;
            }else if(!iconSelected && but.getName()!=null){ //if not selected icon is joker then select 
                iconSelected=true; //we can do without it, we can check for null selected button
                selectedButton=but;
                but.setBorder(BorderFactory.createLineBorder(Color.YELLOW,3));
            }else{ //if already selected or not selected at all
                if(iconSelected){
                    System.out.println("Already Selected");
                }else{
                    System.out.println("Not selected");
                }
            }
           
        }

        @Override public void mousePressed(MouseEvent e) {}

        @Override public void mouseReleased(MouseEvent e) {}

        @Override public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
    public static String JOKERNAME="Joker";
    public static void main(String[] args) throws IOException {
        MovePiece view = new MovePiece(); // create window (jframe)
        view.setSize(300, 300); 
        //view.pack();
        view.setVisible(true);
            
        




    }
}
