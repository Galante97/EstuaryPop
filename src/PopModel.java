import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class PopModel {

	int difficulty;
	
	Objective o = new Objective();
	Objective[] objs = new Objective[3];
	int[] objectives = { 0, 0, 0 };
	boolean[] objdone = {false,false,false};
	int[] objectiveTally = {0,0,0};
	
	int gridColumns = 13;
	int gridRows = 10;
	int startRows;
	
	Bubble[][] grid = new Bubble[gridRows][gridColumns]; //make this connect to view
	int gunListLength = 7;
	Bubble[] gunList = new Bubble[gunListLength]; //make it connect to view
	
	int score = 100;
	Scanner scn = new Scanner(System.in);
	
	
	/*
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
	*/

	///////////////////////////////////////

	/**
	 * constructor
	 * @param d is the difficulty passed in from PopController
	 */
	public PopModel(int d) {
		difficulty = d;
		startRows = 4 + d;
	}

	///////////////////////////////////////
	
//	/**
//	 * @return current angle of the gun
//	 */
//	public int getDegree() {
//		return degree;
//	}
//	/**
//	 * 
//	 * @return gun x-location
//	 */
//	public double getGunXCo() {
//		return gunXCoord;
//	}
//	/**
//	 * 
//	 * @return gun y-location
//	 */
//	public double getGunYCo() {
//		return gunYCoord;
//	}

	////////////////////////////////////////
	
	/**
	 * Randomly chooses 3 objectives that will teach the player 3 things about estuaries
	 */
	public void chooseObjectives(){//fills the array of ints called "objectives" with three different random numbers. these will be the three objectives for the given game
		System.out.println("Choosing objectives..");
		Random rand = new Random();
		while (objectives[0] == objectives[1] || objectives[0] == objectives[2] || objectives[1] == objectives[2]){
			objectives[0] = rand.nextInt(6);
			objectives[1] = rand.nextInt(6);
			objectives[2] = rand.nextInt(6);
		} // close while
	}//close chooseOjbectives
	
	/**
	 *initializes the board by randomly placing bubbles in the top few rows
	 */
	public void setGrid() {
		Random rand = new Random();
		System.out.println("Filling grid...");
		for (int i = 0; i < startRows; i++) {
			for (int j = 0; j < gridColumns; j++) {
				int r = rand.nextInt(3);
				grid[i][j] = new Bubble(o.returnColor(objectives[r]), o.returnGunImg(objectives[r]),o.returnGridImg(objectives[r]));
				grid[i][j].switchImage(); 
			} // close for
		} // close for
	}//close setGrid
			
	/**
	 * used both at the beginning and throughout the game to reload the gun
	 * adds bubbles to the end of the gun list
	 * randomly chooses one of the prechosen objectives for each bubble
	 */
	public void loadGun() {
		System.out.println("Loading Gun...");
		while(gunList[0] == null){
			for(int i = 0; i < gunListLength-1; i++){
				gunList[i] = gunList[i+1];
			}
			Random rand = new Random();
			int r = rand.nextInt(3);
			gunList[gunListLength-1] = new Bubble(o.returnColor(objectives[r]), o.returnGunImg(objectives[r]),o.returnGridImg(objectives[r]));
		}
	}
	
	/**
	 * Waits for the player to click (or enter values for x and y if using dummyShoot or enter a path # if using manual input)
	 * @throws InterruptedException when dealing with the time delay for click to fire
	 */
	public void moveGun() throws InterruptedException{
		score--;
		
		//Use dummyShoot (for testing)
		/*
		System.out.println("Would you like to shoot? ...\n*(1 = yes)*");
		int x = scn.nextInt();
		if(x == 1){
			dummyShoot();//for testing purposes
		}
		*/
		
		
		//Manually input path # (for testing)
		
		System.out.println("What #Path would you like to shoot along? ...");
		int x = scn.nextInt();
		if(x >= 0 && x <= 28){
			shoot(x);
		}
		else{
			System.out.println("Choose a path number between 0 and 28!");
		}
		for(int i = 0; i <= 2; i++){
			System.out.println(objectiveTally[i] + "Tally");
			System.out.println(objdone[i] + "Done" );
			if(objectiveTally[i] >= 5 && !objdone[i]){
				objdone[i] = true;
				System.out.println(objectives[i]);
				System.out.println("\t\t" + o.returnCompleteStatement(objectives[i]));
				System.out.println("Press any number to continue... ");
				int g = scn.nextInt();//kind of pointless. Just used to pause the game to allow the player to read the complete statement. maybe change to a click to continue
			}
			i++;
		}
			
		
		
		//Use click to fire (for real version) 
		//Needs click listener to work
		/*
		boolean clicked = false;//add click listener
		boolean movingRight =true;
		int pathN = 0;
		while(!clicked){
			if(movingRight){
				pathN++;
			}
			else{
				pathN--;
			}
			if(pathN <= 0){
				movingRight = true;
			}
			if(pathN >= 18){
				movingRight = false;
			}
			TimeUnit.MILLISECONDS.sleep(100);
			//System.out.println(pathN);
		}
		shoot(pathN);
		int i = 0;
		for(int n: objectiveTally){
			if(n >= 5 && !objdone[i]){
				objdone[i] = true;
				System.out.println("\t\t" + o.returnCompleteStatement(i));
				System.out.println("Press any number to continue... ");
				int g = scn.nextInt();//kind of pointless
			}
			i++;
		}
		*/
	}
	
	/////////
	public void dummyShoot(){//for testing purposes
		System.out.println("Dummy Shoot...");
		while(true){
		    System.out.println("X coord? ...");
		    int x = scn.nextInt();
			System.out.println("Y coord? ...");
		    int y = scn.nextInt();
			if(grid[y][x] == null){
				grid[y][x] = gunList[0];
				grid[y][x].switchImage();
				gunList[0] = null;
				loadGun();
				checkMatch(x,y);
				break;
			}
			else{
				System.out.println("Choose and empty space!");
			}
		}
	}
	////////	
	
	/**
	 * @param t is the "time" that has passes while the gun was moving waiting for you to shoot.
	 * t determines the path that the bubble will travel along after shot
	 */
	public void shoot(int t){//works with with manual path # input or click to fire
		System.out.println("Shooting...");
		String c = gunList[0].color;
		int[][] finalPositions = {{0,8},{0,7},{0,6},{0,5},{0,4},{0,3},{0,2},{0,1},{0,0},
				{1,0},{2,0},{3,0},{4,0},{5,0},{6,0},{7,0},{8,0},{9,0},{10,0},{11,0},{12,0},
				{12,1},{12,2},{12,3},{12,4},{12,5},{12,6},{12,7},{12,8}};//list of final postions the bubble can be shot to
		Path p = new Path(finalPositions[t][0],finalPositions[t][1]);//creates path based on which direction the bubble is shot
		p.fillPath();
		for(int index = 0; index < p.length; index++){//checks through the path. once it finds a position that already has a bubble in it, it places the shot bubble in the previous position in the path
			if(grid[p.pathSpaces[index][1]][p.pathSpaces[index][0]] != null){//checks position
				grid[p.pathSpaces[index-1][1]][p.pathSpaces[index-1][0]] = gunList[0];//places shot bubble in previous position along the path
				grid[p.pathSpaces[index-1][1]][p.pathSpaces[index-1][0]].switchImage();//switches the image
				gunList[0] = null;//removes bubble from gun
				loadGun();//reloads gun
				checkMatch(p.pathSpaces[index-1][0],p.pathSpaces[index-1][1]);
				updateObjectives(p.pathSpaces[index-1][0],p.pathSpaces[index-1][1],c);
				break;
			}//close if
			if(index == p.length - 1){//if there were no bubble already along the given path, the shot bubble gets placed at the end of the path (sticks to walls, does not bounce off walls)
				grid[finalPositions[t][1]][finalPositions[t][0]] = gunList[0];//places bubble in final position along the path
				grid[finalPositions[t][1]][finalPositions[t][0]] .switchImage();//switches the image
				gunList[0] = null;//removes bubble from gun
				loadGun();//reloads gun
				checkMatch(finalPositions[t][0],finalPositions[t][1]);
				updateObjectives(finalPositions[t][0],finalPositions[t][1],c);
			}//close if
		}//close for
	}//close shoot
	
	/**
	 * recursively checks surrounding bubbles once bubble has landed in the grid and removes matches
	 * @param x is the x position the bubble landed in
	 * @param y is the y position the bubble landed in
	 */
	public void checkMatch(int x, int y){//recursive
		String c = grid[y][x].color;
		if(x+1 <= gridColumns-1){//check right
			if(grid[y][x+1] != null && c.equals(grid[y][x+1].color)){
				grid[y][x] = null;
				checkMatch(x+1, y);
				grid[y][x+1] = null;
			}
		}
		if(x-1 >= 0){//check left
			if(grid[y][x-1] != null && c.equals(grid[y][x-1].color)){
				grid[y][x] = null;
				checkMatch(x-1, y);
				grid[y][x-1] = null;
			}
		}
		if(y+1 <= gridRows-1){//check down (notice the sign)
			if(grid[y+1][x] != null && c.equals(grid[y+1][x].color)){
				grid[y][x] = null;
				checkMatch(x, y+1);
				grid[y+1][x] = null;
			}
		}
		if(y-1 >= 0){//check up
			if(grid[y-1][x] != null && c.equals(grid[y-1][x].color)){
				grid[y][x] = null;
				checkMatch(x, y-1);
				grid[y-1][x] = null;
			}
		}
	}
	
	/**
	 * checks to see if the shot bubble was popped and updates the objectiveTally accordingly
	 * @param x is the x position the bubble landed in
	 * @param y is the y position the bubble landed in
	 * @param c is the color of the bubble. used to identify which objective tally to increase
	 */
	public void updateObjectives(int x, int y, String c){
		if(grid[y][x] == null){//adds 1 to the tally of the objective the player just matched (if they matched)
			int m = 0;
			boolean match = false;
			while(!match){
				if(c.equals(o.returnColor(objectives[m]))){
					match = true;
				}//close if
				else{
					m++;
				}//close else
			}//close while
			objectiveTally[m]++;
		}//close if
	}//close updateObjectives
	
//	/**
//	 * check for matches between bubbles in the game
//	 */
//	public void checkMatch() { // not complete yet
//		matchedList.add(gunList[0]); // if size > 4, popping will occur
//		// matchedList.add(b); // this is our first matching bubble
//		recursion(contactY, contactX, gunList[0]); // look for more matches
//		if (matchedList.size() >= 4) { // pop bubble by deleting from grid
//			for (Bubble bub : matchedList) {
//				for (int i = 0; i < grid.length; i++) {
//					for (int j = 0; j < grid[0].length; j++) {
//						if (grid[i][j] == null) {
//							continue;
//						}
//						if (grid[i][j].xCoord == bub.xCoord && grid[i][j].yCoord == bub.yCoord) {
//							grid[i][j] = null;
//							// matchedList.remove();
//						}
//					}
//				}
//			}
//		}
//	}

//	/**
//	 * 
//	 * @param bub1 grid bubble being checked for match
//	 * @param bub2 gun bubble being used for matching locations
//	 * @return
//	 */
//	public boolean checkContact(Bubble bub1, Bubble bub2) {
//		System.out.println("reached");// checks bubble contact between gunbubble and gridbubble
//		if ((((bub2.xCoord - bubbleImageWidth) <= bub1.xCoord) && (bub1.xCoord <= (bub2.xCoord + bubbleImageWidth)))
//				&& (((bub2.yCoord - bubbleImageHeight) <= bub1.yCoord)
//						&& (bub1.yCoord <= (bub2.yCoord + bubbleImageHeight)))) {
//			System.out.println("tru!!");
//			return true;
//		} else {
//			System.out.println("false!!");
//			return false;
//		}
//	}

//	/**
//	 * 
//	 * @param bub1 bubble used for matching color
//	 * @param bub2 bubble used for matching color
//	 * @return
//	 */
//	public boolean match(Bubble bub1, Bubble bub2) {
//		return bub1.color == bub2.color && bub1 != null && bub2 != null;
//	}

//	/**
//	 * 
//	 * @param row y-index location in grid
//	 * @param col x-index location in grid
//	 * @param pivot gun bubble being compared to
//	 */
//	public void recursion(int row, int col, Bubble pivot) {
//		if (grid[row][col] == null) {
//			return;
//		}
//		if (grid[row][col].color == pivot.color) {
//			matchedList.add(grid[row][col]);
//			beenHere(row, col);
//		} else if (grid[row][col].color != pivot.color || grid[row][col] == null || beenHere(row, col)) {
//			return;
//		}
//		if (isInBound(row, col + 1) && !beenHere(row, col + 1)) {
//			recursion(row, col + 1, pivot);
//		}
//		if (isInBound(row, col - 1) && !beenHere(row, col - 1)) {
//			recursion(row, col - 1, pivot);
//		}
//		if (isInBound(row - 1, col) && !beenHere(row - 1, col)) {
//			recursion(row - 1, col, pivot);
//		}
//		if (isInBound(row + 1, col) && !beenHere(row + 1, col)) {
//			recursion(row + 1, col, pivot);
//		} else {
//			return;
//		}
//	}

//	/**
//	 * 
//	 * @param row y-index of grid
//	 * @param col x-index of grid
//	 * @return	true if inside boundary of grid
//	 */
//	public boolean isInBound(int row, int col) {
//		return row <= grid.length - 1 && col <= grid[0].length - 1 && col >= 0 && row >= 0;
//	}

//	/**
//	 * 
//	 * @param row y-value of grid
//	 * @param col x-value of grid
//	 * @return	true if the grid index have been used before (for recursive check)
//	 */
//	public boolean beenHere(int row, int col) {
//		int[] xy = new int[2];
//		boolean flag = false;
//		for (int[] xys : haveBeenHere) {
//			if (xys[0] == row && xys[1] == col) {
//				flag = true;
//			}
//		}
//		if (flag == false) {
//			xy[0] = row;
//			xy[1] = col;
//			haveBeenHere.add(xy);
//		}
//		return flag;
//	}

//	 **~~lets not worry about this for the alpha~~**
//	 public void shift(){ // shift gridbubbles down screen by one image height to
//	 make room for next new row
//	 for(Bubble[] bArr: grid){
//	 for(Bubble b: bArr){
//	 b.yCoord += 1;
//	 }//close for
//	 }//close for
//	 for(Bubble m : grid[0]){
//	
//	 }
//	 }


//	/**
//	 * 
//	 * @param b gun bubble being added to grid
//	 *   
//	 * checks to see where in the grid the bubble landed compared to the one it made
//	 * contact with
//	 * helper for shoot
//	 * 
//	 */
//	public void addToGrid(Bubble b) {
//		b.showGunImage = false;
//		if ((contactX == b.xCoord) && (contactY > b.yCoord)) {// above contact bubble
//			grid[contactX][contactY - 1] = b;
//		}
//		if ((contactX > b.xCoord) && (contactY > b.yCoord)) {// above contact bubble, to the left (not needed for alpha,
//																// but needed for staggered shape)
//			grid[contactX - 1][contactY - 1] = b;
//		}
//		if ((contactX < b.xCoord) && (contactY > b.yCoord)) {// above contact bubble, to the right (not needed for
//																// alpha, but needed for staggered shape)
//			grid[contactX + 1][contactY - 1] = b;
//		}
//		if ((contactX == b.xCoord) && (contactY < b.yCoord)) {// below contact bubble
//			grid[contactX][contactY + 1] = b;
//		}
//		if ((contactX > b.xCoord) && (contactY < b.yCoord)) {// below contact bubble, to the left (not needed for alpha,
//																// but needed for staggered shape)
//			grid[contactX - 1][contactY + 1] = b;
//		}
//		if ((contactX < b.xCoord) && (contactY < b.yCoord)) {// below contact bubble, to the right (not needed for
//																// alpha, but needed for staggered shape)
//			grid[contactX + 1][contactY + 1] = b;
//		}
//		if ((contactX > b.xCoord) && (contactY == b.yCoord)) {// left of contact bubble
//			grid[contactX - 1][contactY] = b;
//		}
//		if ((contactX < b.xCoord) && (contactY == b.yCoord)) {// right of contact bubble
//			grid[contactX + 1][contactY] = b;
//		}
//	}
	
//	/**
//	 * 
//	 * @param b gun bubble being tested
//	 * @param degr angle of gun
//	 * @param xMoved projection of gun onto x-axis
//	 * @param yMoved projection of gun onto y-axis
//	 * @return true if the gun bubble has made contact
//	 * 
//	 *   if clicked, gun pauses and shoots
//	 * need to do get degree, get gunXcoord, get gunYcoord in controller
//	 * ran during every frame of shoot - checks if it comes into contact with
//	 * another bubble - if it does, we return true
//	 * helper for shoot
//	 * 
//	 * 
//	 */
//	public boolean stopBubble(Bubble b, int degr, int xMoved, int yMoved) {
//		if ((xMoved / (gridBubblesX) >= 1) || (yMoved / (gridBubblesY) >= 1)) { // sees if the xCoord and yCoord would
//			System.out.println("checking");																	// place it into a
//			// bubble spot so we don't have to check every single frame
//			xMoved = 0;
//			yMoved = 0; // reset these values
//			if (degr < 90) { // check left half of grid - minimizes amount of bubbles to check
//				for (int i = 0; i < currGridRows; i ++) {
//					for (int j = 0; j < gridColumns / 2; j ++) {
//						if (checkContact(b, grid[i][j])) { // need to add or y = frame height
//							contactX = i;
//							contactY = j;
//							return true;
//						}
//					}
//				}
//			} else if (degr >= 90) { // check right half of grid
//				for (int i = 0; i < currGridRows; i ++) {
//					for (int j = gridColumns/2; j < gridColumns; j ++) {// call update helper
//						if (checkContact(b, grid[i][j])) {
//							contactX = i;
//							contactY = j;
//							return true;
//						}
//					}
//				}
//			}
//		}
//		System.out.println(false);
//		return false;
//	}

//	/**
//	 * @param b gun bubble being moved
//	 * @param degr angle of gun
//	 * 
//	 * moves forward on an angle
//	 * helper for shoot
//	 */
//	public void moveBubbleForward(Bubble b, int degr) {
//		Long x = Math.round(bubbleSpeed * Math.sin(degr));
//		Long y = Math.round(bubbleSpeed * Math.cos(degr));
//
//		b.xCoord += x.intValue();
//
//		b.yCoord += y.intValue();
//
//	}

//	/**
//	 * @param degr angle of gun
//	 * @param xCo x-component of gun
//	 * @param yCo y-component of gun
//	 * 
//	 *  puts the gunBubble into the xCoord and yCoord of where the gun is and shoots
//	 * it by moving the bubble until it comes in
//	 * contact with another bubble
//	 * takes degree, xCoordinate, and yCoordinate of the gun at the time when shoot
//	 * is activated
//	 * called by moveGun
//	 */
//	public void shoot(int degr, double xCo, double yCo) {
//		Long x = Math.round(xCo);
//		Long y = Math.round(yCo);
//		gunList[0].xCoord = x.intValue();
//		gunList[0].yCoord = y.intValue();
//		int xMoved = gunList[0].xCoord;
//		int yMoved = gunList[0].yCoord;
//		
//		boolean keepChecking = stopBubble(gunList[0], degr, xMoved, yMoved);
//
//
//		while (keepChecking == false) { // move the bubble forward until it makes contact
//			System.out.println("xMoved");
//			moveBubbleForward(gunList[0], degr);
//			xMoved += gunList[0].xCoord;
//			yMoved += gunList[0].yCoord;
//			keepChecking = stopBubble(gunList[0], degr, xMoved, yMoved);
//			// this isn't working, checking entire array takes way too long
//		}
//		
//		System.out.println("added");
//
//		addToGrid(gunList[0]);
//
//		checkMatch(); // check if there are any matches that need to be popped
//
//		gunList[0] = null;
//		reloadGun(); // removes old gunBubble and reloads gun
//
//	}

	public static void main(String[] args) {
		JFrame frame = new JFrame(); //create frame
		PopModel m = new PopModel(1); //create model
		PopView view = new PopView(m, frame); // create view of game play
		PopView menu = new PopView(m,frame); // menu view
		PopView howto = new PopView(m,frame);
		menu.menuView = new MenuCustomMouseListener();
		menu.howToPlay = new HowToPlayMouseListener();
		howto.howToPlay = new HowToPlayMouseListener();
		MenuCustomMouseListener.sendInstancesToMenuCustomMouseListener(view, menu, howto);
		HowToPlayMouseListener.sendInstancesToHowToPlayMouseListener(view, menu, howto);
		
		m.chooseObjectives();                 
		//printObjectives(m);	
		m.setGrid(); //set model grid            
		m.loadGun(); //load model gun            
		menu.drawMenu();
		menu.setVisible(true);
		//view.drawHowToPlay();	
		howto.drawHowToPlay();
		view.draw(); //draw bubbles/panel/gun/etc all corresponding to model
	
		
		
		

		}
}