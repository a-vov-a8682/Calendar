package com.diosoft.trsine.calendar;

import java.util.*;

public interface CalendarService {

    void addEvent (Event event);
    void remove (UUID id);
    Event createEvent (String description, List<String> emails);
    List<Event> searchByTitle (String title);
    List<Event> searchByDate (GregorianCalendar date);
}
