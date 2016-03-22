package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class PrintFileListener implements ActionListener {
	JTextArea textArea;
	
	public PrintFileListener (JTextArea textArea) {
		super();
		
		this.textArea = textArea;
	}
	
	public void actionPerformed(ActionEvent e){
		try {
		    boolean complete = textArea.print();
		    if (complete) {
		        /* show a success message  */
		    	JOptionPane.showMessageDialog(null, "Printing was a success.");
		    } else {
		        /*show a message indicating that printing was cancelled */
		    	JOptionPane.showMessageDialog(null, "Printing was cancelled.");
		    }
		} catch (PrinterException pe) {
		    /* Printing failed, report to the user */
			JOptionPane.showMessageDialog(null, "Print failed..");
		}
	}
}
