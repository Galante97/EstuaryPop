import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PopController {

	public int difficulty; // determines speed of game
	static boolean won = false;
	static boolean playAgain = true;
	boolean clicked;
	
	public static void main(String[] args) {
		while(playAgain){
			PopModel model = new PopModel(1);//will ask for difficulty
			PopView view = new PopView(model); // creates the window
			model.setGrid();
			model.loadGun();
			while(!won){
				model.moveGun();
			}
			if(won){
				//ask if you want to play again
			}
		}

	}
	
	public void setClicked() {
		model.setClicked(clicked);
	}
	
	public void getClicked() {
		clicked = view.getClicked();
	}
}
