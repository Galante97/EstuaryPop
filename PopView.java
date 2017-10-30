import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PopView extends JFrame implements MouseListener {

	JFrame frame;
	JPanel sidePanel;
	JPanel gamePanel;
	PopModel model;
	private boolean clicked = false;

	// constructor
	public PopView(PopModel Model) {
		this.model = Model;

	}
	
	public boolean getClicked() {
		return clicked;
	}
	
	public void setClicked(boolean click) {
		clicked = click;
	}

	// sets up frame and calls all draw functions
	public void draw() {
		frame = new JFrame();
		frame.setTitle("Estuary Pop!");

		drawSidePanel();
		drawObjectives();

		drawGamePanel();

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setSize(1200, 800);// 800 width and 1200 height
		frame.setLayout(null);// using no layout managers
		frame.setVisible(true);// making the frame visible

		System.out.println("Everything Drawn");
	}

	public void drawGamePanel() {
		gamePanel = new JPanel();
		gamePanel.setBounds(10, 10, 975, 760);
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		gamePanel.addMouseListener(this);
		frame.add(gamePanel);
		

	}

	public void drawSidePanel() {
		sidePanel = new JPanel();
		sidePanel.setBounds(990, 10, 200, 760);
		sidePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		sidePanel.setBackground(Color.LIGHT_GRAY);

		sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
		frame.add(sidePanel);
	}

	// gets objectives from model and creates jlabels
	public void drawObjectives() {
		JLabel objLabel1 = new JLabel(model.objectives.get(0), null, JLabel.CENTER);
		JLabel objLabel2 = new JLabel(model.objectives.get(1), null, JLabel.CENTER);
		JLabel objLabel3 = new JLabel(model.objectives.get(2), null, JLabel.CENTER);

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
	

	//changes clicked to true if the mouse has been clicked in the game panel
	@Override
	public void mouseClicked(MouseEvent e) {
		clicked = true;
	}

	//required functions for mouse listener - these do nothing
	public void mousePressed(MouseEvent e) {} //do nothing

	public void mouseReleased(MouseEvent e) {} //do nothing

	public void mouseEntered(MouseEvent e) {} //do nothing

	public void mouseExited(MouseEvent e) {} //do nothing
	
	

}
