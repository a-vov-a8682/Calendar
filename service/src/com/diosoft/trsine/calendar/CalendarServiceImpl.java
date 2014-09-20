package com.diosoft.trsine.calendar;


import java.rmi.RemoteException;
import java.util.*;
import java.util.logging.Logger;

public class CalendarServiceImpl implements CalendarService{

    public static final Logger logger = Logger.getAnonymousLogger();
    private final DataStoreImpl dataStore;

    public CalendarServiceImpl(DataStoreImpl store) {
        this.dataStore = store;
    }

    @Override
    public Event createEvent(String description, List<String> emails) {
        return new Event.Builder()
                .attenders(emails)
                .description(description)
                .build();
    }
    @Override
    public List<Event> searchByTitle(String title) {
        List<Event> result = new LinkedList<Event>();
        if(title == null || title.equals("")){
            return result;
        }
        for (String storeTitle : dataStore.titleMap.keySet()) {
            if(title.equals(storeTitle)){
                result.add(dataStore.eventMap.get(dataStore.titleMap.get(title)));
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
        for (GregorianCalendar storeDate : dataStore.dateMap.keySet()) {
            if (date.getTimeInMillis() == storeDate.getTimeInMillis()){
                result.add(dataStore.eventMap.get(dataStore.dateMap.get(date)));

            }
        }
        if (result.size() == 0){
            System.out.println("В этот день нет событий! \n");
        }
        return result;
    }

    @Override
    public Event getEvent(String name) throws RemoteException {
        return dataStore.getEvent(name);
    }

    @Override
    public void addEvent(Event event) {
        dataStore.addEvent(event);
        logger.info("Published even on service side " + event.getTitle());
    }
    @Override
    public void remove(UUID id) throws RemoteException {
       dataStore.remove(id);
    }
}
