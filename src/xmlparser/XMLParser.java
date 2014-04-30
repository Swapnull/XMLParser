/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlparser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XMLParser {

    static BuildTree buildTree;
    static tree parseTree;
    static String[] namebase;
    static namebase nambas = new namebase();
    static boolean insideText = false;
    static File filePath;
    static Gui gui = null;
    static Form form = null;
    static boolean addedChange =false;

    public static void main(String args[]) throws IOException {

        form = new Form();

//the below is gui stuff
        //just parse
        form.parseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setup();
                } catch (IOException ex) {
                    Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        //parse and view
        form.parseAndOpenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui = new Gui();
                try {

                    setup();
                } catch (IOException ex) {
                    Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public static void setup() throws IOException {
        filePath = form.file_name;
        String username = form.username_input.getText();
        form.closeFrame();
        System.out.println(filePath);
        System.out.println(username);
        buildTree = new BuildTree(filePath, username); // this stores the file in memory
        parseTree = buildTree.parseTree;
        node current = parseTree.head;

        nambas.readNamebase();
        walkTree(current);
        nambas.writeNamebase();
        buildTree.writeToFile();

        System.out.println(parseTree);

        if (gui != null) {
            gui.field.setText(parseTree.toString());
        }

    }

    public static void walkTree(node current) throws IOException {
        boolean inNamebase = false;
        //check to make sure between <text> and </text>
        if (current.name.equals("text")) {
            insideText = true;
        } else if (current.name.equals("/text")) {
            insideText = false;
        }

        if ((current.name.equals("change") && (!addedChange))) {
            ChangeLog log = new ChangeLog(form.username_input.getText());
            node change = new node("change", current.parent);
            log.formatLog(change);
            current.parent.AddChildToStart(change);
            addedChange = true;
        }

        if (insideText) {
            switch (current.name) {
                case "milestone":
                    addXMLID(current);
                    break;
                case "name":
                case "rs":
                    nambas.checkAndAddToNamebase(current);
                    break;
            }
        }
        for (int i = 0; i < current.noOfChildren; i++) {
            Object child = current.children[i];
            if (child.getClass() == node.class) {
                walkTree((node) child);
            }
        }

    }

    public static void addXMLID(node current) {
        boolean found = false;
        String xmlid, n = null, ed = null;
        int i = 0;
        while (!found && (i < current.noOfAttributes)) {
            switch (current.attributes[i].key) {
                case "xml:id":
                    found = true;
                    //System.out.println("found xml:id");
                    break;
                case "n":
                    n = current.attributes[i].value;
                    break;
                case "ed":
                    ed = current.attributes[i].value;
                    break;
            }
            i++;
        }
        if (!found && n != null && ed != null) {
            xmlid = "BookI-Translation_" + ed + "_" + n;
            current.AddAttribute("xml:id", xmlid);
        }
    }

}
