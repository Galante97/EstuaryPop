import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;


/**
 * 
 * @author James Galante, Sam Hughes, Chris Sutton, Olivia Leipa, Scott Miller
 *
 */
public class myPanel extends JPanel {
	
	/**
	 * constructor
	 */
	public myPanel() {
		setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	/**
	 * 
	 * @return size of preferred board
	 * 
	 */
	public Dimension getPreferredSize() {
		return new Dimension(250, 200);
	}

	/**
	 * @param g java.swing parameter passed here
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		System.out.println("");
		// Draw Text
		g.drawString("This is my custom Panel!", 10, 20);
	}
}
