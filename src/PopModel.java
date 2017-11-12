
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;



import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import java.util.Scanner;
public class PopModel {

	int difficulty;
	int[] objectives = { 0, 0, 0 };
	Objective o = new Objective();
	int gridRows = 8; // must be initialized here
	int gridColumns = 5; // must be initialized here
	Bubble[][] grid = new Bubble[gridRows][gridColumns];
	Bubble[] gunList;
	int score;
	boolean obj0done = false;
	boolean obj1done = false;
	boolean obj2done = false;
	int bubbleImageWidth; // need a getter for this
	int bubbleImageHeight; // need a getter for this

	LinkedList<Bubble> matchedList = new LinkedList<Bubble>(); // list of matched bubbles to be possibly popped
	LinkedList<int[]> haveBeenHere = new LinkedList<int[]>();

	LinkedList<Bubble> matchedList;  // list of matched bubbles to be possibly popped
	LinkedList<int[]> haveBeenHere = new LinkedList<int[]>();	

	int gunImageWidth; // need a getter for this
	int gunImageHeight; // need a getter for this
	double gunXCoord;
	double gunYCoord;
	Boolean gunDirec = true;
	double gunEdgeX = 0;
	double gunEdgeY = 0;
	int startGridRows = 3; // number of rows we are starting with, must be initialized here
	int degree;
	int bubbleSpeed;
	boolean clicked = false;
	Objective o1;
	Objective o2;
	Objective o3;
	int gunBubblesX; // determines spacing of gun bubbles
	int gunBubblesY; // constant
	int gridBubblesX = bubbleImageWidth / 2; // determines xCoords grid bubbles
	int gridBubblesY = bubbleImageHeight / 2;
	int contactX;
	int contactY;

	public static String[] modelcolors = {"r","o","y","g","b","p"};
	public static void main(String[] args) {



	

	


		PopModel model = new PopModel(1);
		model.modelSetGrid();
		model.printGrid();
		for(int i = 0; i < 10; i++) {
			model.modelShoot();
		}
	}

	public PopModel() {
	}

	public PopModel(int diff) {
		difficulty = diff;
	}

