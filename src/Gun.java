import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Gun extends JPanel {
	public Image image;
	
	Gun() {
		loadImage();
		int w = image.getWidth(this);
		int h = image.getHeight(this);
		setPreferredSize(new Dimension(w, h));
		
	}

	private void loadImage() {
		ImageIcon gun = new ImageIcon("src/gunMount.png");
		image = gun.getImage();
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
		
	}
	
	
}
