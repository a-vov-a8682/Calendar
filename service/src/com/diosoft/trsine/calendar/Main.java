package com.diosoft.trsine.calendar;

import com.diosoft.trsine.calendar.common.Event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.bind.Unmarshaller;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class Main {

    public static final Logger logger = Logger.getAnonymousLogger();

    public static void main(String[] args) throws RemoteException, InterruptedException {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        logger.info("Service started");

        CalendarService service = (CalendarService) context.getBean("calendarService");

        Unmarshaller um = context.createUnmarshaller();
        Bookstore bookstore2 = (Bookstore) um.unmarshal(new FileReader(BOOKSTORE_XML));
        ArrayList<Book> list = bookstore2.getBooksList();
        for (Book book : list) {
            System.out.println("Book: " + book.getName() + " from "
                    + book.getAuthor());
        }

    }
}