	// initializes the board
	// pics three objectives from the objective class and randomly gives each bubble
	// an objective
	
	
	
	
	//modelSetGrid sets the grid, using the Bubble constructor with no View parameters
	             //right now, it fills every grid index with a bubble, and initializes the 
				 //bottom three rows Bubble.color = N (NULL)
				 //function should be used to write and test a working CheckContact Function
	//@author: Scott
	public void modelSetGrid() {
		Random r = new Random();
		
		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridColumns; j++) {
				//loads the gun
				grid[i][j] = new Bubble(i, j, modelcolors[r.nextInt(5)]);
			}
		}
		for(int i = 5; i<gridRows; i++) {
			for (int j = 0; j<gridColumns; j++) {
				//initializes the bottom three rows Bubble.color to N(NULL)
				grid[i][j].color = "N";
			}
		}
						
	}
	/*
	 * modelShoot() function for quick shooting, type in x and y coords, changes
	 * bubble color at the index you shoot
	 * use to test and write a working Checkcontact function
	 */
	//@author: Scott
	public void modelShoot() {
		Random r = new Random();
		Bubble b = new Bubble(0, 0, modelcolors[r.nextInt(5)]);
		System.out.print("Bubble color is "+ b.color);
		System.out.println();
		System.out.print("enter y coord: ");
		Scanner in = new Scanner(System.in);
		int x = in.nextInt();
		System.out.print("enter x coord: ");
		int y = in.nextInt();
		b.xCoord = x;
		b.yCoord = y;
		grid[x][y] = b;
		//prints the grid after every shot
		printGrid();
	}
	
	
	//printGrid() function, prints the grid
	//@author:Scott
	public void printGrid() {
		System.out.println();
		for(int i = 0; i < gridColumns; i++) {
			System.out.print(i+" ");
		}
		System.out.println();
		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < gridColumns; j++) {
				System.out.print(grid[i][j].color);
				System.out.print(" ");
			}
			System.out.print("  " + i);
			System.out.println();
		}
		System.out.println();
	}
	
	
	
	
	public void setGrid() {
		System.out.println("hi");
		Random rand = new Random();
		while (objectives[0] == objectives[1] || objectives[0] == objectives[2] || objectives[1] == objectives[2]) {// makes
																													// sure
																													// the
																													// objectives
																													// arent
																													// the
																													// same
																													// (poorly
																													// written
																													// but
																													// works)
			objectives[0] = rand.nextInt(6);
			objectives[1] = rand.nextInt(6);
			objectives[2] = rand.nextInt(6);
		} // close while
		for (int i = 0; i < startGridRows; i++) {
			System.out.println("hi again");
			int j = 0;
			for (; j < gridColumns; j++) {
				int r = rand.nextInt(3);
				grid[i][j] = new Bubble(i, j, false, o.returnColor(objectives[r]), o.returnGunImg(objectives[r]),
						o.returnGridImg(objectives[r]));
			} // close for
		} // close for
	}

	// only used at the beginning
	// adds bubbles to the gun list until it is full
	// randomly chooses one of the prechoses objectives for each bubble
	public void loadGun() {
		for (int i = 0; i < gunList.length; i++) {
			Random rand = new Random();
			int r = rand.nextInt(3);
			gunList[i] = new Bubble(-1, -1, true, o.returnColor(objectives[r]), o.returnGunImg(objectives[r]),
					o.returnGridImg(objectives[r]));
		} // close for
	}

	// set from controller
	public void setClicked(boolean click) {
		this.clicked = click;
	}

	public void checkMatch() { // not complete yet


		matchedList = new LinkedList<Bubble>();

		matchedList.add(gunList[0]); // if size > 4, popping will occur
		// matchedList.add(b); // this is our first matching bubble
		recursion(contactY, contactX, gunList[0]); // look for more matches
		if (matchedList.size() >= 4) { // pop bubble by deleting from grid
			for (Bubble bub : matchedList) {
				for (int i = 0; i < grid.length; i++) {
					for (int j = 0; j < grid[0].length; j++) {
						if (grid[i][j] == null) {
							continue;
						}
						if (grid[i][j].xCoord == bub.xCoord && grid[i][j].yCoord == bub.yCoord) {
							grid[i][j] = null;
							// matchedList.remove();
						}
					}
				}
			}

		}
	}

	// like checkMatch() ??? no just breaking down checkmatch into several helpers
	// that can be each be tested so the
	// the composite method, checkmatch, can be guaranteed to work -Sutton
	// gonna use this updateHelper1 for shoot, switched the name to checkContact
	// -Olivia
	public boolean checkContact(Bubble bub1, Bubble bub2) { // checks bubble contact between gunbubble and gridbubble
		if ((((bub2.xCoord - bubbleImageWidth) <= bub1.xCoord) && (bub1.xCoord <= (bub2.xCoord + bubbleImageWidth)))
				&& (((bub2.yCoord - bubbleImageHeight) <= bub1.yCoord)
						&& (bub1.yCoord <= (bub2.yCoord + bubbleImageHeight)))) {
			return true;
		} else {
			return false;
		}
	}


	
	
	public boolean match(Bubble bub1, Bubble bub2){
		return bub1.color == bub2.color && bub1 != null && bub2 != null;  
	}
	
	
	/**
	 * 
	 * @param row x-index of grid where a match between gunBubble and grid was made
	 * @param col y-index of grid where a match between gunBubble and grid was made
	 * @param gunBubble the bubble being matched to
	 */
	public void recursion(int row, int col, Bubble gunBubble) {
		if (grid[row][col] == null) {
			return;
		}
		if (grid[row][col].color == gunBubble.color) {
			matchedList.add(grid[row][col]);
			beenHere(row, col);
		} else if (grid[row][col].color != gunBubble.color || grid[row][col] == null || beenHere(row, col)) {
			return;
		}
		if (isInBound(row, col + 1) && !beenHere(row, col + 1)) { 
			recursion(row, col + 1, gunBubble);
		}
		if (isInBound(row, col - 1) && !beenHere(row, col - 1)) {
			recursion(row, col - 1, gunBubble);
		}
		if (isInBound(row - 1, col) && !beenHere(row - 1, col)) {
			recursion(row - 1, col, gunBubble);
		}
		if (isInBound(row + 1, col) && !beenHere(row + 1, col)) {
			recursion(row + 1, col, gunBubble);

		} else {
			return;
		}
	}

	


	public boolean isInBound(int row, int col) {
		return row <= grid.length - 1 && col <= grid[0].length - 1 && col >= 0 && row >= 0;
	}



	/**
	 * 
	 * @param row is the y-index of the grid
	 * @param col is the x-index of the grid
	 * @return boolean based on if the index has been visited before
	 * builds a list of visited locations on the grid 
	 */
	public boolean beenHere(int row, int col){

		int[] xy = new int[2];
		boolean flag = false;
		for (int[] xys : haveBeenHere) {
			if (xys[0] == row && xys[1] == col) {
				flag = true;
			}
		}
		if (flag == false) {
			xy[0] = row;
			xy[1] = col;
			haveBeenHere.add(xy);
		}
		return flag;
	}

	// **~~lets not worry about this for the alpha~~**
	// public void shift(){ // shift gridbubbles down screen by one image height to
	// make room for next new row
	// for(Bubble[] bArr: grid){
	// for(Bubble b: bArr){
	// b.yCoord += 1;
	// }//close for
	// }//close for
	// for(Bubble m : grid[0]){
	//
	// }
	// }


	public void chooseObjectives() {
		// randomly selects 3 objectives from pool (where is the pool???)
	}

	// checks to see where in the grid the bubble landed compared to the one it made
	// contact with
	// helper for shoot

	 
	
	/**
	 * 
	 * @param b gun bubble to be placed into the grid
	 * checks to see where in the grid the bubble landed compared to the one it made
	 * contact with
	 *helper for shoot
	 */
	public void addToGrid(Bubble b) { 

		b.showGunImage = false;
		if ((contactX == b.xCoord) && (contactY > b.yCoord)) {// above contact bubble
			grid[contactX][contactY - 1] = b;
		}
		if ((contactX > b.xCoord) && (contactY > b.yCoord)) {// above contact bubble, to the left (not needed for alpha,
																// but needed for staggered shape)
			grid[contactX - 1][contactY - 1] = b;
		}
		if ((contactX < b.xCoord) && (contactY > b.yCoord)) {// above contact bubble, to the right (not needed for
																// alpha, but needed for staggered shape)
			grid[contactX + 1][contactY - 1] = b;
		}
		if ((contactX == b.xCoord) && (contactY < b.yCoord)) {// below contact bubble
			grid[contactX][contactY + 1] = b;
		}
		if ((contactX > b.xCoord) && (contactY < b.yCoord)) {// below contact bubble, to the left (not needed for alpha,
																// but needed for staggered shape)
			grid[contactX - 1][contactY + 1] = b;
		}
		if ((contactX < b.xCoord) && (contactY < b.yCoord)) {// below contact bubble, to the right (not needed for
																// alpha, but needed for staggered shape)
			grid[contactX + 1][contactY + 1] = b;
		}
		if ((contactX > b.xCoord) && (contactY == b.yCoord)) {// left of contact bubble
			grid[contactX - 1][contactY] = b;
		}
		if ((contactX < b.xCoord) && (contactY == b.yCoord)) {// right of contact bubble
			grid[contactX + 1][contactY] = b;
		}
	}


	// reloads when we shoot

	
	/**
	 * moves bubbles in array toward index 0
	 */

	public void reloadGun() {
		for (int i = 0; i < gunList.length - 1; i++) {// moves the bubbles in the list up one indice
			gunList[i] = gunList[i + 1];
		} // close for
		Random rand = new Random();
		int r = rand.nextInt(3);
		gunList[gunList.length - 1] = new Bubble(-1, -1, true, o.returnColor(objectives[r]),
				o.returnGunImg(objectives[r]), o.returnGridImg(objectives[r]));// adds a new bubble to the end

	}


	// oscillation of gun that applies clicked as a mouse listener
	// ran throughout game

	
	/**
	 * starts gun moving by continuously varying the angle of the barrel between 0-180 degrees
	 * applies clicked as a mouse listener
	 * ran throughout game
	 */
	public void moveGun() {
		int degree=90;
		Boolean gunDirec = true;
		int r = bubbleImageWidth; // radius - it will be the gun image length 

		while (clicked == false) {
			if (gunDirec == true) { // move right
				degree += 1;
			}
			if (gunDirec == false) { // move left
				degree -= 1;
			}


			gunXCoord = Math.sin(Math.toRadians(degree)) * r;
			gunYCoord = Math.cos(Math.toRadians(degree)) * r;



			if (degree == 0) {
				gunDirec = true; // true means it is moving right
			}
			if (degree == 180) {
				gunDirec = false; // false means it is moving left
			}
		}
		shoot(degree); // if clicked, gun pauses and shoots
	}


	// ran during every frame of shoot - checks if it comes into contact with
	// another bubble - if it does, we return true
	// helper for shoot
	public boolean stopBubble(Bubble b, int degr, int xMoved, int yMoved) {
		if ((xMoved / (gridBubblesX) >= 1) && (yMoved / (gridBubblesY) >= 1)) { // sees if the xCoord and yCoord would
																				// place it into a
			// bubble spot so we don't have to check every single frame
			xMoved = 0;
			yMoved = 0; // reset these values
			if (degr < 45) { // check left half of grid - minimizes amount of bubbles to check
				for (int i = 0; i < gridColumns; i += bubbleImageWidth) {
					for (int j = 0; j < gridRows / 2; j += bubbleImageHeight) {
						if (checkContact(b, grid[i][j])) { // need to add or y = frame height
							contactX = i;
							contactY = j;
							return true;
						}
					}
				}
			} else if (degr >= 45) { // check right half of grid
				for (int i = 0; i < gridColumns; i += bubbleImageWidth) {
					for (int j = gridRows / 2; j < gridRows; j += bubbleImageHeight) {// call update helper
						if (checkContact(b, grid[i][j])) {
							contactX = i;
							contactY = j;
							return true;
						}

			
	
	/**
	 * 
	 * @param gunBubble bubble that is shot from gun
	 * @return true if contact found
	 */
	public boolean stopBubble(Bubble gunBubble) {	
		int j=-1;
		boolean flag = false;
		if(gunList[0].yCoord <= gridHeight){		// if gun bubble has reached grid	
			breakToHere:
			for(Bubble[] barr: grid){
				j++;	
				int i=-1;
				for(Bubble bub : barr){
					i++;
					if(bub == null){                // skip null locations in grid
						continue;
					}
					//if(checkContact(bub, gunBubble)){       // find match
					if((((gunBubble.xCoord - bubbleImageWidth) <= bub.xCoord) &&       // checking contact between gunbubble and grid
							(bub.xCoord <= (gunBubble.xCoord + bubbleImageWidth)))
							&& (((gunBubble.yCoord - bubbleImageHeight) <= bub.yCoord)
									&& (bub.yCoord <= (gunBubble.yCoord + bubbleImageHeight)))){					
						contactX = i;
						contactY = j;
						flag = true;
						break breakToHere;
					}else{
						flag = false;

					}
				}
			}			
		}

		return false;
	}

	// moves forward on an angle
	// helper for shoot
	public void moveBubbleForward(Bubble b, int degr) {
		Long x = Math.round(bubbleSpeed * Math.sin(degr));
		Long y = Math.round(bubbleSpeed * Math.cos(degr));

		b.xCoord += x.intValue();
		b.yCoord += y.intValue();

	}

	// puts the gunBubble into the xCoord and yCoord of where the gun is and shoots
	// it by moving the bubble until it comes in
	// contact with another bubble
	// takes degree, xCoordinate, and yCoordinate of the gun at the time when shoot
	// is activated
	// called by moveGun
	public void shoot(int degr, double xCo, double yCo) {
		Long x = Math.round(xCo);
		Long y = Math.round(yCo);
		gunList[0].xCoord = x.intValue();
		gunList[0].yCoord = y.intValue();
		int xMoved = gunList[0].xCoord;
		int yMoved = gunList[0].yCoord;

		clicked = false; // start moving the gun again
		moveGun();

		while (stopBubble(gunList[0], degr, xMoved, yMoved) == false) { // move the bubble forward until it makes
																		// contact
			moveBubbleForward(gunList[0], degr);
			xMoved += gunList[0].xCoord;
			yMoved += gunList[0].yCoord;
		}

		addToGrid(gunList[0]);

		checkMatch(); // check if there are any matches that need to be popped

		gunList[0] = null;
		reloadGun(); // removes old gunBubble and reloads gun

	}
}

		return flag;
	}
	
	/**
	 * 
	 * @param degr angle of gun barrel
	 * shoots the bubble toward the grid
	 */
	public void shoot(int degr) { 
		gunList[0].xCoord = screenWidth/2; // x, y position of bubble in gun
		gunList[0].yCoord = screenHeight;                        
		while (stopBubble(gunList[0]) == false) { // move the bubble forward until it makes contact													
			gunList[0].xCoord += bubbleSpeed * Math.cos(Math.toRadians(degr));
			gunList[0].yCoord +=-bubbleSpeed * Math.sin(Math.toRadians(degr));			
		}
		checkMatch(); // check if there are any matches that need to be popped
		gunList[0] = null;
		reloadGun(); // removes old gunBubble and reloads gun
		//clicked = false; // start moving the gun again 
		//moveGun();
	}
}
	




	
	
	

	

	

	

	
	
	


	
	

	
		
	
	

	
	
>>>>>>> e77eceb00855875e75d8e4087908f95ac6b59409
