
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Bubble extends JPanel {// needs to extend jpanel

	public int xCoord;
	public int yCoord;
	public String color;
	public String gunImage;
	public String gridImage;
	public Boolean showGunImage = true; // will be used to for if true show the gun image, if false, show grid image
	public Image image;

	int h;
	int w;

	//////////////////////////////////////////

	/**
	 * constructor to be used with the View
	 * 
	 * @param x
	 *            is the x coord of the bubble
	 * @param y
	 *            is the y coord of the bubble
	 * @param bool
	 *            is a boolean if the image is showing or not
	 * @param c
	 *            is the color of the bubble
	 * @param gunI
	 *            is the gun image
	 * @param gridI
	 *            is the grid image
	 */
	/*
	 * Bubble(int x, int y, String c, String gunI, String gridI) { xCoord = x;
	 * yCoord = y; color = c; gunImage = gunI; gridImage = gridI; }
	 */

	/**
	 * constructor for running in the Model
	 * 
	 * @param x
	 *            is the x coord
	 * @param y
	 *            is the y coord
	 * @param c
	 *            is the color
	 */
	Bubble(String c, String gun, String grid) {
		color = c;
		gridImage = grid;
		gunImage = gun;

		xCoord = 0;
		yCoord = 0;

		// commented because it was causing issues
		initBubble();
	}

	Bubble(int x, int y) {
		xCoord = x;
		yCoord = y;

	}

	//////////////////////////////////////////////

	/**
	 * initializes the bubble for the View
	 */
	public void initBubble() {
		loadImage();
		this.setOpaque(false);

		w = image.getWidth(this);
		h = image.getHeight(this);

		setPreferredSize(new Dimension(w, h));
	}

	/**
	 * loads the bubble image for the View
	 */
	private void loadImage() {// the bubble already have colors
		ImageIcon red = new ImageIcon("src/redBubble.png");
		ImageIcon orange = new ImageIcon("src/orangeBubble.png");
		ImageIcon yellow = new ImageIcon("src/yellowBubble.png");
		ImageIcon green = new ImageIcon("src/greenBubble.png");
		ImageIcon blue = new ImageIcon("src/blueBubble.png");
		ImageIcon purple = new ImageIcon("src/purpleBubble.png");

		if (color == "R") {
			image = red.getImage();
		} else if (color == "O") {
			image = orange.getImage();
		} else if (color == "Y") {
			image = yellow.getImage();
		} else if (color == "G") {
			image = green.getImage();
		} else if (color == "B") {
			image = blue.getImage();
		} else if (color == "P") {
			image = purple.getImage();
		}

	}

	// @Override
	/**
	 * draws the bubble image
	 * 
	 * @param g
	 *            is the graphics for Jpanel
	 */
	public void paintComponent(Graphics g) {
		// super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}

	/**
	 * switches the bubble image
	 */
	public void switchImage() {
		showGunImage = false;
	}

	///////////////////////////////////////////////

	/**
	 * bubbles to string method
	 * 
	 * @return the bubbles parameters as a string
	 */
	public String toString() {
		if (showGunImage) {
			return "[" + color /* + ", " + gunImage */ + "]";
		} else {
			return "[" + color /* + ", " + gridImage */ + "]";
		}
	}
}
