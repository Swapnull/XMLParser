/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xmlparser;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static xmlparser.Gui.frame;
/**
 *
 * @author Martyn Rushton
 */
public class Form {
    
    JFrame frame = new JFrame("XML Parser");
    int lab_width = 100;
    public Form(){
        JTextField username_input = new JTextField("", 15);
        JLabel username_label = new JLabel("Enter your name");       
        JPanel username_panel = new JPanel(new BorderLayout());
        username_panel.add(username_label,BorderLayout.WEST);
        username_label.setPreferredSize(new Dimension(lab_width, 15));
        username_panel.add(username_input,BorderLayout.CENTER);
        frame.add(username_panel);
       
        JTextField file_input = new JTextField("", 15);
        JLabel file_label = new JLabel("File to Parse");
        JPanel file_panel = new JPanel(new BorderLayout());
        file_panel.add(file_label, BorderLayout.WEST);
        file_label.setPreferredSize(new Dimension(lab_width, 15));
        file_panel.add(file_input, BorderLayout.CENTER);
        frame.add(file_panel, BorderLayout.SOUTH);
        
        frame.setVisible(true);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

}
