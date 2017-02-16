/*
 * Table model
 */
package rybinski.navqcreader.tabledata.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.swing.table.AbstractTableModel;
import org.openide.util.lookup.ServiceProvider;
import rybinski.navqcreader.tabledata.AbstractDataFileTableModel;
import rybinski.navqcreader.tabledata.DataBox;
import rybinski.navqcreader.util.Maths;
//import rybinski.navqcreader.main.IController;
//import rybinski.navqcreader.util.Maths;
@ServiceProvider(service = DataFileTableModelImpl.class)
public class DataFileTableModelImpl extends AbstractDataFileTableModel {

    public List<Object> tableData;
    protected List<String> columnNames;
    protected List<String> tableColumnNames;
    protected List<String> datafiles;
    //IController controller;
    StringBuffer output;
    StringBuffer outputAverageValues;
    String fileName;
    File file;
    Map<String, ArrayList> ColumnValues;

    public DataFileTableModelImpl(ArrayList<String> paths /*,IController controller*/) {
        this.datafiles = paths;
        //this.controller = controller;
        this.tableColumnNames = new ArrayList<>();
        this.tableData = new ArrayList<>();
        this.initTable();
        this.readData();
    }

    private void initTable() {
        this.tableColumnNames.add("File");
        for (int i = 0; i < 2; ++i) {
            this.tableColumnNames.add("HDOP Min");
            this.tableColumnNames.add("HDOP Max");
            this.tableColumnNames.add("HDOP Mean");
            this.tableColumnNames.add("Sat Max");
            this.tableColumnNames.add("Sat Min");
            this.tableColumnNames.add("Diff Age Mean");
        }
        this.tableColumnNames.add("dE");
        this.tableColumnNames.add("dN");
    }

