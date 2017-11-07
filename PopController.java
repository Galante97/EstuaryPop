import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class PopController {

	public int difficulty; // determines speed of game
	static boolean won = false;
	static boolean playAgain = true;
	private PopModel model;
	private PopView view;
	private MouseListener mouseListener;
	
	private Bubble[][] grid;
	public int degree;
	public double xCo;
	public double yCo;

	public static void main(String[] args) {
		
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {                                           
				PopModel model = new PopModel(1);
				PopView view = new PopView(model);
				PopController controller = new PopController(model,view);
				controller.control();
				model.moveGun(); //since this needs to move in every frame
			}
		});
		
		
	
	//	view.setVisible(true);
		/*/*
		for (Bubble[] x : model.grid) {
			System.out.println("");
			for (Bubble y : x) {
				System.out.print(y + " ");
			}
		}
		while (playAgain) {

			/*
			 * model.setGrid(); model.loadGun(); while(!won){ model.moveGun(); } if(won){
			 * //ask if you want to play again }
			 */
		


	}

	
	public PopController(PopModel model, PopView view) {
		this.model = model;
		this.view = view;
	}
	
	public void control() {
		mouseListener = new MouseListener() {
		
		// changes clicked to true if the mouse has been clicked in the game panel
		public void mouseReleased(MouseEvent e) {
			
			updateView();
			
		}
				
		// required functions for mouse listener - these do nothing
		public void mousePressed(MouseEvent e) {} // do nothing
		
		public void mouseClicked(MouseEvent e) {}// do nothing

		public void mouseEntered(MouseEvent e) {} // do nothing

		public void mouseExited(MouseEvent e) {} // do nothing

		};
		view.getGamePanel().addMouseListener(mouseListener);
		//gets reference to view's gamePanel and assigns mouseListener to it. mouse
		//when mousereleased, we call link panel and  
	}
	
	public void setGrid() {
		grid = model.grid;
		
	}
	
	public void setGunDegree() {
		degree = model.getDegree();
	}
	
	public void setGunXCo() {
		xCo = model.getGunXCo();
	}
	
	public void setGunYCo() {
		yCo = model.getGunYCo();
	}
	
	public void updateView() {
		System.out.println("click");
		model.shoot(90, 450, 500);
		//
		//view.setGrid, set gunBubble, etc.
		
	}
}
