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
/**
 * 
 * @author  James Galante, Sam Hughes, Chris Sutton, Olivia Leipa, Scott Miller
 *
 */

public class PopModel {

	int difficulty = -1;

	Objective o = new Objective();
	Objective[] objs1 = new Objective[3];
	Objective[] objs2 = new Objective[4];
	Objective[] objs3 = new Objective[5];
	int[] objectives1 = { 0, 0, 0 };
	int[] objectives2 = { 0, 0, 0, 0 };
	int[] objectives3 = { 0, 0, 0, 0, 0 };
	boolean[] objdone1 = { false, false, false };
	boolean[] objdone2 = { false, false, false, false };
	boolean[] objdone3 = { false, false, false, false, false };
	int[] objectiveTally = { 0, 0, 0, 0 ,0 };

	int gridColumns = 13;
	int gridRows = 10;
	int startRows;

	int clickedPath = -1;

	int pathX;
	int pathY;

	int gridX;
	int gridY;

	boolean GameModeConsole;

	Bubble[][] grid = new Bubble[gridRows][gridColumns]; // make this connect to view
	int gunListLength = 7;
	Bubble[] gunList = new Bubble[gunListLength]; // make it connect to view

	int shifts = 0; // keeps track of number of shifts so we know when the gridBubbles are indented
	int shotsFired = 0; // updates with every shot, more than 4 the grid shifts

	int score = 0;

	boolean userClicked;

	boolean won = false;
	boolean lost = false;
	boolean playAgain = true;
	int highScore = 0;
	static Scanner scn = new Scanner(System.in);

	

	/**
	 * constructor
	 * 
	 * @param d
	 *            is the difficulty passed in from PopController
	 */
	public PopModel() {

	}
	
	/**
	 * 
	 * @param d difficulty level used to start game
	 */
	public void setDifficulty(int d) {
		difficulty = d;
		startRows = 4;
	}

	

	/**
	 * Randomly chooses 3 objectives that will teach the player 3 things about
	 * estuaries
	 */
	public void chooseObjectives() {// fills the array of ints called "objectives" with three different random
									// numbers. these will be the three objectives for the given game
		System.out.println("Choosing objectives..");
		Random rand = new Random();
		if (difficulty == 1) {
			while (objectives1[0] == objectives1[1] || objectives1[0] == objectives1[2] || objectives1[1] == objectives1[2]) {
				objectives1[0] = rand.nextInt(6);
				objectives1[1] = rand.nextInt(6);
				objectives1[2] = rand.nextInt(6);
			}// close while
		}
		if (difficulty == 2) {
			while (objectives2[0] == objectives2[1] || objectives2[0] == objectives2[2] || objectives2[0] == objectives2[3] ||
					objectives2[1] == objectives2[2] || objectives2[1] == objectives2[3] || objectives2[2] == objectives2[3]) {
				objectives2[0] = rand.nextInt(6);
				objectives2[1] = rand.nextInt(6);
				objectives2[2] = rand.nextInt(6);
				objectives2[3] = rand.nextInt(6);
			}// close while
		}
		if (difficulty == 3) {
			while (objectives3[0] == objectives3[1] || objectives3[0] == objectives3[2] || objectives3[0] == objectives3[3] || 
					objectives3[0] == objectives3[4] || objectives3[1] == objectives3[2] || objectives3[1] == objectives3[3] 
					|| objectives3[1] == objectives3[4]	|| objectives3[2] == objectives3[3] || objectives3[2] == objectives3[4]
						|| objectives3[3] == objectives3[4])  {
				objectives3[0] = rand.nextInt(6);
				objectives3[1] = rand.nextInt(6);
				objectives3[2] = rand.nextInt(6);
				objectives3[3] = rand.nextInt(6);
				objectives3[4] = rand.nextInt(6);
			}// close while
		}
}// close chooseOjbectives

