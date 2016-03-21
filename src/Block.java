import java.io.*;

public class Block {
	private BlockGUI blockGUI;
	
	Block () {
		blockGUI = new BlockGUI();
	}
	
	void Run () {
		blockGUI.Run();
	}
	
	public static void main (String[] arg) {
		Block b = new Block();
		
		b.Run();
	}
}
