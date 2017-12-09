import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * 
 * @author James Galante, Sam Hughes, Chris Sutton, Olivia Leipa, Scott Miller
 *
 */
public class QuitPracticeListener implements MouseListener {

	private static PopView view;
	private static PopView lose;
	private static PopModel model;

	
	/**
	 * used to quit the practice session and return back to the menu page
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		model.lost = true;
	    model.userClicked= true;
	}

	
	/**
	 * not used
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * not used
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * not used
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	
	/**
	 * not used
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param view view instance of the game
	 * @param lose lose instance of game
	 * @param m model instance of game
	 */
	public static void SendInstances(PopView view, PopView lose, PopModel m) {
		QuitPracticeListener.view=view;
		QuitPracticeListener.lose=lose;
		QuitPracticeListener.model=m;
	}

}
