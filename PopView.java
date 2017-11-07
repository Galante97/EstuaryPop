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

public class PopView extends JFrame {

	JFrame frame;
	private JPanel sidePanel;
	private JPanel gamePanel;

	JPanel[] gunBubbleArr = new JPanel[8];
	int curBubbleArrNum = 0;

	JPanel BubbleInGun;
	double BubbleInGunX;
	double BubbleInGunY;
	int mouseX;
	int mouseY;
	Double slope;

	Timer timer;
	private final int DELAY = 1;

	PopModel model;
	

	// constructor
	public PopView(PopModel Model) {  
		this.model = Model;
		frame = new JFrame();
		draw();
	}

	// sets up frame and calls all draw functions
	public void draw() {
		frame.getContentPane().setLayout(new BorderLayout());
		drawSidePanel();
		drawObjectives();
		drawGunBubbles();
		drawGun();
		drawGridBubbles();
		drawGamePanel();

		setTitle("Estuary Pop!");
		setSize(1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		setVisible(true);


		System.out.println("Everything Drawn: Welcome to Estuary Pop");
	}
	
	
	
	
	

	
	public void updateGrid() { //EVERY TIME WE ADD A BUBBLE TO GRID, WE MUST REDRAW GRID BUBBLE
		
	}
	
	public void updateGunBubbles() { //EVERY TIME WE SHOOT BUBBLE, WE MUST REDRAW GUN BUBBLES
		
	}
	
	public void updateBubbleInGun() { //EVERY TIME A BUBBLE MOVES, WE MUST REDRAW BUBBLE IN GUN
		
	}
	
	public void updateGun() { //EVERY TIME AN OBJECTIVE CHANGES STATUS, WE MUST REDRAW OBJECTIVES
		
	}
	
	public void updateObjectives() { //DRAW GUN??? UPDATES EVERY FRAME BECAUSE OF ROTATION??
		
	}
	

	public void drawGridBubbles() {
		Bubble[][] grid = model.grid;
		for (int i = 0; i < model.gridRows; i++) {
			for (int j = 0; j < model.startGridColumns; j++) {
				JPanel panel = new JPanel();
				
				panel.setBounds(i * 50 + 45, j * 50 + 12, 50, 56);
				panel.setOpaque(false);
				panel.add(grid[i][j], BorderLayout.NORTH);
				getContentPane().add(panel);
			}
		}
		
		System.out.println("Draw grid bubbles");

	}

	public void drawGun() {

		JPanel panel = new JPanel();
		Gun gun = new Gun();
		panel.setBounds(410, 400, 131, 120);
		panel.setOpaque(false);
		// panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		panel.add(gun, BorderLayout.NORTH);
		getContentPane().add(panel);

		System.out.println("Draw Gun");

	}
/*
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
*/
	public void drawGunBubbles() {
		System.out.println("Draw Gun Bubbles");
		
		//need getter for these
		BubbleInGunX = model.getGunXCo();
		BubbleInGunY = model.getGunYCo();

		for (int i = 0; i < model.gunList.length; i++) { //need getter here

			JPanel panel = new JPanel();
	
			panel.setOpaque(false);

			if (i == 0) {
				BubbleInGun = panel;
				panel.setBounds(i * 50 + 450, 500, 50, 56);
				panel.setOpaque(false);	
				panel.add(model.gunList[i], BorderLayout.NORTH); //need getter here
				getContentPane().add(panel);
			

			} else if (i == 1) {
				continue; // skip a space
			} else {
				panel.setBounds(i * 50 + 450, 500, 50, 56);
				panel.setOpaque(false);
				panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				panel.add(model.gunList[i], BorderLayout.NORTH); //need getter here
				getContentPane().add(panel);
			

			}
		}
	}

	public void drawGamePanel() {
		System.out.println("Draw game panel");

		gamePanel = new JPanel();
		gamePanel.setBounds(10, 10, 975, 760);
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		getContentPane().add(gamePanel);  

	}

	public void drawSidePanel() {
		System.out.println("Draw info panel");

		sidePanel = new JPanel();
		sidePanel.setBounds(990, 10, 200, 760);
		sidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sidePanel.setBackground(Color.LIGHT_GRAY);

		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		getContentPane().add(sidePanel);
	}

	// gets objectives from model and creates jlabels
	public void drawObjectives() {
		System.out.println("Draw Objectives");
		//need getters
		JLabel objLabel1 = new JLabel(model.objs[0].returnObj(), null, JLabel.CENTER);
		JLabel objLabel2 = new JLabel(model.objs[1].returnObj(), null, JLabel.CENTER);
		JLabel objLabel3 = new JLabel(model.objs[2].returnObj(), null, JLabel.CENTER);

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
	
	public JPanel getGamePanel() {
		return gamePanel;
	}


}