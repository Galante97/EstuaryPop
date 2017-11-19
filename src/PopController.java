
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 * PopController The PopController class is the controller in the MVC that
 * handles the data between the model and the view
 * 
 * @author James Galante, Sam Hughes, Chris Sutton, Olivia Leipa, Scott Miller
 * @version 1.0
 * @since 2017-09-31
 */

public class PopController {

	static boolean won = false;
	static boolean lost = false;
	static boolean playAgain = true;
	static int highScore = 0;
	static Scanner scn = new Scanner(System.in);
	//public int difficulty; // determines speed of game
	//boolean clicked;

	////////////////////////////////
	//Some printing functions

	/**
	 * Used for printing the grid to the console. Essentially for testing purposes
	 * @param m is the PopModel instance that this method is printing
	 */
	public static void printGrid(PopModel m){
		for(int i = 0; i < m.gridRows-3 ; i++){
			System.out.println("");
			for(int j = 0; j < m.gridColumns ; j++){
				if(m.grid[i][j] == null){
					System.out.print("[  Empty  ]\t\t");
				}
				else{
					System.out.print(m.grid[i][j]+"\t");
				}
			}
		}
		System.out.println("");
		System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
		for(Bubble b: m.grid[m.gridRows-3]){
			if(b == null){
				System.out.print("[  Empty  ]\t\t");
			}
			else{
				System.out.print(b+"\t");
			}
		}
	}

	/**
	 * Used for printing the gunList to the console. Essentially for testing purposes
	 * @param m  is the PopModel instance that this method is printing
	 */
	public static void printGunList(PopModel m){
		for(Bubble x: m.gunList){
			System.out.print(x + " - ");
		}
		System.out.println("");
	}

	/**
	 * Used for printing the objectives to the console so the player knows the
	 * objectives. Essentially for testing purposes
	 * @param m is the PopModel instance that this method is printing from
	 */
	public static void printObjectives(PopModel m){
		int n = 1;
		for(int i: m.objectives){
			System.out.println("\t" + n + ".)\t" + m.o.returnStatements(i));
			n++;
		}
	}
	
	/**
	 * Prints the board once the player has won as well as the players score and the overall high score
	 * @param m is the PopModel instance that this method is printing from
	 */
	public static void printWin(PopModel m){
		printGrid(m);
		System.out.print("\n\n\n\n\t\t\t\t\t\t");
		printGunList(m);
		if(m.score > highScore){highScore = m.score;}
		System.out.println("You won!! Your score was " + m.score + ".\nThe high score is " + highScore + ".");
	}
	
	/**
	 * Prints the board once the player has lost
	 * @param m is the PopModel instance that this method is printing from
	 */
	public static void printLose(PopModel m){
		printGrid(m);
		System.out.print("\n\n\n\n\t\t\t\t\t\t");
		printGunList(m);
		System.out.println("You lost... :'(");
	}

	////////////////////////////////

	public static void main(String[] args) {
		//JFrame frame = new JFrame();
		while(playAgain){//Plays all aspects of the game until the player decides to quit
			System.out.println("What difficulty would you like to play? ...\n*(1 = Easy, 2 = Medium, 3 = Hard)*");
			int d = scn.nextInt();
			PopModel m = new PopModel(d);
			m.chooseObjectives();
			printObjectives(m);
			m.setGrid();
			m.loadGun();
			won = false;
			lost = false;
			while (!won && !lost){//plays the game until the player has won or lost
				printGrid(m);
				System.out.print("\n\n\n\nScore: " + m.score + " [" + m.objectiveTally[0] + "," + m.objectiveTally[1] + "," + m.objectiveTally[2] + "]" + "\t\t\t\t");
				printGunList(m);
				try{
					m.moveGun();
				}
				catch(InterruptedException e){
					System.out.println("Delay...");
				}
				won = true;
				for(Bubble[] row: m.grid){//checks to see if the grid is empty. If so, the player wins
					for(Bubble b : row){
						if(b != null){
							won = false;
						}//close if
					}//close for
				}//close for
				for(Bubble b: m.grid[m.gridRows-3]){//checks to see if there are any bubbles under the dashed line. If so, the player loses
					if(b != null){
						lost = true;
					}//close if
				}//close for
			}//close while
			if(won){//print statement if the player wins
				printWin(m);
			}//close if
			if(lost){//print statement if the player loses
				printLose(m);
			}//close if
			int g = -1;
			while(g != 0 || g!= 1){//asks if the player wants to play again after they won/ lost
				System.out.println("Do you want to play again? ...\n*(0 = no, 1 = yes)*");
				g = scn.nextInt();
				if(g == 0 || g == 1){
					break;
				}//close if
				else{
					System.out.println("Please enter 0 or 1.");
				}//close else
			}//close while
			if(g == 0){
				playAgain = false;
				System.out.println("Game over.");
			}//close if
		}//close while
	}//close main
}

