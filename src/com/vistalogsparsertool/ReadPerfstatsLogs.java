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

public class ReadPerfstatsLogs {
    static String[] logPathArray = new String[11];

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        process();
    }

    public static void process() throws IOException {
        // getLogsZonePath();

        File servletMetricsFile = new File(ReadProperties.readProperties().getProperty("waslogExportFileName"));

        if (ReadProperties.readProperties().getProperty("vista_waslog_metrics").equalsIgnoreCase("Y") && !servletMetricsFile.exists())
            // if file doesnt exists, then create it
            servletMetricsFile.createNewFile();

        FileWriter serlvetMetricFileWriter = new FileWriter(servletMetricsFile.getAbsoluteFile());
        BufferedWriter servletMetricBW = new BufferedWriter(serlvetMetricFileWriter);

        StringBuilder commonContent = new StringBuilder();
        commonContent.append("Node");
        commonContent.append(";");
        commonContent.append("Date");
        commonContent.append(";");
        commonContent.append("Hour");
        commonContent.append(";");
        commonContent.append("Minute");
        commonContent.append(";");
        commonContent.append("Seconds");
        commonContent.append(";");
        commonContent.append("mSec");
        servletMetricBW.write(
            "DATE NODE TIME  Web-Active PoolSize   ActiveSessions  LiveSessions    ORBThreads  ORBPoolSize DBPoolSize  DBConnections   AllocHeap(MB) UsedHeap(MB) JVMCPU(%)");
        servletMetricBW.write((System.getProperty("line.separator")));
        logPathArray[0] = ReadProperties.readProperties().getProperty("perfstats-nvista");
        logPathArray[1] = ReadProperties.readProperties().getProperty("perfstats-nvista-batch");

        System.out.println("I am going to search in logs.....:" + new Date());
        for (int i = 0; i < 2; i++) {

            String path = logPathArray[i];
            System.out.println("I am looking into ( " + path.split("=")[0] + ") node at :" + new Date());

            if (ReadProperties.readProperties().getProperty("vista_waslog_metrics").equalsIgnoreCase("Y"))
                readPerfstatstMetricLog(path, servletMetricBW);


        }
        System.out.println("I am writing into CSV file at :" + new Date());
        servletMetricBW.close();

        System.out.println("CSV file export done");

        System.out.println("Completed at:" + new Date());
    }

    private static void readPerfstatstMetricLog(String path, BufferedWriter bw) {

        String[] fileNameFromPath = path.split("=");
        String node = fileNameFromPath[0];

        final String sfFlag = ReadProperties.readProperties().getProperty("vista_waslog_metrics");

        final String fileString = ReadProperties.readProperties().getProperty("waslogfiles");
        final String fileSearchType = ReadProperties.readProperties().getProperty("waslogfilesSearchType");

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
                String ffname = file.getName().split("_")[0];
                String ffdate = file.getName().split("_")[2].substring(0, (file.getName().split("_")[2]).length() - 4);
                String line = null;
                StringBuilder contents = new StringBuilder();
                try {
                    // FileReader reads text files in the default encoding.
                    FileReader fileReader = new FileReader(fileName);

                    // Always wrap FileReader in BufferedReader.
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    while ((line = bufferedReader.readLine()) != null) {
                        if (!line.contains("TIME") && line.contains("[")) {
                            contents.append(ffdate + " ");
                            contents.append(ffname + " ");
                            contents.append(line.replaceAll("[\\[\\]]", ""));
                        contents.append(System.getProperty("line.separator"));
                        }

                    }
                    bw.write(contents.toString());


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
