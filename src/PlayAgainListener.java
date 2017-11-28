import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayAgainListener implements MouseListener {
	public static PopView view;
	public static PopView lose;
	
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		view.setVisible(false);
		lose.setVisible(false);
		
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
	
	public static void SendInstancesToPlayAgain(PopView view, PopView lose){
		PlayAgainListener.view= view;
		PlayAgainListener.lose= lose;
	}

}
