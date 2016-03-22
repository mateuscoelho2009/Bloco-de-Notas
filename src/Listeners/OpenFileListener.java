package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class OpenFileListener implements ActionListener {
	JTextArea textArea;
	File f;
	
	public OpenFileListener(JTextArea textArea, File f) {
		super();
		
		this.f = f;
		
		this.textArea = textArea;
	}
	
	public void actionPerformed(ActionEvent e){ 
        JFileChooser chooser = new JFileChooser();
        int returnVal = chooser.showOpenDialog(null);
        
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            f = chooser.getSelectedFile();
        }
        try{
             RandomAccessFile file = new RandomAccessFile(f,"rw");
             String linha = "";
             StringBuffer sTxt = new StringBuffer();
             while((linha = file.readLine()) != null) {
                 sTxt.append(linha + "\n");
             }
             textArea.setText(sTxt.toString());
             file.seek(0);
        }	catch(FileNotFoundException ex) {
        	
        }   catch(IOException ex) {
         		
        }    
	}
}
