import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class MenuCustomMouseListener implements MouseListener {
	
	boolean isStartClicked= false;

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("clicked in custom");
		JFrame frame = new JFrame(); //create frame
		PopModel m = new PopModel(1); //create model
		PopView view = new PopView(m, frame); // create view
		
		
		m.chooseObjectives();
		//printObjectives(m);
		
		m.setGrid(); //set model grid 
		m.loadGun(); //load model gun
		
		//view.drawMenu();
			
			
		view.draw(); //draw bubbles/panel/gun/etc all corresponding to model
	
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

}
