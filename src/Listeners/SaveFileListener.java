package Listeners;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JTextArea;

public class SaveFileListener implements ActionListener {
	JTextArea textArea;
	FileDialog Fsave;
	
	public SaveFileListener(JTextArea textArea, FileDialog Fsave) {
		super();
		
		this.Fsave = Fsave;
		
		this.textArea = textArea;
	}
	
	public void actionPerformed(ActionEvent e){           
        try{
            Fsave.setVisible(true);
            if (Fsave.getFile() == null)
                return;
            // Salvando em .txt
            String nome = Fsave.getDirectory() + Fsave.getFile() + ".txt";
            FileWriter out = new FileWriter(nome);
            
            out.write(textArea.getText());
            out.close();
        }	catch(java.io.IOException exc) {
        	
        }
    }
}