package org.volvo.order;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FileReader {

    public static void main(String[] args) throws InterruptedException {

        String fileName = "vista_servlet_metrics_2018-12-17.txt";
        fileProcess(fileName);

    }

    public static void fileProcess(String fileName) {
        Map userDetailsMap = new HashMap<String, List>();
        // File logFile = new File("C:\\lakshmikar\\nVista\Workspaces\Tools\VistaLogsParserTool\vista_servlet_metrics_2018-12-17.txt");
        // File logFile = new File("C:\\Users\\lmopuri\\Downloads\\vista_servlet_metrics_2018-08-14.txt");
        File logFile = new File(fileName);
        // vista_servlet_metrics_2018-08-14
        String findConfirmClick = "findstr  confirmClick " + logFile.getAbsolutePath();
        if (logFile.isFile() && logFile.exists()) {

            try {


                Process process = Runtime.getRuntime().exec(findConfirmClick);

                Set<String> userDetails = new HashSet<String>();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String s;
                while ((s = reader.readLine()) != null) {

                    // System.out.println("Line:" + s);
                    String[] splitted = s.split(";");
                    userDetails.add(splitted[6]);
                    // userDetails.add(s.substring(s.indexOf('[') + 1, s.indexOf(']')));
                }


                // System.out.println("I am looking into this file " + logFile.getAbsolutePath()
                // + " for duplicate order and found the possible total no.of Confirm Clicks are:" + userDetails.size());

                OrderExecutor executor = new OrderExecutor();
                // System.out.println("I am going look for duplicate orders: " + new Date());
                userDetailsMap = executor.getDuplicateOrder(userDetails, logFile);
                System.out.println("The duplicate orders details are below: " + new Date());
                System.out.println(userDetailsMap);

            } catch (IOException e) {

                e.printStackTrace();
            }

        }
    }

}
