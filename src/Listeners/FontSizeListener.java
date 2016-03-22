package Listeners;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;

public class FontSizeListener implements ActionListener {

    private JTextArea textArea;

    public FontSizeListener(JTextArea textArea){
        this.textArea = textArea;
    }

    public void actionPerformed(ActionEvent e){
        JRadioButtonMenuItem button_ = (JRadioButtonMenuItem) e.getSource();
        
        Font font_ = textArea.getFont();

        textArea.setFont(new Font( font_.getFontName(),
        						   font_.getStyle(), 
        						   Integer.parseInt(button_.getText()) ));
    }
}
