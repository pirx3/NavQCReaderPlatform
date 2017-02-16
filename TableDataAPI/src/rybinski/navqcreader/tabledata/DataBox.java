/*
 * Single ton class to store an array of data
 */
package rybinski.navqcreader.tabledata;

import java.util.ArrayList;
import java.util.List;
//import java.util.Vector;

public class DataBox {
    private static DataBox instance;
    //private Vector data = new Vector();
private final List<Object> data = new ArrayList<Object>();
    private DataBox() {
    }

    public static DataBox getInstance() {
        if (instance == null) {
            instance = new DataBox();
        }
        return instance;
    }

    public List getAllData() {
        //return (Vector)this.data.clone();
        return this.data;
    }

    public void addData(Object value) {
        //this.data.addElement(value);
        this.data.add(value);
    }

    public void deleteAllData() {
        //this.data.removeAllElements();
        this.data.clear();
    }

    public Object getElementAt(int index) {
        //return this.data.elementAt(index);
        return this.data.get(index);
    }
}

