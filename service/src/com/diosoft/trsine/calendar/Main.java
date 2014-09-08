package com.diosoft.trsine.calendar;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        List<String> emails = new ArrayList<String>();
        emails.add("111@.com");
        emails.add("222@.com");
        emails.add("333@.com");
        emails.add("444@.com");
        emails.add("555@.com");


        CalendarServiceImpl csi = new CalendarServiceImpl();
        Event party = new Event.Builder()
                .attenders(emails)
                .id(UUID.randomUUID())
                .title("Party")
                .date(new GregorianCalendar(2014, 11, 3))
                .build();
        Event birthday = new Event.Builder()
                .attenders(emails)
                .id(UUID.randomUUID())
                .title("Birthday")
                .date(new GregorianCalendar(2014, 5,10))
                .build();

        csi.addEvent(party);
        csi.addEvent(birthday);



        System.out.println();

    }
}
