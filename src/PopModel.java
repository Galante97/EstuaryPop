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
	int bubbleImageWidth;
	int bubbleImageHeight;
	
	public void checkMatch(){ // not complete yet
		for(Bubble[] bArr: grid){
			for (Bubble b: bArr){
				if( updateHelper1(b, gunList[0]) ){ // gunbubble has come into contact with gridbubble
					// do something!
				}
			}
		}
	}
	
	//like checkMatch() ??? no just breaking down checkmatch into several helpers that can be each be tested so the 
	// the composite method, checkmatch, can be guaranteed to work -Sutton
	public boolean updateHelper1(Bubble bub1, Bubble bub2){ // checks bubble contact between gunbubble and gridbubble
		if( (((bub2.xCoord - bubbleImageWidth) <= bub1.xCoord) && (bub1.xCoord <= (bub2.xCoord + bubbleImageWidth)))
				&&
				(((bub2.yCoord - bubbleImageHeight) <= bub1.yCoord) && (bub1.yCoord <= (bub2.yCoord + bubbleImageHeight))) ){
			return true;	
		}else{
			return false;
		}
	}
	
	
	// like shift
	public void shift(){ // shift gridbubbles down screen by one image height to make room for next new row
		for(Bubble[] bArr: grid){
			for(Bubble b: bArr){
			b.yCoord = b.yCoord + bubbleImageHeight;			
			}
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
	
	
	
	
	
	///////////////////////////////////////
}
