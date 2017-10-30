import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PopController {
	public static final int EASY = 0;
	public static final int MEDIUM = 1;
	public static final int HARD = 1;

	public int difficulty; // determines speed of game
	boolean clicked;
	
	PopModel model;
	PopView view;

	

	// creates a new game with input on difficulty
	public PopController(PopModel model, PopView view) {
		this.model = model;
		this.view = view;
	}


	public static void main(String[] args) {
		PopModel model = new PopModel();
		PopView view = new PopView(model); // creates the window
		PopController controller = new PopController(model, view); //creates game
		
		controller.setObjectives(); //randomly generates objectives
		view.draw();

	}
	
	public void setClicked() {
		model.setClicked(clicked);
	}
	
	public void getClicked() {
		clicked = view.getClicked();
	}

	// picks 3 random objectives from "Objectives" class
	public void setObjectives() {
		Objective obj = new Objective();
	
		String objective1 = obj.setObjective();
	    String objective2 = obj.setObjective();
		String objective3 = obj.setObjective();
		
		// make sure objective 2 != objective 2
		while (objective2 == objective1) {
			objective2 = obj.setObjective();
		}

		// make sure objective 3 != objective 2/3
		while (objective3 == objective1 || objective3 == objective2) {
			objective3 = obj.setObjective();
		}

		model.objectives.add(objective1);
		model.objectives.add(objective2);
		model.objectives.add(objective3);
		
	
		
	}

	public void setGrid() {

	}

	public void setGunList() {

	}
	

}
