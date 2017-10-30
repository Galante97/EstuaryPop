import java.util.ArrayList;
import java.util.Random;

public class Objective { 
	
	//stored information about each ojective
	public String[] colors = {"red","orange","yellow","green","blue","purple"};
	public String[] gunImages = {"egg","trash","oyster","fish","horseshoe crab","ship"};
	public String[] gridImages = {"bird","recycle bin","otter","fisherman","hemocyanin collector","port"};
	//the common indices through each list make up a single objective
	
	//so when creating a bubble, it calls these methods to set the bubble's attributes
	public String returnColor(int i){
		return colors[i]; 
	}
	
	public String returnGunImg(int i){
		return gunImages[i];
	}
	
	public String returnGridImg(int i){
		return gridImages[i];
	}

}