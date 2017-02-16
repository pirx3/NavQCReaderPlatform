/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rybinski.navqcreader.tabledata.impl;

import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import org.openide.util.lookup.ServiceProvider;
import rybinski.navqcreader.tabledata.*;

/**
 * Implementation
 * @author PC-Helmert
 */
@ServiceProvider (service = DataManager.class)
public class DataManagerImpl implements DataManager{
    
private final ObservableMap<Long, DataFileTableModelImpl> observableMap = FXCollections.observableHashMap();
long t=0;
DataFileTableModelImpl model = null;
    @Override
    public void addListener(MapChangeListener<? super Long, ? super DataFileTableModelImpl> ml) {
        observableMap.addListener(ml);
    }

    @Override
    public void removeListener(MapChangeListener<? super Long, ? super DataFileTableModelImpl> ml) {
        observableMap.removeListener(ml);
    }

    @Override
    public void addListener(InvalidationListener il) {
        observableMap.addListener(il);
    }

    @Override
    public void removeListener(InvalidationListener il) {
        observableMap.removeListener(il);
    }

    @Override
    public void addTableModel(DataFileTableModelImpl model) {
        //Person person = new Person(p);
        this.model = model;
        observableMap.put(t, model);
        System.out.println("DataManager Object was added to my observable Map!!!");
        
    }

    @Override
    public void updateTableModel(DataFileTableModelImpl model) {
        
    }

    @Override
    public void deleteTableModel(DataFileTableModelImpl model) {
        
    }

    @Override
    public List<DataFileTableModelImpl> getAllTableModel() {
        this.observableMap.get(t);
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public DataFileTableModelImpl getTableModel() {
   
//this.observableMap.get(t);
        System.out.println("DataManager Object was supplied from my observable Map!!!");
        return this.model;     
        }
}
