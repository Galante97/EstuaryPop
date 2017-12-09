import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * 
 * @author James Galante, Sam Hughes, Chris Sutton, Olivia Leipa, Scott Miller
 *
 */
public class YouLoseListener implements MouseListener {
	static PopView view;
	static PopView lose;
	static PopController controller;

	/**
	 * turn on view in game
	 */
	public static void turnOnLoseScreen(){
		view.setVisible(false);
		lose.setVisible(true);	
	}
	
	/**
	 * 
	 * @param view view instance of game
	 * @param loseScreen lose instance of game
	 * @param con controller instance of game
	 */
	public static void sendInstancesToYouLoseListener(PopView view, PopView loseScreen, PopController con){
		YouLoseListener.view = view;
		YouLoseListener.lose= loseScreen;
		YouLoseListener.controller=con;
	}
	
	/**
	 * used to replay the game
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		lose.setVisible(false);
		view.setVisible(false);
		PopController.play = false;
		PopController.quit = false;
		
		
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * not used
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * not used
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * not used
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * not used
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
