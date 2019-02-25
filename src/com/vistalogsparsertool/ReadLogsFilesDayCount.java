package com.vistalogsparsertool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class ReadLogsFilesDayCount {

    static String[] logPathArray = new String[11];
    private static BufferedReader bufferedReader;
    static List<BeanClass> servletMetricsList = new ArrayList<BeanClass>();
    static List<BeanClass> interfaceMetricsList = new ArrayList<BeanClass>();
    static List<BeanClass> vistaErrorList = new ArrayList<BeanClass>();
    static List<BeanClass> vistaRestServiceList = new ArrayList<BeanClass>();
    static List<BeanClass> vistaWebServiceList = new ArrayList<BeanClass>();
    static Map<String, List<ArrayList<String>>> servletMetricsMap = new HashMap<String, List<ArrayList<String>>>();

    public static void main(String[] args) throws IOException, InvalidFormatException {

        // getLogsZonePath();
        File servletMetricsEnhancedFile = new File(ReadProperties.readProperties().getProperty("smeExportFileName"));

        File servletMetricsFile = new File(ReadProperties.readProperties().getProperty("smExportFileName"));
        File interfaceMetricsFile = new File(ReadProperties.readProperties().getProperty("imExportFileName"));
        File vistaErrorFile = new File(ReadProperties.readProperties().getProperty("veExportFileName"));
        File vistaRestServiceFile = new File(ReadProperties.readProperties().getProperty("vrsExportFileName"));
        File vistaWebServiceFile = new File(ReadProperties.readProperties().getProperty("vwsExportFileName"));

        // if file doesnt exists, then create it
        if (!servletMetricsEnhancedFile.exists()) {
            servletMetricsEnhancedFile.createNewFile();
        }

        // if file doesnt exists, then create it
        if (!servletMetricsFile.exists()) {
            servletMetricsFile.createNewFile();
        }
        // if file doesnt exists, then create it
        if (!interfaceMetricsFile.exists()) {
            interfaceMetricsFile.createNewFile();
        }
        // if file doesnt exists, then create it
        if (!vistaErrorFile.exists()) {
            vistaErrorFile.createNewFile();
        }
        // if file doesnt exists, then create it
        if (!vistaRestServiceFile.exists()) {
            vistaRestServiceFile.createNewFile();
        }
        // if file doesnt exists, then create it
        if (!vistaWebServiceFile.exists()) {
            vistaWebServiceFile.createNewFile();
        }

        FileWriter serlvetMetricFileWriter = new FileWriter(servletMetricsFile.getAbsoluteFile());
        BufferedWriter servletMetricBW = new BufferedWriter(serlvetMetricFileWriter);

        FileWriter serlvetMetricEFileWriter = new FileWriter(servletMetricsEnhancedFile.getAbsoluteFile());
        BufferedWriter servletMetricEBW = new BufferedWriter(serlvetMetricEFileWriter);

        FileWriter interfaceMetricsFileWriter = new FileWriter(interfaceMetricsFile.getAbsoluteFile());
        BufferedWriter interfaceMetricBW = new BufferedWriter(interfaceMetricsFileWriter);

        FileWriter vistaErrorFileWriter = new FileWriter(vistaErrorFile.getAbsoluteFile());
        BufferedWriter vistaErrorFileBW = new BufferedWriter(vistaErrorFileWriter);

        FileWriter vistaRestServiceFileWriter = new FileWriter(vistaRestServiceFile.getAbsoluteFile());
        BufferedWriter vistaRestServiceFileBW = new BufferedWriter(vistaRestServiceFileWriter);

        FileWriter vistaWebServiceFileWriter = new FileWriter(vistaWebServiceFile.getAbsoluteFile());
        BufferedWriter vistaWebServiceFileBW = new BufferedWriter(vistaWebServiceFileWriter);

        StringBuilder contentsServletMetric = new StringBuilder();
        contentsServletMetric.append("Node");
        contentsServletMetric.append(";");
        contentsServletMetric.append("Date");
        contentsServletMetric.append(";");
        contentsServletMetric.append("Hour");
        contentsServletMetric.append(";");
        contentsServletMetric.append("Minute");
        contentsServletMetric.append(";");
        contentsServletMetric.append("Seconds");
        contentsServletMetric.append(";");
        contentsServletMetric.append("mSec");
        contentsServletMetric.append(";");
        contentsServletMetric.append("User");
        contentsServletMetric.append(";");
        contentsServletMetric.append("IP");
        contentsServletMetric.append(";");
        contentsServletMetric.append("Brand");
        contentsServletMetric.append(";");
        contentsServletMetric.append("Market");
        contentsServletMetric.append(";");
        contentsServletMetric.append("Resp Time");
        contentsServletMetric.append(";");
        contentsServletMetric.append("Reg Page");
        contentsServletMetric.append(";");
        contentsServletMetric.append("Method");
        contentsServletMetric.append(System.getProperty("line.separator"));
        servletMetricBW.write(contentsServletMetric.toString());

        StringBuilder contentsServletMetricE = new StringBuilder();
        contentsServletMetricE.append("Node");
        contentsServletMetricE.append(";");
        contentsServletMetricE.append("Date");
        contentsServletMetricE.append(";");
        contentsServletMetricE.append("Hour");
        contentsServletMetricE.append(";");
        contentsServletMetricE.append("Minute");
        contentsServletMetricE.append(";");
        contentsServletMetricE.append("Seconds");
        contentsServletMetricE.append(";");
        contentsServletMetricE.append("mSec");
        contentsServletMetricE.append(";");
        contentsServletMetricE.append("User");
        contentsServletMetricE.append(";");
        contentsServletMetricE.append("IP");
        contentsServletMetricE.append(";");
        contentsServletMetricE.append("Brand");
        contentsServletMetricE.append(";");
        contentsServletMetricE.append("Market");
        contentsServletMetricE.append(";");
        contentsServletMetricE.append("Resp Time");
        contentsServletMetricE.append(";");
        contentsServletMetricE.append("Reg Page");
        contentsServletMetricE.append(";");
        contentsServletMetricE.append("Method");
        contentsServletMetricE.append(System.getProperty("line.separator"));
        servletMetricEBW.write(contentsServletMetricE.toString());

        StringBuilder contentsInterfaceMetric = new StringBuilder();


        contentsInterfaceMetric.append("Date");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("Time");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("Hour");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("Minute");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("Seconds");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("mSec");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("User");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("Interface");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("Brand");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("Market");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("Resp Time");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("Count");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("Range");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("US");
        contentsInterfaceMetric.append(";");

        /*
         * contentsInterfaceMetric.append("Reg Time 2");
         * contentsInterfaceMetric.append(";");
         * contentsInterfaceMetric.append("Reg Time 3");
         * contentsInterfaceMetric.append(";");
         * contentsInterfaceMetric.append("Reg Time 4");
         * contentsInterfaceMetric.append(";");
         * contentsInterfaceMetric.append("Reg Time 5");
         * contentsInterfaceMetric.append(";");
         * contentsInterfaceMetric.append("Reg Time 6");
         * contentsInterfaceMetric.append(";");
         */
        contentsInterfaceMetric.append("BODID");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("Succes %");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("Failed %");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("6 Sec Req");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("Market Full");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("TO");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("Consumer");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("US Hour");
        contentsInterfaceMetric.append(";");
        contentsInterfaceMetric.append("Node");
        contentsInterfaceMetric.append(System.getProperty("line.separator"));
        interfaceMetricBW.write(contentsInterfaceMetric.toString());

        StringBuilder contentsVistaError = new StringBuilder();

        contentsVistaError.append("Date");
        contentsVistaError.append(";");
        contentsVistaError.append("Time");
        contentsVistaError.append(";");
        contentsVistaError.append("Hour");
        contentsVistaError.append(";");
        contentsVistaError.append("Minute");
        contentsVistaError.append(";");
        contentsVistaError.append("Seconds");
        contentsVistaError.append(";");
        contentsVistaError.append("mSec");
        contentsVistaError.append(";");
        contentsVistaError.append("User");
        contentsVistaError.append(";");
        contentsVistaError.append("LogLevel");
        contentsVistaError.append(";");
        contentsVistaError.append("Name");
        contentsVistaError.append(";");
        contentsVistaError.append("Description");
        contentsVistaError.append(";");
        contentsVistaError.append("Node");
        contentsVistaError.append(System.getProperty("line.separator"));
        vistaErrorFileBW.write(contentsVistaError.toString());

        StringBuilder contentsVistaRestService = new StringBuilder();

        contentsVistaRestService.append("Date");
        contentsVistaRestService.append(";");
        contentsVistaRestService.append("Time");
        contentsVistaRestService.append(";");
        contentsVistaRestService.append("Hour");
        contentsVistaRestService.append(";");
        contentsVistaRestService.append("Minute");
        contentsVistaRestService.append(";");
        contentsVistaRestService.append("Seconds");
        contentsVistaRestService.append(";");
        contentsVistaRestService.append("mSec");
        contentsVistaRestService.append(";");
        contentsVistaRestService.append("User");
        contentsVistaRestService.append(";");
        contentsVistaRestService.append("LogLevel");
        contentsVistaRestService.append(";");
        contentsVistaRestService.append("Name");
        contentsVistaRestService.append(";");
        contentsVistaRestService.append("Description");
        contentsVistaRestService.append(";");
        contentsVistaRestService.append("Node");
        contentsVistaRestService.append(System.getProperty("line.separator"));
        vistaRestServiceFileBW.write(contentsVistaRestService.toString());

        StringBuilder contentsVistaWebService = new StringBuilder();

        contentsVistaWebService.append("Date");
        contentsVistaWebService.append(";");
        contentsVistaWebService.append("Time");
        contentsVistaWebService.append(";");
        contentsVistaWebService.append("Hour");
        contentsVistaWebService.append(";");
        contentsVistaWebService.append("Minute");
        contentsVistaWebService.append(";");
        contentsVistaWebService.append("Seconds");
        contentsVistaWebService.append(";");
        contentsVistaWebService.append("mSec");
        contentsVistaWebService.append(";");
        contentsVistaWebService.append("User");
        contentsVistaWebService.append(";");
        contentsVistaWebService.append("LogLevel");
        contentsVistaWebService.append(";");
        contentsVistaWebService.append("Name");
        contentsVistaWebService.append(";");
        contentsVistaWebService.append("Description");
        contentsVistaWebService.append(";");
        contentsVistaWebService.append("Node");
        contentsVistaWebService.append(System.getProperty("line.separator"));
        vistaWebServiceFileBW.write(contentsVistaWebService.toString());

        logPathArray[0] = ReadProperties.readProperties().getProperty("nvista1");
        logPathArray[1] = ReadProperties.readProperties().getProperty("nvista2");
        logPathArray[2] = ReadProperties.readProperties().getProperty("nvista-batch1");
        logPathArray[3] = ReadProperties.readProperties().getProperty("nvista-batch2");
        logPathArray[4] = ReadProperties.readProperties().getProperty("nvista3");
        logPathArray[5] = ReadProperties.readProperties().getProperty("nvista4");
        System.out.println("I am going to search in logs.....:" + new Date());
        for (int i = 0; i < 6; i++) {

            String path = logPathArray[i];
            System.out.println("I am looking into ( " + path.split("=")[0] + ") node at :" + new Date());
            if (ReadProperties.readProperties().getProperty("vista_servlet_metrics").equalsIgnoreCase("Y"))
            readServletMetricLog(path, servletMetricBW);
            if (ReadProperties.readProperties().getProperty("vista_servlet_metrics_enhanced").equalsIgnoreCase("Y"))
                readServletMetricEnhancedLog(path, servletMetricEBW);
            if (ReadProperties.readProperties().getProperty("vista_interface_metrics").equalsIgnoreCase("Y"))
            readInterfaceMetricLog(path, interfaceMetricBW);
            if (ReadProperties.readProperties().getProperty("vista_error").equalsIgnoreCase("Y"))
                readVistaErrorLog(path, vistaErrorFileBW);
            if (ReadProperties.readProperties().getProperty("vista_rest_service").equalsIgnoreCase("Y"))
                readVistaRestServiceLog(path, vistaRestServiceFileBW);
            if (ReadProperties.readProperties().getProperty("vista_web_service").equalsIgnoreCase("Y"))
                readVistaWebServiceLog(path, vistaWebServiceFileBW);

        }
        System.out.println("I am writing into CSV file at :" + new Date());
        servletMetricBW.close();
        interfaceMetricBW.close();
        System.out.println("CSV file export done");
        if (ReadProperties.readProperties().getProperty("imExportExcel").equalsIgnoreCase("Y")) {
            System.out.println("I am writing into excel file at :" + new Date());
            XLSXReaderWriter.writeIntoExcel(interfaceMetricsList, ReadProperties.readProperties().getProperty("imExportExcelPath"), "Total");
            System.out.println("CSV file export done :" + new Date());
        }
        if (ReadProperties.readProperties().getProperty("smExportExcel").equalsIgnoreCase("Y")) {
            System.out.println("I am writing into excel file at :" + new Date());
            XLSXReaderWriter.writeIntoExcel(servletMetricsList, ReadProperties.readProperties().getProperty("smExportExcelPath"), "servletMetricsList");
            System.out.println("CSV file export done :" + new Date());
        }
        if (ReadProperties.readProperties().getProperty("veExportExcel").equalsIgnoreCase("Y")) {
            System.out.println("I am writing into excel file at :" + new Date());
            XLSXReaderWriter.writeIntoExcel(vistaErrorList, ReadProperties.readProperties().getProperty("veExportExcelPath"), "vistaErrorList");
            System.out.println("CSV file export done :" + new Date());
        }
        if (ReadProperties.readProperties().getProperty("vrsExportExcel").equalsIgnoreCase("Y")) {
            System.out.println("I am writing into excel file at :" + new Date());
            XLSXReaderWriter.writeIntoExcel(vistaRestServiceList, ReadProperties.readProperties().getProperty("vrsExportExcelPath"), "vistaRestServiceList");
            System.out.println("CSV file export done :" + new Date());
        }
        if (ReadProperties.readProperties().getProperty("vwsExportExcel").equalsIgnoreCase("Y")) {
            System.out.println("I am writing into excel file at :" + new Date());
            XLSXReaderWriter.writeIntoExcel(vistaWebServiceList, ReadProperties.readProperties().getProperty("vwsExportExcelPath"), "vistaWebServiceList");
            System.out.println("CSV file export done :" + new Date());
        }

        System.out.println("Completed at:" + new Date());
    }

    public static String getParsedAppLog(String filecontents, String node) {
        String[] lines = filecontents.toString().split(System.getProperty("line.separator"));
        StringBuilder contents = new StringBuilder();
        contents.append("Node");
        contents.append(";");
        contents.append("Date");
        contents.append(";");
        contents.append("Hour");
        contents.append(";");
        contents.append("Minute");
        contents.append(";");
        contents.append("Seconds");
        contents.append(";");
        contents.append("mSec");
        contents.append(";");
        contents.append("User");
        contents.append(";");
        contents.append("IP");
        contents.append(";");
        contents.append("Brand");
        contents.append(";");
        contents.append("Market");
        contents.append(";");
        contents.append("Resp Time");
        contents.append(";");
        contents.append("Reg Page");
        contents.append(";");
        contents.append("Method");
        contents.append(System.getProperty("line.separator"));

        // String[] samples = { "2012-06-13 00:03:24,841 [GGONZALE] - 10.242.48.123 YV1 EO 0.6s /Vista-Web/welcome.do null" };

        String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] - (\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}) ([^ ]*) ([^ ]*) (.*?)s /Vista-Web/([^ ]*)(.*)$";

        System.out.println("Started  ");
        Pattern p = Pattern.compile(regex);
        for (int i = 0; i < lines.length; i++) {
            Matcher m = p.matcher(lines[i]);
            if (m.matches()) {
                String date = m.group(1);
                String hr = m.group(2);
                String minute = m.group(3);
                String sec = m.group(4);
                String millisec = m.group(5);
                String userId = m.group(6);
                String ipAddr = m.group(7);
                String brand = m.group(8);
                String market = m.group(9);
                String resTime = m.group(10);
                String reqURL = m.group(11);
                String method = m.group(12);

                System.out.println("URL: " + reqURL);
                System.out.println("method: " + method);
                contents.append(node);
                contents.append(";");
                contents.append(date);
                contents.append(";");
                contents.append(hr);
                contents.append(";");
                contents.append(minute);
                contents.append(";");
                contents.append(sec);
                contents.append(";");
                contents.append(millisec);
                contents.append(";");
                contents.append(userId);
                contents.append(";");
                contents.append(ipAddr);
                contents.append(";");
                contents.append(brand);
                contents.append(";");
                contents.append(market);
                contents.append(";");
                contents.append(resTime);
                contents.append(";");
                contents.append(reqURL);
                contents.append(";");
                contents.append(method);
                contents.append(System.getProperty("line.separator"));
            }
        }
        return contents.toString();
    }

    private static void readServletMetricEnhancedLog(String path, BufferedWriter bw) throws IOException {

        String[] fileNameFromPath = path.split("=");
        String node = fileNameFromPath[0];

        final String sfFlag = ReadProperties.readProperties().getProperty("vista_servlet_metrics_enhanced");

        final String fileString = ReadProperties.readProperties().getProperty("smefiles");
        final String fileSearchType = ReadProperties.readProperties().getProperty("smefilesSearchType");

        ArrayList<String> namesList=new ArrayList<String>();
        String name[] = {};
        int startDate = 0;
        int endDate = 0;
        String dummyFileName = fileString;
        if (fileString.contains(",")) {
            name = fileString.split(".log")[1].split(",");
            startDate = Integer.parseInt(name[0]);
            endDate = Integer.parseInt(name[0]);
            dummyFileName = fileString.substring(0, fileString.length() - 5);
        }
        for (int i = startDate; i <= endDate; i++) {

            namesList.add(dummyFileName + i);
        }
        for (String nameOfFile : namesList) {

        }
        

        File f = new File(fileNameFromPath[1]);
        File[] files = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                if (sfFlag.equalsIgnoreCase("Y")) {
                    if (fileSearchType.equalsIgnoreCase("E")) {
                        return name.equals(fileString);
                    } else {

                        return name.contains(fileString);
                    }
                } else {
                    return name.equals("#");
                }
                // return name.endsWith(".log");//|| name.endsWith(".gif");
            }
        });
        if (files != null)
            for (File file : files) {
                File fileName = file.getAbsoluteFile();
                String line = null;

                try {
                    // FileReader reads text files in the default encoding.
                    FileReader fileReader = new FileReader(fileName);

                    // Always wrap FileReader in BufferedReader.
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    while ((line = bufferedReader.readLine()) != null) {

                        if (line.contains(ReadProperties.readProperties().getProperty("smeSearchText"))) {
                            // System.out.println(node + " " + line);
                            // bw.write(line + "\n");

                            // String[] samples = { "2012-06-13 00:03:24,841 [GGONZALE] - 10.242.48.123 YV1 EO 0.6s /Vista-Web/welcome.do null" };

                            // String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] -
                            // (\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}) ([^ ]*) ([^ ]*) (.*?)s /Vista-Web/([^ ]*)(.*)$";
                            String[] samples = { "2012-06-13 00:03:24,841 [GGONZALE] - 10.242.48.123 YV1 EO 0.6s /Vista-Web/welcome.do null" };

                            String regex = ReadProperties.readProperties().getProperty("smeregex");
                            String regex2 = ReadProperties.readProperties().getProperty("smeregex2");

                            Pattern p = Pattern.compile(regex);
                            Pattern p2 = Pattern.compile(regex2);
                            StringBuilder contents = new StringBuilder();
                            Matcher m = p.matcher(line);
                            Matcher m2 = p2.matcher(line);
                            if (m.matches()) {
                                String date = m.group(1);
                                String hr = m.group(2);
                                String minute = m.group(3);
                                String sec = m.group(4);
                                String millisec = m.group(5);
                                String userId = m.group(6);
                                String ipAddr = m.group(9);
                                String brand = m.group(10);
                                String market = m.group(11);
                                // String resTime = m.group(12);
                                String reqURL = m.group(12);
                                String method = m.group(13);

                                BeanClass bean = new BeanClass();

                                bean.setDate(date);
                                bean.setHr(hr);
                                bean.setMinute(minute);
                                bean.setSec(sec);
                                bean.setMillisec(millisec);
                                bean.setUserId(userId);
                                bean.setInterceId(ipAddr);
                                bean.setBrand(brand);
                                bean.setMarket(market);
                                bean.setResTime(null);
                                bean.setReqURL(reqURL);
                                bean.setMethod(method);
                                bean.setNode(node);

                                servletMetricsList.add(bean);

                                // System.out.println("URL: " + reqURL);
                                // System.out.println("method: " + method);
                                contents.append(node);
                                contents.append(";");
                                contents.append(date);
                                contents.append(";");
                                contents.append(hr);
                                contents.append(";");
                                contents.append(minute);
                                contents.append(";");
                                contents.append(sec);
                                contents.append(";");
                                contents.append(millisec);
                                contents.append(";");
                                contents.append(userId);
                                contents.append(";");
                                contents.append(ipAddr);
                                contents.append(";");
                                contents.append(brand);
                                contents.append(";");
                                contents.append(market);
                                contents.append(";");
                                contents.append("");
                                contents.append(";");
                                contents.append(reqURL);
                                contents.append(";");
                                contents.append(method);

                                /**
                                 * Double time = new Double(resTime);
                                 * 
                                 * String log20sec = null;
                                 * 
                                 * if (time < 5) {
                                 * log20sec = "1. 0 < 5 Sec";
                                 * } else if (time > 5 && time < 10) {
                                 * log20sec = "2. 5 < 10 Sec";
                                 * } else if (time > 10 && time < 20) {
                                 * log20sec = "3. 10 < 20 Sec";
                                 * } else if (time > 20) {
                                 * log20sec = "4. 20 Sec <";
                                 * }
                                 * 
                                 * contents.append(";");
                                 * contents.append(log20sec);
                                 * 
                                 * 
                                 * String log90sec = null;
                                 * 
                                 * if (time < 30) {
                                 * log90sec = "1. 0 < 30 Sec";
                                 * } else if (time > 30 && time < 60) {
                                 * log90sec = "2. 30 < 60 Sec";
                                 * } else if (time > 60 && time < 90) {
                                 * log90sec = "3. 60 < 90 Sec";
                                 * } else if (time > 90) {
                                 * log90sec = "4. 90 Sec <";
                                 * }
                                 * 
                                 * contents.append(";");
                                 * contents.append(log90sec);
                                 * 
                                 * String log900sec = null;
                                 * 
                                 * if (time < 300) {
                                 * log900sec = "1. 0 < 300 Sec";
                                 * } else if (time > 300 && time < 600) {
                                 * log900sec = "2. 300 < 600 Sec";
                                 * } else if (time > 600 && time < 900) {
                                 * log900sec = "3. 600 < 900 Sec";
                                 * } else if (time > 900) {
                                 * log900sec = "4. 900 Sec <";
                                 * }
                                 * 
                                 * contents.append(";");
                                 * contents.append(log900sec);
                                 */
                                contents.append(System.getProperty("line.separator"));
                                // System.out.println(contents.toString());
                                bw.write(contents.toString());
                            } else if (m2.matches()) {
                                String date = m2.group(1);
                                String hr = m2.group(2);
                                String minute = m2.group(3);
                                String sec = m2.group(4);
                                String millisec = m2.group(5);
                                String userId = m2.group(6);
                                String ipAddr = m2.group(9);
                                String brand = m2.group(10);
                                String market = "";
                                // String resTime = m.group(12);
                                String reqURL = m2.group(11);
                                String method = m2.group(12);

                                BeanClass bean = new BeanClass();

                                bean.setDate(date);
                                bean.setHr(hr);
                                bean.setMinute(minute);
                                bean.setSec(sec);
                                bean.setMillisec(millisec);
                                bean.setUserId(userId);
                                bean.setInterceId(ipAddr);
                                bean.setBrand(brand);
                                bean.setMarket(market);
                                bean.setResTime(null);
                                bean.setReqURL(reqURL);
                                bean.setMethod(method);
                                bean.setNode(node);

                                servletMetricsList.add(bean);

                                // System.out.println("URL: " + reqURL);
                                // System.out.println("method: " + method);
                                contents.append(node);
                                contents.append(";");
                                contents.append(date);
                                contents.append(";");
                                contents.append(hr);
                                contents.append(";");
                                contents.append(minute);
                                contents.append(";");
                                contents.append(sec);
                                contents.append(";");
                                contents.append(millisec);
                                contents.append(";");
                                contents.append(userId);
                                contents.append(";");
                                contents.append(ipAddr);
                                contents.append(";");
                                contents.append(brand);
                                contents.append(";");
                                contents.append(market);
                                contents.append(";");
                                contents.append("");
                                contents.append(";");
                                contents.append(reqURL);
                                contents.append(";");
                                contents.append(method);

                                /**
                                 * Double time = new Double(resTime);
                                 * 
                                 * String log20sec = null;
                                 * 
                                 * if (time < 5) {
                                 * log20sec = "1. 0 < 5 Sec";
                                 * } else if (time > 5 && time < 10) {
                                 * log20sec = "2. 5 < 10 Sec";
                                 * } else if (time > 10 && time < 20) {
                                 * log20sec = "3. 10 < 20 Sec";
                                 * } else if (time > 20) {
                                 * log20sec = "4. 20 Sec <";
                                 * }
                                 * 
                                 * contents.append(";");
                                 * contents.append(log20sec);
                                 * 
                                 * 
                                 * String log90sec = null;
                                 * 
                                 * if (time < 30) {
                                 * log90sec = "1. 0 < 30 Sec";
                                 * } else if (time > 30 && time < 60) {
                                 * log90sec = "2. 30 < 60 Sec";
                                 * } else if (time > 60 && time < 90) {
                                 * log90sec = "3. 60 < 90 Sec";
                                 * } else if (time > 90) {
                                 * log90sec = "4. 90 Sec <";
                                 * }
                                 * 
                                 * contents.append(";");
                                 * contents.append(log90sec);
                                 * 
                                 * String log900sec = null;
                                 * 
                                 * if (time < 300) {
                                 * log900sec = "1. 0 < 300 Sec";
                                 * } else if (time > 300 && time < 600) {
                                 * log900sec = "2. 300 < 600 Sec";
                                 * } else if (time > 600 && time < 900) {
                                 * log900sec = "3. 600 < 900 Sec";
                                 * } else if (time > 900) {
                                 * log900sec = "4. 900 Sec <";
                                 * }
                                 * 
                                 * contents.append(";");
                                 * contents.append(log900sec);
                                 */
                                contents.append(System.getProperty("line.separator"));
                                // System.out.println(contents.toString());
                                bw.write(contents.toString());
                            } else {
                                if (line.contains("Request Start")) {
                                    System.out.println(line);
                                }
                                ;
                            }
                        }
                    }

                } catch (FileNotFoundException ex) {
                    System.out.println("Unable to open file '" + fileName + "'");
                } catch (IOException ex) {
                    System.out.println("Error reading file '" + fileName + "'");
                    // Or we could just do this:
                    // ex.printStackTrace();
                }
            }

    }

    private static void readServletMetricLog(String path, BufferedWriter bw) throws IOException {

        String[] fileNameFromPath = path.split("=");
        String node = fileNameFromPath[0];

        final String sfFlag = ReadProperties.readProperties().getProperty("vista_servlet_metrics");

        final String fileString = ReadProperties.readProperties().getProperty("smfiles");
        final String fileSearchType = ReadProperties.readProperties().getProperty("smfilesSearchType");

        ArrayList<String> namesList = new ArrayList<String>();
        String name[] = {};
        int startDate = 0;
        int endDate = 0;
        String dummyFileName = fileString;
        if (fileString.contains(",")) {
            name = fileString.split(".log")[1].split(",");
            startDate = Integer.parseInt(name[0]);
            endDate = Integer.parseInt(name[0]);
            dummyFileName = fileString.substring(0, fileString.length() - 5);
        }
        for (int i = startDate; i <= endDate; i++) {

            namesList.add(dummyFileName + i);
        }
        for (String nameOfFile : namesList) {

        }

        File f = new File(fileNameFromPath[1]);
        File[] files = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                if (sfFlag.equalsIgnoreCase("Y")) {
                    if (fileSearchType.equalsIgnoreCase("E")) {
                        return name.equals(fileString);
                    } else {

                        return name.contains(fileString);
                    }
                } else {
                    return name.equals("#");
                }
                // return name.endsWith(".log");//|| name.endsWith(".gif");
            }
        });
        if (files != null)
            for (File file : files) {
                File fileName = file.getAbsoluteFile();
                String line = null;

                try {
                    // FileReader reads text files in the default encoding.
                    FileReader fileReader = new FileReader(fileName);

                    // Always wrap FileReader in BufferedReader.
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    while ((line = bufferedReader.readLine()) != null) {

                        if (line.contains(ReadProperties.readProperties().getProperty("smSearchText"))) {
                            // System.out.println(node + " " + line);
                            // bw.write(line + "\n");

                            // String[] samples = { "2012-06-13 00:03:24,841 [GGONZALE] - 10.242.48.123 YV1 EO 0.6s /Vista-Web/welcome.do null" };

                            // String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] -
                            // (\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}) ([^ ]*) ([^ ]*) (.*?)s /Vista-Web/([^ ]*)(.*)$";
                            String[] samples = { "2012-06-13 00:03:24,841 [GGONZALE] - 10.242.48.123 YV1 EO 0.6s /Vista-Web/welcome.do null" };

                            String regex = ReadProperties.readProperties().getProperty("smregex");
                            String regex2 = ReadProperties.readProperties().getProperty("smregex2");

                            Pattern p = Pattern.compile(regex);
                            Pattern p2 = Pattern.compile(regex2);
                            StringBuilder contents = new StringBuilder();
                            Matcher m = p.matcher(line);
                            Matcher m2 = p2.matcher(line);
                            if (m.matches()) {
                                String date = m.group(1);
                                String hr = m.group(2);
                                String minute = m.group(3);
                                String sec = m.group(4);
                                String millisec = m.group(5);
                                String userId = m.group(6);
                                String ipAddr = m.group(7);
                                String brand = m.group(8);
                                String market = m.group(9);
                                String resTime = m.group(10);
                                String reqURL = m.group(11);
                                String method = m.group(12);

                                BeanClass bean = new BeanClass();

                                bean.setDate(date);
                                bean.setHr(hr);
                                bean.setMinute(minute);
                                bean.setSec(sec);
                                bean.setMillisec(millisec);
                                bean.setUserId(userId);
                                bean.setInterceId(ipAddr);
                                bean.setBrand(brand);
                                bean.setMarket(market);
                                bean.setResTime(resTime);
                                bean.setReqURL(reqURL);
                                bean.setMethod(method);
                                bean.setNode(node);

                                servletMetricsList.add(bean);

                                // System.out.println("URL: " + reqURL);
                                // System.out.println("method: " + method);
                                contents.append(node);
                                contents.append(";");
                                contents.append(date);
                                contents.append(";");
                                contents.append(hr);
                                contents.append(";");
                                contents.append(minute);
                                contents.append(";");
                                contents.append(sec);
                                contents.append(";");
                                contents.append(millisec);
                                contents.append(";");
                                contents.append(userId);
                                contents.append(";");
                                contents.append(ipAddr);
                                contents.append(";");
                                contents.append(brand);
                                contents.append(";");
                                contents.append(market);
                                contents.append(";");
                                contents.append(resTime);
                                contents.append(";");
                                contents.append(reqURL);
                                contents.append(";");
                                contents.append(method);

                                Double time = new Double(resTime);

                                String log20sec = null;

                                if (time <= 5) {
                                    log20sec = "00 - 5 Sec";
                                } else if (time > 5 && time <= 10) {
                                    log20sec = "05 - 10 Sec";
                                } else if (time > 10 && time <= 20) {
                                    log20sec = "10 - 20 Sec";
                                } else if (time > 20) {
                                    log20sec = "20 - Sec";
                                }

                                contents.append(";");
                                contents.append(log20sec);

                                String log90sec = null;

                                if (time <= 30) {
                                    log90sec = "00 - 30 Sec";
                                } else if (time > 30 && time <= 60) {
                                    log90sec = "30 - 60 Sec";
                                } else if (time > 60 && time <= 90) {
                                    log90sec = "60 - 90 Sec";
                                } else if (time > 90) {
                                    log90sec = "90 - Sec";
                                }

                                contents.append(";");
                                contents.append(log90sec);

                                String log900sec = null;

                                if (time <= 300) {
                                    log900sec = "00 - 300 Sec";
                                } else if (time > 300 && time <= 600) {
                                    log900sec = "300 < 600 Sec";
                                } else if (time > 600 && time <= 900) {
                                    log900sec = "600 < 900 Sec";
                                } else if (time > 900) {
                                    log900sec = "900 - Sec";
                                }

                                contents.append(";");
                                contents.append(log900sec);

                                contents.append(System.getProperty("line.separator"));
                                // System.out.println(contents.toString());

                                bw.write(contents.toString());
                            } else if (m2.matches()) {
                                String date = m2.group(1);
                                String hr = m2.group(2);
                                String minute = m2.group(3);
                                String sec = m2.group(4);
                                String millisec = m2.group(5);
                                String userId = m2.group(6);
                                String ipAddr = m2.group(7);
                                String brand = m2.group(8);
                                String market = "";
                                String resTime = m2.group(9);
                                String reqURL = m2.group(10);
                                String method = m2.group(11);

                                BeanClass bean = new BeanClass();

                                bean.setDate(date);
                                bean.setHr(hr);
                                bean.setMinute(minute);
                                bean.setSec(sec);
                                bean.setMillisec(millisec);
                                bean.setUserId(userId);
                                bean.setInterceId(ipAddr);
                                bean.setBrand(brand);
                                bean.setMarket(market);
                                bean.setResTime(resTime);
                                bean.setReqURL(reqURL);
                                bean.setMethod(method);
                                bean.setNode(node);

                                servletMetricsList.add(bean);

                                // System.out.println("URL: " + reqURL);
                                // System.out.println("method: " + method);
                                contents.append(node);
                                contents.append(";");
                                contents.append(date);
                                contents.append(";");
                                contents.append(hr);
                                contents.append(";");
                                contents.append(minute);
                                contents.append(";");
                                contents.append(sec);
                                contents.append(";");
                                contents.append(millisec);
                                contents.append(";");
                                contents.append(userId);
                                contents.append(";");
                                contents.append(ipAddr);
                                contents.append(";");
                                contents.append(brand);
                                contents.append(";");
                                contents.append(market);
                                contents.append(";");
                                contents.append(resTime);
                                contents.append(";");
                                contents.append(reqURL);
                                contents.append(";");
                                contents.append(method);

                                Double time = new Double(resTime);

                                String log20sec = null;

                                if (time < 5) {
                                    log20sec = "1. 0 < 5 Sec";
                                } else if (time > 5 && time < 10) {
                                    log20sec = "2. 5 < 10 Sec";
                                } else if (time > 10 && time < 20) {
                                    log20sec = "3. 10 < 20 Sec";
                                } else if (time > 20) {
                                    log20sec = "4. 20 Sec <";
                                }

                                contents.append(";");
                                contents.append(log20sec);

                                String log90sec = null;

                                if (time < 30) {
                                    log90sec = "1. 0 < 30 Sec";
                                } else if (time > 30 && time < 60) {
                                    log90sec = "2. 30 < 60 Sec";
                                } else if (time > 60 && time < 90) {
                                    log90sec = "3. 60 < 90 Sec";
                                } else if (time > 90) {
                                    log90sec = "4. 90 Sec <";
                                }

                                contents.append(";");
                                contents.append(log90sec);

                                String log900sec = null;

                                if (time < 300) {
                                    log900sec = "1. 0 < 300 Sec";
                                } else if (time > 300 && time < 600) {
                                    log900sec = "2. 300 < 600 Sec";
                                } else if (time > 600 && time < 900) {
                                    log900sec = "3. 600 < 900 Sec";
                                } else if (time > 900) {
                                    log900sec = "4. 900 Sec <";
                                }

                                contents.append(";");
                                contents.append(log900sec);

                                contents.append(System.getProperty("line.separator"));
                                // System.out.println(contents.toString());
                                bw.write(contents.toString());
                            } else {
                                System.out.println(line);
                            }
                        }
                    }

                } catch (FileNotFoundException ex) {
                    System.out.println("Unable to open file '" + fileName + "'");
                } catch (IOException ex) {
                    System.out.println("Error reading file '" + fileName + "'");
                    // Or we could just do this:
                    // ex.printStackTrace();
                }
            }

    }

    private static void readInterfaceMetricLog(String path, BufferedWriter bw) throws IOException {

        String[] fileNameFromPath = path.split("=");
        String node = fileNameFromPath[0];

        final String ifFlag = ReadProperties.readProperties().getProperty("vista_interface_metrics");
        final String fileString = ReadProperties.readProperties().getProperty("imfiles");
        final String fileSearchType = ReadProperties.readProperties().getProperty("imfilesSearchType");

        File f = new File(fileNameFromPath[1]);
        File[] files = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                if (ifFlag.equalsIgnoreCase("Y")) {
                    if (fileSearchType.equalsIgnoreCase("E")) {
                        return name.equals(fileString);
                    } else {
                    return name.contains(fileString);
                    }
                } else {
                    return name.equals("#");
                }
            }
        });
        if (files != null)
            for (File file : files) {
                File fileName = file.getAbsoluteFile();
                String line = null;
                if (fileName.isFile()) {

                }

                try {
                    // FileReader reads text files in the default encoding.
                    FileReader fileReader = new FileReader(fileName);

                    // Always wrap FileReader in BufferedReader.
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    while ((line = bufferedReader.readLine()) != null) {

                        if (line.contains(ReadProperties.readProperties().getProperty("imSearchText"))) {

                            // String[] samples = { "2012-06-13 00:31:20,191 [Vista ] - IF-ADP02 YV1 0.14, 0.01, 0.05, 0.08 0AFE322ED9258061E2D292F1E2D292F1" };

                            // String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] - ([^ ]*) ([^ ]*) ([^ ]*)
                            // (.*?),(.*?),(.*?),(.*?) (.*)$";
                            // String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] - ([^ ]*), ([^ ]*), ([^ ]*), (.*?), (.*?),
                            // (.*?), (.*?), (.*?), (.*?), (.*?)$";
                            // String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] - ([^ ]*), ([^ ]*), ([^ ]*), (.*?), (.*?),
                            // (.*?), (.*?), (.*?), (.*?), (.*?)$";
                            // CoC String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] - ([^ ]*) ([^ ]*) ([^ ]*) (.*?), (.*?),
                            // (.*?), (.*?), (.*?), (.*?) (.*?)$";
                            String regex = ReadProperties.readProperties().getProperty("imregex");
                            Pattern p = Pattern.compile(regex);
                            StringBuilder contents = new StringBuilder();
                            Matcher m = p.matcher(line);
                            if (m.matches()) {
                                BeanClass bean = new BeanClass();

                                String date = m.group(1);
                                String hr = m.group(2);
                                String minute = m.group(3);
                                String sec = m.group(4);
                                String millisec = m.group(5);
                                String userId = m.group(6);
                                String interfaceId = m.group(7);
                                String brand = m.group(8);
                                String market = m.group(9);
                                String resTime = m.group(10);
                                String resTime1 = m.group(11);
                                String resTime2 = m.group(12);
                                String resTime3 = m.group(13);
                                String resTime4 = m.group(14);
                                String resTime5 = m.group(15);
                                String bodId = m.group(16);
bean.setDate(date);bean.setHr(hr);bean.setMinute(minute);bean.setSec(sec);bean.setMillisec(millisec);
                                bean.setUserId(userId);
                                bean.setInterceId(interfaceId);
                                bean.setBrand(brand);
                                bean.setMarket(market);
                                bean.setResTime(resTime);
                                bean.setResTime1(resTime1);
                                bean.setResTime2(resTime2);
                                bean.setResTime3(resTime3);
                                bean.setResTime4(resTime4);
                                bean.setResTime5(resTime5);
                                bean.setBodId(bodId);
                                bean.setNode(node);

                                interfaceMetricsList.add(bean);

                                contents.append(date);
                                contents.append(";");
                                contents.append("");
                                contents.append(";");
                                contents.append(hr);
                                contents.append(";");
                                contents.append(minute);
                                contents.append(";");
                                contents.append(sec);
                                contents.append(";");
                                contents.append(millisec);
                                contents.append(";");
                                contents.append(userId);
                                contents.append(";");
                                contents.append(interfaceId);
                                contents.append(";");
                                contents.append(brand);
                                contents.append(";");
                                contents.append(market);
                                contents.append(";");
                                contents.append(resTime);
                                contents.append(";");
                                contents.append("1");
                                contents.append(";");
                                contents.append("");
                                contents.append(";");
                                contents.append("");
                                contents.append(";");
                                contents.append(bodId);
                                contents.append(";");
                                contents.append("");
                                contents.append(";");
                                contents.append("");
                                contents.append(";");
                                contents.append("");
                                contents.append(";");
                                contents.append("");
                                contents.append(";");
                                contents.append("");
                                contents.append(";");
                                contents.append("");
                                contents.append(";");
                                contents.append("");
                                contents.append(";");
                                contents.append(node);
                                contents.append(System.getProperty("line.separator"));
                                // System.out.println(contents.toString());
                                bw.write(contents.toString());
                            }
                        }
                    }

                } catch (FileNotFoundException ex) {
                    System.out.println("Unable to open file '" + fileName + "'");
                } catch (IOException ex) {
                    System.out.println("Error reading file '" + fileName + "'");
                }
            }

    }

    private static void readVistaErrorLog(String path, BufferedWriter bw) throws IOException {

        String[] fileNameFromPath = path.split("=");
        String node = fileNameFromPath[0];

        final String ifFlag = ReadProperties.readProperties().getProperty("vista_error");
        final String fileString = ReadProperties.readProperties().getProperty("vefiles");
        final String fileSearchType = ReadProperties.readProperties().getProperty("vefilesSearchType");

        File f = new File(fileNameFromPath[1]);
        File[] files = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                if (ifFlag.equalsIgnoreCase("Y")) {
                    if (fileSearchType.equalsIgnoreCase("E")) {
                        return name.equals(fileString);
                    } else {
                        return name.contains(fileString);
                    }
                } else {
                    return name.equals("#");
                }
            }
        });
        if (files != null)
            for (File file : files) {
                File fileName = file.getAbsoluteFile();
                String line = null;
                if (fileName.isFile()) {

                }

                try {
                    // FileReader reads text files in the default encoding.
                    FileReader fileReader = new FileReader(fileName);

                    // Always wrap FileReader in BufferedReader.
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    while ((line = bufferedReader.readLine()) != null) {

                        if (line.contains(ReadProperties.readProperties().getProperty("veSearchText"))) {

                            // String[] samples = { "2012-06-13 00:31:20,191 [Vista ] - IF-ADP02 YV1 0.14, 0.01, 0.05, 0.08 0AFE322ED9258061E2D292F1E2D292F1" };

                            // String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] - ([^ ]*) ([^ ]*) ([^ ]*)
                            // (.*?),(.*?),(.*?),(.*?) (.*)$";
                            // String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] - ([^ ]*), ([^ ]*), ([^ ]*), (.*?), (.*?),
                            // (.*?), (.*?), (.*?), (.*?), (.*?)$";
                            String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3})  \\[(.*)\\] ([^ ]*) ([^ ]*) ([\\s ]*) - log - (.*)$";

                            Pattern p = Pattern.compile(regex);
                            StringBuilder contents = new StringBuilder();
                            Matcher m = p.matcher(line);
                            if (m.matches()) {
                                BeanClass bean = new BeanClass();

                                String date = m.group(1);
                                String hr = m.group(2);
                                String minute = m.group(3);
                                String sec = m.group(4);
                                String millisec = m.group(5);
                                String user = m.group(6);
                                String interfaceId = m.group(7);
                                String brand = m.group(8);
                                // String market = m.group(9);
                                String resTime = m.group(10);
                                // String resTime1 = m.group(11);
                                // String resTime2 = m.group(12);
                                // String resTime3 = m.group(13);
                                // String resTime4 = m.group(14);
                                // String resTime5 = m.group(15);
                                // String bodId = m.group(16);
                                bean.setDate(date);
                                bean.setHr(hr);
                                bean.setMinute(minute);
                                bean.setSec(sec);
                                bean.setMillisec(millisec);
                                bean.setUserId(user);
                                bean.setInterceId(interfaceId);
                                bean.setBrand(brand);
                                // bean.setMarket(market);
                                bean.setResTime(resTime);
                                // bean.setResTime1(resTime1);
                                // bean.setResTime2(resTime2);
                                // bean.setResTime3(resTime3);
                                // bean.setResTime4(resTime4);
                                // bean.setResTime5(resTime5);
                                // bean.setBodId(bodId);
                                bean.setNode(node);

                                vistaErrorList.add(bean);

                                contents.append(date);
                                contents.append(";");
                                contents.append("");
                                contents.append(";");
                                contents.append(hr);
                                contents.append(";");
                                contents.append(minute);
                                contents.append(";");
                                contents.append(sec);
                                contents.append(";");
                                contents.append(millisec);
                                contents.append(";");
                                contents.append(user);
                                contents.append(";");
                                contents.append(interfaceId);
                                contents.append(";");
                                contents.append(brand);
                                contents.append(";");
                                // contents.append(market);
                                // contents.append(";");
                                contents.append(resTime);
                                // contents.append(";");
                                // contents.append("1");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append(bodId);
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                contents.append(";");
                                contents.append(node);
                                contents.append(System.getProperty("line.separator"));
                                // System.out.println(contents.toString());
                                bw.write(contents.toString());
                            }
                        }
                    }

                } catch (FileNotFoundException ex) {
                    System.out.println("Unable to open file '" + fileName + "'");
                } catch (IOException ex) {
                    System.out.println("Error reading file '" + fileName + "'");
                }
            }

    }

    private static void readVistaRestServiceLog(String path, BufferedWriter bw) throws IOException {

        String[] fileNameFromPath = path.split("=");
        String node = fileNameFromPath[0];

        final String ifFlag = ReadProperties.readProperties().getProperty("vista_rest_service");
        final String fileString = ReadProperties.readProperties().getProperty("vrsfiles");
        final String fileSearchType = ReadProperties.readProperties().getProperty("vrsfilesSearchType");

        File f = new File(fileNameFromPath[1]);
        File[] files = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                if (ifFlag.equalsIgnoreCase("Y")) {
                    if (fileSearchType.equalsIgnoreCase("E")) {
                        return name.equals(fileString);
                    } else {
                        return name.contains(fileString);
                    }
                } else {
                    return name.equals("#");
                }
            }
        });
        if (files != null)
            for (File file : files) {
                File fileName = file.getAbsoluteFile();
                String line = null;
                if (fileName.isFile()) {

                }

                try {
                    // FileReader reads text files in the default encoding.
                    FileReader fileReader = new FileReader(fileName);

                    // Always wrap FileReader in BufferedReader.
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    while ((line = bufferedReader.readLine()) != null) {

                        if (line.contains(ReadProperties.readProperties().getProperty("vrsSearchText"))) {

                            // String[] samples = { "2012-06-13 00:31:20,191 [Vista ] - IF-ADP02 YV1 0.14, 0.01, 0.05, 0.08 0AFE322ED9258061E2D292F1E2D292F1" };

                            // String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] - ([^ ]*) ([^ ]*) ([^ ]*)
                            // (.*?),(.*?),(.*?),(.*?) (.*)$";
                            // String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] - ([^ ]*), ([^ ]*), ([^ ]*), (.*?), (.*?),
                            // (.*?), (.*?), (.*?), (.*?), (.*?)$";
                            // String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] ([^ ]*) ([^ ]*) ([\\s ]*) - log - (.*)$";
                            String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] - >>([^ ]*) ([^ ]*), ([^ ]*) = ([^ ]*), ([^ ]*) = (.*)$";// Vista
                            Pattern p = Pattern.compile(regex);
                            StringBuilder contents = new StringBuilder();
                            Matcher m = p.matcher(line);
                            if (m.matches()) {
                                BeanClass bean = new BeanClass();

                                String date = m.group(1);
                                String hr = m.group(2);
                                String minute = m.group(3);
                                String sec = m.group(4);
                                String millisec = m.group(5);
                                String user = m.group(6);
                                String level = m.group(7);
                                String method = m.group(8);
                                String market = m.group(10);
                                String dealer = m.group(12);

                                bean.setDate(date);
                                bean.setHr(hr);
                                bean.setMinute(minute);
                                bean.setSec(sec);
                                bean.setMillisec(millisec);
                                bean.setUserId(user);
                                bean.setInterceId(level);
                                bean.setBrand(method);
                                bean.setMarket(market);
                                bean.setResTime(dealer);
                                // bean.setResTime1(resTime1);
                                // bean.setResTime2(resTime2);
                                // bean.setResTime3(resTime3);
                                // bean.setResTime4(resTime4);
                                // bean.setResTime5(resTime5);
                                // bean.setBodId(bodId);
                                bean.setNode(node);

                                vistaRestServiceList.add(bean);

                                contents.append(date);
                                contents.append(";");
                                contents.append("");
                                contents.append(";");
                                contents.append(hr);
                                contents.append(";");
                                contents.append(minute);
                                contents.append(";");
                                contents.append(sec);
                                contents.append(";");
                                contents.append(millisec);
                                contents.append(";");
                                contents.append(user);
                                contents.append(";");
                                contents.append(level);
                                contents.append(";");
                                contents.append(method);
                                contents.append(";");
                                contents.append(market);
                                contents.append(";");
                                contents.append(dealer);
                                // contents.append(";");
                                // contents.append("1");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append(bodId);
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                contents.append(";");
                                contents.append(node);
                                contents.append(System.getProperty("line.separator"));
                                // System.out.println(contents.toString());
                                bw.write(contents.toString());
                            }
                        }
                    }

                } catch (FileNotFoundException ex) {
                    System.out.println("Unable to open file '" + fileName + "'");
                } catch (IOException ex) {
                    System.out.println("Error reading file '" + fileName + "'");
                }
            }

    }

    private static void readVistaWebServiceLog(String path, BufferedWriter bw) throws IOException {

        String[] fileNameFromPath = path.split("=");
        String node = fileNameFromPath[0];

        final String ifFlag = ReadProperties.readProperties().getProperty("vista_web_service");
        final String fileString = ReadProperties.readProperties().getProperty("vwsfiles");
        final String fileSearchType = ReadProperties.readProperties().getProperty("vwsfilesSearchType");

        File f = new File(fileNameFromPath[1]);
        File[] files = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                if (ifFlag.equalsIgnoreCase("Y")) {
                    if (fileSearchType.equalsIgnoreCase("E")) {
                        return name.equals(fileString);
                    } else {
                        return name.contains(fileString);
                    }
                } else {
                    return name.equals("#");
                }
            }
        });
        if (files != null)
            for (File file : files) {
                File fileName = file.getAbsoluteFile();
                String line = null;
                if (fileName.isFile()) {

                }

                try {
                    // FileReader reads text files in the default encoding.
                    FileReader fileReader = new FileReader(fileName);

                    // Always wrap FileReader in BufferedReader.
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    while ((line = bufferedReader.readLine()) != null) {

                        if (line.contains(ReadProperties.readProperties().getProperty("vwsSearchText"))) {

                            // String[] samples = { "2012-06-13 00:31:20,191 [Vista ] - IF-ADP02 YV1 0.14, 0.01, 0.05, 0.08 0AFE322ED9258061E2D292F1E2D292F1" };

                            // String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] - ([^ ]*) ([^ ]*) ([^ ]*)
                            // (.*?),(.*?),(.*?),(.*?) (.*)$";
                            // String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] - ([^ ]*), ([^ ]*), ([^ ]*), (.*?), (.*?),
                            // (.*?), (.*?), (.*?), (.*?), (.*?)$";
                            // String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] ([^ ]*) ([^ ]*) ([\\s ]*) - log - (.*)$";
                            // String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] - >>([^ ]*) ([^ ]*), ([^ ]*) = ([^ ]*), ([^
                            // ]*) = (.*)$";// Vista
                            String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] - ([^ ]*) ([^ ]*)>>>>>>>>>>>>>>>>>>>>([^ ]*)([^ ]*)=([^ ]*), ([^ ]*)=([^ ]*), ([^ ]*)=([^ ]*), ([^ ]*)=([^ ]*)}$";
                            String regex1 = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] - <<([^ ]*) ([^ ]*) ([^ ]*) ([^ ]*)=([^ ]*)$";
                            Pattern p = Pattern.compile(regex);
                            StringBuilder contents = new StringBuilder();
                            Matcher m = p.matcher(line);
                            if (m.matches()) {
                                BeanClass bean = new BeanClass();

                                String date = m.group(1);
                                String hr = m.group(2);
                                String minute = m.group(3);
                                String sec = m.group(4);
                                String millisec = m.group(5);
                                String user = m.group(6);
                                String type = m.group(7);
                                String con = m.group(11);
                                String fyon = m.group(13);
                                String vin = m.group(15);
                                String dealer = m.group(17);

                                bean.setDate(date);
                                bean.setHr(hr);
                                bean.setMinute(minute);
                                bean.setSec(sec);
                                bean.setMillisec(millisec);
                                bean.setUserId(user);
                                bean.setInterceId(type);
                                bean.setBrand(con);
                                bean.setMarket(fyon);
                                bean.setResTime(vin);
                                bean.setResTime1(dealer);
                                // bean.setResTime2(resTime2);
                                // bean.setResTime3(resTime3);
                                // bean.setResTime4(resTime4);
                                // bean.setResTime5(resTime5);
                                // bean.setBodId(bodId);
                                bean.setNode(node);

                                vistaWebServiceList.add(bean);

                                contents.append(date);
                                contents.append(";");
                                contents.append("");
                                contents.append(";");
                                contents.append(hr);
                                contents.append(";");
                                contents.append(minute);
                                contents.append(";");
                                contents.append(sec);
                                contents.append(";");
                                contents.append(millisec);
                                contents.append(";");
                                contents.append(user);
                                contents.append(";");
                                contents.append(type);
                                contents.append(";");
                                contents.append(con);
                                contents.append(";");
                                contents.append(fyon);
                                contents.append(";");
                                contents.append(vin);
                                contents.append(";");
                                contents.append(dealer);
                                // contents.append(";");
                                // contents.append("1");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append(bodId);
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                // contents.append(";");
                                // contents.append("");
                                contents.append(";");
                                contents.append(node);
                                contents.append(System.getProperty("line.separator"));
                                // System.out.println(contents.toString());
                                bw.write(contents.toString());
                            }

                            Pattern p1 = Pattern.compile(regex1);

                            Matcher m1 = p1.matcher(line);
                            if (m1.matches()) {
                                BeanClass bean = new BeanClass();

                                String date = m1.group(1);
                                String hr = m1.group(2);
                                String minute = m1.group(3);
                                String sec = m1.group(4);
                                String millisec = m1.group(5);
                                String user = m1.group(6);
                                String type = m1.group(7);
                                String con = m1.group(11);

                                bean.setDate(date);
                                bean.setHr(hr);
                                bean.setMinute(minute);
                                bean.setSec(sec);
                                bean.setMillisec(millisec);
                                bean.setUserId(user);
                                bean.setInterceId(type);
                                bean.setBrand(con);
                                bean.setNode(node);

                                vistaWebServiceList.add(bean);

                                contents.append(date);
                                contents.append(";");
                                contents.append("");
                                contents.append(";");
                                contents.append(hr);
                                contents.append(";");
                                contents.append(minute);
                                contents.append(";");
                                contents.append(sec);
                                contents.append(";");
                                contents.append(millisec);
                                contents.append(";");
                                contents.append(user);
                                contents.append(";");
                                contents.append(type);
                                contents.append(";");
                                contents.append(con);
                                contents.append(";");
                                contents.append(";");
                                contents.append(node);
                                contents.append(System.getProperty("line.separator"));
                                // System.out.println(contents.toString());
                                bw.write(contents.toString());
                            }

                        }
                    }

                } catch (FileNotFoundException ex) {
                    System.out.println("Unable to open file '" + fileName + "'");
                } catch (IOException ex) {
                    System.out.println("Error reading file '" + fileName + "'");
                } catch (Exception ex) {
                    System.out.println("Error reading file '" + fileName + "'");
                    ex.printStackTrace();

                }
            }

    }

}
