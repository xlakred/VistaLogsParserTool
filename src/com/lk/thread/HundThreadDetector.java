package com.lk.thread;

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
import java.util.LinkedList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.vistalogsparsertool.ReadProperties;

public class HundThreadDetector {

    static String[] logPathArray = new String[11];
    static LinkedList<String> nvista1 = new LinkedList<String>();
    static LinkedList<String> nvista2 = new LinkedList<String>();
    static LinkedList<String> nvista3 = new LinkedList<String>();
    static LinkedList<String> nvista4 = new LinkedList<String>();
    static LinkedList<String> nvistabatch1 = new LinkedList<String>();
    static LinkedList<String> nvistabatch2 = new LinkedList<String>();

    public static void main(String[] args) throws IOException, InvalidFormatException {
        process();
    }


    public static void process() throws IOException {
        File servletMetricsFile = new File(ReadProperties.readHungThreadProperties().getProperty("soExportFileName"));

        // if file doesnt exists, then create it
        if (!servletMetricsFile.exists()) {
            servletMetricsFile.createNewFile();
        }

        FileWriter serlvetMetricFileWriter = new FileWriter(servletMetricsFile.getAbsoluteFile());
        BufferedWriter servletMetricBW = new BufferedWriter(serlvetMetricFileWriter);


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
        contentsServletMetric.append("ThreadCount");
        contentsServletMetric.append(System.getProperty("line.separator"));
        servletMetricBW.write(contentsServletMetric.toString());

        logPathArray[0] = ReadProperties.readHungThreadProperties().getProperty("nvista1");
        logPathArray[1] = ReadProperties.readHungThreadProperties().getProperty("nvista2");
        logPathArray[2] = ReadProperties.readHungThreadProperties().getProperty("nvista-batch1");
        logPathArray[3] = ReadProperties.readHungThreadProperties().getProperty("nvista-batch2");
        logPathArray[4] = ReadProperties.readHungThreadProperties().getProperty("nvista3");
        logPathArray[5] = ReadProperties.readHungThreadProperties().getProperty("nvista4");
        System.out.println("I am going to search in logs.....:" + new Date());
        for (int i = 0; i < 6; i++) {

            String path = logPathArray[i];
            System.out.println("I am looking into ( " + path.split("=")[0] + ") node at :" + new Date());
            if (ReadProperties.readProperties().getProperty("vista_servlet_metrics").equalsIgnoreCase("Y"))
            readServletMetricLog(path, servletMetricBW);
        }
        System.out.println("I am writing into CSV file at :" + new Date());
        servletMetricBW.close();
        System.out.println("================HUNG THREADS REPORT======================");
        System.out.print("NVISTA1: ");
        if (nvista1.size() != 0) {
            System.out.println(nvista1.getLast());
        } else {
            System.out.println("0");
        }
        System.out.print("NVISTA2: ");
        if (nvista2.size() != 0) {
            System.out.println(nvista2.getLast());
        } else {
            System.out.println("0");
        }
        System.out.print("NVISTA3: ");
        if (nvista3.size() != 0) {
            System.out.println(nvista3.getLast());
        } else {
            System.out.println("0");
        }
        System.out.print("NVISTA4: ");
        if (nvista4.size() != 0) {
            System.out.println(nvista4.getLast());
        } else {
            System.out.println("0");
        }
        System.out.print("NVISTA-BATCH1: ");
        if (nvistabatch1.size() != 0) {
            System.out.println(nvistabatch1.getLast());
        } else {
            System.out.println("0");
        }
        System.out.print("NVISTA-BATCH2: ");
        if (nvistabatch2.size() != 0) {
            System.out.println(nvistabatch2.getLast());
        } else {
            System.out.println("0");
        }

        System.out.println("================END              ======================");
        System.out.println("Completed at:" + new Date());
    }


    private static void readServletMetricLog(String path, BufferedWriter bw) throws IOException {

        String[] fileNameFromPath = path.split("=");
        String node = fileNameFromPath[0];

        final String sfFlag = ReadProperties.readHungThreadProperties().getProperty("systemout");

        final String fileString = ReadProperties.readHungThreadProperties().getProperty("sofiles");
        final String fileSearchType = ReadProperties.readHungThreadProperties().getProperty("sofilesSearchType");

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

                        if (line.contains(ReadProperties.readHungThreadProperties().getProperty("soSearchText")) || line.contains(
                            ReadProperties.readHungThreadProperties().getProperty("soSearchText1"))) {
                           
                            StringBuilder contents = new StringBuilder();
                                contents.append(node);
                                contents.append(";");
                            contents.append(":");
                                contents.append(";");
                            contents.append(line);
                                contents.append(System.getProperty("line.separator"));
                                bw.write(contents.toString());
                            // System.out.println(line);
                            if(node.equalsIgnoreCase("nvista1")){
                            nvista1.add(line);
                            } else if (node.equalsIgnoreCase("nvista2")) {
                                nvista2.add(line);
                            } else if (node.equalsIgnoreCase("nvista3")) {
                                nvista3.add(line);
                            } else if (node.equalsIgnoreCase("nvista4")) {
                                nvista4.add(line);
                            } else if (node.equalsIgnoreCase("nvista-batch1")) {
                                nvistabatch1.add(line);
                            } else if (node.equalsIgnoreCase("nvista-batch2")) {
                                nvistabatch2.add(line);
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


}
