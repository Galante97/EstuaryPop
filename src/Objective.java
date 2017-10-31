import java.util.ArrayList;
import java.util.Random;

public class Objective { 

	public String returnObjective;
	public String obj1 = "objective one";
	public String obj2 = "objective two";
	public String obj3 = "objective three"; 
	public String obj4 = "objective four";
	public String obj5 = "objective five";
	public String obj6 = "objective six";
	
	public String[] colors = {"red","orange","yellow","green","blue","purple"};
	public String[] gunImages = {"crab eggs","trash","Amebocytes collector","fish","fly","oyster"};
	public String[] gridImages = {"birds","recyle bin","horseshoe crab","fisherman","frog","otter"};

	//constructor
	public Objective() {

	}
	
	///////////////////////////////////////////////
	//methods for what we currently have in setGrid
	
	public String returnColor(int i){
		return colors[i];
	}
	
	public String returnGunImg(int i){
		return gunImages[i];
	}
	
	public String returnGridImg(int i){
		return gridImages[i];
	}
	
	///////////////////////////////////////////////

	//sets a single objective
	public String setObjective() {
		Random rand = new Random();
		int obj = rand.nextInt(6) + 1; // 6 is the maximum and the 1 is our minimum

		switch (obj) {
		case 1:
			returnObjective = obj1;
			break;
		case 2:
			returnObjective = obj2;
			break;
		case 3:
			returnObjective = obj3;
			break;
		case 4:
			returnObjective = obj4;
			break;
		case 5:
			returnObjective = obj5;
			break;
		case 6:
			returnObjective = obj6;
			break;

		default:
			returnObjective = "no objective set";
			break;
		}

		return returnObjective;
	}

}
