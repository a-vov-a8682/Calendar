package com.diosoft.trsine.calendar;


import java.util.*;

public class CalendarServiceImpl implements CalendarService{

    private final DataStore store;

    public CalendarServiceImpl(DataStore store) {
        this.store = store;
    }

    @Override
    public void addEvent(Event event) {
        store.eventMap.put(event.getId(), event);
        store.dateMap.put(event.getDate(), event.getId());
        store.titleMap.put(event.getTitle(), event.getId());
    }
    @Override
    public void createEvent(String description, String email) {
            new Event.Builder()
                .id(UUID.randomUUID())
                .description(description)
                .email(email)
                .build();
    }
    @Override
    public void remove(UUID id) {
        store.eventMap.remove(id);
        store.titleMap.remove(id);
        store.dateMap.remove(id);
    }
    @Override
    public List<Event> searchByTitle(String title) {
        List<Event> result = new LinkedList<Event>();
        for (String storeTitle : store.titleMap.keySet()) {
            if(title.equals(storeTitle)){
                result.add(store.eventMap.get(store.titleMap.get(title)));
            }
        }
        return result;
    }
    @Override
    public List<Event> searchByDate(GregorianCalendar date) {
        List<Event> result = new LinkedList<Event>();
        for (GregorianCalendar storeDate : store.dateMap.keySet()) {
            if (date.get(GregorianCalendar.DATE) == storeDate.get(GregorianCalendar.DATE) &&
                date.get(GregorianCalendar.MONTH) == storeDate.get(GregorianCalendar.MONTH) &&
                date.get(GregorianCalendar.YEAR) == storeDate.get(GregorianCalendar.YEAR)){
                result.add(store.eventMap.get(store.dateMap.get(date)));
            }
        }
        return result;
    }
}
