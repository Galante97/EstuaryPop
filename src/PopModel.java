import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class PopModel {
	
	int difficulty;
	ArrayList<String> objectives = new ArrayList<String>();
	Bubble[][] grid;
	Bubble[] gunList;
	//gunPosition
	//time
	int score;
	boolean obj0done = false;
	boolean obj1done = false;
	boolean obj2done = false;
	
	
	public void update(){
		for(Bubble b: LL){
			if( bubbleContact(b) ){
				// do something!
			}
		}
	}
	
	//like checkMatch() ???
	public boolean bubbleContact(Bubble bub){ // is there gun bubble and gridbubble contact
		if( (((gunBub.xCoord - bubbleImageWidth) <= bub.xCoord) && (bub.xCoord <= (gunBub.xCoord + bubbleImageWidth)))
				&&
				(((gunBub.yCoord - bubbleImageHeight) <= bub.yCoord) && (bub.yCoord <= (gunBub.yCoord + bubbleImageHeight))) ){
			return true;	
		}else{
			return false;
		}
	}
	
	
	// like shift
	public void moveBubblesDown(){ // shift gridbubbles down screen by one image height to make room for next new row
		for(Bubble b: LL){
			b.yCoord = b.yCoord + bubbleImageHeight;			
			}
	}
	
	////////////////////////////////////////
	
	public void chooseObjectives(){
		//randomly selects 3 objectives from pool (where is the pool???)
	}
	
	public void setGrid(){
		//fills the grid with bubbles
	}
	
	public void loadGun(){
		//adds bubbles to the gun list until it is full
		//used both for initialization and reloading the gun every time you shoot
	}
	
	public void moveGun(){
		//waits for you to 'click' and then calls shoot with a time parameter
	}
	
	public void shoot(){//time will be a parameter
		//determines the location the bubble will land and calls checkMatch()
	}
	
	public void checkMatch(){
		//somehow loops to check for matching bubbles and then deletes them from the grid list if they match
	}
	
	public void shift(){
		//shifts the grid down one row periodically
	}
	
	///////////////////////////////////////
}
