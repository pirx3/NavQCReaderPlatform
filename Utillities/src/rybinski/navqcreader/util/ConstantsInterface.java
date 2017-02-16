/*
 * Decompiled with CFR 0_115.
 */
package rybinski.navqcreader.util;

public interface ConstantsInterface {
    public static final String SPACE = "\\s+";
    public static final String TAB = "\\t+";
    public static final String COMMA = ",";
    public static final String HOMEDIR = System.getProperty("user.home").toString();
    public static final String USERDIR = System.getProperty("user.dir").toString();
    public static final String PATH = ".";
    public static final String FILESEP = System.getProperty("file.separator").toString();
    public static final String FILE_PROPERTIES = HOMEDIR + FILESEP + ".navqcreader.properties";
    public static final String VERSION = "NavQC Reader 13.06 \n paul.rybinski@freenet.de \n------\n";
}

