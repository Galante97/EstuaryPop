import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class QuitPracticeListener implements MouseListener {

	private static PopView view;
	private static PopView lose;
	private static PopModel model;

	@Override
	public void mouseClicked(MouseEvent e) {
		model.lost = true;
	    model.userClicked= true;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void SendInstances(PopView view, PopView lose, PopModel m) {
		QuitPracticeListener.view=view;
		QuitPracticeListener.lose=lose;
		QuitPracticeListener.model=m;
	}

}
