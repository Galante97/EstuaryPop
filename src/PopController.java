import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PopController {
	public static final int EASY = 0;
	public static final int MEDIUM = 1;
	public static final int HARD = 1;

	
	public int difficulty; //determines speed of game

	static Collection<String> Objectives = new ArrayList<String>(); 
	public String objective1 = "no objective set";
	public String objective2 = "no objective set";
	public String objective3 = "no objective set";

 
	//creates a new game with input on difficulty
	public PopController(int diff) {
		this.difficulty = diff;
	}
	
	public static void main(String[] args) {
		PopController Game = new PopController(EASY);
		Game.setObjectives();
		
		 EventQueue.invokeLater(() -> {
	            PopView frame = new PopView();
	            frame.setVisible(true);
	        });
		
		
	}
	
	//picks 3 random objectives from "Objectives" class
	public void setObjectives() {
		Objective obj = new Objective();
	
		objective1 = obj.setObjective();
		objective2 = obj.setObjective();
		
		//make sure objective 2 != objective 2
		while (objective2 == objective1) {
			objective2 = obj.setObjective();
		}
		
		objective3 = obj.setObjective();
		
		//make sure objective 3 != objective 2/3
		while (objective3 == objective1 || objective3 == objective2) { 
			objective3 = obj.setObjective();
		}
		
		Objectives.add(objective1);
		Objectives.add(objective2);
		Objectives.add(objective3);
	}
	
	
	public void setGrid() {
		
	}
	
	public void setGunList() {
		
	}
	
	
}


