package com.diosoft.trsine.calendar;

import com.diosoft.trsine.calendar.common.Event;

import java.util.*;

public class DataStoreImpl implements DataStore {
    Map<UUID, Event> eventMap = new HashMap<UUID, Event>();

    @Override
    public void addEvent(Event event) {
        if (!(eventMap.containsKey(event.getId()))){
            if(event != null){
                eventMap.put(event.getId(), event);
            }
        } else {
            System.out.println("Введите событие!");
        }
    }
    @Override
    public void remove(UUID id) {
        eventMap.remove(id);
    }
    @Override

        public Event getEvent(UUID id) {
            return eventMap.get(id);
        }

    @Override
    public List<Event> getEventBySubTitle(String subTitle) {
        if(subTitle == null){
            throw new IllegalArgumentException();
        }

        Collection<Event> eventList = eventMap.values();
        List<Event> eventListRes = new ArrayList<Event>();
        for (Event event : eventList) {
            if (event.getTitle().startsWith(subTitle)) {
                eventListRes.add(event);
            }
        }
        return eventListRes;
    }
}