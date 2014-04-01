package xmlparser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Martyn Rushton
 */
public class vector {
  private static final int MAXDISPLAY=20;
  
  private int growby;
  private int noofitems;
  private node[] data;
  
  private static final int MINGROW=10;
  
  public vector()
  {
    Init(10);
  }

  
  private void Init(int initsize)
  {
    growby=initsize/10;
    
    if (growby<MINGROW)
      growby=MINGROW;
    
    noofitems=0;
    data=new node[initsize];
  }
  
  public int GetNoOfItems()
  {
    return noofitems;
  }
  
  public node GetItem(int index)
  {
    return data[index];
  }
  
  public node GetNode(String name){
      int i = 0;   
      while((i < noofitems)){
          if(data[i].name == name){
              return data[i];
          }
          else{
              i++;
          }
      }
      return data[noofitems+1];
  }
  
  
  public void AddItem(node item)
  {
    if (noofitems==data.length)
      GrowDataStore();
    data[noofitems++]=item;
  }
  
  public boolean InsertItem(int index, node item)
  {
    if (index>=0 && index<=noofitems)
    {
      if (noofitems==data.length)
        GrowDataStore();
      for (int i=noofitems; i>index; i--)
        data[i]=data[i-1];
      data[index]=item;
      ++noofitems;
      return true;
    }
    else
      return false;
  }
  
  public boolean DeleteItem(int index)
  {
    if (index>=0 && index<noofitems)
    {
      --noofitems;
      for (int i=index; i<noofitems; i++)
        data[i]=data[i+1];
      return true;
    }
    else
      return false;
  }

  
  private void GrowDataStore()
  {
    node[] tmp=new node[noofitems+growby];
    System.arraycopy(data, 0, tmp, 0, noofitems);
    data=tmp;
  }
  
  public String toString()
  {
    StringBuilder str=new StringBuilder();
    str.append('[');
    if (noofitems>0)
      str.append(data[0]);

    int max=(noofitems<MAXDISPLAY?noofitems:MAXDISPLAY);
    for (int i=1; i<max; i++)
    {
      str.append(", ");
      str.append(data[i]);
    }
    if (noofitems>MAXDISPLAY)
    {
      str.append(", ...(");
      str.append(noofitems-MAXDISPLAY);
      str.append(')');
    }
    str.append(']');
    return str.toString();
  }
}
