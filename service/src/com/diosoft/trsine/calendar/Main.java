package com.diosoft.trsine.calendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;
import java.util.logging.Logger;

public class Main {

    public static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) throws RemoteException, InterruptedException {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        logger.info("Service started");

        CalendarService service = (CalendarService) context.getBean("calendarService");
    }
}
