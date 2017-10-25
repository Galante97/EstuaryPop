import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PopView extends JFrame {

	// constructor
	public PopView() {
		initUI();

	}

	// window set up
	private void initUI() {
		setTitle("Estuary Pop!");
		setSize(900, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}



}
