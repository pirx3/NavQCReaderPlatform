/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rybinski.navqcreader.actions;

//import com.sun.istack.internal.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.openide.LifecycleManager;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileChooserBuilder;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import rybinski.navqcreader.tabledata.DataBox;
import rybinski.navqcreader.tabledata.impl.DataFileTableModel;
import rybinski.navqcreader.tabledata.DataManager;
import rybinski.navqcreader.tabledata.editor.TableDataEditorTopComponent;
import rybinski.navqcreader.util.MyFileFilter;


@ActionID(
        category = "File",
        id = "rybinski.navqcreader.actions.OpenFileAction"
)
@ActionRegistration(
        iconBase = "rybinski/navqcreader/actions/049-folder-open.png",
        displayName = "#CTL_OpenFileAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/File", position = 1200)
    ,
  @ActionReference(path = "Toolbars/File", position = 200)
    ,
  @ActionReference(path = "Shortcuts", name = "D-O")
})
@Messages("CTL_OpenFileAction=Open File(s)")
public final class OpenFileAction implements ActionListener {
    
//private static final Logger logger = Logger.getLogger(OpenFileAction.class.getName());
    
@Override
    public void actionPerformed(ActionEvent e) {
        FileChooserBuilder fileChooser = new FileChooserBuilder(OpenFileAction.class);        
        fileChooser.setFilesOnly(true);
fileChooser.setFileFilter(new MyFileFilter("txt", "Text File"));   
File [] f = fileChooser.setTitle("Select File(s)").showMultiOpenDialog();
//fileChooser.setFileSelectionMode(0);
//fileChooser.setCurrentDirectory(new File(PropertiesXml.getWorkingDir()));
        if (f != null) {
            int noFiles = f.length;
            List<File> files = new ArrayList<>();
            ArrayList<String> paths = new ArrayList<>();
            for (int k = 0; k < noFiles; ++k) {
                files.add(f [k]);
                paths.add(files.get(k).getAbsolutePath());
               // paths.add(fileChooser.getSelectedFiles()[k].getPath());
                System.out.println("-----------selected File " + files.get(k));
                System.out.println("-----------selected PAths " + paths.get(k));
            }
            // this.controller.setPath(paths);
            // this.controller.setFile(files);
            
            
            DataFileTableModel tableModel = new DataFileTableModel(paths);
            DataManager dm = Lookup.getDefault().lookup(DataManager.class);
            if (dm == null){
                //logger.log(Level.SEVERE, "Cannot get DataManager Object!!!");
                System.out.println("Cannot get DataManager Object!!!");
                    LifecycleManager.getDefault().exit();
            }else{
                System.out.println("okokokokoko,");
            dm.addTableModel(tableModel);
            DataBox db = DataBox.getInstance();
            db.addData(paths);
            TopComponent tc = new TableDataEditorTopComponent ();
            tc.open();
            tc.requestActive();
            System.out.println("got db after reading file "+db.getElementAt(0).toString());
            }
            
            
            
            
            
            //this.table.setModel(tableModel2);
        }
    
    }
}
