package com.vistalogsparsertool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/** * Sample Java program to read and write Excel file in Java using Apache POI * */
public class XLSXReaderWriter {
    public static void main(String[] args) {
        try {

            File excel = new File("C://temp/Employee.xlsx");
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            XSSFSheet sheet = book.getSheetAt(1);

            Iterator<Row> itr = sheet.iterator();
            // Iterating over Excel file in Java
            while (itr.hasNext()) {
                Row row = itr.next();
                // Iterating over each column of Excel file
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        System.out.print(cell.getStringCellValue() + "\t");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t");
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.print(cell.getBooleanCellValue() + "\t");
                        break;
                    default:
                    }
                }
                System.out.println("");
            }

            // writing data into XLSX file
            Map<String, Object[]> newData = new HashMap<String, Object[]>();
            newData.put("7", new Object[] { 7d, "Sonya", "75K", "SALES", "Rupert" });
            newData.put("8", new Object[] { 8d, "Kris", "85K", "SALES", "Rupert" });
            newData.put("9", new Object[] { 9d, "Dave", "90K", "SALES", "Rupert" });

            Set<String> newRows = newData.keySet();
            int rownum = sheet.getLastRowNum();
            for (String key : newRows) {
                Row row = sheet.createRow(rownum++);
                Object[] objArr = newData.get(key);
                int cellnum = 0;
                for (Object obj : objArr) {
                    Cell cell = row.createCell(cellnum++);
                    if (obj instanceof String) {
                        cell.setCellValue((String) obj);
                    } else if (obj instanceof Boolean) {
                        cell.setCellValue((Boolean) obj);
                    } else if (obj instanceof Date) {
                        cell.setCellValue((Date) obj);
                    } else if (obj instanceof Double) {
                        cell.setCellValue((Double) obj);
                    }
                }
            }
            // open an OutputStream to save written data into Excel file
            FileOutputStream os = new FileOutputStream(excel);
            book.write(os);
            System.out.println("Writing on Excel file Finished ...");
            // Close workbook, OutputStream and Excel file to prevent leak
            os.close();

            fis.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    public static void writeIntoExcel(List<BeanClass> listBook, String fileName, String sheetName) {

        try {

            File excel = new File(fileName);
            FileInputStream fis = new FileInputStream(excel);
            XSSFWorkbook book = new XSSFWorkbook(fis);
            XSSFSheet sheet = book.getSheet("Total");

            int rownum = sheet.getLastRowNum();
            for (BeanClass aBook : listBook) {
                Row row = sheet.createRow(++rownum);
                interfaceMetrics(aBook, row);
            }
            // open an OutputStream to save written data into Excel file
            FileOutputStream os = new FileOutputStream(excel);
            book.write(os);
            System.out.println("Writing on Excel file Finished ...");
            // Close workbook, OutputStream and Excel file to prevent leak
            os.close();

            fis.close();
        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        }

    }

    private static void interfaceMetrics(BeanClass breanClass, Row row) {
        // Cell cell = row.createCell(0);
        // cell.setCellValue(breanClass.getNode());

        Cell cell = row.createCell(0);
        cell.setCellValue(breanClass.getDate());

        cell = row.createCell(1);
        cell.setCellValue("");

        cell = row.createCell(2);
        cell.setCellValue(breanClass.getHr());

        cell = row.createCell(3);
        cell.setCellValue(breanClass.getMinute());
        cell = row.createCell(4);
        cell.setCellValue(breanClass.getSec());
        cell = row.createCell(5);
        cell.setCellValue(breanClass.getMillisec());
        cell = row.createCell(6);
        String s = breanClass.getUserId();
        // if (s != null) {
        //
        // cell.setCellValue(s.substring(1, s.length() - 1));
        // } else {
        cell.setCellValue(s);
        // }
        cell = row.createCell(7);
        cell.setCellValue(breanClass.getInterceId());
        cell = row.createCell(8);
        cell.setCellValue(breanClass.getBrand());
        cell = row.createCell(9);
        cell.setCellValue(breanClass.getMarket());
        cell = row.createCell(10);
        String s1 = breanClass.getResTime();
        // if (s1 != null) {
        //
        // cell.setCellValue(s1.substring(0, s1.length() - 1));
        // } else {
        cell.setCellValue(Double.valueOf(s1));
        // }
        cell = row.createCell(11);
        cell.setCellValue("1");
        cell = row.createCell(12);
        cell.setCellFormula("IF($K2<30,\"1. 0 < 30 Sec\",IF($K2<60,\"2. 30 < 60 Sec\",IF($K2<90,\"3. 60 < 90 Sec\",\"4. 90 Sec < \")))");
        cell = row.createCell(13);
        cell.setCellFormula("IF($J2=\"CH\",\"China Market\",\"Other Markets\")");

        cell = row.createCell(14);
        cell.setCellValue(breanClass.getBodId());

        cell = row.createCell(15);
        cell.setCellFormula("IF($K2<90,100,0)");
        cell = row.createCell(16);
        cell.setCellFormula("IF($K2>90,100,0)");
        cell = row.createCell(17);
        cell.setCellFormula("IF($K2>6,100,0)");
        cell = row.createCell(18);
        cell.setCellFormula("VLOOKUP($J2,Abbrevations!$A$1:$B$19,2,1)");
        cell = row.createCell(19);
        cell.setCellFormula("IF($K2>89.99,1,0)");
        cell = row.createCell(20);
        cell.setCellFormula("VLOOKUP($H2,Abbrevations!$A$24:$B$27,2,1)");
        cell = row.createCell(21);
        cell.setCellFormula("CONCATENATE(IF(SUM($C2-6)>0,SUM($C2-6),SUM($C2-6)+24),\"UST\")");

        cell = row.createCell(22);
        cell.setCellValue(breanClass.getNode());

    }

}
