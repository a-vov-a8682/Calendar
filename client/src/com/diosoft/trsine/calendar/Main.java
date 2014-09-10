package com.diosoft.trsine.calendar;


import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        DataStore dataStore = new DataStore();
        CalendarService calendarService = new CalendarServiceImpl(dataStore);
        List<String> emails0 = new ArrayList<String>();
        emails0.add("111@aaa.com");
        emails0.add("222@bbb.com");
        emails0.add("333@ccc.com");
        emails0.add("444@ddd.com");
        List<String> emails1 = new ArrayList<String>();
        emails1.add("555@eee.com");
        emails1.add("222@bbb.com");
        emails1.add("567@efg.com");

        calendarService.addEvent(new Event.Builder()
                .id(UUID.randomUUID())
                .date(new GregorianCalendar(2014, 11, 12))
                .description("День рождения у Васи.")
                .title("Birthday")
                .attenders(emails0)
                .build());
        calendarService.addEvent(new Event.Builder()
                .id(UUID.randomUUID())
                .date(new GregorianCalendar(2014, 9, 10))
                .title("Party")
                .attenders(emails1)
                .build());

        //Searching by title
        List<Event> listSearchByTitle = calendarService.searchByTitle("Birthday");
        for (Event event : listSearchByTitle) {
            System.out.println(event.toString());
        }
        //Searching by date
        List<Event> listSearchByDate = calendarService.searchByDate(new GregorianCalendar(2014, 9, 10));
        for (Event event : listSearchByDate) {
            System.out.println(event.toString());
        }
    }
}
