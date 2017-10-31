import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

public class PopModel {
	
	int difficulty;
	int[] objectives = {0,0,0};
	int gridRows = 5; // must be initialized here
	int gridColumns = 18; // must be initialized here
	Bubble[][] grid = new Bubble[gridColumns][gridRows];  
	Bubble[] gunList;
	int score;
	boolean obj0done = false; 
	boolean obj1done = false;
	boolean obj2done = false;

	int bubbleImageWidth; // need a getter for this  
	int bubbleImageHeight; // need a getter for this
	LinkedList<Bubble> matchedList = new LinkedList<Bubble>(); // list of matched bubbles to be possibly popped
	LinkedList<int[]> haveBeenHere = new LinkedList<int[]>();	
	int gunImageWidth; // need a getter for this
	int gunImageHeight; // need a getter for this
	double gunXCoord;
	double gunYCoord;
	Boolean gunDirec = true;
	double gunEdgeX = 0; 
	double gunEdgeY = 0;
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

	public PopModel(){	
	}
	
	
	public PopModel(int diff){
		difficulty = diff;
	}
	
	
	
	//initializes the board
	//pics three objectives from the objective class and randomly gives each bubble an objective
	public void setGrid(){ 
		Random rand = new Random();
		while(objectives[0]!=objectives[1] && objectives[0]!=objectives[2] && objectives[1]!=objectives[2]){//makes sure the objectives arent the same (poorly written but works)
			objectives[0] = rand.nextInt(6);
			objectives[1] = rand.nextInt(6);
			objectives[2] = rand.nextInt(6);
		}//close while
		for(int i = 0; i<startGridRows; i++) {
			for (int j = 0; j<gridColumns; j++) {
				r = rand.nextInt(3);
				grid[i][j] = new Bubble(i, j, false, returnColor(objectives[r]), returnGunImg(objectives[r]), returnGridImg(objectives[r]));
			}//close for
		}//close for
	}
	
	//only used at the beginning
	//adds bubbles to the gun list until it is full 
	//randomly chooses one of the prechoses objectives for each bubble
	public void loadGun(){
		for(int i = 0; i < gunList.length; i++) { 
			Random rand = new Random();
			r = rand.nextInt(3);
			gunList[i] = new Bubble(null, null, true, returnColor(objectives[r]), returnGunImg(objectives[r]), returnGridImg(objectives[r]));
		}//close for
	}
	
	
	
	//set from controller
	public void setClicked(boolean click) {
		this.clicked = click;
	}

	
	public void checkMatch(){ // not complete yet
		matchedList.add(gunList[0]);                    // if size > 4, popping will occur
		//matchedList.add(b);                           // this is our first matching bubble	 		       
		recursion(contactY,contactX,gunList[0]);        // look for more matches 						
		if(matchedList.size()>=4){                      // pop bubble by deleting from grid
			for(Bubble bub: matchedList){
				for(int i=0; i<grid.length; i++){
					for(int j=0; j<grid[0].length;j++){
						if(grid[i][j]== null){
							continue;
						}
						if(grid[i][j].xCoord == bub.xCoord && grid[i][j].yCoord == bub.yCoord){
							grid[i][j]= null;
							//  matchedList.remove();		 	 						  
						}
					}
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
	
	public boolean match(Bubble bub1, Bubble bub2){
		return bub1.color == bub2.color && bub1 != null && bub2 != null;  
	}
	
	
	public void recursion(int row, int col, Bubble pivot) { 
		if(grid[row][col] == null){
			return;
		}
	    if(grid[row][col].color== pivot.color ){
	        matchedList.add(grid[row][col]);
	        beenHere(row,col);
	    }
	    else if (grid[row][col].color!= pivot.color || grid[row][col] == null || beenHere(row,col) ){
	        return;
	    }
	    if (isInBound(row,col + 1) && !beenHere(row,col+1)){
	        recursion(row,col+1,pivot);
	    }
	    if(isInBound(row, col - 1)&& !beenHere(row,col-1)){
	        recursion(row, col - 1,pivot);
	    }
	    if(isInBound(row -1, col)&& !beenHere(row-1,col)){
	        recursion(row-1,col,pivot);
	    } 
	    if(isInBound(row + 1, col)&& !beenHere(row+1,col)) {
	        recursion(row+1,col, pivot);
	    }
	    else {
	        return;
	    }
	}
	

	public boolean isInBound(int row, int col) {	
	    return row<= grid.length-1 && col <= grid[0].length-1 && col >= 0 && row>= 0;
	}
	
	public boolean beenHere(int row, int col){
		int[] xy = new int[2];
		boolean flag = false;
		for(int[] xys: haveBeenHere){
			if(xys[0] == row && xys[1]==col){
				flag = true;
			}			
		}if(flag == false){
			xy[0] = row;
			xy[1] = col;
			haveBeenHere.add(xy);
		}return flag;
	}
		
	
	
	
		
	// **~~lets not worry about this for the alpha~~**	
//		public void shift(){ // shift gridbubbles down screen by one image height to make room for next new row
//			for(Bubble[] bArr: grid){
//				for(Bubble b: bArr){
//					b.yCoord += 1;			
//				}//close for
//			}//close for
//			for(Bubble m : grid[0]){
//				
//			}
//		}
	
	
	public void chooseObjectives(){
		//randomly selects 3 objectives from pool (where is the pool???)
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
	
	//reloads when we shoot
	public void reloadGun() {
		for(int i = 0; i < gunList.length-1; i++) {//moves the bubbles in the list up one indice
			gunList[i] = gunList[i+1];
		}//close for
		Random rand = new Random();
		int r = rand.nextInt(3)
		gunList[gunList.length-1] = new Bubble(null, null, true, returnColor(objectives[r]), returnGunImg(objectives[r]), returnGridImg(objectives[r]));//adds a new bubble to the end

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
	public boolean stopBubble(Bubble b, int degr, int xMoved, int yMoved) {
		if ((xMoved / (gridBubblesX) >= 1) && (yMoved / (gridBubblesY) >= 1)){ //sees if the xCoord and yCoord would place it into a 
			//bubble spot so we don't have to check every single frame
			xMoved = 0;
			yMoved = 0; //reset these values
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
		int xMoved = gunList[0].xCoord;
		int yMoved = gunList[0].yCoord;
		
		clicked = false; //start moving the gun again
		moveGun();
		
		while (stopBubble(gunList[0], degr, xMoved, yMoved) == false) { //move the bubble forward until it makes contact
			moveBubbleForward(gunList[0], degr);
			xMoved += gunList[0].xCoord;
			yMoved += gunList[0].yCoord;
		}
		
		addToGrid(gunList[0]);
		
		checkMatch(); //check if there are any matches that need to be popped
		
		gunList[0] = null;
		reloadGun(); //removes old gunBubble and reloads gun
		
	}
	

}