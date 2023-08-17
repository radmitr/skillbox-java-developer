package com.company;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

// Task 1.6
public class Main {

    public static void main(String[] args) {
//	    DateFormat format = new SimpleDateFormat("hh:mm a MM/dd/yyyy"); // usa
	    DateFormat format = new SimpleDateFormat("hh:mm dd/MM/yyyy"); // ru
	    Date date = new Date();
        System.out.println(format.format(date));
    }
}
