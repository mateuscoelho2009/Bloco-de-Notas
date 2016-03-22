package Listeners;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;

public class FontTypeListener implements ActionListener {

    private JTextArea textArea;

    public FontTypeListener(JTextArea textArea){
        this.textArea = textArea;
    }

    public void actionPerformed(ActionEvent e){
        JRadioButtonMenuItem button_ = (JRadioButtonMenuItem) e.getSource();
        
        Font font_ = textArea.getFont();

        textArea.setFont(new Font(button_.getText(), font_.getStyle(), font_.getSize()));
    }
}