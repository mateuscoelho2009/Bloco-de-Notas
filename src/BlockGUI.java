import java.awt.Container;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

public class BlockGUI extends JFrame {
	// Attributes	
	final int HEIGHT = 600,
			  WIDTH = 800;
	
	private boolean wasSaved;
	private JTextArea textArea;
    private JMenu archive,
    			  help,
    			  format,
    			  font,
    			  fontStyle,
    			  fontSize;
    private JMenuItem newFile,
    				  open,
    				  save,
    				  quit,
    				  about,
    				  show,
    				  saveAs;
    private JRadioButtonMenuItem fonts[],
    							 styles[],
    							 sizes[];
    private ButtonGroup fontGroup,
    					stylesGroup,
    					sizesGroup;
    private File f;
    private FileDialog Fsave;
	
    // Constructors
	BlockGUI () {
		super ("----------- ITA ----------- Bloco de Notas ----------- ITA -----------");
		
		initBlockGUI();
	}

	// Methods
	private void initBlockGUI() {
		textArea = new JTextArea();
		
		wasSaved = false;
		
		Container c = new Container();
        c.setLayout(new FlowLayout());

        archive = new JMenu("Archive");
        archive.setMnemonic('A');
        
        newFile = new JMenuItem("New");
        open = new JMenuItem("Open...");
        save = new JMenuItem("Save");
        saveAs = new JMenuItem("Save As...");
        saveAs.setMnemonic('S');
        
        
        quit = new JMenuItem("Quit");
        
        Fsave = new FileDialog(this, "Save Archive", FileDialog.SAVE);
	}
	
	public void Run () {
		add (textArea);		
		
		// Archive
		archive.add(open);
        archive.add(save);
        archive.add(newFile);
        archive.add(saveAs);
        archive.addSeparator();
        archive.add(quit);
        quit.setMnemonic('Q');

        quit.addActionListener (new QuitListener());
        newFile.addActionListener(new NewFileListener(textArea));
        open.addActionListener(new OpenFileListener());         
        save.addActionListener(new SaveFileListener());
        
        // Help
        help = new JMenu("Help");
        help.setMnemonic('H');
        
        show = new JMenuItem("Show Help");
        about = new JMenuItem("About Bloco de Notas ITA");
        
        help.add(show);
        help.add(about);
        
        // Format
        format = new JMenu("Format");
        format.setMnemonic('F');
        
        String fontName_[] = {"TimesRoman", "Arial","Comic Sans MS","Verdana"};      
        String nameStyle_[] = {"Plain", "Bold", "Italic", "Bold/Italic"};
        String fontSize_[] = {"8","12", "14","16","20"};

        fonts = new JRadioButtonMenuItem[fontName_.length];
        fontGroup = new ButtonGroup();
        
        styles = new JRadioButtonMenuItem[nameStyle_.length];
        stylesGroup = new ButtonGroup();
        
        sizes = new JRadioButtonMenuItem[fontSize_.length];
        sizesGroup = new ButtonGroup();
        
        font = new JMenu("Font");
        format.add(font);
        
        fontStyle = new JMenu("Font Style");
        format.add(fontStyle);
        
        fontSize = new JMenu("Size");
        format.add(fontSize);
        
        for(int i = 0; i < fonts.length; i++) {
            fonts[i] = new JRadioButtonMenuItem(fontName_[i]);
            font.add(fonts[i]);
            fontGroup.add(fonts[i]);
            
            fonts[i].addActionListener(new FontTypeListener(textArea));
        }
        
        for(int i = 0; i < styles.length; i++) {
            styles[i] = new JRadioButtonMenuItem(nameStyle_[i]);
            fontStyle.add(styles[i]);
            stylesGroup.add(styles[i]);
            
            styles[i].addActionListener(new FontStyleListener(textArea));
        }
        
        for(int i = 0; i < sizes.length; i++) {
            sizes[i] = new JRadioButtonMenuItem(fontSize_[i]);
            fontSize.add(sizes[i]);
            sizesGroup.add(sizes[i]);
            
            sizes[i].addActionListener(new FontSizeListener(textArea));
        }

        JMenuBar bar_ = new JMenuBar();
        setJMenuBar(bar_);
        bar_.add(archive);
        bar_.add(format);
        bar_.add(help);
    
        JScrollPane scroll_ = new JScrollPane(textArea);
        textArea.setFont(new Font("TimesRoman", Font.PLAIN, 14));
        
        scroll_.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        textArea.setLineWrap(true);
        add(scroll_);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
	}
	
	/*
	 * Internal ActionListeners Classes
	 */
	class QuitListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
            System.exit(0);
        }
	}
	
	class NewFileListener implements ActionListener {
		JTextArea textArea;
		
		public NewFileListener(JTextArea textArea) {
			super();
			
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
		                    String nome = Fsave.getDirectory()+Fsave.getFile();
		                    
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
	
	// TODO: From this part on..
	// TODO: Take these classes out of here.
	class OpenFileListener implements ActionListener {
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
            	
            }
             	catch(IOException ex){}    
            }
	}
	
	class SaveFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e){           
            try{
                // Fsave.show();
                if (Fsave.getFile()==null) 
                    return;
                String nome = Fsave.getDirectory()+Fsave.getFile();
                FileWriter out = new FileWriter(nome);
                out.write(textArea.getText());
                out.close();
            }	catch(java.io.IOException exc) {
            	
            }
        }
	}
	
	class FontTypeListener implements ActionListener {

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
	
	class FontStyleListener implements ActionListener {
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
	
	class FontSizeListener implements ActionListener {

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
}
