
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

	// public int difficulty; // determines speed of game
	// boolean clicked;

	////////////////////////////////
	// Some printing functions

	////////////////////////////////
	// Main with view
	public static void main(String[] args) {

		

		while (play) {// Plays all aspects of the game until the player decides to quit
			System.out.println("What difficulty would you like to play? ...\n*(1 = Easy, 2 = Medium, 3 = Hard)*");
			int d = scn.nextInt();

			JFrame frame = new JFrame(); // create frame
			PopModel m = new PopModel(d); // create model
			PopController pop = new PopController();
			PopView view = new PopView(m, frame); // create view of game play
			PopView menu = new PopView(m, frame); // menu view
			PopView howto = new PopView(m, frame);
			PopView lose = new PopView(m, frame);
			menu.menuView = new MenuCustomMouseListener();
			menu.howToPlay = new HowToPlayMouseListener();
			howto.howToPlay = new HowToPlayMouseListener();
			MenuCustomMouseListener.sendInstancesToMenuCustomMouseListener(view, menu, howto);
			HowToPlayMouseListener.sendInstancesToHowToPlayMouseListener(view, menu, howto);
			YouLoseListener.sendInstancesToYouLoseListener(view, lose, pop);
			
			m.chooseObjectives();
			m.printObjectives(m);
			m.setGrid(); // set model grid
			m.loadGun(); // load model gun
			menu.drawMenu();
			menu.setVisible(true);
			// view.drawHowToPlay();
			howto.drawHowToPlay();
			view.draw(); // draw bubbles/panel/gun/etc all corresponding to model

			m.GameModeConsole = false;
			
			
			
			System.out.println("Grid Set!");

			m.won = false;
			m.lost = false;
			while (!m.won && !m.lost) {// plays the game until the player has won or lost
				m.printGrid(m);
				System.out.print("\nScore: " + m.score + " [" + m.objectiveTally[0] + "," + m.objectiveTally[1] + ","
						+ m.objectiveTally[2] + "]" + "\t\t\t\t");
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
			} // close if
			
			
//			int g = -1;
//			while (g != 0 || g != 1) {// asks if the player wants to play again after they won/ lost
//				System.out.println("Do you want to play again? ...\n*(0 = no, 1 = yes)*");
//				g = scn.nextInt();
//				if (g == 0 || g == 1) {
//					break;
//				} // close if
//				else {
//					System.out.println("Please enter 0 or 1.");
//				} // close else
//			} // close while
//			if (g == 0) {
//				m.playAgain = false;
//				System.out.println("Game over.");
//			} // close if
		} // close while
	}// close main

}