    @Override
    public void readData() {
        System.out.println("Model reading data... ");
        System.out.println("Datafiles size... "+datafiles.size());
        String delimiter = ",";
        this.output = new StringBuffer();
        for (int i = 0; i < this.datafiles.size(); ++i) {
            ArrayList<String> data = new ArrayList<>();
            ArrayList<String> Starfix_HDOP = new ArrayList<>();
            ArrayList<String> Starfix_Satellite_Count = new ArrayList<>();
            ArrayList<String> Starfix_Diff_Age = new ArrayList<>();
            ArrayList<String> F180_HDOP = new ArrayList<>();
            ArrayList<String> F180_Satellite_Count = new ArrayList<>();
            ArrayList<String> F180_Diff_Age = new ArrayList<>();
            ArrayList<String> dE = new ArrayList<>();
            ArrayList<String> dN = new ArrayList<>();
            
            ColumnValues = new HashMap<>();
            try {
                String aLine;
                this.columnNames = new ArrayList();
                FileInputStream fin = new FileInputStream(this.datafiles.get(i));
                try (BufferedReader br = new BufferedReader(new InputStreamReader(fin))) {
                    StringTokenizer st1 = new StringTokenizer(br.readLine(), delimiter);
                    while (st1.hasMoreTokens()) {
                        this.columnNames.add(st1.nextToken());
                    }
                    System.out.println("Anzahl Spalten: " + this.columnNames.size());
                    while ((aLine = br.readLine()) != null) {
                        aLine = aLine.replace(",,", ",NaN,");
                        StringTokenizer st2 = new StringTokenizer(aLine.replace(",,", ",NaN,"), delimiter);
                        int numberTokens = st2.countTokens();
                        if (numberTokens != this.columnNames.size()) {
                            continue;
                        }
                        while (st2.hasMoreTokens()) {
                            data.add(st2.nextToken());
                        }
                    }
                }

                for (int k = 0; k < data.size(); ++k) {
                    Starfix_HDOP.add(data.get(k));
                    Starfix_Satellite_Count.add(data.get(k + 1));
                    Starfix_Diff_Age.add(data.get(k + 2));
                    F180_HDOP.add(data.get(k + 3));
                    F180_Satellite_Count.add(data.get(k + 4));
                    F180_Diff_Age.add(data.get(k + 5));
                    dE.add(data.get(k + 6));
                    dN.add(data.get(k + 7));
                    k = k + this.columnNames.size() - 1;
                }
                ColumnValues.put("Starfix_HDOP",Starfix_HDOP);
                
                this.outputComputedValues(this.datafiles.get(i), Starfix_HDOP, Starfix_Satellite_Count, Starfix_Diff_Age, F180_HDOP, F180_Satellite_Count, F180_Diff_Age, dE, dN);
                data.clear();
                //continue;
            } catch (IOException ex) {
                // empty catch block
            }
        }
        /* Get an instance of the singleton DataBox class 
        to hold data read from selected files */

        DataBox dataBox = DataBox.getInstance();
        tableData.forEach(s -> {
            dataBox.addData(s);
            System.out.println(
                    "Adding data set to dataBox..." + s + "\n"
                    + "DataBox size after adding: " + dataBox.getAllData().size());
        });

    }
// Map einfuehren
    public void outputComputedValues(String datafile,
            ArrayList Starfix_HDOP, ArrayList Starfix_Satellite_Count,
            ArrayList Starfix_Diff_Age, ArrayList F180_HDOP,
            ArrayList F180_Satellite_Count, ArrayList F180_Diff_Age,
            ArrayList dE, ArrayList dN) {
        String fileSeparator = System.getProperty("file.separator");
        int k = datafile.lastIndexOf(fileSeparator);
        String inputFileName = datafile.substring(k + 1);
        int j = inputFileName.lastIndexOf(".");
        inputFileName = inputFileName.substring(0, j);

        double Starfix_HDOP_MIN = Maths.computeMin(Starfix_HDOP);
        double Starfix_HDOP_MAX = Maths.computeMax(Starfix_HDOP);
        double Starfix_HDOP_MEAN = Maths.computeAverage(Starfix_HDOP);
        int Starfix_Satellite_Count_MAX = (int) Maths.computeMax(Starfix_Satellite_Count);
        int Starfix_Satellite_Count_MIN = (int) Maths.computeMin(Starfix_Satellite_Count);
        int Starfix_Diff_Age_MAX = (int) Maths.computeAverage(Starfix_Diff_Age);
        double F180_HDOP_MIN = Maths.computeMin(F180_HDOP);
        double F180_HDOP_MAX = Maths.computeMax(F180_HDOP);
        double F180_HDOP_MEAN = Maths.computeAverage(F180_HDOP);
        int F180_Satellite_Count_MAX = (int) Maths.computeMax(F180_Satellite_Count);
        int F180_Satellite_Count_MIN = (int) Maths.computeMin(F180_Satellite_Count);
        int F180_Diff_Age_MAX = (int) Maths.computeAverage(F180_Diff_Age);
        double dE_MEAN = Maths.computeAverage(dE);
        double dN_MEAN = Maths.computeAverage(dN);
        this.tableData.add(inputFileName);
        this.tableData.add(Double.toString(Starfix_HDOP_MIN));
        this.tableData.add(Double.toString(Starfix_HDOP_MAX));
        this.tableData.add(Maths.formatDouble(Starfix_HDOP_MEAN, 0));
        this.tableData.add(Integer.toString(Starfix_Satellite_Count_MAX));
        this.tableData.add(Integer.toString(Starfix_Satellite_Count_MIN));
        this.tableData.add(Integer.toString(Starfix_Diff_Age_MAX));
        this.tableData.add(Double.toString(F180_HDOP_MIN));
        this.tableData.add(Double.toString(F180_HDOP_MAX));
        this.tableData.add(Maths.formatDouble(F180_HDOP_MEAN, 0));
        this.tableData.add(Integer.toString(F180_Satellite_Count_MAX));
        this.tableData.add(Integer.toString(F180_Satellite_Count_MIN));
        this.tableData.add(Integer.toString(F180_Diff_Age_MAX));
        this.tableData.add(Double.toString(dE_MEAN));
        this.tableData.add(Double.toString(dN_MEAN));
        System.out.println("tableDAta size: " + this.tableData.size());
    }

    @Override
    public int getRowCount() {
        DataBox dataBox = DataBox.getInstance();
        return dataBox.getAllData().size() / this.getColumnCount();
    }

    @Override
    public int getColumnCount() {
      return this.tableColumnNames.size();
    
    }

    @Override
    public String getColumnName(int columnIndex) {
        String colName = "";
        if (columnIndex <= this.getColumnCount()) {
            colName = (String) this.tableColumnNames.get(columnIndex);
        }
        return colName;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //DataBox dataBox = DataBox.getInstance();
        //return (String) dataBox.getElementAt(rowIndex * this.getColumnCount() + columnIndex);
    return tableData.get(rowIndex * this.getColumnCount() + columnIndex);
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }
    

}
