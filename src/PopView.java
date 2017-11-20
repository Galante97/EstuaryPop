
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * PopModel The PopModel class is the model in the MVC that hold the current
 * state of the game
 * 
 * @author James Galante, Sam Hughes, Chris Sutton, Olivia Leipa, Scott Miller
 * @version 1.0
 * @since 2017-09-31
 */

public class PopView extends JFrame implements MouseListener, ActionListener {

	JFrame frame;
	JPanel sidePanel;
	JPanel gamePanel;
	Gun gun;

	JPanel[] gunBubbleArr = new JPanel[8];
	int curBubbleArrNum = 0;

	JPanel BubbleInGun;
	int BubbleInGunX = 450;
	int BubbleInGunY = 712;
	int mouseX;
	int mouseY;
	Double slope;

	int bubbleWH = 70;
	Double oscillationFactor = 5.5;
	int CurrPath;

	Timer timer;
	private final int DELAY = 50;

	PopModel model;
	private boolean clicked = false;

	/**
	 * Constructor for the PopView class, handles connecting the model and frame
	 * 
	 * @param Model
	 *            used for the view to talk to the model in MVC
	 * @param frame
	 *            used as the window for the game to be played
	 * @return none
	 */
	public PopView(PopModel Model, JFrame frame) {
		this.model = Model;
		this.frame = frame;

	}

	/**
	 * getter to see if the user clicked the mouse
	 * 
	 * @param none
	 * @return clicked returns true or false if the user clicked
	 */
	public boolean getClicked() {
		return clicked;
	}

	/**
	 * setter to set clicked
	 * 
	 * @param none
	 * @return none
	 */
	public void setClicked(boolean click) {
		clicked = click;
	}

	/**
	 * sets up frame using JFrame calls all other draw methods for drawing items on
	 * screen
	 * 
	 * @param none
	 * @return none
	 */
	public void draw() {

		drawSidePanel();
		drawGridBubbles();
		drawGunBubbles();
		drawGun();

		// drawObjectives();

		drawGridForTesting();
		drawGamePanel();

		setTitle("Estuary Pop!");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		timer = new Timer(DELAY, this);
		timer.start();

		System.out.println("Everything Drawn: Welcome to Estuary Pop");
	}

	public void drawGridForTesting() { // this is strictly for visuals and will be removed later on
		for (int i = 0; i < model.startRows + 6; i++) {
			for (int j = 0; j < model.gridColumns; j++) {
				JPanel gridPanel = new JPanel();

				gridPanel.setBounds(j * bubbleWH + 45, i * bubbleWH + 17, bubbleWH, bubbleWH);
				gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				gridPanel.setOpaque(false);
				add(gridPanel);

			}
		}

	}

	/**
	 * DrawGridBubbles, draws the bubbles on screen that are to be popped on the top
	 * of the screen
	 * 
	 * @param none
	 * @return none
	 */
	public void drawGridBubbles() {
		for (int i = 0; i < model.startRows; i++) {
			for (int j = 0; j < model.gridColumns; j++) {
				JPanel panel = new JPanel();

				model.grid[i][j].xCoord = i * bubbleWH;
				model.grid[i][j].yCoord = j * bubbleWH;

				panel.setBounds(j * bubbleWH + 45, i * bubbleWH + 12, bubbleWH, bubbleWH + 6);
				panel.setOpaque(false);
				add(panel);
				panel.add(model.grid[i][j], BorderLayout.NORTH);
			}
		}

		System.out.println("Draw grid bubbles");

	}

	/**
	 * drawGun, draws the gun where the bubbles will be shot out of
	 * 
	 * @param none
	 * @return none
	 */
	public void drawGun() {
		JPanel panel = new JPanel();
		gun = new Gun();
		panel.setBounds(350, 550, gun.w + 50, gun.h);
		// panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel.setOpaque(false);
		add(panel);
		panel.add(gun, BorderLayout.CENTER);

	}

	/**
	 * drawGunBubbles, puts the bubbles to be shot, in the correct place on screen
	 * and puts the first bubble in gunBubbleArr as BubbleInGun
	 * 
	 * @param none
	 * @return none
	 */
	public void drawGunBubbles() {
		System.out.println("Draw Gun Bubbles");

		for (int i = 0; i < model.gunListLength - 1; i++) {
			JPanel panel = new JPanel();
			Bubble bub = model.gunList[i]; // connection to model
			panel.setOpaque(false);

			if (i == 0) {
				BubbleInGun = panel;
				panel.setBounds(i * bubbleWH + 442, 690, bubbleWH, bubbleWH + 6);
				panel.setOpaque(false);
				add(panel);
				panel.add(bub, BorderLayout.NORTH);
				gunBubbleArr[i] = panel;

			} else {
				panel.setBounds(i * bubbleWH + 480, 690, bubbleWH, bubbleWH + 6);
				panel.setOpaque(false);
				// panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				add(panel);
				panel.add(bub, BorderLayout.NORTH);
				gunBubbleArr[i] = panel;

			}

		}
	}

