/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xmlparser;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Martyn Rushton
 */
public class Form {
    
    JFrame frame = new JFrame("XML Parser");
    int lab_width = 100;
    File file_name;
    boolean wantsToView = false;
    final JButton parseButton;
    final JButton fileChooseButton;
    final JButton parseAndOpenButton;
    final JTextField username_input;
    
    public Form(){
        JPanel panel1 = new JPanel(new BorderLayout());
        
        username_input = new JTextField("", 30);
        JLabel username_label = new JLabel("Enter your name");       
        JPanel username_panel = new JPanel(new BorderLayout());
        username_panel.add(username_label,BorderLayout.WEST);
        username_label.setPreferredSize(new Dimension(lab_width, 30));
        username_panel.add(username_input,BorderLayout.CENTER);
        panel1.add(username_panel, BorderLayout.NORTH);
       
        
        final JTextField file_input = new JTextField("", 30);
        JLabel file_label = new JLabel("File to Parse");
        JPanel file_panel = new JPanel(new BorderLayout());
        file_panel.add(file_label, BorderLayout.WEST);
        file_label.setPreferredSize(new Dimension(lab_width, 30));
        file_panel.add(file_input, BorderLayout.CENTER);
        panel1.add(file_panel);
        
        fileChooseButton = new JButton("Choose File");
        fileChooseButton.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
                openFile(file_input);
                file_input.setText(file_name.toString());
           } 
        });
        fileChooseButton.setToolTipText("Choose the file that you would like to be parsed.");
        panel1.add(fileChooseButton, BorderLayout.EAST);
        
        frame.add(panel1, BorderLayout.NORTH);
        
        
        
        
        
        JPanel panel2 = new JPanel(new BorderLayout());
        
        parseButton = new JButton("Parse File");
        parseButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //parse the file
            } 
        });
        parseButton.setToolTipText("<html><p width='300'>This option will just parse the file. The next screen will"
                + " then tell you where your parsed file has been stored.</p></html>");
        parseButton.setPreferredSize(new Dimension(300, 30));
        parseButton.setEnabled(false);
        panel2.add(parseButton, BorderLayout.WEST);
        
        parseAndOpenButton = new JButton("Parse and View File");
        parseAndOpenButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                wantsToView = true;
            }
        });
        parseAndOpenButton.setToolTipText("<html><p width='300'>This option will parse your xml file and then"
                + " give you a preview of your parsed file. You will also be able to view other"
                + " things such as the current namebase.</p></html>");
        parseAndOpenButton.setPreferredSize(new Dimension(300, 30));
        parseAndOpenButton.setEnabled(false);
        panel2.add(parseAndOpenButton, BorderLayout.EAST);
        frame.add(panel2);
      
     username_input.getDocument().addDocumentListener(new DocumentListener()
        {
        @Override
        public void changedUpdate(DocumentEvent e){
            changed();
        }
        @Override
        public void removeUpdate(DocumentEvent e){
            changed();
        }
        @Override
        public void insertUpdate(DocumentEvent e){
            changed();
        }
        public void changed(){
            if((username_input.getText().trim().equals("")
                    || (file_input.getText().equals("")))){
                parseButton.setEnabled(false);
                parseAndOpenButton.setEnabled(false);
                        
            }
            else{
                parseButton.setEnabled(true);
                parseAndOpenButton.setEnabled(true);
            }
        }
        });
     
        file_input.getDocument().addDocumentListener(new DocumentListener()
        {
        @Override
        public void changedUpdate(DocumentEvent e){
            changed();
        }
        @Override
        public void removeUpdate(DocumentEvent e){
            changed();
        }
        @Override
        public void insertUpdate(DocumentEvent e){
            changed();
        }
        public void changed(){
            if((username_input.getText().trim().equals("")
                    || (file_input.getText().equals("")))){
                parseButton.setEnabled(false);
                parseAndOpenButton.setEnabled(false);
                        
            }
            else{
                parseButton.setEnabled(true);
                parseAndOpenButton.setEnabled(true);
            }
        }
        });
     
        JPanel panel3 = new JPanel(new BorderLayout());
        
        frame.add(panel3, BorderLayout.SOUTH);
        
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
    }

    File openFile(JTextField file_input){
        File currentDirectory = new File(new File(".").getAbsolutePath());
        JFileChooser fc = new JFileChooser(currentDirectory.getAbsolutePath());
        FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter(
            "xml files(*.xml)", "xml");
        fc.setFileFilter(xmlfilter);
        int returnVal = fc.showOpenDialog(fc);
        File file = null;
        if(returnVal == JFileChooser.APPROVE_OPTION){
            file = fc.getSelectedFile();
            file_name = file.getAbsoluteFile();
        }
       /* if(!file.getName().endsWith(".xml")){
            file_input.setBackground(Color.red);
        }
        else{
            file_input.setBackground(Color.white);
        }*/
        return file_name;
    }
    
    void closeFrame(){
        frame.dispose();
    }
}