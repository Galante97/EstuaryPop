import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JFrame;

public class PopController {

	public int difficulty; // determines speed of game
	static boolean won = false;
	static boolean lost = false;
	static boolean playAgain = true;
	boolean clicked;
	static int highScore = 0;
	static Scanner scn = new Scanner(System.in);
	
	////////////////////////////////
	//Some printing functions//

	/**
	 * Used for printing the grid to the console. Essentially for testing purposes
	 * @param m is the PopModel instance that this method is printing
	 */
	public static void printGrid(PopModel m){
		for(int i = 0; i < m.gridRows-1 ; i++){
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
		for(Bubble b: m.grid[m.gridRows-1]){
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
	 * @param m is the PopModel instance that this method is printing
	 */
	public static void printGunList(PopModel m){
		for(Bubble x: m.gunList){
			System.out.print(x + " - ");
		}
		System.out.println("");
	}
	
	/**
	 * Used for printing the objectives to the console so the player knows the objectives. Essentially for testing purposes
	 * @param m is the PopModel instance that this method is printing from
	 */
	public static void printObjectives(PopModel m){
		int n = 1;
		for(int i: m.objectives){
			System.out.println("\t" + n + ".)\t" + m.o.returnStatements(i));
			n++;
		}
	}
	
	////////////////////////////////

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		PopModel model = new PopModel(1);// will ask for difficulty
		//model.setGrid();
		PopView view = new PopView(model, frame); // creates the window
		view.draw();
		view.setVisible(true);
		while (playAgain) {	//just uses model methods to play game
							//must play in console, ignor the popup window to play this
			System.out.println("What difficulty would you like to play? ...\n*(1 = Easy, 2 = Medium, 3 = Hard)*");
			int d = scn.nextInt();
			PopModel m = new PopModel(d);
			m.chooseObjectives();
			printObjectives(m);
			m.setGrid();
			m.loadGun();
			won = false;
			lost = false;
			while (!won && !lost) {
				printGrid(m);
				System.out.print("\n\n\n\nScore: " + m.score + "\t\t\t\t\t");
				printGunList(m);
				m.dummyShoot();
				won = true;
				for(Bubble[] row: m.grid){//checks if the grid is empty
					for(Bubble b : row){
						if(b != null){
							won = false;
						}
					}
				}
				for(Bubble b: m.grid[m.gridRows-1]){//checks if there are any bubbles below the line
					if(b != null){
						lost = true;
					}
				}
			}
			if(won){
				printGrid(m);
				System.out.print("\n\n\n\n\t\t\t\t\t\t");
				printGunList(m);
				if(m.score > highScore){highScore = m.score;}
				System.out.println("You won!! Your score was " + m.score + ".\nThe high score is " + highScore + ".");
			}
			if(lost){
				printGrid(m);
				System.out.print("\n\n\n\n\t\t\t\t\t\t");
				printGunList(m);
				System.out.println("You lost... :'(");
			}
			int g = -1;
			while(g != 0 || g!= 1){
				System.out.println("Do you want to play again? ...\n*(0 = no, 1 = yes)*");
				g = scn.nextInt();
				if(g == 0 || g == 1){
					break;
				}
				else{
					System.out.println("Please enter 0 or 1.");
				}
			}
			if(g == 0){
				playAgain = false;
				System.out.println("Game over.");
			}
		}

	}

	public void setClicked() {
		//model.setClicked(clicked);
	}

	public void getClicked() {
		//clicked = view.getClicked();
	}
}

