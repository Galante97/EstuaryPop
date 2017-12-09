import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 
 * @author  James Galante, Sam Hughes, Chris Sutton, Olivia Leipa, Scott Miller
 *
 */
public class MediumMouseListener implements MouseListener {

	public static PopView diffMenu;
	public static PopView view;
	public static PopModel model;
	
	
	/**
	 * used for setting medium difficultly in game via button
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		diffMenu.setVisible(false);
		model.setDifficulty(2);
		view.setVisible(true);	
	}
	
	/**
	 * 
	 * @param view view instance of game
	 * @param dmenu menu instance of game
	 * @param m model instance of game
	 */
	public static void pullInstances(PopView view, PopView dmenu, PopModel m){
		MediumMouseListener.view= view;
		MediumMouseListener.diffMenu=dmenu;
		MediumMouseListener.model = m;
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
