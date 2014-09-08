package com.diosoft.trsine.calendar;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public interface CalendarService {

    void addEvent(Event event);
    void createEvent(String description, String email);
    void remove(UUID id);
  //  List<Event> searchByTitle (String title);
  //  List<Event> searchByDay (GregorianCalendar day);
}
