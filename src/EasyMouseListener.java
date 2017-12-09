import java.awt.event.MouseEvent;



/**
 * 
 * @author  James Galante, Sam Hughes, Chris Sutton, Olivia Leipa, Scott Miller
 *
 */
import java.awt.event.MouseListener;


public class EasyMouseListener implements MouseListener{

	public static PopView diffMenu;
	public static PopView view;
	public static PopModel model;
	
	
	/**
	 * used for easy difficulty level on game start
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		diffMenu.setVisible(false);
		model.setDifficulty(1);
		view.setVisible(true);	
	}
	
	/**
	 * 
	 * @param view view instance in the game
	 * @param dmenu menu instance in the game
	 * @param m model instance in the game
	 */
	public static void pullInstances(PopView view, PopView dmenu, PopModel m){
		EasyMouseListener.view= view;
		EasyMouseListener.diffMenu=dmenu;
		EasyMouseListener.model = m;
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
