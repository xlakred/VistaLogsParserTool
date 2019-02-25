package org.volvo.order;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class FindDuplicateOrder implements Callable<List> {

    private String userName;
    private File logFilePath;

    FindDuplicateOrder(String user, File logFile) {
        this.userName = user;
        this.logFilePath = logFile;
    }

    private List<String> getDuplicateOrderDetails(String user, File logFile) {

        List<String> duplicateOrderDetails = new ArrayList<String>();
        // user = "R-ARNING";
        String findConfirmClick = "findstr  " + user + " " + logFile.getAbsolutePath();

        Process process;
        try {

            process = Runtime.getRuntime().exec(findConfirmClick);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String currentLine, previosLine = null;
            while ((currentLine = reader.readLine()) != null) {

                String currentMethod = getMethodClick(currentLine);

                if (!currentMethod.isEmpty()) {

                    if (currentMethod.trim().equalsIgnoreCase("confirmClick")) {

                        if (previosLine != null) {

                            String prvMethod = getMethodClick(previosLine);

                            if (prvMethod.trim().equalsIgnoreCase("confirmClick")) {
                                duplicateOrderDetails.add(getDateTime(currentLine));
                                // duplicateOrderDetails.add(currentLine);
                            }

                        }
                    }
                }
                previosLine = currentLine;

            }

        } catch (IOException e) {

            e.printStackTrace();
        }

        return (duplicateOrderDetails.isEmpty() ? null : duplicateOrderDetails);

    }

    private String getDateTime(String currentLine) {
        String ret = "";


        // if (currentLine.contains("maintainOrder.do ")) {
        // ret = currentLine.substring(0, currentLine.indexOf("["));
        // }

        String[] splitter = currentLine.split(";");
        // 11,12
        if (splitter[11] != null && splitter[11].trim().equalsIgnoreCase("maintainOrder.do")) {

            ret = splitter[1] + ":" + splitter[2] + ":" + splitter[3] + ":" + splitter[4] + ":" + splitter[5];
        }
        return ret;
    }

    private String getMethodClick(String s) {

        String ret = "";

        // if (s.contains("maintainOrder.do ")) {
        // ret = s.substring(s.indexOf("maintainOrder.do ") + "maintainOrder.do ".length());
        // // System.out.println("Get Click:" + ret);
        // }

        String[] splitter = s.split(";");
        // 11,12
        if (splitter[11] != null && splitter[11].trim().equalsIgnoreCase("maintainOrder.do")) {

            ret = splitter[12];
        }

        return ret;
    }

    @Override
    public List call() throws Exception {

        return getDuplicateOrderDetails(userName, logFilePath);
    }

}
