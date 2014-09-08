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
   // @Override
    public Event searchById(UUID id) {
        if(store.keySet().contains(id)){
            return store.get(id);
        }
        throw new NoSuchElementException();
    }
  //  @Override
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
  //  @Override
    public Map<GregorianCalendar, List<UUID>> searchByDate(GregorianCalendar date) {
        Map<GregorianCalendar, List<UUID>> result = new HashMap<GregorianCalendar, List<UUID>>();
        List<UUID> idList = new LinkedList<UUID>();
        List<Event> events = new ArrayList<Event>();
        events.addAll(store.values());
        for (Event event : events) {
            if(event.getDate().equals(date)){
                idList.add(event.getId());
            }
        }
        result.put(date, idList);
        return result;
    }
}
