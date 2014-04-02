/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xmlparser;

import java.io.*;

public class XMLParser
{
    
  public static void main(String args[])
  {
    String filePath ="C:\\Users\\Martyn Rushton\\Dropbox\\ADS2\\Froissart1.xml" ; 
    try
    {       Gui gui = new Gui();
	    FileInputStream in=new FileInputStream(filePath);
	    parse(in, gui);
            in.close();
    }
    catch (IOException e) { System.out.println("IOError: "+e); }
  }

  public static void parse(FileInputStream file, Gui gui)
  {
    
    tree parseTree = null;
    namebase[] nb = new namebase[0];
    
    try
    {
      
      node current = null;
      char val=(char) file.read();
      
      while (val!=(char) -1)
      { 

        if (val=='<')
        {

          boolean inquotes=false;
          String name;
          StringBuilder temp = new StringBuilder();
          do  // read the name of the XML node
          {
            val=(char) file.read();
            if (val=='\"')
              inquotes^=true;
            else if (inquotes || (val!='>' && val!=' '))
              temp.append(val);
          } while (val!=(char) -1 && ((val!=' ' && val!='>') || inquotes));
          
          name=temp.toString();
          
          
          if(parseTree == null){
          //sets head of tree. this is the 'root node'
              current = new node(name, null);
              parseTree = new tree(current);
          }
          else{
             node newNode = new node(name, current); //creates new node 
             current.AddChild(newNode);
             current = newNode;
          }
          String fp = "C:\\Users\\Martyn Rushton\\Dropbox\\ADS2\\namebase.txt";
          FileWriter fileWriter = new FileWriter(fp, true);
          BufferedWriter bufferFileWriter = new BufferedWriter(fileWriter);
      
          if((current.name.equals("name")) ||(current.name.equals("rs"))){
              boolean found = false;
              if(nb.length != 0){
              for(int i=0; i<nb.length; i++){
                  if(current.name.equals(nb[i].value)){//already in list
                      found = true;
                  }//end of if
              }//end of for
              }
              if(found == false){ //not already in list
                  namebase[] newArray = new namebase[nb.length + 1];
                  System.arraycopy(nb, 0, newArray, 0, nb.length);
                  nb = newArray;
                  nb[(nb.length)-1] = new namebase(nb.length, current.name);
                  fileWriter.append(current.name + '\n');
                  bufferFileWriter.close();
              }
          }//end of outer if
          if (name.endsWith("/")){
            current = current.parent;
          }
          else if (name.charAt(0)=='/'){
            current = current.parent.parent;
          }
          else
          {
           // System.out.println("Opening tag: "+temp);

            // the '>' character indicates that all key-value pairs have been read
            while (val!=(char) -1 && val!='>') // read its properties
            {
              // key="value"
              StringBuilder tmp= new StringBuilder();
              do // read in a key
              {
                val=(char) file.read();
                if (val=='\"')
                  inquotes^=true;
                else if ((val!='=' && val!=' ') || inquotes)
                  tmp.append(val);
              } while (val!=(char) -1 && ((val!='=' && val!=' ' && val!='>') || inquotes));
              
              String key = tmp.toString();
              if (key.endsWith(">"))
              {
                if (key.endsWith("/>") || key.endsWith("/ >"))
                {
                 // System.out.println("closing tag");
                    current = current.parent;
                }
              }
              else
              {
                  inquotes = false;
                  StringBuilder value = new StringBuilder();
                  do{
                      val =(char) file.read();
                      if(val == '\"'){
                          inquotes^=true;
                      }
                      else if(val != '>' && val != '/'){
                          value.append(val);
                      }
                  }while(val !=(char) -1 && ((val!='>' && val != '/' && val!=' ') || inquotes));
                  
                  current.AddAttribute(key, value.toString());
                  }
                  
                //checks to see if 
                boolean attFound = false;
                for(int i=0; i<current.noOfAttributes;i++){
                    if(current.attributes[i].key.equals("xml:id")){
                        attFound = true;
                    }
                }
                if(!attFound)
                    current.appMilestone(); //appends the milestones to include xml:id if needed
                
                
                if (val=='/')
                {
                  //System.out.println("closing tag");
                  current = current.parent;
                  val=(char) file.read();
                }
              }
            }

          //System.out.println("nodeName : <" + (newNode.name) + ">" + "   Parent : <" +(newNode.parent) + ">");
          //System.out.println(current.name);
        }
        else
        {
          //System.out.println("Normal character: "+val);
            if(current != null)
                current.AddChild(Character.toString(val)); //adds plain text
        }
        val=(char) file.read();
      }
      
      gui.field.setText(parseTree.toString());
      System.out.println(parseTree);
      for(int i=0; i<nb.length; i++){
          System.out.println(nb[i].key + " : " + nb[i].value);
      }
      
     
    }
    catch (IOException e) {System.out.println("XMLParser.parse() - "+e);}
    
    
  }
}