	/** 
	 * initializes the board by randomly placing bubbles in the top few rows
	 */
	public void setGrid() {
		int columns = gridColumns;
		Random rand = new Random();
		System.out.println("Filling grid...");
		for (int i = 0; i < startRows; i++) {
			if (i % 2 != 0) {
				columns = gridColumns - 1;
			}
			for (int j = 0; j < gridColumns; j++) {
				if (difficulty == 1) {
					int r = rand.nextInt(3); 
					grid[i][j] = new Bubble(o.returnColor(objectives1[r]), o.returnGunImg(objectives1[r]),
							o.returnGridImg(objectives1[r]), difficulty);
					grid[i][j].switchImage();
				}
				if (difficulty == 2) {	
					int r = rand.nextInt(4);
					grid[i][j] = new Bubble(o.returnColor(objectives2[r]), o.returnGunImg(objectives2[r]),
							o.returnGridImg(objectives2[r]), difficulty);
					grid[i][j].switchImage();
				}
				if (difficulty == 3) {
					int r = rand.nextInt(5);
					grid[i][j] = new Bubble(o.returnColor(objectives3[r]), o.returnGunImg(objectives3[r]),
							o.returnGridImg(objectives3[r]), difficulty);
				}
				grid[i][j].switchImage();
			} // close for
			columns = gridColumns;
		} // close for
	}// close setGrid

	/**
	 *  shift grid down during game play
	 */
	public void shiftGrid() {
		Random rand = new Random();
		int columns = gridColumns;
		System.out.println("Shifting grid...");
		for (int i = gridRows-1; i >= 0; i--) {
			if (i == 0|| i ==1) {
				/*if (shifts % 2 == 0) {
					columns = gridColumns - 1; // add indented row
					//System.out.println("columns" + columns);
					grid[i][columns] = null; // maintain staggering
				}*/
				for (int j = 0; j < columns; j++) {
					
					if (difficulty ==1) {
					int r = rand.nextInt(3);
					
					grid[i][j] = new Bubble(o.returnColor(objectives1[r]), o.returnGunImg(objectives1[r]),
							o.returnGridImg(objectives1[r]), difficulty);
					grid[i][j].switchImage();
					}
					if (difficulty ==2) {
						int r = rand.nextInt(4);
						
						grid[i][j] = new Bubble(o.returnColor(objectives2[r]), o.returnGunImg(objectives2[r]),
								o.returnGridImg(objectives2[r]), difficulty);
						grid[i][j].switchImage();
						}
					if (difficulty ==3) {
						int r = rand.nextInt(5);
						
						grid[i][j] = new Bubble(o.returnColor(objectives3[r]), o.returnGunImg(objectives3[r]),
								o.returnGridImg(objectives3[r]), difficulty);
						grid[i][j].switchImage();
						}
					//System.out.println("columns" + j);
				}
			}
			if (i > 1) {
				for (int j = 0; j < gridColumns; j++) {
					//int x = grid[i - 1][j].yCoord;
					grid[i][j] = grid[i - 2][j];
				//	grid[i][j].yCoord = x;
				}
			} // close for
		} // close for
	}// close shiftGrid

	/**
	 * used both at the beginning and throughout the game to reload the gun adds
	 * bubbles to the end of the gun list randomly chooses one of the prechosen
	 * objectives for each bubble
	 */
	public void loadGun() {
		System.out.println("Loading Gun...");
		while (gunList[0] == null) {
			for (int i = 0; i < gunListLength - 1; i++) {
				gunList[i] = gunList[i + 1];
			}
			Random rand = new Random();
			if (difficulty == 1) {
			int r = rand.nextInt(3);
			gunList[gunListLength - 1] = new Bubble(o.returnColor(objectives1[r]), o.returnGunImg(objectives1[r]),
					o.returnGridImg(objectives1[r]), difficulty);
			}
			if (difficulty == 2) {
				int r = rand.nextInt(4);
				gunList[gunListLength - 1] = new Bubble(o.returnColor(objectives2[r]), o.returnGunImg(objectives2[r]),
						o.returnGridImg(objectives2[r]), difficulty);
				}
			if (difficulty == 3) {
				int r = rand.nextInt(5);
				gunList[gunListLength - 1] = new Bubble(o.returnColor(objectives3[r]), o.returnGunImg(objectives3[r]),
						o.returnGridImg(objectives3[r]), difficulty);
				}
		}
	}

