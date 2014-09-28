package com.diosoft.trsine.calendar;

import com.diosoft.trsine.calendar.common.Event;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DataStoreImplTest {

    @Test
    public void testSearchBySubTitle() throws Exception{
        List<String> emails = Arrays.asList("111@aaa.com", "222@aaa.com", "333@aaa.com");
        String searchStr = "Bi";
        Event event = new Event.Builder()
                .title("Birthday")
                .id(UUID.randomUUID())
                .description("День рождения Васи")
                .startTime(new GregorianCalendar(2014, 6, 12, 12, 30))
                .endTime(new GregorianCalendar(2014, 6, 12, 13, 30))
                .attenders(emails)
                .build();

        List<Event> expectedResult = new ArrayList<Event>();
        expectedResult.add(event);

        DataStoreImpl dataStore = new DataStoreImpl();
        CalendarService calendarService = new CalendarServiceImpl(dataStore);
        calendarService.addEvent(event);
        List<Event> returnValue = calendarService.getEventBySubTitle(searchStr);

        assertEquals(expectedResult, returnValue);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSearchBySubTitle_withNullSubString() throws Exception{
        List<String> emails = Arrays.asList("111@aaa.com", "222@aaa.com", "333@aaa.com");
        String searchStr = null;
        Event event = new Event.Builder()
                .title("Birthday")
                .id(UUID.randomUUID())
                .description("День рождения Васи")
                .startTime(new GregorianCalendar(2014, 6, 12, 12, 30))
                .endTime(new GregorianCalendar(2014, 6, 12, 13, 30))
                .attenders(emails)
                .build();

        List<Event> expectedResult = new ArrayList<Event>();
        expectedResult.add(event);

        DataStoreImpl dataStore = new DataStoreImpl();
        CalendarService calendarService = new CalendarServiceImpl(dataStore);
        calendarService.addEvent(event);
        List<Event> returnValue = calendarService.getEventBySubTitle(searchStr);

        assertEquals(expectedResult, returnValue);
    }
    @Test
    public void testSearchBySubTitle_withEmptySubString() throws Exception{
        List<String> emails = Arrays.asList("111@aaa.com", "222@aaa.com", "333@aaa.com");
        String searchStr = "";
        Event event = new Event.Builder()
                .title("Birthday")
                .id(UUID.randomUUID())
                .description("День рождения Васи")
                .startTime(new GregorianCalendar(2014, 6, 12, 12, 30))
                .endTime(new GregorianCalendar(2014, 6, 12, 13, 30))
                .attenders(emails)
                .build();

        List<Event> expectedResult = new ArrayList<Event>();

        DataStoreImpl dataStore = new DataStoreImpl();
        CalendarService calendarService = new CalendarServiceImpl(dataStore);
        calendarService.addEvent(event);
        List<Event> returnValue = calendarService.getEventBySubTitle(searchStr);

        assertEquals(expectedResult, returnValue);
    }
    @Test
    public void testAddEvent() throws Exception {
        DataStoreImpl dataStore = mock(DataStoreImpl.class);
        CalendarServiceImpl calendarService = new CalendarServiceImpl(dataStore);
        Event event = new Event.Builder()
                .title("Christmas")
                .startTime(new GregorianCalendar(2014, 11, 23))
                .build();

        calendarService.addEvent(event);
        verify(dataStore).addEvent(event);
    }
    @Test
    public void testRemove() throws Exception {
        DataStoreImpl dataStore = mock(DataStoreImpl.class);
        CalendarServiceImpl calendarService = new CalendarServiceImpl(dataStore);
        UUID id = UUID.randomUUID();

        calendarService.remove(id);
        verify(dataStore).remove(id);
    }
}