
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

	static Scanner scn = new Scanner(System.in);
	static boolean play = true;
    static boolean quit = true;
	static boolean pract = false;
	// public int difficulty; // determines speed of game
	// boolean clicked;

	////////////////////////////////
	// Some printing functions

	////////////////////////////////
	// Main with view
	public static void main(String[] args) {


		while (play) {// Plays all aspects of the game until the player decides to quit
			JFrame frame = new JFrame(); // create frame
			PopModel m = new PopModel(); // create model
			PopView view = new PopView(m, frame); // create view of game play
			PopView menu = new PopView(m, frame); // menu view
			PopView howto = new PopView(m, frame);
			PopView lose = new PopView(m, frame);
			PopController pop = new PopController();
			menu.menuView = new MenuCustomMouseListener();
			menu.howToPlay = new HowToPlayMouseListener();
			howto.howToPlay = new HowToPlayMouseListener();
			PopView diffMenu = new PopView(m, frame);
			
			
			diffMenu.drawDifficutlyMenu();
			
			MenuCustomMouseListener.sendInstancesToMenuCustomMouseListener(diffMenu, menu, howto);
			EasyMouseListener.pullInstances(view, diffMenu, m);
			MediumMouseListener.pullInstances(view, diffMenu, m);
			HardMouseListener.pullInstances(view, diffMenu, m);
			HowToPlayMouseListener.sendInstancesToHowToPlayMouseListener(diffMenu, menu, howto);
			YouLoseListener.sendInstancesToYouLoseListener(view, lose, pop);
			PlayAgainListener.SendInstancesToPlayAgain(view, lose, pop);
			menu.drawMenu();
			menu.setVisible(true);
			//howto.drawHowToPlay();
			lose.drawLoseScreen();
		
			while(m.difficulty == -1) {
				System.out.print(""); //wait
			}
			while(menu.isVisible()){
				System.out.println("");
			}
			if(pract){
				m.chooseObjectives();
				m.printObjectives(m);
				m.setpracticeGrid(); // set model grid
				m.loadpracticeGun(); // load model gun
				view.drawPractice(); // draw bubbles/panel/gun/etc all corresponding to model		
				m.GameModeConsole = false;
				m.won = false;
				m.lost = false;
				while (!m.won && !m.lost) {// plays the game until the player has won or lost
					m.printGrid(m);
					m.printGunList(m);
					try {
						while (m.userClicked == false) {
							System.out.print(""); // needs something in while loop or it wont work
						}
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
					YouLoseListener.turnOnLoseScreen();
					while(quit){
						System.out.print("");
					}
					
				} // close if
			}else{
				m.chooseObjectives();
				m.printObjectives(m);
				m.setGrid(); // set model grid
				m.loadGun(); // load model gun
				view.draw(); // draw bubbles/panel/gun/etc all corresponding to model				
				m.GameModeConsole = false;		
				System.out.println("Grid Set!");
				m.won = false;
				m.lost = false;
				while (!m.won && !m.lost) {// plays the game until the player has won or lost
					m.printGrid(m);
					m.printGunList(m);
					try {
						while (m.userClicked == false) {
							System.out.print(""); // needs something in while loop or it wont work
						}
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
					YouLoseListener.turnOnLoseScreen();
					while(quit){
						System.out.print("");
					}			
				} // close if
			}// close else reg game 
		} // close while
	}// close main

}