	/**
	 * Waits for the player to click (or enter values for x and y if using
	 * dummyShoot or enter a path # if using manual input)
	 * 
	 * @throws InterruptedException
	 *             when dealing with the time delay for click to fire
	 */
	public void moveGun() throws InterruptedException {
		 score--;

		// Use dummyShoot (for testing)
		/*
		 * System.out.println("Would you like to shoot? ...\n*(1 = yes)*"); int x =
		 * scn.nextInt(); if(x == 1){ dummyShoot();//for testing purposes }
		 */

		// Manually input path # (for testing)

		System.out.println("What # Path would you like to shoot along? ...");
		int x;
		if (GameModeConsole == true) { // when running in console
			x = scn.nextInt();
		} else { // when NOT running in console
			x = clickedPath;
			userClicked = false;
		}

		if (x >= 0 && x <= 28) {
			shoot(x);

		} else {
			System.out.println("Choose a path number between 0 and 28!");
		}
		if (difficulty == 1) {
			for (int i = 0; i <= 2; i++) {
				System.out.println(objectiveTally[i] + " Tally");
				System.out.println(objdone1[i] + "Done");
				if (objectiveTally[i] >= 3 && !objdone1[i]) {
					objdone1[i] = true;
					System.out.println(objectives1[i]);
					System.out.println("\t\t" + o.returnCompleteStatement(objectives1[i]));
					i++;
				}
			}
			if (difficulty == 2) {
				for (int i = 0; i <= 3; i++) {

					System.out.println(objectiveTally[i] + " Tally");
					System.out.println(objdone2[i] + "Done");
					if (objectiveTally[i] >= 3 && !objdone2[i]) {
						objdone2[i] = true;
						System.out.println(objectives2[i]);
						System.out.println("\t\t" + o.returnCompleteStatement(objectives2[i]));
						i++;
					}
				}
				if (difficulty == 3) {
					for (int i = 0; i <= 4; i++) {	
						System.out.println(objectiveTally[i] + " Tally");
						System.out.println(objdone3[i] + "Done");
						if (objectiveTally[i] >= 3 && !objdone3[i]) {
							objdone3[i] = true;
							System.out.println(objectives3[i]);
							System.out.println("\t\t" + o.returnCompleteStatement(objectives3[i]));
							i++;
						}
					}
				}			
				System.out.println("Press any number to continue... ");
				int g = scn.nextInt();// kind of pointless. Just used to pause the game to allow the player to read
				// the complete statement. maybe change to a click to continue
			}

		}

		
	}

	/**
	 * used for txt based game play. it input x,y for shooting
	 */
	public void dummyShoot() {// for testing purposes
		System.out.println("Dummy Shoot...");
		while (true) {
			System.out.println("X coord? ...");
			int x = scn.nextInt();
			System.out.println("Y coord? ...");
			int y = scn.nextInt();
			if (grid[y][x] == null) {
				grid[y][x] = gunList[0];
				grid[y][x].switchImage();
				gunList[0] = null;
				loadGun();
				checkMatch(x, y);
				break;
			} else {
				System.out.println("Choose and empty space!");
			}
		}
	}
	////////

