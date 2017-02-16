/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rybinski.navqcreader.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import javax.swing.JFileChooser;
import org.openide.*;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.util.*;
import org.openide.util.NbBundle.Messages;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;
import rybinski.navqcreader.tabledata.DataBox;
import rybinski.navqcreader.tabledata.impl.DataFileTableModel;
import rybinski.navqcreader.tabledata.editor.TableDataEditorTopComponent;

@ActionID(
        category = "File",
        id = "rybinski.navqcreader.actions.FileOpenAction"
)
@ActionRegistration(
        iconBase = "rybinski/navqcreader/actions/Open16.gif",
        displayName = "#CTL_FileOpenAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1300)
    ,
  @ActionReference(path = "Toolbars/File", position = 300)
    ,
  @ActionReference(path = "Shortcuts", name = "D-O")
})
@Messages("CTL_FileOpenAction=Open File(s)")
public final class FileOpenAction_ implements ActionListener {
private final InstanceContent instanceContent = new InstanceContent(); 
 private Lookup.Result<TableDataEditorTopComponent> lookupResult = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        FileChooserBuilder fileChooser = new FileChooserBuilder(FileOpenAction_.class);
        //fileChooser.setFileFilter(new MyFileFilter("txt", "Text File"));
        //fileChooser.setFileSelectionMode(0);
        File [] f = fileChooser.setTitle("Select File(s)").showMultiOpenDialog();
        fileChooser.setFilesOnly(true);
        //fileChooser.setCurrentDirectory(new File(PropertiesXml.getWorkingDir()));
        if (f != null) {
            int noFiles = f.length;
            List<File> files = new ArrayList<File>();
            ArrayList<String> paths = new ArrayList<String>();
            for (int k = 0; k < noFiles; ++k) {
                files.add(f [k]);
               // paths.add(fileChooser.getSelectedFiles()[k].getPath());
                System.out.println("-----------selected File " + files.get(k));
            }
            // this.controller.setPath(paths);
            // this.controller.setFile(files);
            
            /*To place the current tableModel selection in the TopComponents Lookup!*/
          
           // lookupResult = Utilities.actionsGlobalContext().lookupResult(TableDataEditorTopComponent.class);
            DataFileTableModel tableModel = new DataFileTableModel(paths);
            DataBox db = DataBox.getInstance();
            db.addData(paths);
            TopComponent tc = new TableDataEditorTopComponent ();
            tc.open();
            tc.requestActive();
            
            System.out.println("got db after reading file "+db.getElementAt(0).toString());
            
            //this.table.setModel(tableModel2);
        }
    }
        
    }

