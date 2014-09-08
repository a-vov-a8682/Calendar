package com.diosoft.trsine.calendar;

import java.util.*;

public class CalendarServiceImpl implements CalendarService, DataStore{

    private final Map<UUID, Event> store = new HashMap<UUID, Event>();

    @Override
    public void addEvent(Event event) {
        store.put(event.getId(), event);
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
        store.remove(id);
    }
    @Override
    public Event searchById(UUID id) {
        if(store.keySet().contains(id)){
            return store.get(id);
        }
        throw new NoSuchElementException();
    }
    @Override
    public Map<String, List<UUID>> searchByTitle(String title) {
        Map<String, List<UUID>> result = new HashMap<String, List<UUID>>();
        List<Event> events = new ArrayList<Event>();
        List<UUID> idList = new LinkedList<UUID>();
        events.addAll(store.values());
        for (Event event : events) {
            if(event.getTitle().equals(title)){
                idList.add(event.getId());
            }
        }
        result.put(title, idList);
        return result;
    }

    @Override
    public Map<GregorianCalendar, List<UUID>> searchByDate(GregorianCalendar date) {
        Map<GregorianCalendar, List<UUID>> result = new HashMap<GregorianCalendar, List<UUID>>();
//        Что использовать для даты?Какой формат даты?


        return result;
    }
}
