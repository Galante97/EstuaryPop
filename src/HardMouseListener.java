import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * 
 * @author James Galante, Sam Hughes, Chris Sutton, Olivia Leipa, Scott Miller
 *
 */
public class HardMouseListener implements MouseListener{

	public static PopView diffMenu;
	public static PopView view;
	public static PopModel model;

	
	/**
	 * used for hard difficulty button in game play
	 */
	@Override
	public void mouseClicked(MouseEvent e) {	
		diffMenu.setVisible(false);
		model.setDifficulty(3);
		view.setVisible(true);
	}
	
	/**
	 * 
	 * @param view view instance in game
	 * @param dmenu menu instance in game
	 * @param m model instance in game
	 */
	public static void pullInstances(PopView view, PopView dmenu, PopModel m){
		HardMouseListener.view= view;
		HardMouseListener.diffMenu=dmenu;
		HardMouseListener.model = m;
	}

	/**
	 * not used
	 */
	@Override
	public void mousePressed(MouseEvent e) {}

	/**
	 * not used
	 */
	@Override
	public void mouseReleased(MouseEvent e) {}

	/**
	 * not used
	 */
	@Override
	public void mouseEntered(MouseEvent e) {}

	
	/**
	 * not used
	 */
	@Override
	public void mouseExited(MouseEvent e) {}
}
