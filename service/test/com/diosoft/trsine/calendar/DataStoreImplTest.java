package com.diosoft.trsine.calendar;

import com.diosoft.trsine.calendar.common.Event;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class DataStoreImplTest {
//    private DataStoreImpl dataStore;
//    private CalendarServiceImpl calendarService;

    @Test
    public void testSearchBySubTitle() throws Exception{
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

        List<Event> expectedResult = new ArrayList<Event>();
        expectedResult.add(event);

        DataStoreImpl dataStore = new DataStoreImpl();
        CalendarService calendarService = new CalendarServiceImpl(dataStore);
        calendarService.addEvent(event);
        List<Event> returnValue = calendarService.getEventBySubTitle(searchStr);

        assertEquals(expectedResult, returnValue);
    }





//    @Before
//    public void setUp() throws Exception{
//        dataStore = mock(DataStoreImpl.class);
//        calendarService = new CalendarServiceImpl(dataStore);
//    }
//
//    @Test
//    public void testAddEvent() throws Exception {
//        Event event = new Event.Builder()
//                .title("Christmas")
//                .startTime(new GregorianCalendar(2014, 11, 23))
//                .build();
//
//        calendarService.addEvent(event);
//        verify(dataStore).addEvent(event);
//    }
//    @Test
//    public void testRemove() throws Exception {
//        UUID id = UUID.randomUUID();
//
//        calendarService.remove(id);
//        verify(dataStore).remove(id);
//    }
}