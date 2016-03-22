package Listeners;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;

public class FontStyleListener implements ActionListener {
	private JTextArea textArea;

    public FontStyleListener(JTextArea textArea){
        this.textArea = textArea;
    }
    
    public void actionPerformed(ActionEvent e){
        JRadioButtonMenuItem button = (JRadioButtonMenuItem) e.getSource();
        
        Font font_ = textArea.getFont();
        
        if (button.getText().equals("Bold")) {
        	textArea.setFont(new Font(font_.getFontName(), Font.BOLD, font_.getSize()));
        }
        else if (button.getText().equals("Italic")) {
        	textArea.setFont(new Font(font_.getFontName(), Font.ITALIC, font_.getSize()));
        }
        else if (button.getText().equals("Bold/Italic")) {
        	textArea.setFont(new Font(font_.getFontName(), Font.BOLD + Font.ITALIC, font_.getSize()));
        }
        else {
        	textArea.setFont(new Font(font_.getFontName(), Font.PLAIN, font_.getSize()));
        }
    }
}
