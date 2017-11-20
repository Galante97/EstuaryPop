import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Gun extends JPanel {

	public Image image;
	public Image gunBaseImage;
	
	AffineTransform afft = new AffineTransform();
	int degree = 0;

	int w;
	int h;

	/**
	 * Gun constructor
	 */
	Gun() {
		loadImage();
		w = image.getWidth(this) + 100;
		h = image.getHeight(this) + 50;
		setPreferredSize(new Dimension(w, h));

	}

	/**
	 * loads the gun image for the View
	 */
	private void loadImage() {
		this.setOpaque(false);
		ImageIcon arrow = new ImageIcon("src/arrow.png");
		image = arrow.getImage();
		
		ImageIcon gunBase = new ImageIcon("src/gunMount.png");
		gunBaseImage = gunBase.getImage();
	}

	@Override
	/**
	 * paints the gun for the View
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// g.drawImage(image, 0,0, null);

		int x = (this.getWidth() - image.getWidth(this)) / 2;
		int y = (this.getHeight() - image.getHeight(this)) / 2;

		Graphics2D g2d = (Graphics2D) g;
		AffineTransform at = AffineTransform.getTranslateInstance(x, y);
		at.rotate(Math.toRadians(degree), image.getWidth(this) / 2, image.getHeight(this) / 2); //gun arrow
		g2d.drawImage(image, at, null); 
		
		g.drawImage(gunBaseImage, image.getWidth(this) / 2 - 15, image.getHeight(this) / 2 - 20, null); //gun base positon

	}

}
