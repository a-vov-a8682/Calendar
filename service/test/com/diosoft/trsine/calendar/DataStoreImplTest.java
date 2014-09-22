package com.diosoft.trsine.calendar;

import com.diosoft.trsine.calendar.common.Event;
import org.junit.Before;
import org.junit.Test;
import java.util.GregorianCalendar;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class DataStoreImplTest {
    private DataStoreImpl dataStore;
    private CalendarServiceImpl calendarService;

    @Before
    public void setUp() throws Exception{
        dataStore = mock(DataStoreImpl.class);
        calendarService = new CalendarServiceImpl(dataStore);
    }

    @Test
    public void testAddEvent() throws Exception {
        Event event = new Event.Builder()
                .title("Christmas")
                .startTime(new GregorianCalendar(2014, 11, 23))
                .build();

        calendarService.addEvent(event);
        verify(dataStore).addEvent(event);
    }
    @Test
    public void testRemove() throws Exception {
        UUID id = UUID.randomUUID();

        calendarService.remove(id);
        verify(dataStore).remove(id);
    }
}