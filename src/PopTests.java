import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

import org.junit.Test;

public class PopTests {
	static boolean play = true;
    static boolean quit = true;
	static boolean pract = false;
	
	@Test
	public void PopTest(){

			
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
				QuitPracticeListener.SendInstances(view, lose, m);
				menu.drawMenu();

				lose.drawLoseScreen();	
				m.difficulty =1;

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
								TimeUnit.MILLISECONDS.sleep(100); // needs something in while loop or it wont work
								m.userClicked =true;
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
						//m.lost = true;
					} // close while
					if (m.won) {// print statement if the player wins
						//m.printWin(m);
					} // close if
					if (m.lost) {// print statement if the player loses
						m.printLose(m);
						YouLoseListener.turnOnLoseScreen();
						while(quit){
							try {
								TimeUnit.MILLISECONDS.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}			
					} // close if
	//			}// close else reg game 
	//		} // close while
				
					assertTrue(m.gunList.length>0);
					assertTrue(m.grid[0].length>0);
					assertTrue(m.grid.length>0);
					assertTrue(m.shotsFired==0);
					assertTrue(m.difficulty==1);
					
					
				 
				    
	}
	
}




