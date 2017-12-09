import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

/**
 * 
 * @author James Galante, Sam Hughes, Chris Sutton, Olivia Leipa, Scott Miller
 *
 *
 *class holds a custom mouse listener to fire the actual gameplay
 */
public class MenuCustomMouseListener implements MouseListener {
	
	

	public static PopView diffMenu;
	public static PopView menu;
    public static PopView howToPlay;

    /**
     * used to start game window and close menu
     */
	@Override
	public void mouseClicked(MouseEvent e) {
		PopController.pract=false;
		menu.setVisible(false);
		diffMenu.setVisible(true);
	
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
	 * @param diffMenu diff menu instance of game
	 * @param menu  menu instance of game
	 * @param howToPlay howtoplay instance of game
	 */
	public static void sendInstancesToMenuCustomMouseListener(PopView diffMenu, PopView menu, PopView howToPlay){
		MenuCustomMouseListener.diffMenu= diffMenu;
		MenuCustomMouseListener.menu=menu;
		MenuCustomMouseListener.howToPlay=howToPlay;
	}

}
