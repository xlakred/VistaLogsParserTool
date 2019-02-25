package com.lk.thread;

import java.util.Timer;

/**
 *
 * @author lakshmikar .
 */

// Main class
public class SchedulerMain {
    public static void main(String args[]) throws InterruptedException {

        Timer time = new Timer(); // Instantiate Timer Object
        WASThread st = new WASThread(); // Instantiate SheduledTask class
        time.schedule(st, 0, 1000); // Create Repetitively task for every 1 secs

        // for demo only.
        // for (int i = 0; i <= 5; i++) {
        // System.out.println("Execution in Main Thread...." + i);
        // Thread.sleep(2000);
        // if (i == 5) {
        // System.out.println("Application Terminates");
        // System.exit(0);
        // }
        // }
    }
}