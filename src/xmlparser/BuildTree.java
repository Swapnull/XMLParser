/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlparser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Martyn Rushton
 */
public class BuildTree {

    public static tree parseTree = null;
    static String username;
    static boolean addedChange = false;

    public BuildTree(File filePath, String username) {
        this.username = username;
        try {
            FileInputStream in = new FileInputStream(filePath);
            parse(in);
            in.close();
        } catch (IOException e) {
            System.out.println("IOError: " + e);
        }
    }

    public static void parse(FileInputStream file) {

        //tree parseTree = null;
        namebase[] nb = new namebase[0];

        try {

            node current = null;
            char val = (char) file.read();

            while (val != (char) -1) {

                if (val == '<') {

                    boolean inquotes = false;
                    String name;
                    StringBuilder temp = new StringBuilder();
                    do // read the name of the XML node
                    {
                        val = (char) file.read();
                        if (val == '\"') {
                            inquotes ^= true;
                        } else if (inquotes || (val != '>' && val != ' ')) {
                            temp.append(val);
                        }
                    } while (val != (char) -1 && ((val != ' ' && val != '>') || inquotes));

                    name = temp.toString();

                    if (parseTree == null) {
                        //sets head of tree. this is the 'root node'
                        current = new node(name, null);
                        parseTree = new tree(current);
                    } else {
                        node newNode = new node(name, current); //creates new node 
                        current.AddChild(newNode);
                        current = newNode;
                    }

                    //change log was here

                    if (name.endsWith("/")) {
                        current = current.parent;
                    } else if (name.charAt(0) == '/') {
                        current = current.parent.parent;
                    } else {
           // System.out.println("Opening tag: "+temp);

                        // the '>' character indicates that all key-value pairs have been read
                        while (val != (char) -1 && val != '>') // read its properties
                        {
                            // key="value"
                            StringBuilder tmp = new StringBuilder();
                            do // read in a key
                            {
                                val = (char) file.read();
                                if (val == '\"') {
                                    inquotes ^= true;
                                } else if ((val != '=' && val != ' ') || inquotes) {
                                    tmp.append(val);
                                }
                            } while (val != (char) -1 && ((val != '=' && val != ' ' && val != '>') || inquotes));

                            String key = tmp.toString();
                            if (key.endsWith(">")) {
                                if (key.endsWith("/>") || key.endsWith("/ >")) {
                                    // System.out.println("closing tag");
                                    current = current.parent;
                                }
                            } else {
                                inquotes = false;
                                StringBuilder value = new StringBuilder();
                                do {
                                    val = (char) file.read();
                                    if (val == '\"') {
                                        inquotes ^= true;
                                    } else if (val != '>' && val != '/') {
                                        value.append(val);
                                    }
                                } while (val != (char) -1 && ((val != '>' && val != '/' && val != ' ') || inquotes));

                                current.AddAttribute(key, value.toString());
                            }

                            if (val == '/') {
                                //System.out.println("closing tag");
                                current = current.parent;
                                val = (char) file.read();
                            }
                        }
                    }

                    //System.out.println("nodeName : <" + (newNode.name) + ">" + "   Parent : <" +(newNode.parent) + ">");
                    //System.out.println(current.name);
                } else {
                    //System.out.println("Normal character: "+val);
                    if (current != null) {
                        current.AddChild(Character.toString(val)); //adds plain text
                    }
                }
                val = (char) file.read();
            }

            //gui.field.setText(parseTree.toString());
            // System.out.println("username: " + gui.getUsername())
        } catch (IOException e) {
            System.out.println("XMLParser.parse() - " + e);
        }

    }

    public void writeToFile() throws IOException {
        String res = parseTree.toString();
        //System.out.print(res);sd

        File filepth = new File("new.xml");
        filepth.delete();
        FileWriter fileWriter = new FileWriter(filepth);
        BufferedWriter bw = new BufferedWriter(fileWriter);
        bw.write(res);
        bw.close();
    }
}
