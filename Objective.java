import java.util.ArrayList;
import java.util.Random;

public class Objective { 
	
	public int objectiveNum;

	
	
	
	public static String[] objectives = {"objective one", "objective two", "objective three", "objective four", "objective five", "objective six" };
	
	public static String[] colors = {"red","orange","yellow","green","blue","purple"};
	public static String[] gunImages = {"crab eggs","trash","Amebocytes collector","fish","fly","oyster"};
	public static String[] gridImages = {"birds","recyle bin","horseshoe crab","fisherman","frog","otter"};

	//constructor
	public Objective(int obj) {
		objectiveNum = obj;

	}
	
	public Objective() {
		objectiveNum = 100;

	}
	
	///////////////////////////////////////////////
	//Methods for accessing a specific Objective
	
	public String returnColor(){
		return colors[objectiveNum];
	}
	
	public String returnGunImg(){
		return gunImages[objectiveNum];
	}
	
	public String returnGridImg(){
		return gridImages[objectiveNum];
	}
	
	public String returnObj() {
		return objectives[objectiveNum];
	}
	

	// Static methods for accessing a field without Objective instantiation
	public static String returnRandColor(int i){
		return colors[i];
	}
	
	public static String returnRandGunImg(int i){
		return gunImages[i];
	}
	
	public static String returnRandGridImg(int i){
		return gridImages[i];
	}
	
	public static String returnRandObj(int i) {
		return objectives[i];
	}
	///////////////////////////////////////////////


}
