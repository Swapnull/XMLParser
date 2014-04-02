package xmlparser;

class keyValue {

    public String key;
    public String value;

    keyValue(String key, String value) {
        this.key = key.trim();
        this.value = value.trim();
    }

    @Override
    public String toString() {
        return key + '=' + '\"' + value + '\"';
    }
}

/**
 *
 * @author Martyn Rushton
 */
public class node {

    public String name;
    public node parent;
    public int noOfChildren;
    public Object[] children;
    public int noOfAttributes;
    public keyValue[] attributes;

    public node(String name, node parent) {
        this.name = name;
        this.parent = parent;
        this.children = new Object[1];
        this.attributes = new keyValue[1];
        this.noOfChildren = 0;
        this.noOfAttributes = 0;
    }

    public void AddAttribute(String key, String value) {
        if (noOfAttributes >= attributes.length) {
            keyValue[] newArray = new keyValue[attributes.length + 1];
            System.arraycopy(attributes, 0, newArray, 0, attributes.length);
            attributes = newArray;
        }
        attributes[noOfAttributes] = new keyValue(key, value);
        noOfAttributes++;
    }

    public void AddChild(Object child) {
        if (noOfChildren >= children.length) {
            Object[] newArray = new Object[children.length + 1];
            System.arraycopy(children, 0, newArray, 0, children.length);
            children = newArray;
        }
        children[noOfChildren] = child;
        noOfChildren++;
    }// end of AddChild

    public void appMilestone() {
        //edits the attributes to include xml:id (XML Operations task 1)
    // Need to check weather
        String n = null;
        String ed = null;
        String xmlid;
        for (int i = 0; i < noOfAttributes; i++) {
            if (name.equals("milestone")) {
                if (attributes[i].key.equals("n")) {
                    n = attributes[i].value;
                }//end of if
                if (attributes[i].key.equals("ed")) {
                    ed = attributes[i].value;
                }//end of if
                if ((n != null) && (ed != null)) {
                    xmlid = "BookI-Translation_" + ed + "_" + n;
                    AddAttribute("xml:id", xmlid);
                    break;
                }// end of if
            }//end of milestone if
        } // end of for
    }//end of appMilestone
    
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("<").append(name);
        for (int i = 0; i < noOfAttributes; i++) {
            str.append(" ").append(attributes[i]);
        }
        return str.append(">").toString();
    }
}
