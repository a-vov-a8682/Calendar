package com.diosoft.trsine.calendar;

import com.diosoft.trsine.calendar.common.Event;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class CalendarServiceImplTest {
    private CalendarServiceImpl calendarService;
    private DataStoreImpl dataStore;

    @Before
    public void setUp(){
        dataStore = mock(DataStoreImpl.class);
        calendarService = new CalendarServiceImpl(dataStore);
    }

    @Test
    public void testMockSearchByTitle() throws Exception{
        CalendarServiceImpl service = mock(CalendarServiceImpl.class);
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

        service.addEvent(e1);
        service.addEvent(e2);
        List<Event> expectedList = new ArrayList<Event>();
        expectedList.add(e1);

        when(service.searchByTitle(inputTitle)).thenReturn(expectedList);
    }
    @Test
    public void testMockSearchByTitle_withNullTitle() throws Exception{
        CalendarServiceImpl service = mock(CalendarServiceImpl.class);
        String title = null;

        service.addEvent(new Event.Builder()
                .id(UUID.randomUUID())
                .date(new GregorianCalendar(2014, 11, 12))
                .description("День рождения у Васи.")
                .title("Birthday")
                .build());
        service.addEvent(new Event.Builder()
                .id(UUID.randomUUID())
                .date(new GregorianCalendar(2014, 9, 10))
                .title("Party")
                .build());
        List<Event> expectedList = new ArrayList<Event>();

        when(service.searchByTitle(title)).thenReturn(expectedList);
        assertEquals(expectedList, service.searchByTitle(title));
    }
    @Test
    public void testMockSearchByTitle_withEmptyTitle() throws Exception{
        CalendarServiceImpl service = mock(CalendarServiceImpl.class);
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
        service.addEvent(e1);
        service.addEvent(e2);
        List<Event> expectedList = new ArrayList<Event>();
        when(service.searchByTitle(title)).thenReturn(expectedList);
        assertEquals(expectedList, service.searchByTitle(title));
    }

    @Test
    public void testMockSearchByDate() throws Exception{
        CalendarServiceImpl service = mock(CalendarServiceImpl.class);

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

        service.addEvent(e1);
        service.addEvent(e2);

        List<Event> expectedList = new ArrayList<Event>();
        expectedList.add(e2);
        when(service.searchByDate(date)).thenReturn(expectedList);
        assertEquals(expectedList, service.searchByDate(date));
    }
    @Test
    public void testMockSearchByDate_withNullDate() throws Exception{
        CalendarServiceImpl service = mock(CalendarServiceImpl.class);
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
        service.addEvent(e1);
        service.addEvent(e2);

        List<Event> expectedList = new ArrayList<Event>();
        when(service.searchByDate(date)).thenReturn(expectedList);
        assertEquals(expectedList, service.searchByDate(date));
    }

    @Test
    public void testMockCreateEvent() throws Exception{
        CalendarService service = mock(CalendarService.class);

        String description = "День рождения у Васи.";
        List<String> emails = new ArrayList<String>();
        emails.add("111@aaa.com");
        emails.add("222@bbb.com");
        emails.add("333@ccc.com");
        emails.add("444@ddd.com");

        Event expectedEvent = new Event.Builder()
                .attenders(emails)
                .description(description)
                .build();

        when(service.createEvent(description, emails)).thenReturn(expectedEvent);
        assertEquals(expectedEvent, service.createEvent(description, emails));
    }
    @Test
    public void testMockCreateEvent_withNullDescriptions() throws Exception{
        CalendarService service = mock(CalendarService.class);

        String description = null;
        List<String> emails = new ArrayList<String>();
        emails.add("111@aaa.com");
        emails.add("222@bbb.com");
        emails.add("333@ccc.com");
        emails.add("444@ddd.com");

        Event expectedValue = service.createEvent(description, emails);

        when(service.createEvent(description, emails)).thenReturn(expectedValue);
        assertEquals(expectedValue, service.createEvent(description, emails));

    }
    @Test
    public void testMockCreateEvent_withEmptyDescriptions() throws Exception{
        CalendarService service = mock(CalendarService.class);
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

        when(service.createEvent(description, emails)).thenReturn(expectedEvent);
        Event returnValue = service.createEvent(description, emails);
        assertEquals(expectedEvent, returnValue);
    }
    @Test
    public void testMockCreateEvent_withNullEmailsList() throws Exception{
        CalendarService service = mock(CalendarService.class);
        String description = "День рождения у Васи.";
        List<String> emails = null;

        Event expectedValue = new Event.Builder()
                .attenders(emails)
                .description(description)
                .build();

        when(service.createEvent(description,emails)).thenReturn(expectedValue);
        Event returnValue = service.createEvent(description,emails);

        assertEquals(expectedValue, returnValue);
    }
    @Test
    public void testMockCreateEvent_withEmptyEmailsList() throws Exception{
        CalendarService service = mock(CalendarService.class);
        String description = "День рождения у Васи.";
        List<String> emails = new ArrayList<String>();
        Event expectedEvent = service.createEvent(description, emails);

        when(service.createEvent(description, emails)).thenReturn(expectedEvent);
        assertEquals(expectedEvent, service.createEvent(description, emails));
    }





// JUnit tests

  /*  @Test
    public void testSearchByTitle() throws Exception{
        DataStoreImpl ds = new DataStoreImpl();
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
        DataStoreImpl ds = new DataStoreImpl();
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
        DataStoreImpl ds = new DataStoreImpl();
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
        DataStoreImpl ds = new DataStoreImpl();
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
        DataStoreImpl ds = new DataStoreImpl();
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
        DataStoreImpl dataStore = new DataStoreImpl();
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
    @Test
    public void testCreateEvent_withNullDescriptions() throws Exception{
        DataStoreImpl dataStore = new DataStoreImpl();
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
    @Test
    public void testCreateEvent_withEmptyDescriptions() throws Exception{
        DataStoreImpl dataStore = new DataStoreImpl();
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
        DataStoreImpl dataStore = new DataStoreImpl();
        CalendarServiceImpl calendarService = new CalendarServiceImpl(dataStore);

        String description = "День рождения у Васи.";
        List<String> emails = null;

        Event expectedEvent = new Event.Builder()
                .description(description)
                .attenders(emails)
                .build();
        Event returnValue = calendarService.createEvent(description, emails);

        assertEquals(expectedEvent, returnValue);
    }
    @Test
    public void testCreateEvent_withEmptyEmailsList() throws Exception{
        DataStoreImpl dataStore = new DataStoreImpl();
        CalendarServiceImpl calendarService = new CalendarServiceImpl(dataStore);

        String description = "День рождения у Васи.";
        List<String> emails = new ArrayList<String>();

        Event returnValue = calendarService.createEvent(description, emails);
        Event expectedEvent = new Event.Builder()
                .description(description)
                .attenders(emails)
                .build();

        assertEquals(expectedEvent, returnValue);
    }*/
}