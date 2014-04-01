package xmlparser;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import jsyntaxpane.*;
/**
 *
 * @author Martyn Rushton
 */
public class Gui {

    /**
     * Creates new form Gui
     */
    JEditorPane field = new JEditorPane();
    static JFrame frame = new JFrame(Gui.class.getName());
    public Gui() {
        
        DefaultSyntaxKit.initKit(); //get syntax highlighting
        JScrollPane scrPane = new JScrollPane(field);
 
        field.setEditable(false);
        field.setContentType("text/xml");
        field.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.add(field);
        createGUI();
        createMenu();
    }
    
    public static void createGUI(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(screenSize);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private static void createMenu(){
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        
        JMenu file = new JMenu("File");
        menubar.add(file);
        file.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem eMenuItem = new JMenuItem("Exit");
        eMenuItem.setMnemonic(KeyEvent.VK_E);
        eMenuItem.setToolTipText("Exit Application");
        eMenuItem.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent event){
               System.exit(0);
           }
        });
        
        file.add(eMenuItem);
        
        
        menubar.add(file);
        menubar.setSize(300,300);
        menubar.setVisible(true);
    }

}
