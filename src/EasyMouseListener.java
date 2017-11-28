import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EasyMouseListener implements MouseListener{

	public static PopView diffMenu;
	public static PopView view;
	public static PopModel model;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		diffMenu.setVisible(false);
		model.setDifficulty(1);
		view.setVisible(true);	
	}
	
	public static void pullInstances(PopView view, PopView dmenu, PopModel m){
		EasyMouseListener.view= view;
		EasyMouseListener.diffMenu=dmenu;
		EasyMouseListener.model = m;
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
