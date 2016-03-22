import java.awt.Container;

import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

import Listeners.*;

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
        newFile.addActionListener(new NewFileListener(textArea, Fsave));
        open.addActionListener(new OpenFileListener(textArea, f));         
        save.addActionListener(new SaveFileListener(textArea, Fsave));
        
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
}
