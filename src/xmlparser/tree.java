/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package xmlparser;

/**
 *
 * @author Martyn Rushton
 */
public class tree {
    public node head;
    public StringBuilder str;
    
    public tree(node head){
        this.head = head;
    }
   
   @Override
   public String toString(){
       str = new StringBuilder();
       str.append(head.toString());
       toString(head);
       return str.toString();
   }
   
   public void toString(node element){
        for (int i = 0; i < element.noOfChildren; i++) {
            Object child = element.children[i];
            if (child.getClass() == node.class){
                str.append(child.toString());
                toString((node) child);
            } else if(child.getClass() == String.class){
                str.append((String) child);
            }
        }
   } 
   
}
