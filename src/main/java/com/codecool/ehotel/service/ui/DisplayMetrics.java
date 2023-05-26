package com.codecool.ehotel.service.ui;

import com.codecool.ehotel.model.Guest;

import java.util.List;

public class DisplayMetrics {
    public void displayMetrics(int unhappyGuests, int wasteCost) {
        String format = "| %-30s | %-30s |%n";
        System.out.printf(format, "******************************", "******************************");
        System.out.printf(format, "UNHAPPY GUESTS", "COST OF WASTED FOOD");
        System.out.printf(format, "******************************", "******************************");
        System.out.printf(format, unhappyGuests, wasteCost);
        System.out.printf(format, "******************************", "******************************");
        System.out.println("");
        System.out.println("");
    }
    public void displayGuests(List<Guest> guests){
        String line = "******************************";
        String format = "| %-30s | %-30s | %-30s | %-30s |%n";
        System.out.printf(format, line, line, line, line );
        System.out.printf(format,"NAME", "RACE", "CHECK-IN", "CHECK-OUT");
        System.out.printf(format, line, line, line, line );
        for (Guest guest : guests){
            System.out.printf(format, guest.guestName(), guest.guestType(), guest.checkIn(), guest.checkOut());
        }
        System.out.printf(format, line, line, line, line );
        System.out.println("");
        System.out.println("");
    }
}
