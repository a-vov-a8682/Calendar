package com.diosoft.trsine.calendar;

import org.junit.Test;

import java.util.*;

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
    public void testSearchByTitle_withNullTitle() throws Exception{
        DataStore ds = new DataStore();
        CalendarServiceImpl csi = new CalendarServiceImpl(ds);

        String title = null;

        csi.addEvent(new Event.Builder()
                .id(UUID.randomUUID())
                .date(new GregorianCalendar(2014, 11, 12))
                .description("День рождения у Васи.")
                .title("Birthday")
                .build());
        csi.addEvent(new Event.Builder()
                .id(UUID.randomUUID())
                .date(new GregorianCalendar(2014, 9, 10))
                .title("Party")
                .build());

        List<Event> expectedList = new ArrayList<Event>();

        List<Event> returnValue = csi.searchByTitle(title);
        assertEquals(expectedList, returnValue);
    }
    @Test
    public void testSearchByTitle_withEmptyTitle() throws Exception{
        DataStore ds = new DataStore();
        CalendarServiceImpl csi = new CalendarServiceImpl(ds);
        String title = "";

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

        List<Event> returnValue = csi.searchByTitle(title);
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
    @Test
    public void testSearchByDate_withNullDate() throws Exception{
        DataStore ds = new DataStore();
        CalendarServiceImpl csi = new CalendarServiceImpl(ds);
        GregorianCalendar date = null;

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

        List<Event> returnValue = csi.searchByDate(date);

        assertEquals(expectedList, returnValue);
    }


    @Test
    public void testCreateEvent() throws Exception{
        DataStore dataStore = new DataStore();
        CalendarServiceImpl calendarService = new CalendarServiceImpl(dataStore);

        String description = "День рождения у Васи.";
        List<String> emails = new ArrayList<String>();
        emails.add("111@aaa.com");
        emails.add("222@bbb.com");
        emails.add("333@ccc.com");
        emails.add("444@ddd.com");

        Event returnValue = calendarService.createEvent(description, emails);
        Event expectedEvent = new Event.Builder()
                .id(returnValue.getId())
                .date(returnValue.getDate())
                .description(description)
                .title("")
                .attenders(emails)
                .build();
        assertEquals(expectedEvent, returnValue);
    }
    @Test (expected = NoSuchElementException.class)
    public void testCreateEvent_withNullDescriptions() throws Exception{
        DataStore dataStore = new DataStore();
        CalendarServiceImpl calendarService = new CalendarServiceImpl(dataStore);

        String description = null;
        List<String> emails = new ArrayList<String>();
        emails.add("111@aaa.com");
        emails.add("222@bbb.com");
        emails.add("333@ccc.com");
        emails.add("444@ddd.com");

        Event expectedEvent = new Event.Builder()
                .description(description)
                .attenders(emails)
                .build();

        Event returnValue = calendarService.createEvent(description, emails);
        assertEquals(expectedEvent, returnValue);
    }
    @Test (expected = NoSuchElementException.class)
    public void testCreateEvent_withEmptyDescriptions() throws Exception{
        DataStore dataStore = new DataStore();
        CalendarServiceImpl calendarService = new CalendarServiceImpl(dataStore);

        String description = "";
        List<String> emails = new ArrayList<String>();
        emails.add("111@aaa.com");
        emails.add("222@bbb.com");
        emails.add("333@ccc.com");
        emails.add("444@ddd.com");

        Event expectedEvent = new Event.Builder()
                .description(description)
                .attenders(emails)
                .build();

        Event returnValue = calendarService.createEvent(description, emails);
        assertEquals(expectedEvent, returnValue);
    }
    @Test
    public void testCreateEvent_withNullEmailsList() throws Exception{
        DataStore dataStore = new DataStore();
        CalendarServiceImpl calendarService = new CalendarServiceImpl(dataStore);

        String description = "День рождения у Васи.";
        List<String> emails = null;

        Event returnValue = calendarService.createEvent(description, emails);
        Event expectedEvent = new Event.Builder()
                .id(returnValue.getId())
                .attenders(new ArrayList<String>())
                .title("")
                .date(returnValue.getDate())
                .description(description)
                .build();
        assertEquals(expectedEvent, returnValue);
    }
    @Test
    public void testCreateEvent_withEmptyEmailsList() throws Exception{
        DataStore dataStore = new DataStore();
        CalendarServiceImpl calendarService = new CalendarServiceImpl(dataStore);

        String description = "День рождения у Васи.";
        List<String> emails = new ArrayList<String>();

        Event returnValue = calendarService.createEvent(description, emails);
        Event expectedEvent = new Event.Builder()
                .id(returnValue.getId())
                .attenders(new ArrayList<String>())
                .title("")
                .date(returnValue.getDate())
                .description(description)
                .build();
        assertEquals(expectedEvent, returnValue);
    }
}