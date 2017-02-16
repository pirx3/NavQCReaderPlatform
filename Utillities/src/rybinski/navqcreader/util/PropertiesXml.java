/*
 * Decompiled with CFR 0_118.
 */
package rybinski.navqcreader.util;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class PropertiesXml implements ConstantsInterface {
    static String working_dir = null;
    static String dest_dir = null;
    static String defaultSystem = null;

    public static void readProperties() {
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(FILE_PROPERTIES);
        }
        catch (FileNotFoundException ex) {
            fs = null;
        }
        if (fs != null) {
            XMLDecoder xmlread = new XMLDecoder(fs);
            try {
                working_dir = (String)xmlread.readObject();
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                // empty catch block
            }
            try {
                dest_dir = (String)xmlread.readObject();
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                // empty catch block
            }
            try {
                defaultSystem = (String)xmlread.readObject();
            }
            catch (ArrayIndexOutOfBoundsException ex) {
                // empty catch block
            }
        }
    }

    public static String getWorkingDir() {
        PropertiesXml.readProperties();
        System.out.println(working_dir + "---" + USERDIR);
        if (working_dir != null) {
            return working_dir;
        }
        return USERDIR;
    }

    public static String getDestDir() {
        PropertiesXml.readProperties();
        if (dest_dir != null) {
            return dest_dir;
        }
        return USERDIR;
    }
}

