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

	@Override
	public void mouseClicked(MouseEvent e) {
		PopController.pract=false;
		menu.setVisible(false);
		diffMenu.setVisible(true);
	
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public static void sendInstancesToMenuCustomMouseListener(PopView diffMenu, PopView menu, PopView howToPlay){
		MenuCustomMouseListener.diffMenu= diffMenu;
		MenuCustomMouseListener.menu=menu;
		MenuCustomMouseListener.howToPlay=howToPlay;
	}

}
