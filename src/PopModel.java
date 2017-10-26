import java.util.LinkedList;

public class PopModel {
	
	int bubbleImageHeight;
	int bubbleImageWidth;
	Bubble gunBub;
	
	
	
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
		//continuously changes the position of the gun until you 'click' at which point the loop is broken and shoot() is called
	}
	
	public void shoot(){
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
