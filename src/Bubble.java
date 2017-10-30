import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Bubble extends JPanel  {

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
	
	Bubble(int x, int y, String s){
		xCoord = x;
		yCoord = y;
		color = s;
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
		ImageIcon blue = new ImageIcon("src/blueBubble.png");
		ImageIcon green = new ImageIcon("src/greenBubble.png");
		ImageIcon red = new ImageIcon("src/redBubble.png");
		ImageIcon yellow = new ImageIcon("src/yellowBubble.png");
		ImageIcon black = new ImageIcon("src/blackBubble.png");

		Random rand = new Random();
		int colorChoose = rand.nextInt(5) + 1; // 5 is the maximum and the 1 is our minimum
		switch (colorChoose) {
		case 1:
			color = "blue";
			image = blue.getImage();
			break;
		case 2:
			color = "green";
			image = green.getImage();
			break;
		case 3:
			color = "red";
			image = red.getImage();
			break;
		case 4:
			color = "yellow";
			image = yellow.getImage();
			break;
		case 5:
			color = "black";
			image = black.getImage();
			break;

		default:
			break;
		}
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

	public String toString() {
		if (showGunImage) {
			return color + gunImage;
		} else {
			return color + gridImage;
		}
	}


}
