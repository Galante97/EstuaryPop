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
	
	public static PopView menu;
    public static PopView howToPlay;
	private static PopView diff;
	
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		PopController.pract=true;
		menu.setVisible(false);
		diff.setVisible(true);

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

	
	public static void sendInstancesToHowToPlayMouseListener(PopView diff, PopView menu, PopView howToPlay){
		HowToPlayMouseListener.diff= diff;
		HowToPlayMouseListener.menu=menu;
		HowToPlayMouseListener.howToPlay=howToPlay;
	}
	
	
	
}
