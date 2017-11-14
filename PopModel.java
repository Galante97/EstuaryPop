import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;

public class PopModel {

	int difficulty;
	
	Objective[] objs = new Objective[3];
	int[] objectives = { 0, 0, 0 };

	
	//need getters for all of these
	int startGridColumns = 5;
	int gridRows = 18; // must be initialized here
	int gridColumns = 20; // must be initialized here, this should be length of gamePanel
	int currGridRows = 5;
	
	Bubble[][] grid = new Bubble[gridRows][gridColumns]; //make this connect to view
	Bubble[] gunList = new Bubble[8]; //make it connect to view
	
	
	int score;
	boolean obj0done = false;
	boolean obj1done = false;
	boolean obj2done = false;
	int bubbleImageWidth = 50; // need a getter for this
	int bubbleImageHeight = 50; // need a getter for this
	
	LinkedList<Bubble> matchedList = new LinkedList<Bubble>(); // list of matched bubbles to be possibly popped
	LinkedList<int[]> haveBeenHere = new LinkedList<int[]>();
	
	int gunImageWidth; // need a getter for this
	int gunImageHeight; // need a getter for this
	
	private double gunXCoord =450;
	private double gunYCoord = 500;
	Boolean gunDirec = true;
	double gunEdgeX = 0;
	double gunEdgeY = 0;
	 // number of rows we are starting with, must be initialized here
	private int degree = 90; //just a test, will be initialized with moveGun when that actually works
	int bubbleSpeed = 1;
	boolean clicked = false;
	
	
	int gridBubblesX = 25; // determines xCoords grid bubbles
	int gridBubblesY = 25;
	int contactX;
	int contactY;

	public static void main(String[] args) {
	/*	JFrame frame = new JFrame();
		PopModel model = new PopModel(1);// will ask for difficulty
		PopView view = new PopView(model, frame); // creates the window
		view.draw();
		view.setVisible(false);
		model.setGrid();
		System.out.println("Grid Set!");
		model.gunList = new Bubble[5];
		model.loadGun();
		System.out.println("Gun Loaded!");
		model.clicked = true;
		System.out.println("User Clicked");
		model.shoot(85, 450, 712);
		model.clicked = false;
		System.out.println(); */

	}

	/**
	 * constructor
	 */
	public PopModel() {
		setGrid();
		loadGun();
	}

	 /**
	  * 
	  * @param diff difficultly level of game
	  */
	public PopModel(int diff) {
		difficulty = diff;
		setGrid();
		loadGun();
	}
	
	/**
	 * 
	 * @return current angle of the gun
	 */
	public int getDegree() {
		return degree;
	}
	
	/**
	 * 
	 * @return gun x-location
	 */
	public double getGunXCo() {
		return gunXCoord;
	}
	
	/**
	 * 
	 * @return gun y-location
	 */
	public double getGunYCo() {
		return gunYCoord;
	}

	/**
	 *   initializes the board
	 *   pics three objectives from the objective class and randomly gives each bubble
	 *    an objective
	 */
	
	public void setGrid() {
		System.out.println("hi");
		Random rand = new Random();
		while (objectives[0] == objectives[1] || objectives[0] == objectives[2] || objectives[1] == objectives[2]) {
			//checks objective equivalence 																										
			objectives[0] = rand.nextInt(6);
			objectives[1] = rand.nextInt(6);
			objectives[2] = rand.nextInt(6);
		} 
		
		for(int i =0; i < objs.length; i++) {
			objs[i] = new Objective(objectives[i]);
		} //set objectives
		
		
		
		// close while
		for (int i = 0; i < gridRows; i++) {
			for (int j = 0; j < startGridColumns; j++) {
				System.out.println("hi again");
				int r = objectives[rand.nextInt(3)]; //set from random objective in list
				//use static methods to access
				grid[i][j] = new Bubble(i*50, j*50, false, Objective.returnRandColor(r), Objective.returnRandGunImg(r),
						Objective.returnRandGridImg(r)); //50 is the bubble size
				}
			
			for (int k = 0; k < gridColumns; k++) {
				System.out.println("hi again");
				int r = objectives[rand.nextInt(3)]; //set from random objective in list
				//use static methods to access
				grid[i][k] = new Bubble(-1, -1, false, Objective.returnRandColor(r), Objective.returnRandGunImg(r),
						Objective.returnRandGridImg(r)); //these are the bubbles that are not seen
			}//idk if we need this or not?? 
		}
		
		//bubbleImageWidth = grid[2][2].width;
		//bubbleImageHeight = grid[2][2].height; //setting these values, need getters/a better way to do this
		}
			
