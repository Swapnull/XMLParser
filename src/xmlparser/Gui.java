package xmlparser;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import jsyntaxpane.*;
import org.fife.ui.rsyntaxtextarea.*;
import org.fife.ui.rtextarea.*;

/**
 *
 * @author Martyn Rushton
 */
public class Gui{

    /**
     * Creates new form Gui
     */
    RSyntaxTextArea field = new RSyntaxTextArea(20, 40);
    static JFrame frame = new JFrame("XML Parser");
    static String username;
    public Gui() {
        
        DefaultSyntaxKit.initKit(); //get syntax highlighting
        RTextScrollPane scrPane = new RTextScrollPane(field);
        
        field.setEditable(true);        
        field.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
        field.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        field.setLineWrap(true);
        //frame.add(field);
        frame.add(scrPane);
        frame.pack();
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
        
        JMenuItem fileItem = new JMenuItem("Exit");
        fileItem.setMnemonic(KeyEvent.VK_E);
        fileItem.setToolTipText("Exit Application");
        fileItem.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent event){
               System.exit(0);
           }
        });
        
        file.add(fileItem);
  
        JMenuItem user = new JMenu("User");
        menubar.add(user);
        file.setMnemonic(KeyEvent.VK_U);
        
        JMenuItem userItem = new JMenuItem("Username");
        userItem.setMnemonic(KeyEvent.VK_E);
        userItem.setToolTipText("Change Username");
        userItem.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent event){
               //change the dialog pane to allow username changes.
               JOptionPane username_frame = new JOptionPane();
               
               JTextField username_input = new JTextField();
               username_frame.add(username_input);
               username_frame.showMessageDialog(frame, username_input);
               System.out.println(username_input.getText());
               setUsername(username_input.getText());
               
           }
        });
        
        user.add(userItem);
        
        menubar.setSize(300,300);
        menubar.setVisible(true);
    }
    
    public static void setUsername(String Username){
        username = Username;
    }
    public String getUsername(){
        return username;
    }
}