	/**
	 * newShooter, reloads the gun allowing the user to shoot again it changes the
	 * Position of the bubble and updates the back-end array
	 * 
	 * @param none
	 * @return none
	 */
	public void newShooter() {
		System.out.println("Reload");
		BubbleInGunX = 442;
		BubbleInGunY = 690;
		// if (curBubbleArrNum == 1) {
		// curBubbleArrNum++;
		// }
		gunBubbleArr[curBubbleArrNum].setBounds(442, 690, bubbleWH, bubbleWH + 6);
		BubbleInGun = gunBubbleArr[curBubbleArrNum];

	}

	/**
	 * actionPerformed, is an overrided method from the actionLisenter class that
	 * lets the program know if the user clicked and where
	 * 
	 * @param e,
	 *            unused
	 * @return none
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (gun.degree > 80) {
			oscillationFactor = -oscillationFactor;
		}

		if (gun.degree < -80) {
			oscillationFactor = -oscillationFactor;
		}

		gun.degree += oscillationFactor;
		gun.repaint();

		

		if (clicked == true) { 
			//SHOOT BUBBLE TO GRID SQUARE BASED ON PATH (CurrPath) which is set in mousePressed in the big if else statement
			
			/* THIS IS THE OLD CLICK MOVEMENT
			 * if (slope < 0) { BubbleInGunX++; } else { BubbleInGunX--; }
			 * 
			 * Double aSlope = Math.abs(slope);
			 * 
			 * // BubbleInGunX++; BubbleInGunY = (int) (BubbleInGunY - aSlope);
			 * 
			 * if (BubbleInGunY > 262) { BubbleInGun.setBounds(BubbleInGunX, BubbleInGunY,
			 * bubbleWH, bubbleWH + 6); } else { clicked = false;
			 * 
			 * curBubbleArrNum++;
			 * 
			 * if (curBubbleArrNum < 8) { newShooter(); } }
			 */
		}

	}

	/**
	 * drawGamePanel, draws a boarder (made with borderFactory) around the screen
	 * which becomes the playable area
	 * 
	 * @param none
	 * @return none
	 */
	public void drawGamePanel() {
		System.out.println("Draw game panel");

		gamePanel = new JPanel();
		gamePanel.setBounds(10, 10, 975, 760);
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gamePanel.addMouseListener(this);
		add(gamePanel);

	}

	/**
	 * drawSidePanel, draws a boarder (made with borderFactory) around the side of
	 * the screen which becomes the info area (time, score, etc)
	 * 
	 * @param none
	 * @return none
	 */
	public void drawSidePanel() {
		System.out.println("Draw info panel");

		sidePanel = new JPanel();
		sidePanel.setBounds(990, 10, 200, 760);
		sidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sidePanel.setBackground(Color.LIGHT_GRAY);

		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		add(sidePanel);
	}

	/**
	 * drawObjectives, gets the objectives from the model and displays them on the
	 * screen
	 * 
	 * @param none
	 * @return none
	 */
	public void drawObjectives() {
		System.out.println("Draw Objectives");

		JLabel objLabel1 = new JLabel(model.objectives.get(0), null, JLabel.CENTER);
		JLabel objLabel2 = new JLabel(model.objectives.get(1), null, JLabel.CENTER);
		JLabel objLabel3 = new JLabel(model.objectives[2], null, JLabel.CENTER);

		sidePanel.add(objLabel1);
		sidePanel.add(objLabel2);
		sidePanel.add(objLabel3);

		objLabel1.setBounds(100, 0, 100, 15);
		objLabel2.setBounds(100, 120, 100, 15);
		objLabel3.setBounds(100, 150, 100, 15);

		Font font = new Font("Verdana", Font.ITALIC, 14);
		objLabel1.setFont(font);
		objLabel2.setFont(font);
		objLabel3.setFont(font);

	}

	/**
	 * mouseReleased, is an overrided method from mouseListener that lets the
	 * program know if the mouse has been released
	 * 
	 * this method is used as a shoot function to shoot the bubble
	 * 
	 * @param none
	 * @return none
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		clicked = true;
		if (clicked) {
			mouseX = e.getX();
			mouseY = e.getY();
			
			if (gun.degree >= -80 && gun.degree < -74.5) {
				System.out.println("PATH 1");
			} else if (gun.degree >= -74.5 && gun.degree < -69) {
				System.out.println("PATH 2");
			} else if (gun.degree >= -74.5 && gun.degree < -69) {
				System.out.println("PATH 3");
			} else if (gun.degree >= -69 && gun.degree < -63.5) {
				System.out.println("PATH 4");
			} else if (gun.degree >= -63.5 && gun.degree < -58) {
				System.out.println("PATH 5");
			} else if (gun.degree >= -58 && gun.degree < -52.5) {
				System.out.println("PATH 6");
			} else if (gun.degree >= -52.5 && gun.degree < -47) {
				System.out.println("PATH 7");
			} else if (gun.degree >= -47 && gun.degree < -41.5) {
				System.out.println("PATH 8");
			} else if (gun.degree >= -41.5 && gun.degree < -36) {
				System.out.println("PATH 9");
			} else if (gun.degree >= -36 && gun.degree < -30.5) {
				System.out.println("PATH 10");
			} else if (gun.degree >= -30.5 && gun.degree < -25) {
				System.out.println("PATH 11");
			} else if (gun.degree >= -25 && gun.degree < -19.5) {
				System.out.println("PATH 12");
			} else if (gun.degree >= -19.5 && gun.degree < -14) {
				System.out.println("PATH 13");
			} else if (gun.degree >= -14 && gun.degree < -8.5) {
				System.out.println("PATH 14");
			} else if (gun.degree >= -8.5 && gun.degree < -3) {
				System.out.println("PATH 15");
			} else if (gun.degree >= -3 && gun.degree < 2.5) {
				System.out.println("PATH 16");
			} else if (gun.degree >= 2.5 && gun.degree < 8) {
				System.out.println("PATH 17");
			} else if (gun.degree >= 8 && gun.degree < 13.5) {
				System.out.println("PATH 18");
			} else if (gun.degree >= 13.5 && gun.degree < 19) {
				System.out.println("PATH 19");
			} else if (gun.degree >= 19 && gun.degree < 24.5) {
				System.out.println("PATH 20");
			} else if (gun.degree >= 24.5 && gun.degree < 30) {
				System.out.println("PATH 21");
			} else if (gun.degree >= 30 && gun.degree < 35.5) {
				System.out.println("PATH 22");
			} else if (gun.degree >= 35.5 && gun.degree < 41) {
				System.out.println("PATH 23");
			} else if (gun.degree >= 41 && gun.degree < 46.5) {
				System.out.println("PATH 24");
			} else if (gun.degree >= 46.5 && gun.degree < 52) {
				System.out.println("PATH 25");
			} else if (gun.degree >= 52 && gun.degree < 57.5) {
				System.out.println("PATH 26");
			} else if (gun.degree >= 57.5 && gun.degree < 63) {
				System.out.println("PATH 27");
			} else if (gun.degree >= 63 && gun.degree < 68.5) {
				System.out.println("PATH 28");
			} else if (gun.degree >= 74 && gun.degree < 80) {
				System.out.println("PATH 29");
			}

		}
		
		/*
		 * System.out.println("Fire!");
		 * 
		 * System.out.println(mouseX + "," + mouseY);// these co-ords are relative to //
		 * the component
		 * 
		 * Double y2 = (double) mouseY; Double y1 = (double) BubbleInGunY;
		 * 
		 * Double x2 = (double) mouseX; Double x1 = (double) BubbleInGunX;
		 * 
		 * System.out.println("mouseY: " + y2); System.out.println("MouseX: " + x2); //
		 * System.out.println("BubbleInGunY: " + y1); System.out.println();
		 * 
		 * // System.out.println("BubbleInGunX: " + y1);
		 * 
		 * slope = (double) ((y2 - y1) / (x2 - x1));
		 */

	}

	/**
	 * mousePressed, is an overrided method from mouseListener that lets the program
	 * know if the mouse has been pressed
	 * 
	 * we do not use this method, but must be overrided from mouseListener
	 * 
	 * @param none
	 * @return none
	 */
	public void mousePressed(MouseEvent e) {
	}

	/**
	 * mouseClicked, is an overrided method from mouseListener that lets the program
	 * know if the mouse has been clicked
	 * 
	 * we do not use this method, but must be overrided from mouseListener
	 * 
	 * @param none
	 * @return none
	 */
	public void mouseClicked(MouseEvent e) {

	}

	/**
	 * mouseEntered, is an overrided method from mouseListener that lets the program
	 * know if the mouse has been entered a particular area
	 * 
	 * we do not use this method, but must be overrided from mouseListener
	 * 
	 * @param none
	 * @return none
	 */
	public void mouseEntered(MouseEvent e) {
	} // do nothing

	/**
	 * mouseExited, is an overrided method from mouseListener that lets the program
	 * know if the mouse has been exited a particular area
	 * 
	 * we do not use this method, but must be overrided from mouseListener
	 * 
	 * @param none
	 * @return none
	 */
	public void mouseExited(MouseEvent e) {
	} // do nothing

}
