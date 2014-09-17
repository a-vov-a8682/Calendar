package com.diosoft.trsine.calendar;

import java.util.UUID;

public interface DataStore {

    void addEvent(Event event);
    void remove(UUID id);
    }