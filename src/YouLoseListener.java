import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class YouLoseListener implements MouseListener {
	static PopView view;
	static PopView lose;
	static PopController controller;

	public static void turnOnLoseScreen(){
		view.setVisible(false);
		lose.setVisible(true);
		
	}
	public static void sendInstancesToYouLoseListener(PopView view, PopView loseScreen, PopController con){
		YouLoseListener.view = view;
		YouLoseListener.lose= loseScreen;
		YouLoseListener.controller=con;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		lose.setVisible(false);
		view.setVisible(false);
		YouLoseListener.controller.play = false;
		
		
		// TODO Auto-generated method stub
		
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
}
