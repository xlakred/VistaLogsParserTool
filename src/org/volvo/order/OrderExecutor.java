package org.volvo.order;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class OrderExecutor {

    public Map getDuplicateOrder(Set<String> userDetails, File logFile) {

        
        ExecutorService executor = Executors.newFixedThreadPool(100);

        Map<String, List> orderMapp = new HashMap<String, List>();
        //create a list to hold the Future object associated with Callable
        Map<String, Future<List>> map = new HashMap<String, Future<List>>();
        long time_1 = System.currentTimeMillis();
        for (String user : userDetails) {

            if (user != null && !user.trim().isEmpty()) {

                Callable<List> callable = new FindDuplicateOrder(user, logFile);
                Future<List> future = executor.submit(callable);
                map.put(user, future);
            }

        }


        for (Map.Entry<String, Future<List>> entry : map.entrySet()) {
            try {

                if (null != entry.getValue() && entry.getValue().get() != null) {
                    orderMapp.put(entry.getKey(), entry.getValue().get());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //shut down the executor service now
        executor.shutdown();
        long time_2 = System.currentTimeMillis();
        long difference = time_1 - time_2;
        // System.out.println("Time taken to Complete " + TimeUnit.MILLISECONDS.toSeconds(difference) + "milliseconds");

        return orderMapp;

    }

}
