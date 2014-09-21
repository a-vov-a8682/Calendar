package com.diosoft.trsine.calendar;

import com.diosoft.trsine.calendar.common.Event;

import java.util.UUID;

public interface DataStore{

    void addEvent(Event event);
    void remove(UUID id);
    Event getEvent(String name);
    }