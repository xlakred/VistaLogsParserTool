package com.lk.automation;

import java.util.ArrayList;

/**
 * @author Vinod Kumar
 *
 */
public class TabCharacterSample {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
         
        //String name2 = "Vinod"+"\n"+"Kumar"+"\n"+"Nair";
        //System.out.println("Print String2 "+name2);
         
       // String name1 = "Vinod"+"\t"+"Kumar"+"\t"+"Nair";
        ArrayList<String> al = new ArrayList<String>();
        al.add("20");
        al.add("30");
        al.add("112");
        al.add("140");
        al.add("155");
        al.add("160");
        al.add("165");

        String s = "35";

        if (al.contains(s)) {
            System.out.println("Yes");
        }
     
    }

 
}