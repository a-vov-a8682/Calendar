package com.diosoft.trsine.calendar;

import org.junit.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class CalendarServiceImplTest {

    @Test
    public void testSearchByTitle() throws Exception{
        DataStore ds = new DataStore();
        CalendarServiceImpl csi = new CalendarServiceImpl(ds);

        Event e1 = new Event.Builder()
                .id(UUID.randomUUID())
                .date(new GregorianCalendar(2014, 11, 12))
                .description("День рождения у Васи.")
                .title("Birthday")
                .build();
        Event e2 = new Event.Builder()
                .id(UUID.randomUUID())
                .date(new GregorianCalendar(2014, 9, 10))
                .title("Party")
                .build();

        csi.addEvent(e1);
        csi.addEvent(e2);

        List<Event> expectedList = new ArrayList<Event>();
        expectedList.add(e1);

        List<Event> returnValue = csi.searchByTitle("Birthday");
        assertEquals(expectedList, returnValue);
    }


    @Test
    public void testSearchByDate() throws Exception{
        DataStore ds = new DataStore();
        CalendarServiceImpl csi = new CalendarServiceImpl(ds);

        Event e1 = new Event.Builder()
                .id(UUID.randomUUID())
                .date(new GregorianCalendar(2014, 11, 12))
                .description("День рождения у Васи.")
                .title("Birthday")
                .build();
        Event e2 = new Event.Builder()
                .id(UUID.randomUUID())
                .date(new GregorianCalendar(2014, 9, 10))
                .title("Party")
                .build();
        csi.addEvent(e1);
        csi.addEvent(e2);

        List<Event> expectedList = new ArrayList<Event>();
        expectedList.add(e2);

        List<Event> returnValue = csi.searchByDate(new GregorianCalendar(2014, 9, 10));

        assertEquals(expectedList, returnValue);
    }


}