	/**
	 * only used at the beginning
	 * adds bubbles to the gun list until it is full
	 * randomly chooses one of the prechoses objectives for each bubble
	 */
	public void loadGun() {
		System.out.println("loaded");
		for (int i = 0; i < gunList.length; i++) {
			Random rand = new Random();
			int r = objectives[rand.nextInt(3)];
			
			if(i == 0) {
			
			gunList[i] = new Bubble(i*50, 500, true, Objective.returnRandColor(r), Objective.returnRandGunImg(r), //need getter for coords
					Objective.returnRandGridImg(r)); //use static methods to access
			System.out.println("loaded");
			}
			else {
				gunList[i] = new Bubble((i+1)*50, 500, true, Objective.returnRandColor(r), Objective.returnRandGunImg(r), //need getter for coords
						Objective.returnRandGridImg(r)); //use static methods to access
				System.out.println("loaded"); //since x coords are different
			}
			
		} // close for
	}
	

	/**
	 * check for matches between bubbles in the game
	 */
	public void checkMatch() { // not complete yet
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

	/**
	 * 
	 * @param bub1 grid bubble being checked for match
	 * @param bub2 gun bubble being used for matching locations
	 * @return
	 */
	public boolean checkContact(Bubble bub1, Bubble bub2) {
		System.out.println("reached");// checks bubble contact between gunbubble and gridbubble
		if ((((bub2.xCoord - bubbleImageWidth) <= bub1.xCoord) && (bub1.xCoord <= (bub2.xCoord + bubbleImageWidth)))
				&& (((bub2.yCoord - bubbleImageHeight) <= bub1.yCoord)
						&& (bub1.yCoord <= (bub2.yCoord + bubbleImageHeight)))) {
			System.out.println("tru!!");
			return true;
		} else {
			System.out.println("false!!");
			return false;
		}
	}

	/**
	 * 
	 * @param bub1 bubble used for matching color
	 * @param bub2 bubble used for matching color
	 * @return
	 */
	public boolean match(Bubble bub1, Bubble bub2) {
		return bub1.color == bub2.color && bub1 != null && bub2 != null;
	}

	/**
	 * 
	 * @param row y-index location in grid
	 * @param col x-index location in grid
	 * @param pivot gun bubble being compared to
	 */
	public void recursion(int row, int col, Bubble pivot) {
		if (grid[row][col] == null) {
			return;
		}
		if (grid[row][col].color == pivot.color) {
			matchedList.add(grid[row][col]);
			beenHere(row, col);
		} else if (grid[row][col].color != pivot.color || grid[row][col] == null || beenHere(row, col)) {
			return;
		}
		if (isInBound(row, col + 1) && !beenHere(row, col + 1)) {
			recursion(row, col + 1, pivot);
		}
		if (isInBound(row, col - 1) && !beenHere(row, col - 1)) {
			recursion(row, col - 1, pivot);
		}
		if (isInBound(row - 1, col) && !beenHere(row - 1, col)) {
			recursion(row - 1, col, pivot);
		}
		if (isInBound(row + 1, col) && !beenHere(row + 1, col)) {
			recursion(row + 1, col, pivot);
		} else {
			return;
		}
	}

	/**
	 * 
	 * @param row y-index of grid
	 * @param col x-index of grid
	 * @return	true if inside boundary of grid
	 */
	public boolean isInBound(int row, int col) {
		return row <= grid.length - 1 && col <= grid[0].length - 1 && col >= 0 && row >= 0;
	}

	/**
	 * 
	 * @param row y-value of grid
	 * @param col x-value of grid
	 * @return	true if the grid index have been used before (for recursive check)
	 */
	public boolean beenHere(int row, int col) {
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

	/**
	 * randomly selects 3 objectives from pool
	 */
	public void chooseObjectives() {
		//  (where is the pool???)
	}

	/**
	 * 
	 * @param b gun bubble being added to grid
	 *   
	 * checks to see where in the grid the bubble landed compared to the one it made
	 * contact with
	 * helper for shoot
	 * 
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

	
	/**
	 * reloads when we shoot
	 */
	public void reloadGun() {
		for (int i = 0; i < gunList.length - 1; i++) {// moves the bubbles in the list up one indice
			gunList[i] = gunList[i + 1];
		} // close for
		Random rand = new Random();
		int r = objectives[rand.nextInt(3)];
		gunList[gunList.length - 1] = new Bubble(-1, -1, true, Objective.returnRandColor(r),
				Objective.returnRandGunImg(r), Objective.returnRandGridImg(r));// adds a new bubble to the end

	}

	
	/**
	 * 
	 * oscillation of gun that applies clicked as a mouse listener
	 * ran throughout game
	 * 
	 */
	public void moveGun() {
		int r = gunImageHeight; // radius - it will be the gun image length
	//	while (clicked == false) {
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
		
	/**
	 * 
	 * @param b gun bubble being tested
	 * @param degr angle of gun
	 * @param xMoved projection of gun onto x-axis
	 * @param yMoved projection of gun onto y-axis
	 * @return true if the gun bubble has made contact
	 * 
	 *   if clicked, gun pauses and shoots
	 * need to do get degree, get gunXcoord, get gunYcoord in controller
	 * ran during every frame of shoot - checks if it comes into contact with
	 * another bubble - if it does, we return true
	 * helper for shoot
	 * 
	 * 
	 */
	public boolean stopBubble(Bubble b, int degr, int xMoved, int yMoved) {
		if ((xMoved / (gridBubblesX) >= 1) || (yMoved / (gridBubblesY) >= 1)) { // sees if the xCoord and yCoord would
			System.out.println("checking");																	// place it into a
			// bubble spot so we don't have to check every single frame
			xMoved = 0;
			yMoved = 0; // reset these values
			if (degr < 90) { // check left half of grid - minimizes amount of bubbles to check
				for (int i = 0; i < currGridRows; i ++) {
					for (int j = 0; j < gridColumns / 2; j ++) {
						if (checkContact(b, grid[i][j])) { // need to add or y = frame height
							contactX = i;
							contactY = j;
							return true;
						}
					}
				}
			} else if (degr >= 90) { // check right half of grid
				for (int i = 0; i < currGridRows; i ++) {
					for (int j = gridColumns/2; j < gridColumns; j ++) {// call update helper
						if (checkContact(b, grid[i][j])) {
							contactX = i;
							contactY = j;
							return true;
						}
					}
				}
			}
		}
		System.out.println(false);
		return false;
	}

	
	/**
	 * 
	 * @param b gun bubble being moved
	 * @param degr angle of gun
	 * 
	 * moves forward on an angle
	 * helper for shoot
	 * 
	 * 
	 */
	public void moveBubbleForward(Bubble b, int degr) {
		Long x = Math.round(bubbleSpeed * Math.sin(degr));
		Long y = Math.round(bubbleSpeed * Math.cos(degr));

		b.xCoord += x.intValue();

		b.yCoord += y.intValue();

	}

	
	/**
	 * 
	 * @param degr angle of gun
	 * @param xCo x-component of gun
	 * @param yCo y-component of gun
	 * 
	 * 
	 * 
	 *  puts the gunBubble into the xCoord and yCoord of where the gun is and shoots
	 * it by moving the bubble until it comes in
	 * contact with another bubble
	 * takes degree, xCoordinate, and yCoordinate of the gun at the time when shoot
	 * is activated
	 * called by moveGun
	 * 
	 * 
	 * 
	 */
	public void shoot(int degr, double xCo, double yCo) {
		Long x = Math.round(xCo);
		Long y = Math.round(yCo);
		gunList[0].xCoord = x.intValue();
		gunList[0].yCoord = y.intValue();
		int xMoved = gunList[0].xCoord;
		int yMoved = gunList[0].yCoord;
		
		boolean keepChecking = stopBubble(gunList[0], degr, xMoved, yMoved);


		while (keepChecking == false) { // move the bubble forward until it makes contact
			System.out.println("xMoved");
			moveBubbleForward(gunList[0], degr);
			xMoved += gunList[0].xCoord;
			yMoved += gunList[0].yCoord;
			keepChecking = stopBubble(gunList[0], degr, xMoved, yMoved);
			// this isn't working, checking entire array takes way too long
		}
		
		System.out.println("added");

		addToGrid(gunList[0]);

		checkMatch(); // check if there are any matches that need to be popped

		gunList[0] = null;
		reloadGun(); // removes old gunBubble and reloads gun

	}


}