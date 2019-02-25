package com.vistalogsparsertool;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {

    ReadProperties() {
        readProperties();
    }

    public static void main(String[] args) {
        readProperties();
    }

    public static Properties readProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            // System.out.println(prop.getProperty("nvista1"));
            // System.out.println(prop.getProperty("nvista2"));
            // System.out.println(prop.getProperty("nvista-batch1"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;

    }

    public static Properties readHungThreadProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("hungconfig.properties");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            // System.out.println(prop.getProperty("nvista1"));
            // System.out.println(prop.getProperty("nvista2"));
            // System.out.println(prop.getProperty("nvista-batch1"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;

    }

}
