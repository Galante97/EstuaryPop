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

public class PopView extends JFrame implements MouseListener, ActionListener {

	JFrame frame;
	JPanel sidePanel;
	JPanel gamePanel;

	JPanel BubbleInGun;
	int BubbleInGunX;
	int BubbleInGunY;
	int mouseX;
	int mouseY;

	Timer timer;
	private final int DELAY = 25;

	PopModel model;
	private boolean clicked = false;

	// constructor
	public PopView(PopModel Model, JFrame frame) {
		this.model = Model;
		this.frame = frame;

	}

	public boolean getClicked() {
		return clicked;
	}

	public void setClicked(boolean click) {
		clicked = click;
	}

	// sets up frame and calls all draw functions
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

		System.out.println("Everything Drawn");
	}

	public void drawGridBubbles() {
		for (int i = 0; i < model.gridColumns; i++) {
			for (int j = 0; j < model.gridRows; j++) {
				JPanel panel = new JPanel();
				Bubble bub = new Bubble(i * 50, j * 50);
				model.grid[i][j] = bub; // ADDS TO MODEL BUBBLE GRID LIST
				panel.setBounds(i * 50 + 45, j * 50 + 12, 50, 56);
				panel.setOpaque(false);
				add(panel);
				panel.add(bub, BorderLayout.NORTH);
			}
		}

	}

	public void drawGun() {

		JPanel panel = new JPanel();
		Gun gun = new Gun();
		panel.setBounds(410, 649, 131, 120);
		panel.setOpaque(false);
		// panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(panel);
		panel.add(gun, BorderLayout.NORTH);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (clicked == true) {
		//	BubbleInGunX;
		//	BubbleInGunY;
		//	MouseX;
		//	MouseY;
			
			BubbleInGunY--;
			BubbleInGun.setBounds(mouseX, mouseY, 50, 56);
		}

	}

	public void drawGunBubbles() {
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

			} else if (i == 1) {
				continue; // skip a space
			} else {
				panel.setBounds(i * 50 + 450, 712, 50, 56);
				panel.setOpaque(false);
				// panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				add(panel);
				panel.add(bub, BorderLayout.NORTH);

			}
		}
	}

	public void drawGamePanel() {
		gamePanel = new JPanel();
		gamePanel.setBounds(10, 10, 975, 760);
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gamePanel.addMouseListener(this);
		add(gamePanel);

	}

	public void drawSidePanel() {
		sidePanel = new JPanel();
		sidePanel.setBounds(990, 10, 200, 760);
		sidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sidePanel.setBackground(Color.LIGHT_GRAY);

		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		add(sidePanel);
	}

	// gets objectives from model and creates jlabels
	public void drawObjectives() {
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

	// changes clicked to true if the mouse has been clicked in the game panel

	@Override
	public void mouseReleased(MouseEvent e) {
		clicked = true;
		if (clicked) {
			System.out.println("Fire!");
			 mouseX = e.getX();
		     mouseY = e.getY();
		    System.out.println(mouseX + "," + mouseY);//these co-ords are relative to the component

		}
		//clicked = false;
	}

	// required functions for mouse listener - these do nothing
	public void mousePressed(MouseEvent e) {
	} // do nothing

	public void mouseClicked(MouseEvent e) {
	}// do nothing

	public void mouseEntered(MouseEvent e) {
	} // do nothing

	public void mouseExited(MouseEvent e) {
	} // do nothing

}
