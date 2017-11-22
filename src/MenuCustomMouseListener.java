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
	
	

	public static PopView view;
	public static PopView menu;
    public static PopView howToPlay;

	@Override
	public void mouseClicked(MouseEvent e) {	
		menu.setVisible(false);
		view.setVisible(true);
	
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
	
	public static void sendInstancesToMenuCustomMouseListener(PopView view, PopView menu, PopView howToPlay){
		MenuCustomMouseListener.view= view;
		MenuCustomMouseListener.menu=menu;
		MenuCustomMouseListener.howToPlay=howToPlay;
	}

}
