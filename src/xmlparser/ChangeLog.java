/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xmlparser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Martyn Rushton
 */
class ChangeLog{
	String date;
	String name;
	String message;
	public ChangeLog(String name){
		this.name = name;
		this.message = " This file was parsed using XMLParser, for more information visit www.github.com/martynrushton/XMLParser/README.md";
	}
        
        public void formatLog(node change){
            //makes a date node
            node dateNode = new node("date", change);
            //get date from system
            DateFormat dateFormat = new SimpleDateFormat("dd MMMM yy");
            Date getDate = new Date();
            date = dateFormat.format(getDate).trim(); 
            System.out.println(date + "At length" + date.length());
            
            for(int i=0;i<this.date.length();i++){
                dateNode.AddChild(Character.toString(date.charAt(i)));
            }
            dateNode.AddChild(new node("/date", dateNode));
                      
            //makes a name node
            node nameNode = new node("name", change);
            System.out.println(name);
            for(int i=0; i<name.length(); i++){
                nameNode.AddChild(Character.toString(name.charAt(i)));
            }
            
            //gets a value for the name key attribute 
            String[] temp = name.split(" "); // use to get initials
            StringBuilder valueAttr = new StringBuilder();  
            valueAttr.append(temp[0].charAt(0)).append(temp[1].charAt(0));
            nameNode.AddAttribute("key", valueAttr.toString());
            nameNode.AddAttribute("type", "person");
            nameNode.AddChild(new node("/name", nameNode));
            
            
            //add all to change
            change.AddChild(dateNode);
            change.AddChild(nameNode);
            System.out.println(message);
            for(int i=0; i<this.message.length(); i++){
                change.AddChild(Character.toString(message.charAt(i)));
            }
            change.AddChild(new node("/change", change));
        }
}