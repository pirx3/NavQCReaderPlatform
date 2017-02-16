/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rybinski.navqcreader.tabledata;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC-Helmert
 */
public abstract class AbstractDataFileTableModel extends AbstractTableModel{
    //public abstract void outputComputedValues();
    public abstract void readData();
    
}
