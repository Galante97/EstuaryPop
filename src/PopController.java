
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JFrame;

public class PopController {

	public int difficulty; // determines speed of game
	static boolean won = false; 
	static boolean playAgain = true;
	boolean clicked;

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		PopModel model = new PopModel(1);// will ask for difficulty
		//model.setGrid();
		PopView view = new PopView(model, frame); // creates the window
		view.draw();
		view.setVisible(true);
		
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

	}

	public void setClicked() {
		model.setClicked(clicked); 
	}

	public void getClicked() {
		clicked = view.getClicked();
	}
}


