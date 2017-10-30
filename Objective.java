import java.util.ArrayList;
import java.util.Random;

public class Objective { 
	
	String color;
	String gunImg;
	String gridImg;
	String objec;

	public String returnObjective;
	public String obj1 = "objective one";
	public String obj2 = "objective two";
	public String obj3 = "objective three";
	public String obj4 = "objective four";
	public String obj5 = "objective five";
	public String obj6 = "objective six";

	//constructor
	public Objective() {
		setObjective();
	}

	//sets a single objective
	public void setObjective() {
		Random rand = new Random();
		int obj = rand.nextInt(6) + 1; // 6 is the maximum and the 1 is our minimum

		switch (obj) {
		case 1:
			objec = obj1;
			color = "blue";
			gunImg = "egg";
			gridImg = "bird";
			break;
		case 2:
			objec = obj2;
			color = "orange";
			gunImg = "trash";
			gridImg = "recycle";
			break;
		case 3:
			objec = obj3;
			color = "blue";
			gunImg = "egg";
			gridImg = "bird";
			break;
		case 4:
			objec = obj4;
			color = "blue";
			gunImg = "egg";
			gridImg = "bird";
			break;
		case 5:
			objec = obj5;
			color = "blue";
			gunImg = "egg";
			gridImg = "bird";
			break;
		case 6:
			objec = obj6;
			color = "blue";
			gunImg = "egg";
			gridImg = "bird";
			break;

		default:
			objec = "no objective set";
			color = "no color";
			gunImg = "no item";
			gridImg = "no item";
			break;
		}
	}

}