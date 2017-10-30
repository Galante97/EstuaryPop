
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;



public class PopModel {
	
	int difficulty;
	ArrayList<String> objectives = new ArrayList<String>();
	Bubble[][] grid;
	Bubble[] gunList;
	int score;
	boolean obj0done = false;
	boolean obj1done = false;
	boolean obj2done = false;
	int bubbleImageWidth; // need a getter for this
	int bubbleImageHeight; // need a getter for this
	int gunImageWidth; // need a getter for this
	int gunImageHeight; // need a getter for this
	double gunXCoord;
	double gunYCoord;
	Boolean gunDirec = true;
	double gunEdgeX = 0; 
	double gunEdgeY = 0;
	int gridRows; // must be initialized here
	int gridColumns; // must be initialized here
	int startGridRows; // number of rows we are starting with, must be initialized here
	int degree;
	int bubbleSpeed;
	boolean clicked = false;
	Objective o1;
	Objective o2;
	Objective o3;
	int gunBubblesX;  //determines spacing of gun bubbles
	int gunBubblesY; //constant
	int gridBubblesX = bubbleImageWidth/2; //determines xCoords grid bubbles
	int gridBubblesY = bubbleImageHeight/2;
	int contactX;
	int contactY;

	//set from controller
	public void setClicked(boolean click) {
		this.clicked = click;
	}
	
	
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
		o1.setObjective();
		o2.setObjective();
		o3.setObjective();
	}
	
	public void setGrid(){
		Objective obj = new Objective(); 
		for(int i = 0; i<gridRows; i++) {
			for (int j = 0; j<gridColumns; j++) {
				while(i<startGridRows) { //fill starting rows with Bubbles with Objectives
					obj.setObjective();
					grid[i][j] = new Bubble(gridBubblesX + (i*gridBubblesX), gridBubblesY + (j*gridBubblesY), false, obj);
				} //fill the rest with null objectives
				grid[i][j] = new Bubble(gridBubblesX + (i*gridBubblesX), gridBubblesY + (j*gridBubblesY), false, null);
			}
		}
			
	}
	
	//checks to see where in the grid the bubble landed compared to the one it made contact with
	//helper for shoot
	public void addToGrid(Bubble b) { 
		b.showGunImage = false;
		if((contactX == b.xCoord)&&(contactY > b.yCoord)){//above contact bubble
			grid[contactX][contactY-1] = b;
		}
		if((contactX > b.xCoord)&&(contactY > b.yCoord)){//above contact bubble, to the left (not needed for alpha, but needed for staggered shape)
			grid[contactX-1][contactY-1] = b;
		}
		if((contactX < b.xCoord)&&(contactY > b.yCoord)){//above contact bubble, to the right (not needed for alpha, but needed for staggered shape)
			grid[contactX+1][contactY-1] = b;
		}
		if((contactX == b.xCoord)&&(contactY < b.yCoord)){//below contact bubble
			grid[contactX][contactY+1] = b;
		}
		if((contactX > b.xCoord)&&(contactY < b.yCoord)){//below contact bubble, to the left (not needed for alpha, but needed for staggered shape)
			grid[contactX-1][contactY+1] = b;
		}
		if((contactX < b.xCoord)&&(contactY < b.yCoord)){//below contact bubble, to the right (not needed for alpha, but needed for staggered shape)
			grid[contactX+1][contactY+1] = b;
		}
		if((contactX > b.xCoord)&&(contactY == b.yCoord)){//left of contact bubble
			grid[contactX-1][contactY] = b;
		}
		if((contactX < b.xCoord)&&(contactY == b.yCoord)){//right of contact bubble
			grid[contactX+1][contactY] = b;
		}
	}
	
	//only used at the beginning
	//adds bubbles to the gun list until it is full
	public void loadGun(){
		Objective obj = new Objective();
		for(int i = 0; i < gunList.length; i++) {
			obj.setObjective(); 
			gunList[i] = new Bubble(gunBubblesX + (i*gunBubblesX), gunBubblesY, true, obj);
		}
	}
	
	//reloads when we shoot
	public void reloadGun() {
		for(int i=0; i<gunList.length-1; i++) {
			gunList[i] = gunList[i+1];
		}
		gunList[gunList.length-1] = new Bubble(gunList[gunList.length-2].xCoord + gunBubblesX, gunBubblesY, true, new Objective());
	}
	
	//oscillation of gun that applies clicked as a mouse listener
	//ran throughout game
	public void moveGun(){
		int r = gunImageHeight; // radius - it will be the gun image length
		while (clicked == false) {
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
		shoot(degree, gunXCoord, gunYCoord); // if clicked, gun pauses and shoots
	}
			
	
	//ran during every frame of shoot - checks if it comes into contact with another bubble - if it does, we return true
	//helper for shoot
	public boolean stopBubble(Bubble b, int degr) {
		if ((b.xCoord % (gridBubblesX) == 0) && (b.yCoord % (gridBubblesY) == 0)){ //sees if the xCoord and yCoord would place it into a 
			//bubble spot so we don't have to check every single frame
			if (degr < 45) { //check left half of grid - minimizes amount of bubbles to check
				for (int i = 0; i < gridColumns; i+=bubbleImageWidth) {
					for (int j = 0; j < gridRows/2; j+=bubbleImageHeight) {
						if (checkContact(b, grid[i][j])) { //need to add or y = frame height
							contactX = i;
							contactY = j;
							return true;
						}
					}
				}
			}
			else if (degr >= 45) { // check right half of grid 
				for (int i = 0; i < gridColumns; i+=bubbleImageWidth) {
					for (int j = gridRows/2; j < gridRows; j+=bubbleImageHeight) {//call update helper
						if (checkContact(b, grid[i][j])){
							contactX = i;
							contactY = j;
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	
	//moves forward on an angle
	//helper for shoot
	public void moveBubbleForward(Bubble b, int degr) {
		Long x = Math.round(bubbleSpeed * Math.sin(degr));
		Long y = Math.round(bubbleSpeed * Math.cos(degr));
	
		b.xCoord += x.intValue();
		b.yCoord += y.intValue();
		
	}
	
	
	//puts the gunBubble into the xCoord and yCoord of where the gun is and shoots it by moving the bubble until it comes in
	//contact with another bubble
	//takes degree, xCoordinate, and yCoordinate of the gun at the time when shoot is activated
	//called by moveGun
	public void shoot(int degr, double xCo, double yCo){
		Long x = Math.round(xCo);
		Long y = Math.round(yCo);
	
		
		gunList[0].xCoord = x.intValue();
		gunList[0].yCoord = y.intValue();
		
		clicked = false; //start moving the gun again
		moveGun();
		
		while (stopBubble(gunList[0], degr) == false) { //move the bubble forward until it makes contact
			moveBubbleForward(gunList[0], degr);
		}
		
		addToGrid(gunList[0]);
		
		checkMatch(); //check if there are any matches that need to be popped
		
		gunList[0] = null;
		reloadGun(); //removes old gunBubble and reloads gun
		
	}

}