	/**
	 * @param t
	 *            is the "time" that has passes while the gun was moving waiting for
	 *            you to shoot. t determines the path that the bubble will travel
	 *            along after shot
	 */
	public void shoot(int t) {// works with with manual path # input or click to fire
		System.out.println("Shooting...");
		String c = gunList[0].color;
		int[][] finalPositions = { { 0, 7 }, { 0, 7 }, { 0, 6 }, { 0, 5 }, { 0, 4 }, { 0, 3 }, { 0, 2 }, { 0, 1 },
				{ 0, 0 }, { 1, 0 }, { 2, 0 }, { 3, 0 }, { 4, 0 }, { 5, 0 }, { 6, 0 }, { 7, 0 }, { 8, 0 }, { 9, 0 },
				{ 10, 0 }, { 11, 0 }, { 12, 0 }, { 12, 1 }, { 12, 2 }, { 12, 3 }, { 12, 4 }, { 12, 5 }, { 12, 6 },
				{ 12, 7 }, { 12, 7 } };// list of final postions the bubble can be shot to
		Path p = new Path(finalPositions[t][0], finalPositions[t][1]);// creates path based on which direction the
																		// bubble is shot
		p.fillPath();
		for (int index = 0; index < p.length; index++) {// checks through the path. once it finds a position that
														// already has a bubble in it, it places the shot bubble in the
														// previous position in the path
			if (grid[p.pathSpaces[index][1]][p.pathSpaces[index][0]] != null) {// checks position
				grid[p.pathSpaces[index - 1][1]][p.pathSpaces[index - 1][0]] = gunList[0];// places shot bubble in
																							// previous position along
																							// the path
				grid[p.pathSpaces[index - 1][1]][p.pathSpaces[index - 1][0]].switchImage();// switches the image

				System.out.println("!! [ " + p.pathSpaces[index - 1][1] + "," + p.pathSpaces[index - 1][0] + "  ] !!");
				gridToCoordinates gToC = new gridToCoordinates();
				gToC.gridX = p.pathSpaces[index - 1][0];
				gToC.gridY = p.pathSpaces[index - 1][1];

				gridX = p.pathSpaces[index - 1][0];
				gridY = p.pathSpaces[index - 1][1];

				if (gridY % 2 == 0) {
					gToC.isStagerred = false;

				} else {
					gToC.isStagerred = false;

				}

				pathX = gToC.getCoordX();
				pathY = gToC.getCoordY();

				System.out.println("!! [ " + gToC.getCoordX() + "," + gToC.getCoordY() + "  ] !!");

				gunList[0] = null;// removes bubble from gun
				loadGun();// reloads gun
				checkMatch(p.pathSpaces[index - 1][0], p.pathSpaces[index - 1][1]);
				updateObjectives(p.pathSpaces[index - 1][0], p.pathSpaces[index - 1][1], c);
				break;
			} // close if
			if (index == p.length - 1) {// if there were no bubble already along the given path, the shot bubble gets
										// placed at the end of the path (sticks to walls, does not bounce off walls)
				grid[finalPositions[t][1]][finalPositions[t][0]] = gunList[0];// places bubble in final position along
																				// the path

				grid[finalPositions[t][1]][finalPositions[t][0]].switchImage();// switches the image
				gunList[0] = null;// removes bubble from gun
				loadGun();// reloads gun
				checkMatch(finalPositions[t][0], finalPositions[t][1]);
				updateObjectives(finalPositions[t][0], finalPositions[t][1], c);
			} // close if
		} // close for

		shotsFired++;
		if (shotsFired > 7) { //every 3rd
			System.out.println("------Shift----");
			shiftGrid();
			shifts += 2;
			shotsFired = 0;
		}
		
	}// close shoot

	/**
	 * recursively checks surrounding bubbles once bubble has landed in the grid and
	 * removes matches
	 * 
	 * @param x
	 *            is the x position the bubble landed in
	 * @param y
	 *            is the y position the bubble landed in
	 */
	public void checkMatch(int x, int y) {// recursive
		System.out.println(x + "ycoord" + y);
		String c = grid[y][x].color;
		int xIndent = x + 1;
		if ((y + shifts) % 2 == 0) { // not indented, check y+1, x-1 and y+1, x
			xIndent = x - 1;
		}
		if (x + 1 <= gridColumns - 1) {// check right
			if (grid[y][x + 1] != null && c.equals(grid[y][x + 1].color)) {
				grid[y][x] = null;
				checkMatch(x + 1, y);
				grid[y][x + 1] = null;
				score++;
			}
		}
		if (x - 1 >= 0) {// check left
			if (grid[y][x - 1] != null && c.equals(grid[y][x - 1].color)) {
				grid[y][x] = null;
				checkMatch(x - 1, y);
				grid[y][x - 1] = null;
				score++;
			}
		}
		if (y + 1 <= gridRows - 1) {// check down (notice the sign)
			if (grid[y + 1][x] != null && c.equals(grid[y + 1][x].color)) {
				grid[y][x] = null;
				checkMatch(x, y + 1);
				grid[y + 1][x] = null;
				score++;
			}
			if (xIndent >= 0 && xIndent <= gridColumns - 1) {
				if (grid[y + 1][xIndent] != null && c.equals(grid[y + 1][xIndent].color)) {
					grid[y][x] = null;
					checkMatch(xIndent, y + 1);
					grid[y + 1][xIndent] = null;
					score++;
				}
			}
		}
		if (y - 1 >= 0) {// check up
			if (grid[y - 1][x] != null && c.equals(grid[y - 1][x].color)) {
				grid[y][x] = null;
				checkMatch(x, y - 1);
				grid[y - 1][x] = null;
				score++;
			}
			if (xIndent >= 0 && xIndent <= gridColumns - 1) {
				if (grid[y - 1][xIndent] != null && c.equals(grid[y - 1][xIndent].color)) {
					grid[y][x] = null;
					checkMatch(xIndent, y - 1);
					grid[y - 1][xIndent] = null;
					score++;
				}
			}
		}
	}

