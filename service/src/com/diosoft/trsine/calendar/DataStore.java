package com.diosoft.trsine.calendar;

import com.diosoft.trsine.calendar.common.Event;

import java.util.List;
import java.util.UUID;

public interface DataStore{

    void addEvent (Event event);
    void remove (UUID id);
    Event getEvent (UUID id);
    void editEvent (Event oldEvent, Event newEvent);
    List<Event> getEventBySubTitle (String subTitle);
    void initEventsFromXml ();
    }