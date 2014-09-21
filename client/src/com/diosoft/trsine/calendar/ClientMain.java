package com.diosoft.trsine.calendar;


import com.diosoft.trsine.calendar.common.Event;
import com.diosoft.trsine.calendar.common.EventType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;
import java.util.*;
import java.util.logging.Logger;

public class ClientMain {

    public static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) throws RemoteException {

        ApplicationContext context = new ClassPathXmlApplicationContext("clientApplicationContext.xml");
        CalendarService service = (CalendarService) context.getBean("calendarService");

        List<String> emails0 = new ArrayList<String>();
        emails0.add("111@aaa.com");
        emails0.add("222@bbb.com");
        emails0.add("333@ccc.com");
        emails0.add("444@ddd.com");

        List<String> emails1 = new ArrayList<String>();
        emails1.add("555@eee.com");
        emails1.add("222@bbb.com");
        emails1.add("567@efg.com");

        service.addEvent(new Event.Builder()
                .id(UUID.randomUUID())
                .startTime(new GregorianCalendar(2014, 11, 12, 10, 15))
                .endTime(new GregorianCalendar(2014, 11, 12, 22, 15))
                .description("День рождения у Васи.")
                .title("Birthday")
                .attenders(emails0)
                .build());
        service.addEvent(new Event.Builder()
                .eventType(EventType.ALLDAY)
                .id(UUID.randomUUID())
                .startTime(new GregorianCalendar(2014, 9, 10, 12, 00))
                .endTime(new GregorianCalendar(2014, 9, 10, 12, 30))
                .title("Party")
                .attenders(emails1)
                .build());

        //Searching by title
        List<Event> listSearchByTitle = service.searchByTitle("Birthday");
        for (Event event : listSearchByTitle) {
            System.out.println(event.toString());
        }

        //Searching by date
        List<Event> listSearchByDate = service.searchByDate(new GregorianCalendar(2014, 9, 10));
        for (Event event : listSearchByDate) {
            System.out.println(event.toString());
        }
    }
}