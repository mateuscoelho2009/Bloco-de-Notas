package Listeners;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class NewFileListener implements ActionListener {
	JTextArea textArea;
	FileDialog Fsave;
	
	public NewFileListener(JTextArea textArea, FileDialog Fsave) {
		super();
		
		this.Fsave = Fsave;
		
		this.textArea = textArea;
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
           if (textArea.getText() != null){
                int opc = JOptionPane.showConfirmDialog(null, "Do You Wish To Save The Current Changes?");
                if(opc == JOptionPane.YES_OPTION){
	                try{
	                    // Fsave.show();
	                    if (Fsave.getFile()==null) 
	                        return;
	                    String nome = Fsave.getDirectory() + Fsave.getFile();
	                    
	                    FileWriter out = new FileWriter(nome);
	                    
	                    out.write(textArea.getText());
	                    out.close();
	                }	catch(java.io.IOException exc) {
	                	  
	                } 
                }
                if (opc == JOptionPane.NO_OPTION)
                    textArea.setText(null);            
           }
     }
}
