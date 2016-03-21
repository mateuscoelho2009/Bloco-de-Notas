import java.awt.TextArea;

import javax.swing.*;

public class BlockGUI extends JFrame {
	final int HEIGHT = 400,
			  WIDTH = 500;
	
	JTextPane textArea;
	
	BlockGUI () {
		super ();
		
		initBlockGUI();
	}

	private void initBlockGUI() {
		textArea = new JTextPane();
	}
	
	public void Run () {
		add (textArea);
		
		setVisible(true);
		setSize(WIDTH, HEIGHT);
	}
}