	/**
	 * checks to see if the shot bubble was popped and updates the objectiveTally
	 * accordingly
	 * 
	 * @param x
	 *            is the x position the bubble landed in
	 * @param y
	 *            is the y position the bubble landed in
	 * @param c
	 *            is the color of the bubble. used to identify which objective tally
	 *            to increase
	 */
	public void updateObjectives(int x, int y, String c) {
		if (grid[y][x] == null) {// adds 1 to the tally of the objective the player just matched (if they
									// matched)
			int m = 0;
			boolean match = false;
			while (!match) {
				if (difficulty == 1) {
					if (c.equals(o.returnColor(objectives1[m]))) {
						match = true;
					} // close if
					else {
						m++;
					}
				}//close if
				if (difficulty == 2) {
					if (c.equals(o.returnColor(objectives2[m]))) {
						match = true;
					} // close if
					else {
						m++;
					}
				}//close if
				if (difficulty == 3) {
					if (c.equals(o.returnColor(objectives3[m]))) {
						match = true;
					} // close if
					else {
						m++;
					}
				}//close if
			} // close while
			objectiveTally[m]++;
		} // close if
	}// close updateObjectives

	/**
	 * Used for printing the grid to the console. Essentially for testing purposes
	 * 
	 * @param m
	 *            is the PopModel instance that this method is printing
	 */
	public void printGrid(PopModel m) {
		for (int i = 0; i < m.gridRows - 2; i++) {
			System.out.println("");
			for (int j = 0; j < m.gridColumns; j++) {
				if (m.grid[i][j] == null) {
					System.out.print("[ ]\t");
				} else {
					System.out.print(m.grid[i][j] + "\t");
				}
			}
		}
		System.out.println("");
		System.out.println(
				"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		for (Bubble b : m.grid[m.gridRows - 2]) {
			if (b == null) {
				System.out.print("[ ]\t");
			} else {
				System.out.print(b + "\t");
			}
		}
	}

	/**
	 * Used for printing the gunList to the console. Essentially for testing
	 * purposes
	 * 
	 * @param m
	 *            is the PopModel instance that this method is printing
	 */
	public void printGunList(PopModel m) {
		for (Bubble x : m.gunList) {
			System.out.print(x + " - ");
		}
		System.out.println("");
	}

	/**
	 * Used for printing the objectives to the console so the player knows the
	 * objectives. Essentially for testing purposes
	 * 
	 * @param m
	 *            is the PopModel instance that this method is printing from
	 */
	public void printObjectives(PopModel m) {
		int n = 1;
		if (difficulty == 1) {
			for (int i : m.objectives1) {
				System.out.println("\t" + n + ".)\t" + m.o.returnStatements(i));
				n++;
			}
		}
		if (difficulty == 2) {
			for (int i : m.objectives2) {
				System.out.println("\t" + n + ".)\t" + m.o.returnStatements(i));
				n++;
			}
		}
		if (difficulty == 3) {
			for (int i : m.objectives3) {
				System.out.println("\t" + n + ".)\t" + m.o.returnStatements(i));
				n++;
			}
		}
	}

	/**
	 * Prints the board once the player has won as well as the players score and the
	 * overall high score
	 * 
	 * @param m
	 *            is the PopModel instance that this method is printing from
	 */
	public void printWin(PopModel m) {
		printGrid(m);
		System.out.print("\n\n\n\n\t\t\t\t\t\t");
		printGunList(m);
		if (m.score > m.highScore) {
			m.highScore = m.score;
		}
		System.out.println("You won!! Your score was " + m.score + ".\nThe high score is " + m.highScore + ".");
	}

