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
 * PopView The PopView class is the viee in the MVC that handles the data being
 * being show on the view, and draws all objects
 *
 *
 * @author James Galante, Sam Hughes, Chris Sutton, Olivia Leipa, Scott Miller
 * @version 1.0
 * @since 2017-09-31
 */

public class PopView extends JFrame implements MouseListener, ActionListener {

	JFrame frame;
	JPanel sidePanel;
	JPanel gamePanel;

	JPanel[] gunBubbleArr = new JPanel[8];
	int curBubbleArrNum = 0;

	JPanel BubbleInGun;
	int BubbleInGunX;
	int BubbleInGunY;
	int mouseX;
	int mouseY;
	Double slope;

	Timer timer;
	private final int DELAY = 1;

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
		// drawObjectives();
		drawGunBubbles();
		drawGun();
		drawGridBubbles();
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

	/**
	 * DrawGridBubbles, draws the bubbles on screen that are to be popped on the top
	 * of the screen
	 * 
	 * @param none
	 * @return none
	 */
	public void drawGridBubbles() {
		for (int i = 0; i < model.gridColumns; i++) {
			for (int j = 0; j < model.gridRows; j++) {
				JPanel panel = new JPanel();
				Bubble bub = new Bubble(i * 50, j * 50);
				// model.grid[i][j] = bub; // ADDS TO MODEL BUBBLE GRID LIST
				panel.setBounds(i * 50 + 45, j * 50 + 12, 50, 56);
				panel.setOpaque(false);
				add(panel);
				panel.add(bub, BorderLayout.NORTH);
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
		Gun gun = new Gun();
		panel.setBounds(410, 649, 131, 120);
		panel.setOpaque(false);
		// panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(panel);
		panel.add(gun, BorderLayout.NORTH);

		System.out.println("Draw Gun");

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
		if (clicked == true) {
			if (slope < 0) {
				BubbleInGunX++;
			} else {
				BubbleInGunX--;
			}

			Double aSlope = Math.abs(slope);

			// BubbleInGunX++;
			BubbleInGunY = (int) (BubbleInGunY - aSlope);

			if (BubbleInGunY > 262) {
				BubbleInGun.setBounds(BubbleInGunX, BubbleInGunY, 50, 56);
			} else {
				clicked = false;

				curBubbleArrNum++;

				if (curBubbleArrNum < 8) {
					newShooter();
				}
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
		BubbleInGunX = 450;
		BubbleInGunY = 712;
		if (curBubbleArrNum == 1) {
			curBubbleArrNum++;
		}
		gunBubbleArr[curBubbleArrNum].setBounds(450, 712, 50, 56);
		BubbleInGun = gunBubbleArr[curBubbleArrNum];

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

		BubbleInGunX = 450;
		BubbleInGunY = 712;

		for (int i = 0; i < 8; i++) {

			JPanel panel = new JPanel();
			Bubble bub = new Bubble(0, 0);
			panel.setOpaque(false);

			if (i == 0) {
				BubbleInGun = panel;
				panel.setBounds(i * 50 + 450, 712, 50, 56);
				panel.setOpaque(false);
				add(panel);
				panel.add(bub, BorderLayout.NORTH);
				gunBubbleArr[i] = panel;

			} else if (i == 1) {
				continue; // skip a space
			} else {
				panel.setBounds(i * 50 + 450, 712, 50, 56);
				panel.setOpaque(false);
				// panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				add(panel);
				panel.add(bub, BorderLayout.NORTH);
				gunBubbleArr[i] = panel;

			}
		}
	}

	/**
	 * drawGamePanel, draws a boarder (made with borderFactory) around
	 * the screen which becomes the playable area
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
	 * drawSidePanel, draws a boarder (made with borderFactory) around
	 * the side of the screen which becomes the info area (time, score, etc)
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
	 * drawObjectives, gets the objectives from the 
	 * model and displays them on the screen
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
	 * mouseReleased, is an overrided method from mouseListener
	 * that lets the program know if the mouse has been released
	 * 
	 * this method is used as a shoot function to shoot the bubble
	 * 
	 * @param none
	 * @return none
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Mouse Clicked: bubble shoot");

		clicked = true;
		if (clicked) {
			mouseX = e.getX();
			mouseY = e.getY();
			System.out.println("Fire!");

			// System.out.println(mouseX + "," + mouseY);// these co-ords are relative to
			// the component

			Double y2 = (double) mouseY;
			Double y1 = (double) BubbleInGunY;

			Double x2 = (double) mouseX;
			Double x1 = (double) BubbleInGunX;

			System.out.println("mouseY: " + y2);
			System.out.println("MouseX: " + x2);
			// System.out.println("BubbleInGunY: " + y1);
			System.out.println();

			// System.out.println("BubbleInGunX: " + y1);

			slope = (double) ((y2 - y1) / (x2 - x1));

		}

	}

	/**
	 * mousePressed, is an overrided method from mouseListener
	 * that lets the program know if the mouse has been pressed
	 * 
	 * we do not use this method, but must be overrided from mouseListener
	 * 
	 * @param none
	 * @return none
	 */
	public void mousePressed(MouseEvent e) {
	} 
	/**
	 * mouseClicked, is an overrided method from mouseListener
	 * that lets the program know if the mouse has been clicked
	 * 
	 * we do not use this method, but must be overrided from mouseListener
	 * 
	 * @param none
	 * @return none
	 */
	public void mouseClicked(MouseEvent e) {

	}

	/**
	 * mouseEntered, is an overrided method from mouseListener
	 * that lets the program know if the mouse has been entered a particular area
	 * 
	 * we do not use this method, but must be overrided from mouseListener
	 * 
	 * @param none
	 * @return none
	 */
	public void mouseEntered(MouseEvent e) {
	} // do nothing

	/**
	 * mouseExited, is an overrided method from mouseListener
	 * that lets the program know if the mouse has been exited a particular area
	 * 
	 * we do not use this method, but must be overrided from mouseListener
	 * 
	 * @param none
	 * @return none
	 */
	public void mouseExited(MouseEvent e) {
	} // do nothing

}
