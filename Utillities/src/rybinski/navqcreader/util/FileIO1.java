/*
 * Decompiled with CFR 0_118.
 */
package rybinski.navqcreader.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileIO1 {
    public static boolean success;

    public static String fileToString(String fileName) {
        String s = "";
        try {
            int chars_read;
            char[] c;
            File file = new File(fileName);
            int size = (int)file.length();
            FileReader in = new FileReader(file);
            Throwable throwable = null;
            try {
                c = new char[size];
                chars_read = 0;
                while (in.ready()) {
                    chars_read += in.read(c, chars_read, size - chars_read);
                }
            }
            catch (Throwable x2) {
                throwable = x2;
                throw x2;
            }
            finally {
                if (in != null) {
                    if (throwable != null) {
                        try {
                            in.close();
                        }
                        catch (Throwable x2) {
                            throwable.addSuppressed(x2);
                        }
                    } else {
                        in.close();
                    }
                }
            }
            s = new String(c, 0, chars_read);
            success = true;
        }
        catch (IOException e) {
            success = false;
        }
        return s;
    }

    public static String fileToString(File file) {
        String s = "";
        try {
            int chars_read;
            char[] c;
            int size = (int)file.length();
            FileReader in = new FileReader(file);
            Throwable throwable = null;
            try {
                c = new char[size];
                chars_read = 0;
                while (in.ready()) {
                    chars_read += in.read(c, chars_read, size - chars_read);
                }
            }
            catch (Throwable x2) {
                throwable = x2;
                throw x2;
            }
            finally {
                if (in != null) {
                    if (throwable != null) {
                        try {
                            in.close();
                        }
                        catch (Throwable x2) {
                            throwable.addSuppressed(x2);
                        }
                    } else {
                        in.close();
                    }
                }
            }
            s = new String(c, 0, chars_read);
            success = true;
        }
        catch (IOException e) {
            success = false;
        }
        return s;
    }

    public static boolean stringToFile(String fileName, String text) {
        boolean result;
        try {
            FileWriter out = new FileWriter(fileName);
            Throwable throwable = null;
            try {
                out.write(text);
            }
            catch (Throwable x2) {
                throwable = x2;
                throw x2;
            }
            finally {
                if (out != null) {
                    if (throwable != null) {
                        try {
                            out.close();
                        }
                        catch (Throwable x2) {
                            throwable.addSuppressed(x2);
                        }
                    } else {
                        out.close();
                    }
                }
            }
            result = true;
        }
        catch (IOException e) {
            System.out.println("Problem beim Speichern von " + fileName);
            result = false;
        }
        return result;
    }

    public static boolean writeTextFile(String data, String fileName, String charSet) {
        boolean result = true;
        try {
            OutputStreamWriter writer = charSet != null ? new OutputStreamWriter((OutputStream)new FileOutputStream(fileName), charSet) : new OutputStreamWriter(new FileOutputStream(fileName));
            BufferedWriter out = new BufferedWriter(writer);
            Throwable throwable = null;
            try {
                out.write(data, 0, data.length());
            }
            catch (Throwable x2) {
                throwable = x2;
                throw x2;
            }
            finally {
                if (out != null) {
                    if (throwable != null) {
                        try {
                            out.close();
                        }
                        catch (Throwable x2) {
                            throwable.addSuppressed(x2);
                        }
                    } else {
                        out.close();
                    }
                }
            }
        }
        catch (Exception e) {
            result = false;
        }
        return result;
    }

    public static boolean appendToTextFile(String data, String fileName, boolean append) {
        boolean result = true;
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException ex) {
                Logger.getLogger(FileIO1.class.getName()).log(Level.SEVERE, null, ex);
                result = false;
                System.out.println("Problem beim Speichern von " + fileName);
            }
        }
        try {
            PrintWriter pWriter = new PrintWriter(new FileWriter(fileName, append), true);
            pWriter.println(data);
        }
        catch (IOException ex) {
            Logger.getLogger(FileIO1.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Problem beim Speichern von " + fileName);
            result = false;
        }
        return result;
    }

    public static boolean copyFile(String sourceFile, String targetFile) {
        boolean result;
        try {
            FileInputStream inputFile = new FileInputStream(sourceFile);
            FileChannel input = inputFile.getChannel();
            FileOutputStream outputFile = new FileOutputStream(targetFile);
            FileChannel output = outputFile.getChannel();
            long num = input.size();
            input.transferTo(0, num, output);
            input.close();
            output.close();
            result = true;
        }
        catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}

