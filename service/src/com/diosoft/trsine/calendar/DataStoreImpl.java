package com.diosoft.trsine.calendar;

import java.util.*;

public class DataStoreImpl implements DataStore {
    Map<UUID, Event> eventMap = new HashMap<UUID, Event>();
    Map<String, UUID> titleMap = new HashMap<String, UUID>();
    Map<GregorianCalendar, UUID> dateMap = new HashMap<GregorianCalendar, UUID>();

    @Override
    public void addEvent(Event event) {
        if (event != null) {
            eventMap.put(event.getId(), event);
            dateMap.put(event.getDate(), event.getId());
            titleMap.put(event.getTitle(), event.getId());
        } else {
            System.out.println("Введите событие!");
        }
    }
    @Override
    public void remove(UUID id) {
        eventMap.remove(id);
        titleMap.remove(eventMap.get(id).getTitle());
        dateMap.remove(eventMap.get(id).getDate());
    }
    @Override
    public Event getEvent(String name) {
        UUID id = titleMap.get(name);
        return eventMap.get(id);
    }

}