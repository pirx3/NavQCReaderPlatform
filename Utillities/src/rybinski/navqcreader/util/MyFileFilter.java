/*
 * Decompiled with CFR 0_118.
 */
package rybinski.navqcreader.util;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class MyFileFilter
extends FileFilter {
    private String ext;
    private String description;

    public MyFileFilter(String ext, String description) {
        this.ext = ext;
        this.description = description;
    }

    @Override
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        String filename = file.getName();
        return filename.endsWith(this.ext);
    }

    @Override
    public String getDescription() {
        return this.description + " *." + this.ext;
    }
}

