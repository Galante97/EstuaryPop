
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;



import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

import java.util.Scanner;


/**
 * @author James Galante, Sam Hughes, Chris Sutton, Olivia Leipa, Scott Miller
 */


public class PopModel {

	int difficulty;
	int[] objectives = {0, 0, 0};
	Objective o = new Objective();


	
	
	int score;

	boolean obj0done = false;
	boolean obj1done = false;
	boolean obj2done = false;
	int gridRows = 8; //this size for testing purposes (prints nicely in the console)
	int gridColumns = 5;
	int startGridRows;
	Bubble[][] grid = new Bubble[gridRows][gridColumns];
	int gunListLength = 5;
	Bubble[] gunList = new Bubble[gunListLength];
	
	Scanner scn = new Scanner(System.in);
	
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
	int degree;
	int bubbleSpeed;
	boolean clicked = false;
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
	
	//////////////////////////////////////////////

	/**
	 * Constructor.
	 * @param diff is the difficulty of the game passed in from PopController. It is used to set the initial number of bubbles on the board
	 */
	public PopModel(int diff) {//constructor
		difficulty = diff;
		startGridRows = 3 + diff;//Just a temporary use of difficulty
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
	
	
	
	

	
	///////////////////////////////////////////////
	
	/**
	 * randomly selects three objectives which are the teaching points of each game
	 * allows the game to be different every time you play
	 */
	public void chooseObjectives(){	//fills the array of ints called "objectives" with three different random numbers.
									//these will be the three objectives for the given game
		System.out.println("Choosing objectives..");

		Random rand = new Random();
		while (objectives[0] == objectives[1] || objectives[0] == objectives[2] || objectives[1] == objectives[2]){//inefficient but works
			objectives[0] = rand.nextInt(6);
			objectives[1] = rand.nextInt(6);
			objectives[2] = rand.nextInt(6);
		} // close while
	}
	
	/**
	 * places the bubbles in the grid randomly
	 */
	public void setGrid() {//randomly initializes the grid
		Random rand = new Random();
		System.out.println("Filling grid...");
		for (int i = 0; i < startGridRows; i++) {
			for (int j = 0; j < gridColumns; j++) {
				int r = rand.nextInt(3);
				grid[i][j] = new Bubble(i, j, o.returnColor(objectives[r]), o.returnGunImg(objectives[r]),o.returnGridImg(objectives[r]));
				grid[i][j].switchImage();
			} // close for
		} // close for
	}

	/**
	 * initializes the gun at the beginning of the game by filling the gunList with random bubbles
	 * also reloads the gun after everytime you shoot
	 */
	public void loadGun() {//combines loadGun and reloadGun into one (more efficient)
		System.out.println("Loading Gun...");
		while(gunList[0] == null){
			for(int i = 0; i < gunListLength-1; i++){
				gunList[i] = gunList[i+1];
			}
			Random rand = new Random();
			int r = rand.nextInt(3);
			gunList[gunListLength-1] = new Bubble(-1, -1, o.returnColor(objectives[r]), o.returnGunImg(objectives[r]),o.returnGridImg(objectives[r]));
		}
	}
	
	/**
	 * Once the shot bubble finds it place in the grid, this method is called
	 * to check the surrounding bubbles for matches and removes them from the
	 * board if they match
	 * @param x is the x coord of the bubble you are checking around (initially the one just shot but its recursive so all the matches around that one too)
	 * @param y is the y coord of the bubble you are checking around
	 */
	public void checkMatch2(int x, int y){//recursive
		String c = grid[y][x].color;
		if(x+1 <= gridColumns-1){//check right
			if(grid[y][x+1] != null && c.equals(grid[y][x+1].color)){
				grid[y][x] = null;
				checkMatch2(x+1, y);
				grid[y][x+1] = null;
			}
		}
		if(x-1 >= 0){//check left
			if(grid[y][x-1] != null && c.equals(grid[y][x-1].color)){
				grid[y][x] = null;
				checkMatch2(x-1, y);
				grid[y][x-1] = null;
			}
		}
		if(y+1 <= gridRows-1){//check down (notice the sign)
			if(grid[y+1][x] != null && c.equals(grid[y+1][x].color)){
				grid[y][x] = null;
				checkMatch2(x, y+1);
				grid[y+1][x] = null;
			}
		}
		if(y-1 >= 0){//check up
			if(grid[y-1][x] != null && c.equals(grid[y-1][x].color)){
				grid[y][x] = null;
				checkMatch2(x, y-1);
				grid[y-1][x] = null;
			}
		}
	}
	
	/**
	 * Just used for testing
	 * prompts the play for x and y coords of the place they would like to shoot the bubble that is in the front of the gunList
	 */
	public void dummyShoot(){	//for testing purposes. Just enter the location in the grid you want to shoot the bubble
								//(top left is (0,0))
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
				checkMatch2(x,y);
				break;
			}
			else{
				System.out.println("Choose and empty space!");
			}
		}
	}

	
	/**
	 * 
	 * @param click mouse clicked = true
	 *  set from controller
	 * 
	 * 
	 */
	public void setClicked(boolean click) {
		this.clicked = click;
	}

	
	/**
	 *  checks for location matches between gun bubble and grid bubble
	 */
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

	
	/**
	 * 
	 * @param bub1 grid bubble being checked location match 
	 * @param bub2 gun bubble being checked for location match
	 * @return true if match found based on locations
	 */
	public boolean checkContact(Bubble bub1, Bubble bub2) { // checks bubble contact between gunbubble and gridbubble
		if ((((bub2.xCoord - bubbleImageWidth) <= bub1.xCoord) && (bub1.xCoord <= (bub2.xCoord + bubbleImageWidth)))
				&& (((bub2.yCoord - bubbleImageHeight) <= bub1.yCoord)
						&& (bub1.yCoord <= (bub2.yCoord + bubbleImageHeight)))) {
			return true;
		} else {
			return false;
		}
	}



	
	

	
	
	/**
	 * 
	 * @param row x-index of grid where a match between gunBubble and grid was made
	 * @param col y-index of grid where a match between gunBubble and grid was made
	 * @param gunBubble the bubble being matched to
	 */
	public void recursion(int row, int col, Bubble gunBubble) {

	/**
	 * 
	 * @param bub1 bubble being compared for color
	 * @param bub2 bubble being compared for color
	 * @return true if same color
	 */
	public boolean match(Bubble bub1, Bubble bub2) {
		return bub1.color == bub2.color && bub1 != null && bub2 != null;
	}

	/**
	 * 
	 * @param row y-value of grid index
	 * @param col x-vlaue of grid index
	 * @param pivot gun bubble being compared to grid for matches based on color and touching
	 */



	/**
	 * 
	 * @param row y-value of grid index
	 * @param col x-value of grid index
	 * @return true if inside the grid
	 */

	public boolean isInBound(int row, int col) {
		return row <= grid.length - 1 && col <= grid[0].length - 1 && col >= 0 && row >= 0;
	}





	/**
	 * 
	 * @param row y-value of grid
	 * @param col x-value of grid
	 * @return true if the recursive method has been to this grid location
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




	// checks to see where in the grid the bubble landed compared to the one it made
	// contact with
	// helper for shoot

	 
	


	

	
	/**
	 * 
	 * @param b bubble being added to grid
	 * 
	 *  checks to see where in the grid the bubble landed compared to the one it made
	 * contact with
	 * helper for shoot
	 * 
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



	// reloads when we shoot

	
	/**
	 * moves bubbles in array toward index 0
	 */


	
	/**
	 *  reloads when we shoot
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
=======
	
	/**
	 * 
	 *  oscillation of gun that applies clicked as a mouse listener
	 * ran throughout game
	 * 
	 * 
>>>>>>> 2813ca3cc6ce32068d84a44e1a051f726aa974f6
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
	/**
	 * 
	 * @param b gun bubble being checked for location
	 * @param degr angle of gun
	 * @param xMoved projection of gun angle onto x plane
	 * @param yMoved projection of gun angle onto y plane
	 * @return true if bubble has made contact with grid
	 */
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

	
	/**
	 * 
	 * @param b gun bubble being checked
	 * @param degr angle of gun
	 * 
	 *  moves forward on an angle
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
	 * @param xCo projection of gun onto x-axis
	 * @param yCo projection of gun onto y-axis
	 * 
	 */
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
		//reloadGun(); // removes old gunBubble and reloads gun
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
