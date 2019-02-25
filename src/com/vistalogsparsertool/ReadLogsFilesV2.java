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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class ReadLogsFilesV2 {

    static String[] logPathArray = new String[11];
    private static BufferedReader bufferedReader;
    static List<BeanClass> servletMetricsList = new ArrayList<BeanClass>();
    static List<BeanClass> interfaceMetricsList = new ArrayList<BeanClass>();

    public static void main(String[] args) throws IOException, InvalidFormatException {

        // getLogsZonePath();
        File servletMetricsFile = new File("C:/nVista/mytools/VistaLogsParserTool/vista_servlet_metrics_july15.txt");
        File interfaceMetricsFile = new File("vista_interface_metrics.txt");
        // if file doesnt exists, then create it
        if (!servletMetricsFile.exists()) {
            servletMetricsFile.createNewFile();
        }
        // if file doesnt exists, then create it
        if (!interfaceMetricsFile.exists()) {
            interfaceMetricsFile.createNewFile();
        }

        FileWriter serlvetMetricFileWriter = new FileWriter(servletMetricsFile.getAbsoluteFile());
        BufferedWriter servletMetricBW = new BufferedWriter(serlvetMetricFileWriter);

        FileWriter interfaceMetricsFileWriter = new FileWriter(interfaceMetricsFile.getAbsoluteFile());
        BufferedWriter interfaceMetricBW = new BufferedWriter(interfaceMetricsFileWriter);

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

        logPathArray[0] = ReadProperties.readProperties().getProperty("nvista1");
        logPathArray[1] = ReadProperties.readProperties().getProperty("nvista2");
        logPathArray[2] = ReadProperties.readProperties().getProperty("nvista-batch1");
        logPathArray[3] = ReadProperties.readProperties().getProperty("nvista-batch2");
        System.out.println("I am going to search in logs.....:" + new Date());
        for (int i = 0; i < 4; i++) {

            String path = logPathArray[i];
            System.out.println("I am looking into ( " + path.split("=")[0] + ") node at :" + new Date());
            if (ReadProperties.readProperties().getProperty("vista_servlet_metrics").equalsIgnoreCase("Y"))
            readServletMetricLog(path, servletMetricBW);
            if (ReadProperties.readProperties().getProperty("vista_interface_metrics").equalsIgnoreCase("Y"))
            readInterfaceMetricLog(path, interfaceMetricBW);
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

    private static void readServletMetricLog(String path, BufferedWriter bw) throws IOException {

        String[] fileNameFromPath = path.split("=");
        String node = fileNameFromPath[0];

        final String sfFlag = ReadProperties.readProperties().getProperty("vista_servlet_metrics");

        final String fileString = ReadProperties.readProperties().getProperty("smfiles");
        final String fileSearchType = ReadProperties.readProperties().getProperty("smfilesSearchType");

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

                        if (line.contains(ReadProperties.readProperties().getProperty("smSearchText"))) {
                            // System.out.println(node + " " + line);
                            // bw.write(line + "\n");

                            // String[] samples = { "2012-06-13 00:03:24,841 [GGONZALE] - 10.242.48.123 YV1 EO 0.6s /Vista-Web/welcome.do null" };

                            // String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] -
                            // (\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}) ([^ ]*) ([^ ]*) (.*?)s /Vista-Web/([^ ]*)(.*)$";
                            String[] samples = { "2012-06-13 00:03:24,841 [GGONZALE] - 10.242.48.123 YV1 EO 0.6s /Vista-Web/welcome.do null" };

                            String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] - (\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}) ([^ ]*) ([^ ]*) (.*?)s /Vista-Web/([^ ]*)(.*)$";

                            Pattern p = Pattern.compile(regex);
                            StringBuilder contents = new StringBuilder();
                            Matcher m = p.matcher(line);
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
                    System.out.println("yes");
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
                            String regex = "(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2}):(\\d{2}),(\\d{3}) \\[(.*)\\] - ([^ ]*), ([^ ]*), ([^ ]*), (.*?), (.*?), (.*?), (.*?), (.*?), (.*?), (.*?)$";

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
                                contents.append(Double.valueOf(resTime));
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


}
