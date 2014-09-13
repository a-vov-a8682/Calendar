package com.diosoft.trsine.calendar;


import java.util.*;

public class CalendarServiceImpl implements CalendarService{

    private final DataStore store;

    public CalendarServiceImpl(DataStore store) {
        this.store = store;
    }

    @Override
    public void addEvent(Event event) {
        if (event != null) {
            store.eventMap.put(event.getId(), event);
            store.dateMap.put(event.getDate(), event.getId());
            store.titleMap.put(event.getTitle(), event.getId());
        }else {
            System.out.println("Введите событие!");
        }
    }
    @Override
    public Event createEvent(String description, List<String> emails) {
        if (description == null || description.equals("")){
            throw new NoSuchElementException();
        }else if(emails == null){
            return new Event.Builder()
                    .id(null)
                    .attenders(new ArrayList<String>())
                    .title(null)
                    .date(null)
                    .description(description)
                    .build();
        }
        return new Event.Builder()
                .id(null)
                .attenders(emails)
                .title(null)
                .date(null)
                .description(description)
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
        if(title == null || title.equals("")){
            return result;
        }
        for (String storeTitle : store.titleMap.keySet()) {
            if(title.equals(storeTitle)){
                result.add(store.eventMap.get(store.titleMap.get(title)));
            }
        }
        if(result.size() == 0){
            System.out.println("События с названием " + "'" + title + "'" + " не существует! \n");
        }
        return result;
    }
    @Override
    public List<Event> searchByDate(GregorianCalendar date) {

        List<Event> result = new LinkedList<Event>();
        if(date == null){
            return result;
        }
        for (GregorianCalendar storeDate : store.dateMap.keySet()) {
            if (date.get(GregorianCalendar.DATE) == storeDate.get(GregorianCalendar.DATE) &&
                date.get(GregorianCalendar.MONTH) == storeDate.get(GregorianCalendar.MONTH) &&
                date.get(GregorianCalendar.YEAR) == storeDate.get(GregorianCalendar.YEAR)){
                result.add(store.eventMap.get(store.dateMap.get(date)));
            }
        }
        if (result.size() == 0){
            System.out.println("В этот день нет событий! \n");
        }
        return result;
    }
}
