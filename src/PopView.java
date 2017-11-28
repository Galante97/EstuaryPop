
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
	JPanel menu;

	Gun gun;

	int gunAmmoLength = 8;
	JPanel[] gunBubbleArr = new JPanel[gunAmmoLength];
	JPanel[][] gridBubbleArr = new JPanel[15][13];
	int curBubbleArrNum = 0;

	JPanel timerPanel;
	JLabel timerLabel;
	JLabel scoreLabel;
	JPanel scorePanel;
	JLabel shiftLabel;
	JPanel oPanel;
	JLabel objLabel1;
	JLabel objLabel2;
	JLabel objLabel3;
	JLabel objLabel4;
	JLabel objLabel5;
	int counter = 0;

	JPanel BubbleInGun;
	int BubbleInGunX = 450; // for shooting
	int BubbleInGunY = 712; // for shooting
	int bubbleMovingPosX = 450; // for shooting
	int bubbleMovingPosY = 712; // for shooting
	double lineErrorHandlerY = BubbleInGunY;
	double m;

	int finalShootigPosX;
	int finalShootigPosY;

	int mouseX;
	int mouseY;

	int bubbleWH = 70;
	Double oscillationFactor = 5.5;
	int CurrPath;

	int bubbleShootPosX = 885;
	int bubbleShootPosY = 362;

	Timer timer;
	private final int DELAY = 1;
	int OcilationDelay = 0;

	PopModel model;
	private boolean clicked = false;
	boolean start = false;
	boolean BubbleMoving = false;

	ImageIcon checkbox = createImageIcon("checkbox.png", "");
	ImageIcon checkboxdone = createImageIcon("checkboxdone.png", "");


	MenuCustomMouseListener menuView; // mouselistener used to switch views between menu and game
	HowToPlayMouseListener howToPlay; // mouselistener used to switch views between menu and howToPLay
	EasyMouseListener easy = new EasyMouseListener();
	MediumMouseListener medium = new MediumMouseListener();
	HardMouseListener hard = new HardMouseListener();

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
		drawLoseBar();

		// drawObjectives();

		// drawGridForTesting();
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

				// System.out.print("["+j+","+i+"]" + " ");
				// System.out.print(" ");

				if (i == 0) { // set first row
					gridPanel.setBounds(j * bubbleWH + 30, i * bubbleWH + 17, bubbleWH, bubbleWH);
				} else {
					if (i % 2 == 0) { // stagering if statement
						gridPanel.setBounds(j * bubbleWH + 30, (i * (bubbleWH - 10)) + 17, bubbleWH, bubbleWH);
						System.out.print("[" + (j * bubbleWH + 30) + "," + (i * (bubbleWH - 10)) + 17 + "]" + " ");
					} else {
						gridPanel.setBounds(j * bubbleWH + 65, (i * (bubbleWH - 10)) + 17, bubbleWH, bubbleWH);
						System.out.print("[" + (j * bubbleWH + 65) + "," + (i * (bubbleWH - 10)) + 17 + "]" + " ");
					}
				}

				gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				gridPanel.setOpaque(false);
				add(gridPanel);
			}

			System.out.println();

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
		int rowAdder = 6;

		for (int i = 0; i < model.startRows + rowAdder; i++) {

			for (int j = 0; j < model.gridColumns; j++) {
				JPanel panel = new JPanel();

				if (i < 6 && model.grid[i][j] != null) {
					model.grid[i][j].xCoord = i * bubbleWH;
					model.grid[i][j].yCoord = j * bubbleWH;
					System.out.print(" [nil] ");
					panel.add(model.grid[i][j], BorderLayout.NORTH);

				}

				if (i == 0) {
					panel.setBounds(j * bubbleWH + 30, i * bubbleWH + 12, bubbleWH, bubbleWH + 6);
				} else {
					if (i % 2 == 0) { // staggering if statement

						panel.setBounds(j * bubbleWH + 30, (i * (bubbleWH - 10)) + 12, bubbleWH, bubbleWH + 6);
					} else {
						panel.setBounds(j * bubbleWH + 65, (i * (bubbleWH - 10)) + 12, bubbleWH, bubbleWH + 6);
					}
				}

				panel.setOpaque(false);
				add(panel);

				//System.out.print(" i: " + i + " j: " + j);
				gridBubbleArr[i][j] = panel;
				//gridBubbleArr[i][j].setBorder(BorderFactory.createLineBorder(Color.BLUE));
			}
			System.out.println("");
		}
	}

	public void updateGrid() {
		int rowAdder = 6;
		for (int i = 0; i < model.startRows + rowAdder; i++) {
			for (int j = 0; j < model.gridColumns; j++) {
				// System.out.print("[" + i + "," + j + "]");
				if (i < 10 && model.grid[i][j] == null) {
					gridBubbleArr[i][j].removeAll();
					gridBubbleArr[i][j].repaint();

				} else if (i < 10) {
					gridBubbleArr[i][j].add(model.grid[i][j], BorderLayout.NORTH);
					gridBubbleArr[i][j].repaint();

				}
			}
			// System.out.println();
		}

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
		panel.setBounds(BubbleInGunX - 92, BubbleInGunY - 150, gun.w + 50, gun.h);
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
				panel.setBounds(i * bubbleWH + BubbleInGunX, BubbleInGunY, bubbleWH, bubbleWH + 6);
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
	 * updateGunBubbles(), reloads the gun allowing the user to shoot again it
	 * changes the Position of the bubble and updates the back-end array
	 * 
	 * @param none
	 * @return none
	 */
	public void updateGunBubbles() {
		System.out.println("-Reload-");
		gunBubbleArr[0].setBounds(BubbleInGunX, BubbleInGunY, bubbleWH, bubbleWH + 6);

		for (int i = 0; i < 6; i++) {
			gunBubbleArr[i].removeAll();
			gunBubbleArr[i].add(model.gunList[i], BorderLayout.NORTH);
			// gunBubbleArr[i].setBorder(BorderFactory.createLineBorder(Color.BLUE));

			gunBubbleArr[i].repaint();

		}

	}

	protected ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public void drawLoseBar() {
		JPanel loseBar = new JPanel();
		loseBar.setBounds(10, 508, 975, 5);
		loseBar.setBackground(Color.RED);
		loseBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		loseBar.addMouseListener(this);
		add(loseBar);
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
		counter++;
		int delay = (counter / 675) * DELAY; // ONE SECOND

		if (counter % 675 == 0) { // avoid repaint issues
			timerLabel.setText((delay + " seconds"));
			timerLabel.repaint();
		}
		if (model.difficulty == 1) {
			if (model.objdone1[0] == true) {
				objLabel1.setIcon(checkboxdone);
				objLabel1.repaint();
			}
			if (model.objdone1[1] == true) {
				objLabel2.setIcon(checkboxdone);
				objLabel2.repaint();
			}
			if (model.objdone1[2] == true) {
				objLabel3.setIcon(checkboxdone);
				objLabel3.repaint();
			}
		}

		if (model.difficulty == 2) {
			if (model.objdone2[0] == true) {
				objLabel1.setIcon(checkboxdone);
				objLabel1.repaint();
			}
			if (model.objdone2[1] == true) {
				objLabel2.setIcon(checkboxdone);
				objLabel2.repaint();
			}
			if (model.objdone2[2] == true) {
				objLabel3.setIcon(checkboxdone);
				objLabel3.repaint();
			}
			if (model.objdone2[3] == true) {
				objLabel4.setIcon(checkboxdone);
				objLabel4.repaint();
			}
		}

		if (model.difficulty == 3) {
			if (model.objdone3[0] == true) {
				objLabel1.setIcon(checkboxdone);
				objLabel1.repaint();
			}
			if (model.objdone3[1] == true) {
				objLabel2.setIcon(checkboxdone);
				objLabel2.repaint();
			}
			if (model.objdone3[2] == true) {
				objLabel3.setIcon(checkboxdone);
				objLabel3.repaint();
			}
			if (model.objdone3[3] == true) {
				objLabel4.setIcon(checkboxdone);
				objLabel4.repaint();
			}
			if (model.objdone3[4] == true) {
				objLabel5.setIcon(checkboxdone);
				objLabel5.repaint();
			}
		}

		shiftLabel.setText("<html>" + (8 - model.shotsFired) + " shots until bubbles shift down" + "</html>");
		shiftLabel.repaint();

		OcilationDelay++;
		if (OcilationDelay % 50 == 0) { // slows down thre speed of the arrow
			if (gun.degree > 80) {
				oscillationFactor = -oscillationFactor;
			}
			if (gun.degree < -80) {
				oscillationFactor = -oscillationFactor;
			}
			gun.degree += oscillationFactor;
			gun.repaint();
		}
		if (clicked == true) {
			bubbleMovingPosX = BubbleInGunX;
			bubbleMovingPosY = BubbleInGunY;
			clicked = false;

		}
		if (BubbleMoving == true) {
			finalShootigPosX = model.pathX;// 695;
			finalShootigPosY = model.pathY;// 312;
			// BubbleInGunX = 450;
			// BubbleInGunY = 712;

			double x1 = bubbleMovingPosX;
			double y1 = bubbleMovingPosY;
			double x2 = finalShootigPosX;
			double y2 = finalShootigPosY;

			m = ((y2 - y1) / (x2 - x1)); // slope
			m = Math.abs(m);

			// condition to see if
			// bubble has made
			if (bubbleMovingPosX <= finalShootigPosX) { // changing bubble x //contact with other bubbles
				bubbleMovingPosX += 1;
			}

			if (bubbleMovingPosX >= finalShootigPosX) { // changing bubble x
				bubbleMovingPosX -= 1;

			}
			if (bubbleMovingPosY >= finalShootigPosY) { // changing bubble y
				lineErrorHandlerY -= m; // since grid is double this is needed
				bubbleMovingPosY = (int) lineErrorHandlerY;

			}

			BubbleInGun.setBounds(bubbleMovingPosX, bubbleMovingPosY, bubbleWH, bubbleWH + 6); // reseting bubble bounds

			if (bubbleMovingPosY <= finalShootigPosY) { // bubble made contact
				lineErrorHandlerY = BubbleInGunY; // reset
				updateGunBubbles(); // update
				updateGrid(); // update grid first then gun bubbles

				scoreLabel.setText("Score: " + model.score);
				scoreLabel.repaint();
				BubbleMoving = false;

				System.out.println("+++_+_+_+_+_++_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+_+");
			}
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

		sidePanel.setLayout(null);
		// add(sidePanel);
		// new code starts here, copy somewhere else before pulling/pushing
		// timer
		timerPanel = new JPanel();
		timerPanel.setBounds(988, 10, 190, 200);
		timerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		timerPanel.setBackground(Color.LIGHT_GRAY);
		timerLabel = new JLabel();
		timerLabel.setText("");
		timerLabel.setFont(timerLabel.getFont().deriveFont(32.0f));
		timerPanel.add(timerLabel);
		add(timerPanel);

		scorePanel = new JPanel(new BorderLayout());
		scorePanel.setBounds(988, 210, 190, 200);
		scorePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		scorePanel.setBackground(Color.LIGHT_GRAY);
		scoreLabel = new JLabel();
		scoreLabel.setText("");
		scoreLabel.setFont(scoreLabel.getFont().deriveFont(32.0f));
		scorePanel.add(scoreLabel);
		add(scorePanel);

		shiftLabel = new JLabel();
		shiftLabel.setText("<html>" + (8 - model.shotsFired) + " shots until bubbles shift down" + "</html>");
		shiftLabel.setFont(scoreLabel.getFont().deriveFont(16.0f));
		scorePanel.add(shiftLabel, BorderLayout.SOUTH);
		add(scorePanel);

		if (model.difficulty == 1) {

			oPanel = new JPanel();
			BoxLayout box = new BoxLayout(oPanel, BoxLayout.Y_AXIS);

			oPanel.setBounds(988, 410, 190, 332);
			oPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			oPanel.setBackground(Color.WHITE);

			JTextArea text1 = new JTextArea(model.o.returnStatements(model.objectives1[0]));
			text1.setWrapStyleWord(true);
			text1.setLineWrap(true);
			oPanel.add(text1);

			objLabel1 = new JLabel(checkbox);// JLabel("<html>" + model.o.returnStatements(model.objectives1[0]) +
												// "</html>", checkbox, JLabel.CENTER);
			// objLabel1.setText("<html>" + model.o.returnStatements(model.objectives1[0]) +
			// "</html>");
			objLabel1.setFont(objLabel1.getFont().deriveFont(16.0f));
			objLabel1.setSize(10, 10);
			oPanel.add(objLabel1);

			JTextArea text2 = new JTextArea(model.o.returnStatements(model.objectives1[1]));
			text2.setWrapStyleWord(true);
			text2.setLineWrap(true);
			oPanel.add(text2);

			objLabel2 = new JLabel(checkbox);
			// objLabel2.setText("<html>" + model.o.returnStatements(model.objectives1[1]) +
			// "</html>");
			objLabel2.setFont(objLabel2.getFont().deriveFont(16.0f));
			objLabel2.setSize(10, 10);
			oPanel.add(objLabel2);

			JTextArea text3 = new JTextArea(model.o.returnStatements(model.objectives1[2]));
			text3.setWrapStyleWord(true);
			text3.setLineWrap(true);
			oPanel.add(text3);

			objLabel3 = new JLabel(checkbox);
			// objLabel3.setText("<html>" + model.o.returnStatements(model.objectives1[2]) +
			// "</html>");
			objLabel3.setFont(objLabel3.getFont().deriveFont(16.0f));
			objLabel3.setSize(10, 10);
			oPanel.add(objLabel3);

			add(oPanel);
		}

		if (model.difficulty == 2) {

			oPanel = new JPanel();
			BoxLayout box = new BoxLayout(oPanel, BoxLayout.Y_AXIS);

			oPanel.setBounds(988, 410, 190, 332);
			oPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			oPanel.setBackground(Color.WHITE);

			JTextArea text1 = new JTextArea(model.o.returnStatements(model.objectives2[0]));
			text1.setWrapStyleWord(true);
			text1.setLineWrap(true);
			oPanel.add(text1);

			objLabel1 = new JLabel(checkbox);
			oPanel.add(objLabel1, BorderLayout.NORTH);
			// objLabel1.setText("<html>" + model.o.returnStatements(model.objectives2[0]) +
			// "</html>");
			objLabel1.setFont(objLabel1.getFont().deriveFont(14.0f));
			objLabel1.setSize(8, 8);

			JTextArea text2 = new JTextArea(model.o.returnStatements(model.objectives2[1]));
			text2.setWrapStyleWord(true);
			text2.setLineWrap(true);
			oPanel.add(text2);

			objLabel2 = new JLabel(checkbox);
			oPanel.add(objLabel2, BorderLayout.WEST);
			// objLabel2.setText("<html>" + model.o.returnStatements(model.objectives2[1]) +
			// "</html>");
			objLabel2.setFont(objLabel2.getFont().deriveFont(14.0f));
			objLabel2.setSize(8, 8);
			oPanel.add(objLabel2);

			JTextArea text3 = new JTextArea(model.o.returnStatements(model.objectives2[2]));
			text3.setWrapStyleWord(true);
			text3.setLineWrap(true);
			oPanel.add(text3);

			objLabel3 = new JLabel(checkbox);
			oPanel.add(objLabel3, BorderLayout.SOUTH);
			// objLabel3.setText("<html>" + model.o.returnStatements(model.objectives2[2]) +
			// "</html>");
			objLabel3.setFont(objLabel3.getFont().deriveFont(14.0f));
			objLabel3.setSize(8, 8);
			oPanel.add(objLabel3);

			JTextArea text4 = new JTextArea(model.o.returnStatements(model.objectives2[3]));
			text4.setWrapStyleWord(true);
			text4.setLineWrap(true);
			oPanel.add(text4);

			objLabel4 = new JLabel(checkbox);
			oPanel.add(objLabel4, BorderLayout.SOUTH);
			// objLabel4.setText("<html>" + model.o.returnStatements(model.objectives2[3]) +
			// "</html>");
			objLabel4.setFont(objLabel4.getFont().deriveFont(14.0f));
			objLabel4.setSize(8, 8);
			oPanel.add(objLabel4);

			add(oPanel);
		}
		if (model.difficulty == 3) {
			oPanel = new JPanel();
			BoxLayout box = new BoxLayout(oPanel, BoxLayout.Y_AXIS);

			oPanel.setBounds(988, 410, 190, 332);
			oPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			oPanel.setBackground(Color.WHITE);

			JTextArea text1 = new JTextArea(model.o.returnStatements(model.objectives3[0]));
			text1.setWrapStyleWord(true);
			text1.setLineWrap(true);
			oPanel.add(text1);

			objLabel1 = new JLabel(checkbox);
			oPanel.add(objLabel1, BorderLayout.NORTH);
			// objLabel1.setText("<html>" + model.o.returnStatements(model.objectives3[0]) +
			// "</html>");
			objLabel1.setFont(objLabel1.getFont().deriveFont(12.0f));
			objLabel1.setSize(7, 7);

			JTextArea text2 = new JTextArea(model.o.returnStatements(model.objectives3[1]));
			text2.setWrapStyleWord(true);
			text2.setLineWrap(true);
			oPanel.add(text2);

			objLabel2 = new JLabel(checkbox);
			oPanel.add(objLabel2, BorderLayout.WEST);
			// objLabel2.setText("<html>" + model.o.returnStatements(model.objectives3[1]) +
			// "</html>");
			objLabel2.setFont(objLabel2.getFont().deriveFont(12.0f));
			objLabel2.setSize(7, 7);
			oPanel.add(objLabel2);

			JTextArea text3 = new JTextArea(model.o.returnStatements(model.objectives3[2]));
			text3.setWrapStyleWord(true);
			text3.setLineWrap(true);
			oPanel.add(text3);

			objLabel3 = new JLabel(checkbox);
			oPanel.add(objLabel3, BorderLayout.WEST);
			// objLabel3.setText("<html>" + model.o.returnStatements(model.objectives3[2]) +
			// "</html>");
			objLabel3.setFont(objLabel2.getFont().deriveFont(12.0f));
			objLabel3.setSize(7, 7);
			oPanel.add(objLabel3);

			JTextArea text4 = new JTextArea(model.o.returnStatements(model.objectives3[3]));
			text4.setWrapStyleWord(true);
			text4.setLineWrap(true);
			oPanel.add(text4);

			objLabel4 = new JLabel(checkbox);
			oPanel.add(objLabel4, BorderLayout.SOUTH);
			// objLabel4.setText("<html>" + model.o.returnStatements(model.objectives3[3]) +
			// "</html>");
			objLabel4.setFont(objLabel4.getFont().deriveFont(12.0f));
			objLabel4.setSize(7, 7);
			oPanel.add(objLabel4);

			JTextArea text5 = new JTextArea(model.o.returnStatements(model.objectives3[4]));
			text5.setWrapStyleWord(true);
			text5.setLineWrap(true);
			oPanel.add(text5);

			objLabel5 = new JLabel(checkbox);
			oPanel.add(objLabel5, BorderLayout.SOUTH);
			// objLabel5.setText("<html>" + model.o.returnStatements(model.objectives3[4]) +
			// "</html>");
			objLabel5.setFont(objLabel5.getFont().deriveFont(12.0f));
			objLabel5.setSize(7, 7);
			oPanel.add(objLabel5);

			add(oPanel);
		}

	}

	/**
	 * drawObjectives, gets the objectives from the model and displays them on the
	 * screen
	 * 
	 * @param none
	 * @return none
	 */

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
		BubbleMoving = true;

		if (clicked) {
			mouseX = e.getX();
			mouseY = e.getY();

			if (gun.degree >= -80 && gun.degree < -74.5) {
				System.out.println("PATH 0");
				model.clickedPath = 0;
				model.userClicked = true;
			} else if (gun.degree >= -74.5 && gun.degree < -69) {
				System.out.println("PATH 1");
				model.clickedPath = 1;
				model.userClicked = true;
			} else if (gun.degree >= -74.5 && gun.degree < -69) {
				System.out.println("PATH 2");
				model.clickedPath = 2;
				model.userClicked = true;
			} else if (gun.degree >= -69 && gun.degree < -63.5) {
				System.out.println("PATH 3");
				model.clickedPath = 3;
				model.userClicked = true;
			} else if (gun.degree >= -63.5 && gun.degree < -58) {
				System.out.println("PATH 4");
				model.clickedPath = 4;
				model.userClicked = true;
			} else if (gun.degree >= -58 && gun.degree < -52.5) {
				System.out.println("PATH 5");
				model.clickedPath = 5;
				model.userClicked = true;
			} else if (gun.degree >= -52.5 && gun.degree < -47) {
				System.out.println("PATH 6");
				model.clickedPath = 6;
				model.userClicked = true;
			} else if (gun.degree >= -47 && gun.degree < -41.5) {
				System.out.println("PATH 7");
				model.clickedPath = 7;
				model.userClicked = true;
			} else if (gun.degree >= -41.5 && gun.degree < -36) {
				System.out.println("PATH 8");
				model.clickedPath = 8;
				model.userClicked = true;
			} else if (gun.degree >= -36 && gun.degree < -30.5) {
				System.out.println("PATH 9");
				model.clickedPath = 9;
				model.userClicked = true;
			} else if (gun.degree >= -30.5 && gun.degree < -25) {
				System.out.println("PATH 10");
				model.clickedPath = 10;
				model.userClicked = true;
			} else if (gun.degree >= -25 && gun.degree < -19.5) {
				System.out.println("PATH 11");
				model.clickedPath = 11;
				model.userClicked = true;
			} else if (gun.degree >= -19.5 && gun.degree < -14) {
				System.out.println("PATH 12");
				model.clickedPath = 12;
				model.userClicked = true;
			} else if (gun.degree >= -14 && gun.degree < -8.5) {
				System.out.println("PATH 13");
				model.clickedPath = 13;
				model.userClicked = true;
			} else if (gun.degree >= -8.5 && gun.degree < -3) {
				System.out.println("PATH 14");
				model.clickedPath = 14;
				model.userClicked = true;
			} else if (gun.degree >= -3 && gun.degree < 2.5) {
				System.out.println("PATH 15");
				model.clickedPath = 15;
				model.userClicked = true;
			} else if (gun.degree >= 2.5 && gun.degree < 8) {
				System.out.println("PATH 16");
				model.clickedPath = 16;
				model.userClicked = true;
			} else if (gun.degree >= 8 && gun.degree < 13.5) {
				System.out.println("PATH 17");
				model.clickedPath = 17;
				model.userClicked = true;
			} else if (gun.degree >= 13.5 && gun.degree < 19) {
				System.out.println("PATH 18");
				model.clickedPath = 18;
				model.userClicked = true;
			} else if (gun.degree >= 19 && gun.degree < 24.5) {
				System.out.println("PATH 19");
				model.clickedPath = 19;
				model.userClicked = true;
			} else if (gun.degree >= 24.5 && gun.degree < 30) {
				System.out.println("PATH 20");
				model.clickedPath = 20;
				model.userClicked = true;
			} else if (gun.degree >= 30 && gun.degree < 35.5) {
				System.out.println("PATH 21");
				model.clickedPath = 21;
				model.userClicked = true;
			} else if (gun.degree >= 35.5 && gun.degree < 41) {
				System.out.println("PATH 22");
				model.clickedPath = 22;
				model.userClicked = true;
			} else if (gun.degree >= 41 && gun.degree < 46.5) {
				System.out.println("PATH 23");
				model.clickedPath = 23;
				model.userClicked = true;
			} else if (gun.degree >= 46.5 && gun.degree < 52) {
				System.out.println("PATH 24");
				model.clickedPath = 24;
				model.userClicked = true;
			} else if (gun.degree >= 52 && gun.degree < 57.5) {
				System.out.println("PATH 25");
				model.clickedPath = 25;
				model.userClicked = true;
			} else if (gun.degree >= 57.5 && gun.degree < 63) {
				System.out.println("PATH 26");
				model.clickedPath = 26;
				model.userClicked = true;
			} else if (gun.degree >= 63 && gun.degree < 68.5) {
				System.out.println("PATH 27");
				model.clickedPath = 27;
				model.userClicked = true;
			} else if (gun.degree >= 74 && gun.degree < 80) {
				System.out.println("PATH 28");
				model.clickedPath = 28;
				model.userClicked = true;
			}

		}

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
	 *
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

	/**
	 * method is the entry point for the game and brings the user to the menu
	 * screen. MouseListeners are watching buttons to fire off intended action at
	 * the home screen
	 * 
	 */

	public void drawMenu() {
		JPanel menu = new JPanel();
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/estMenu.jpg")); // https://coast.noaa.gov/estuaries/curriculum/climate-extension.html
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		setContentPane(new JLabel(imageIcon));
		menu.setBounds(375, 400, 450, 50);
		menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JButton b1 = new JButton("How To Play");
		b1.setBounds(50, 100, 80, 30);
		b1.setBackground(Color.yellow);
		b1.addMouseListener(howToPlay);
		JButton b2 = new JButton("Start Game");
		b2.setBounds(100, 100, 80, 30);
		b2.setBackground(Color.green);
		b2.addMouseListener(menuView);
		JButton b3 = new JButton("View HighScores");
		b3.setBounds(150, 100, 80, 30);
		b3.setBackground(Color.red);
		menu.add(b1);
		menu.add(b2);
		//menu.add(b3);
		add(menu);
		setTitle("Start Menu");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

	}

	public void drawHowToPlay() {
		JPanel menu = new JPanel();
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/estMenu.jpg")); // https://coast.noaa.gov/estuaries/curriculum/climate-extension.html
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		setContentPane(new JLabel(imageIcon));
		menu.setBounds(200, 400, 800, 150);
		menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JLabel jlabel = new JLabel(
				"<html>The gun moves automatically from side to side.<br> " + " Use the left mouse button to fire! <br>"
						+ "Take some shots at matching bubbles above<br>" + " to pop them!</html");
		jlabel.setFont(new Font("Verdana", 1, 20));
		menu.add(jlabel);
		JButton b1 = new JButton("OK, Start Game!");
		b1.setBounds(0, 100, 80, 30);
		b1.setBackground(Color.yellow);
		b1.addMouseListener(new MenuCustomMouseListener());
		menu.add(b1);
		add(menu);
		setTitle("How to Play");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

	}
	

	public void drawLoseScreen(){
		JPanel menu = new JPanel();
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/gameOver.png")); // https://coast.noaa.gov/estuaries/curriculum/climate-extension.html
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		setContentPane(new JLabel(imageIcon));
		menu.setBounds(200, 400, 800, 150);
		menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JButton b1 = new JButton("Try Again");
		b1.setBounds(0, 100, 80, 30);
		b1.setBackground(Color.yellow);
		b1.addMouseListener(new PlayAgainListener());
		JButton b2 = new JButton("Quit");
		b2.setBounds(0, 100, 80, 30);
		b2.setBackground(Color.yellow);
		b2.addMouseListener(new YouLoseListener());
		menu.add(b1);
		menu.add(b2);
		add(menu);
		setTitle("Game Over");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
	}

	
	public void drawDifficutlyMenu(){
		JPanel menu = new JPanel();
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/estMenu.jpg")); // https://coast.noaa.gov/estuaries/curriculum/climate-extension.html
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		setContentPane(new JLabel(imageIcon));
		menu.setBounds(375, 400, 450, 50);
		menu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		JButton b1 = new JButton("Easy");
		b1.setBounds(50, 100, 80, 30);
		b1.setBackground(Color.yellow);
		b1.addMouseListener(easy);
		JButton b2 = new JButton("Medium");
		b2.setBounds(100, 100, 80, 30);
		b2.setBackground(Color.green);
		b2.addMouseListener(medium);
		JButton b3 = new JButton("Hard");
		b3.setBounds(150, 100, 80, 30);
		b3.setBackground(Color.red);
		b3.addMouseListener(hard);
		menu.add(b1);
		menu.add(b2);
		menu.add(b3);
		add(menu);
		setTitle("Start Menu");setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
}



}