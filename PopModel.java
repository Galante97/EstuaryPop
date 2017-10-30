
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
	int gunImageWidth;
	int gunImageHeight;
	double gunXCoord;
	double gunYCoord;
	Boolean gunDirec = true;
	double gunEdgeX = 0; //middle - gunImglen;
	double gunEdgeY = 0;
	int gridRows;
	int gridColumns;
	int degree;
	int bubbleSpeed;
	
	public void checkMatch(){ // not complete yet
		for(Bubble[] bArr: grid){
			for (Bubble b: bArr){
				if( checkContact(b, gunList[0]) ){ // gunbubble has come into contact with gridbubble
					// do something!
				}
			}
		}
	}
	
	//like checkMatch() ??? no just breaking down checkmatch into several helpers that can be each be tested so the 
	// the composite method, checkmatch, can be guaranteed to work -Sutton
	//gonna use this updateHelper1 for shoot, switched the name to checkContact -Olivia
	public boolean checkContact(Bubble bub1, Bubble bub2){ // checks bubble contact between gunbubble and gridbubble
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
	
	
	//oscillation of gun
	
	public void moveGun(){
		int r = gunImageHeight; // radius - it will be the gun image length
		if (gunDirec == true) { //move right
			degree += 1;
		}
		if (gunDirec == false) { //move left
			degree -= 1;
		}
		
		gunXCoord = Math.sin(Math.toRadians(degree)) * r;
		gunYCoord = Math.cos(Math.toRadians(degree)) * r;
		
		if (degree == 0) {
    			gunDirec = true; //true means it is moving right
		}
		if (degree == 180) {
		    gunDirec = false; //false means it is moving left
		}
	
	}
			
	
	//ran during every frame of shoot - checks if we should stop the bubble - if we should, we return true
	public boolean stopBubble(Bubble b) {
		if ((b.xCoord % (bubbleImageWidth/2) == 0) && (b.yCoord % (bubbleImageHeight/2) == 0)){ //sees if the xCoord and yCoord would place it into a 
			//bubble spot so we don't have to check every single frame
			if (degree < 45) { //check left half of grid - minimizes amount of bubbles to check
				for (int i = 0; i < gridColumns; i+=bubbleImageWidth) {
					for (int j = 0; j < gridRows/2; j+=bubbleImageHeight) {
						if (checkContact(b, grid[i][j])){
							return true;
						}
					}
				}
			}
			else if (degree >= 45) { // check right half of grid 
				for (int i = 0; i < gridColumns; i+=bubbleImageWidth) {
					for (int j = gridRows/2; j < gridRows; j+=bubbleImageHeight) {//call update helper
						if (checkContact(b, grid[i][j])){
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	
	//we want it to choose a certain spot in the grid so the x coordinate must round to the nearest to a certain number that is divisible by the bubbleImageWidth
	public void moveBubbleForward(Bubble b) {
		Long x = Math.round(bubbleSpeed * Math.sin(degree));
		Long y = Math.round(bubbleSpeed * Math.cos(degree));
	
		b.xCoord = x.intValue();
		b.yCoord = y.intValue();
		
	}
	
	
	//puts the gunBubble into the xCoord and yCoord of where the gun is and shoots it by moving the bubble until it comes in
	//contact with another bubble
	public void shoot(){
		Long x = Math.round(gunXCoord);
		Long y = Math.round(gunYCoord);
	
		
		gunList[0].xCoord = x.intValue();
		gunList[0].yCoord = y.intValue();
		
		
		while (stopBubble(gunList[0]) == false) {
			moveBubbleForward(gunList[0]);
		}
		checkMatch();
		
		//determines the location the bubble will land and calls checkMatch()
	}

}