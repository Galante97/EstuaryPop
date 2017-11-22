import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HowToPlayMouseListener implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		JFrame frame = new JFrame();
		PopModel m = new PopModel(1); //create model
		PopView view = new PopView(m, frame); // create view
		JPanel menu = new JPanel();
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("src/estMenu.jpg")); //https://coast.noaa.gov/estuaries/curriculum/climate-extension.html
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		view.setContentPane(new JLabel(imageIcon));
		menu.setBounds(200, 400, 800, 150);
		menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	    JLabel jlabel = new JLabel("<html>The gun moves automatically from side to side.<br> "
	    		+ " Use the left mouse button to fire! <br>"
	    		+ "Take some shots at matching bubbles above<br>"
	    		+ " to pop them!</html");
		jlabel.setFont(new Font("Verdana",1,20));
		menu.add(jlabel);
		JButton b1=new JButton("OK, Start Game!");     
	    b1.setBounds(0,100,80,30);    
	    b1.setBackground(Color.yellow);
	    b1.addMouseListener(new MenuCustomMouseListener());
	    menu.add(b1);
		view.add(menu);
		view.setTitle("How to Play");
		view.setSize(1200, 800);
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setLocationRelativeTo(null);
		view.getContentPane().setLayout(null);
		view.setVisible(true);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
