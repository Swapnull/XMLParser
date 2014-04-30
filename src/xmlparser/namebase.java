/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xmlparser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Martyn Rushton
 */

class NBase{
    int key;
    String value;
    NBase(int key, String value){
        this.key = key;
        this.value = value;
    }
}

public class namebase {

    NBase[] namebase;
    int namebaseKey = 1;
    public namebase() {
        namebase = new NBase[0];
    }

    public void readNamebase() throws FileNotFoundException, IOException {
        namebase = new NBase[0];
        BufferedReader bfr = new BufferedReader(new FileReader("namebase.txt"));
        String in;
        StringBuilder str;
        while ((in = bfr.readLine()) != null) {
            str= new StringBuilder();
            String[] temp = in.split(" ");
            int i=1;
            while(i<temp.length){ 
                str.append(temp[i]).append(" ");
                i++;
            }
            
            NBase[] newArray = new NBase[namebase.length + 1];
            System.arraycopy(namebase, 0, newArray, 0, namebase.length);
            namebase = newArray;
            namebase[namebase.length - 1] = new NBase(namebaseKey++, str.toString().trim());
        }
        bfr.close();

        /*for (int i = 0; i < namebase.length; i++) {
            System.out.println(namebase[i].value);
        }*/
    }

    public void addToNamebase(node current, String str) {
        NBase[] newArray = new NBase[namebase.length + 1];
        System.arraycopy(namebase, 0, newArray, 0, namebase.length);
        namebase = newArray;
        namebase[namebase.length-1]= new NBase(namebaseKey++, str);
    }

    public void writeNamebase() throws IOException {
        File nb = new File("namebase.txt");
        nb.delete();
        FileWriter fw = new FileWriter(nb, true);
        BufferedWriter bfw = new BufferedWriter(fw);

        //System.out.println(current.toString());
        //System.out.println("adding to namebase");
        int index = 1;
        for (int i = 0; i < namebase.length; i++) {
            fw.append(Integer.toString(namebase[i].key) + ". " + namebase[i].value + '\n');
            index++;
        }
        fw.close();

    }

    public void checkAndAddToNamebase(node current) { 
        StringBuilder str = new StringBuilder();
        boolean found = false;
        for (int i = 0; i < current.noOfChildren; i++) {
            Object child = current.children[i];
            if (child.getClass() == String.class) {
                str.append((String) child);
            }
        }
        for(int i=0; i<namebase.length;i++){
            if(namebase[i].value.equals(str.toString())){
                found = true;
            }
        }
        
        if(!found){
            addToNamebase(current, str.toString());
        }
        addNamebaseKey(current, str.toString());
    }
    
    public void addNamebaseKey(node current, String str){
        boolean found = false;
        for(int i=0; i<current.noOfAttributes;i++){
            if(current.attributes[i].key.equals("key")){
                found= true;
            }
        }
        if(!found){
            for(int i=0; i<namebase.length; i++){
                if(str.equals(namebase[i].value)){
                    current.AddAttribute("key", Integer.toString(namebase[i].key));
                    //System.out.println(Integer.toString(namebase[i].key));
                }
            }
        }
    }
}
