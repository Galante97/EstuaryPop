
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
	public int difficulty;

	int h;
	int w;

	//easy difficulty images
	ImageIcon redGun = new ImageIcon("src/redBubbleGun.png"); 
	ImageIcon redGrid = new ImageIcon("src/redBubbleGrid.png"); 
	ImageIcon orangeGun = new ImageIcon("src/orangeBubbleGun.png");
	ImageIcon orangeGrid = new ImageIcon("src/orangeBubbleGrid.png"); 
	ImageIcon yellowGun = new ImageIcon("src/yellowBubbleGun.png"); 
	ImageIcon yellowGrid = new ImageIcon("src/yellowBubbleGrid.png"); 
	ImageIcon greenGun = new ImageIcon("src/greenBubbleGun.png");
	ImageIcon greenGrid = new ImageIcon("src/greenBubbleGrid.png"); 
	ImageIcon blueGun = new ImageIcon("src/blueBubbleGun.png"); 
	ImageIcon blueGrid = new ImageIcon("src/blueBubbleGrid.png"); 
	ImageIcon purpleGun = new ImageIcon("src/purpleBubbleGun.png");
	ImageIcon purpleGrid = new ImageIcon("src/purpleBubbleGrid.png"); 

	//medium and hard difficutly images
	ImageIcon a1 = new ImageIcon("src/a1.png"); 
	ImageIcon a2 = new ImageIcon("src/a2.png"); 
	ImageIcon b1 = new ImageIcon("src/b1.png"); 
	ImageIcon b2 = new ImageIcon("src/b2.png");
	ImageIcon c1 = new ImageIcon("src/c1.png"); 
	ImageIcon c2 = new ImageIcon("src/c2.png"); 
	ImageIcon d1 = new ImageIcon("src/d1.png"); 
	ImageIcon d2 = new ImageIcon("src/d2.png"); 
	ImageIcon e1 = new ImageIcon("src/e1.png"); 
	ImageIcon e2 = new ImageIcon("src/e2.png"); 
	ImageIcon f1 = new ImageIcon("src/f1.png"); 
	ImageIcon f2 = new ImageIcon("src/f2.png"); 

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
	Bubble(String c, String gun, String grid, int d) {
		color = c;
		gridImage = grid;
		gunImage = gun;

		difficulty = d;
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
		double ww = 70 * PopView.SCALE_FACTOR;
		double hh = 70 * PopView.SCALE_FACTOR;
		
		w = (int) ww;
		h = (int) hh;

		loadImage();
		this.setOpaque(false);
		setPreferredSize(new Dimension(w, h));
	}

	/**
	 * loads the bubble image for the View
	 */
	private void loadImage() {// the bubble already have colors

		if (difficulty == 2 || difficulty == 3) {
			if (color == "R") {
				image = a1.getImage();
			} else if (color == "O") {
				image = b1.getImage();
			} else if (color == "Y") {
				image = c1.getImage();
			} else if (color == "G") {
				image = d1.getImage();
			} else if (color == "B") {
				image = e1.getImage();
			} else if (color == "P") {
				image = f1.getImage();
			}
		} else { //easy difficulty 
			if (color == "R") {
				image = redGun.getImage();
			} else if (color == "O") {
				image = orangeGun.getImage();
			} else if (color == "Y") {
				image = yellowGun.getImage();
			} else if (color == "G") {
				image = greenGun.getImage();
			} else if (color == "B") {
				image = blueGun.getImage();
			} else if (color == "P") {
				image = purpleGun.getImage();
			}
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
		// g.drawImage(image, 0, 0, null);
		
		double ww = 70 * PopView.SCALE_FACTOR;
		double hh = 70 * PopView.SCALE_FACTOR;
		
		w = (int) ww;
		h = (int) hh;
				
		setPreferredSize(new Dimension(w, h));
		g.drawImage(image, 0, 0, w, h, null);
		
		
	}

	/**
	 * switches the bubble image
	 */
	public void switchImage() {

		if (difficulty == 2 || difficulty == 3) {
			if (color == "R") {
				image = a2.getImage();
			} else if (color == "O") {
				image = b2.getImage();
			} else if (color == "Y") {
				image = c2.getImage();
			} else if (color == "G") {
				image = d2.getImage();
			} else if (color == "B") {
				image = e2.getImage();
			} else if (color == "P") {
				image = f2.getImage();
			}
		} else { //easy difficulty 
			if (color == "R") {
				image = redGrid.getImage();
			} else if (color == "O") {
				image = orangeGrid.getImage();
			} else if (color == "Y") {
				image = yellowGrid.getImage();
			} else if (color == "G") {
				image = greenGrid.getImage();
			} else if (color == "B") {
				image = blueGrid.getImage();
			} else if (color == "P") {
				image = purpleGrid.getImage();
			}
		}
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
