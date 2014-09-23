package com.diosoft.trsine.calendar;

import com.diosoft.trsine.calendar.common.Event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class Main {

    public static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) throws RemoteException{

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        logger.info("Service started");

        CalendarService service = (CalendarService) context.getBean("calendarService");

        List<String> emails = Arrays.asList("111@aaa.com", "222@aaa.com", "333@aaa.com");
        String searchStr = "Bi";
        Event event = new Event.Builder()
                .title("Birthday")
                .id(UUID.randomUUID())
                .description("День рождения Васи")
                .startTime(new GregorianCalendar(2014, 06, 12, 12, 30))
                .endTime(new GregorianCalendar(2014, 06, 12, 13, 30))
                .attenders(emails)
                .build();

        service.addEvent(event);
        List<Event> eventList = service.getEventBySubTitle(searchStr);
        for (Event event1 : eventList) {
            System.out.println(event1);
        }


    }
}
