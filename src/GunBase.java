import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GunBase extends JPanel {
	
	ImageIcon base = new ImageIcon("src/gunMount.png");

	int h;
	int w;
	public Image image;
	
	GunBase() {

		double ww = 131 * PopView.SCALE_FACTOR;
		double hh = 121 * PopView.SCALE_FACTOR;

		w = (int) ww;
		h = (int) hh;

		loadImage();
		this.setOpaque(false);
		setPreferredSize(new Dimension(w, h));
	}

	private void loadImage() {
		image = base.getImage();

	}
	
	public void paintComponent(Graphics g) {
		// super.paintComponent(g);
		// g.drawImage(image, 0, 0, null);
		
		double ww = 131 * PopView.SCALE_FACTOR;
		double hh = 121 * PopView.SCALE_FACTOR;
		
		w = (int) ww;
		h = (int) hh;
				
		setPreferredSize(new Dimension(w, h));
		g.drawImage(image, 0, 0, w, h, null);
		
		
	}

}
