package Listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

public class HelpListener implements ActionListener {
	JTextArea textArea;
	
	public HelpListener(JTextArea textArea) {
		super();
		
		this.textArea = textArea;
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		
	}
}
