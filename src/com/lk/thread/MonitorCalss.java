package com.lk.thread;

import java.io.File;

import com.vistalogsparsertool.ReadProperties;

public class MonitorCalss {

    File vistawaslogFile = new File(ReadProperties.readProperties().getProperty("vista_waslog_metrics"));

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
