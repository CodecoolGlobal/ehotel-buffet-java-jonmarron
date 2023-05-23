package com.codecool.ehotel.service.ui;

public class DisplayMetrics {
    public void displayMetrics(int unhappyGuests, int wasteCost){
        String format = "| %-30s | %-30s |";
        System.out.printf(format, "******************************", "******************************");
        System.out.printf(format, "UNHAPPY GUESTS", "COST OF WASTED FOOD");
        System.out.printf(format, "******************************", "******************************");
        System.out.printf(format, unhappyGuests, wasteCost);
        System.out.printf(format, "******************************", "******************************");
    }
}
