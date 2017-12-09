import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 
 * @author James Galante, Sam Hughes, Chris Sutton, Olivia Leipa, Scott Miller
 *
 */
public class PlayAgainListener implements MouseListener {
	public static PopView view;
	public static PopView lose;
	public static PopController con;
	
/**
 * used for the play again button to launch new game
 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		view.setVisible(false);
		lose.setVisible(false);
		PopController.quit = false;
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
	
	/**
	 * 
	 * @param view view instance of the game
	 * @param lose lose instance of the game
	 * @param pop controller instance of the game
	 */
	public static void SendInstancesToPlayAgain(PopView view, PopView lose, PopController pop){
		PlayAgainListener.view= view;
		PlayAgainListener.lose= lose;
		PlayAgainListener.con=pop;
	}

}
