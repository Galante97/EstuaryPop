import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Bubble extends JPanel {

	public int xCoord;
	public int yCoord;
	public String color;
	public String gunImage;
	public String gridImage;
	public Boolean showGunImage; // will be used to for if true show the gun image, if false, show grid image
	public Objective obj;

	
	public Image image;


	Bubble(int x, int y, Boolean bool, String c, String gunI, String gridI) {
		xCoord = x;
		yCoord = y;
		showGunImage = bool;
		color = c;
		gunImage = gunI;
		gridImage = gridI;
	}

	Bubble(int x, int y) {
		xCoord = x;
		yCoord = y;

		initBubble();
	}
	

	public void initBubble() {
		loadImage();

		int w = image.getWidth(this);
		int h = image.getHeight(this);
		setPreferredSize(new Dimension(w, h));
	}

	private void loadImage() {
		System.out.println("loadImage");
		ImageIcon ii = new ImageIcon("src/blueBubble.png");
		image = ii.getImage();
		System.out.println(ii.getIconWidth());
		

	}

/*	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("paintComponent");
		g.drawImage(image, xCoord, yCoord, null);

	} */

	public String toString() {
		if (showGunImage) {
			return color + gunImage;
		} else {
			return color + gridImage;
		}
	}

}