	/**
	 * Prints the board once the player has lost
	 * 
	 * @param m
	 *            is the PopModel instance that this method is printing from
	 */
	public void printLose(PopModel m) {
		printGrid(m);
		System.out.print("\n\n\n\n\t\t\t\t\t\t");
		printGunList(m);
		System.out.println("You lost... :'(");
	}

	

	public static void main(String[] args) {
		System.out.println("What difficulty would you like to play? ...\n*(1 = Easy, 2 = Medium, 3 = Hard)*");
		int d = scn.nextInt();

		PopModel m = new PopModel(); // create model

		m.GameModeConsole = true;

		while (m.playAgain) {// Plays all aspects of the game until the player decides to quit

			m.chooseObjectives();
			m.printObjectives(m);

			m.setGrid(); // set model grid
			m.loadGun(); // load model gun

			System.out.println("Grid Set!");

			m.won = false;
			m.lost = false;
			while (!m.won && !m.lost) {// plays the game until the player has won or lost
				m.printGrid(m);
				System.out.print("\nScore: " + m.score + " [" + m.objectiveTally[0] + "," + m.objectiveTally[1] + ","
						+ m.objectiveTally[2] + "]" + "\t\t\t\t");
				m.printGunList(m);
				try {
					m.moveGun();
				} catch (InterruptedException e) {
					System.out.println("Delay...");
				}
				m.won = true;
				for (Bubble[] row : m.grid) {// checks to see if the grid is empty. If so, the player wins
					for (Bubble b : row) {
						if (b != null) {
							m.won = false;
						} // close if
					} // close for
				} // close for
				for (Bubble b : m.grid[m.gridRows - 2]) {// checks to see if there are any bubbles under the dashed
															// line. If so, the player loses
					if (b != null) {
						m.lost = true;
					} // close if
				} // close for
			} // close while
			if (m.won) {// print statement if the player wins
				m.printWin(m);
			} // close if
			if (m.lost) {// print statement if the player loses
				m.printLose(m);
			} // close if
			int g = -1;
			while (g != 0 || g != 1) {// asks if the player wants to play again after they won/ lost
				System.out.println("Do you want to play again? ...\n*(0 = no, 1 = yes)*");
				g = scn.nextInt();
				if (g == 0 || g == 1) {
					break;
				} // close if
				else {
					System.out.println("Please enter 0 or 1.");
				} // close else
			} // close while
			if (g == 0) {
				m.playAgain = false;
				System.out.println("Game over.");
			} // close if
		} // close while
	}// close main
	
	public void setpracticeGrid() {
		int columns = gridColumns;
		System.out.println("Filling grid...");
		for (int i = 0; i < startRows; i++) {
			if (i % 2 != 0) {
				columns = gridColumns - 1;
			}
			for (int j = 0; j < gridColumns; j++) {
				if (j <  gridColumns/2) {
					
					grid[i][j] = new Bubble(o.returnColor(objectives1[1]), o.returnGunImg(objectives1[1]),
							o.returnGridImg(objectives1[1]), difficulty);
					grid[i][j].switchImage();
				}else{
					grid[i][j] = new Bubble(o.returnColor(objectives1[2]), o.returnGunImg(objectives1[2]),
							o.returnGridImg(objectives1[2]), difficulty);
					grid[i][j].switchImage();
				}
				
			} // close for
			columns = gridColumns;
		} // close for
	}// close setGrid

	public void loadpracticeGun() {
		System.out.println("Loading Gun...");
		int j = 0;
		while (gunList[0] == null) {
			
			for (int i = 0; i < gunListLength - 1; i++) {
				gunList[i] = gunList[i + 1];
			}
			if(j%2==0){
			gunList[gunListLength - 1] = new Bubble(o.returnColor(objectives1[1]), o.returnGunImg(objectives1[1]),
					o.returnGridImg(objectives1[1]), difficulty);
			}else{
				gunList[gunListLength - 1] = new Bubble(o.returnColor(objectives1[2]), o.returnGunImg(objectives1[2]),
						o.returnGridImg(objectives1[2]), difficulty); 
			}
			j++;
		}
	}
	
	
}
