/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rybinski.navqcreader.tabledata;

import rybinski.navqcreader.tabledata.impl.DataFileTableModel;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.collections.MapChangeListener;

/**
 *
 * @author PC-Helmert
 */
public interface DataManager {
    public void addListener(MapChangeListener<? super Long, ? super DataFileTableModel> ml);
    
    public void removeListener(MapChangeListener<? super Long, ? super DataFileTableModel> ml);
   
    public void addListener(InvalidationListener il);
    
    public void removeListener(InvalidationListener il);
    public void addTableModel(DataFileTableModel model);

    public void updateTableModel(DataFileTableModel model);

    public void deleteTableModel(DataFileTableModel model);
           
    public List<DataFileTableModel> getAllTableModel();
    public DataFileTableModel getTableModel();
}
