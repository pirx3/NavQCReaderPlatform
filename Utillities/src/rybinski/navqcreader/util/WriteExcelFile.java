/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  org.apache.poi.hssf.usermodel.HSSFCell
 *  org.apache.poi.hssf.usermodel.HSSFCellStyle
 *  org.apache.poi.hssf.usermodel.HSSFFont
 *  org.apache.poi.hssf.usermodel.HSSFRow
 *  org.apache.poi.hssf.usermodel.HSSFSheet
 *  org.apache.poi.hssf.usermodel.HSSFWorkbook
 *  org.apache.poi.ss.usermodel.IndexedColors
 */
package rybinski.navqcreader.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JTable;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;

public class WriteExcelFile {
    public static boolean writeExcelFile(JTable table, File file, String sheetName) {
        String fileName = file.getAbsolutePath();
        boolean result = false;
        try {
            int i;
            HSSFCell cell;
            int colNum = table.getColumnCount();
            int rowNum = table.getRowCount();
            System.out.println("\n-----ROWNUM: " + rowNum);
            HSSFWorkbook workBook = new HSSFWorkbook();
            HSSFSheet sheet = workBook.createSheet(sheetName);
            HSSFCellStyle style = workBook.createCellStyle();
            HSSFFont font = workBook.createFont();
            //font.setBoldweight(700);
            style.setFont(font);
            //style.setBorderBottom(1);
            style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            //style.setBorderLeft(1);
            style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            //style.setBorderRight(1);
            style.setRightBorderColor(IndexedColors.BLACK.getIndex());
            //style.setBorderTop(1);
            style.setTopBorderColor(IndexedColors.BLACK.getIndex());
            //style.setAlignment(1);
            //style.setAlignment(2);
            HSSFRow row = sheet.createRow(0);
            short[] maxWidth = new short[colNum];
            for (i = 0; i < colNum; ++i) {
                cell = row.createCell((short)i);
                cell.setCellType(1);
                String name = table.getColumnName(i);
                if (name.length() > maxWidth[i]) {
                    maxWidth[i] = (short)name.length();
                }
                cell.setCellValue(table.getColumnName(i));
                cell.setCellStyle(style);
                sheet.autoSizeColumn(i);
            }
            //font.setBoldweight(400);
            style.setFont(font);
            for (i = 0; i < rowNum; ++i) {
                row = sheet.createRow((short)i + 1);
                for (int j = 0; j < colNum; ++j) {
                    cell = row.createCell((short)j);
                    cell.setCellType(0);
                    String value = (String)table.getValueAt(i, j);
                    if (value.length() > maxWidth[j]) {
                        maxWidth[j] = (short)value.length();
                    }
                    cell.setCellValue(value);
                    cell.setCellStyle(style);
                }
            }
            for (i = 0; i < colNum; i = (int)((short)(i + 1))) {
                sheet.setColumnWidth((short)i, (short)(maxWidth[i] * 256));
            }
            FileOutputStream out = new FileOutputStream(file);
            workBook.write((OutputStream)out);
            out.close();
            result = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (result) {
            System.out.println("Written Excel file " + fileName + " " + result);
        }
        return result;
    }
}

