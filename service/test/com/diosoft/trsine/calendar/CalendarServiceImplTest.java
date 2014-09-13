package com.diosoft.trsine.calendar;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class CalendarServiceImplTest {
    private CalendarServiceImpl calendarService;

    @Before
    public void setUp(){
        calendarService = mock(CalendarServiceImpl.class);
    }

    @Test
    public void testMockSearchByTitle() throws Exception{
        UUID id = UUID.randomUUID();
        GregorianCalendar inputDate = new GregorianCalendar(2014, 11, 12);
        String inputDescriptions = "День рождения у Васи.";
        String inputTitle = "Birthday";
        List<String> inputAttenders = new ArrayList<String>();

        Event e1 = new Event.Builder()
                .id(id)
                .date(inputDate)
                .description(inputDescriptions)
                .title(inputTitle)
                .attenders(inputAttenders)
                .build();


        Event e2 = new Event.Builder()
                .id(UUID.randomUUID())
                .date(new GregorianCalendar(2014, 9, 10))
                .title("Party")
                .build();

        calendarService.addEvent(e1);
        calendarService.addEvent(e2);
        List<Event> expectedList = new ArrayList<Event>();
        expectedList.add(e1);

        when(calendarService.searchByTitle(inputTitle)).thenReturn(expectedList);
        List<Event> returnValue = calendarService.searchByTitle(inputTitle);

        assertEquals(expectedList, returnValue);
    }
    @Test
    public void testMockSearchByTitle_withNullTitle() throws Exception{
        String title = null;

        calendarService.addEvent(new Event.Builder()
                .id(UUID.randomUUID())
                .date(new GregorianCalendar(2014, 11, 12))
                .description("День рождения у Васи.")
                .title("Birthday")
                .build());
        calendarService.addEvent(new Event.Builder()
                .id(UUID.randomUUID())
                .date(new GregorianCalendar(2014, 9, 10))
                .title("Party")
                .build());
        List<Event> expectedList = new ArrayList<Event>();

        when(calendarService.searchByTitle(title)).thenReturn(expectedList);
        List<Event> returnValue = calendarService.searchByTitle(title);
        assertEquals(expectedList, returnValue);
    }
    @Test
    public void testMockSearchByTitle_withEmptyTitle() throws Exception{
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
        calendarService.addEvent(e1);
        calendarService.addEvent(e2);
        List<Event> expectedList = new ArrayList<Event>();
        when(calendarService.searchByTitle(title)).thenReturn(expectedList);
        List<Event> returnValue = calendarService.searchByTitle(title);
        assertEquals(expectedList, returnValue);
    }

    @Test
    public void testMockSearchByDate() throws Exception{
        GregorianCalendar date = new GregorianCalendar(2014, 9, 10);
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

        calendarService.addEvent(e1);
        calendarService.addEvent(e2);

        List<Event> expectedList = new ArrayList<Event>();
        expectedList.add(e2);
        when(calendarService.searchByDate(date)).thenReturn(expectedList);
        List<Event> returnValue = calendarService.searchByDate(date);
        assertEquals(expectedList, returnValue);
    }
    @Test
    public void testMockSearchByDate_withNullDate() throws Exception{
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
        calendarService.addEvent(e1);
        calendarService.addEvent(e2);

        List<Event> expectedList = new ArrayList<Event>();
        when(calendarService.searchByDate(date)).thenReturn(expectedList);
        assertEquals(expectedList, calendarService.searchByDate(date));
    }

    @Test
    public void testMockCreateEvent() throws Exception{
        String description = "День рождения у Васи.";
        List<String> emails = new ArrayList<String>();
        emails.add("111@aaa.com");
        emails.add("222@bbb.com");
        emails.add("333@ccc.com");
        emails.add("444@ddd.com");

        Event expectedEvent = new Event.Builder()
                .title("")
                .attenders(emails)
                .date(new GregorianCalendar())
                .description(description)
                .id(null)
                .build();

        when(calendarService.createEvent(description, emails)).thenReturn(expectedEvent);
        assertEquals(expectedEvent, calendarService.createEvent(description, emails));
    }
    @Test
    public void testMockCreateEvent_withNullDescriptions() throws Exception{
        String description = null;
        List<String> emails = new ArrayList<String>();
        emails.add("111@aaa.com");
        emails.add("222@bbb.com");
        emails.add("333@ccc.com");
        emails.add("444@ddd.com");

        when(calendarService.createEvent(description, emails)).thenThrow(NoSuchElementException.class);
//        assertEquals(, calendarService.createEvent(description, emails));

    }
    @Test
    public void testMockCreateEvent_withEmptyDescriptions() throws Exception{
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

        when(calendarService.createEvent(description, emails)).thenReturn(expectedEvent);
        Event returnValue = calendarService.createEvent(description, emails);
        assertEquals(expectedEvent, returnValue);
    }
    @Test
    public void testMockCreateEvent_withNullEmailsList() throws Exception{
        String description = "День рождения у Васи.";
        List<String> emails = null;

        Event expectedValue = new Event.Builder()
                .id(null)
                .attenders(emails)
                .title(null)
                .date(null)
                .description(description)
                .build();

        when(calendarService.createEvent(description,emails)).thenReturn(expectedValue);
        Event returnValue = calendarService.createEvent(description,emails);

        assertEquals(expectedValue, returnValue);
    }
    @Test
    public void testMockCreateEvent_withEmptyEmailsList() throws Exception{
        String description = "День рождения у Васи.";
        List<String> emails = new ArrayList<String>();
        Event expectedEvent = new Event.Builder()
                .id(null)
                .attenders(new ArrayList<String>())
                .title(null)
                .date(null)
                .description(description)
                .build();
        when(calendarService.createEvent(description, emails)).thenReturn(expectedEvent);
        assertEquals(expectedEvent, calendarService.createEvent(description, emails));
    }
    @Test
    public void testMockAddEvent() throws Exception{
        Event e = new Event.Builder()
                .id(UUID.randomUUID())
                .date(new GregorianCalendar(2014, 11, 12))
                .description("День рождения у Васи.")
                .title("Birthday")
                .build();

        calendarService.addEvent(e);
        verify(calendarService).addEvent(e);
    }
    @Test
    public void testMockAddEvent_withNullEvent() throws Exception{
        Event e = null;
        calendarService.addEvent(e);
        verify(calendarService).addEvent(null);
    }





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

        Event expectedEvent = new Event.Builder()
                .description(description)
                .attenders(emails)
                .build();
        Event returnValue = calendarService.createEvent(description, emails);
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

        Event expectedEvent = new Event.Builder()
                .id(null)
                .attenders(new ArrayList<String>())
                .title(null)
                .date(null)
                .description(description)
                .build();
        Event returnValue = calendarService.createEvent(description, emails);

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
                .id(null)
                .attenders(new ArrayList<String>())
                .title(null)
                .date(null)
                .description(description)
                .build();
        assertEquals(expectedEvent, returnValue);
    }
}