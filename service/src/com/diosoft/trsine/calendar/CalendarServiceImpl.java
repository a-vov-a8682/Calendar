package com.diosoft.trsine.calendar;


import java.rmi.RemoteException;
import java.util.*;

public class CalendarServiceImpl implements CalendarService{

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
            if (
                date.get(GregorianCalendar.DATE) == storeDate.get(GregorianCalendar.DATE) &&
                date.get(GregorianCalendar.MONTH) == storeDate.get(GregorianCalendar.MONTH) &&
                date.get(GregorianCalendar.YEAR) == storeDate.get(GregorianCalendar.YEAR)){
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
    public void addEvent(Event event) throws RemoteException {
        dataStore.addEvent(event);
    }
    @Override
    public void remove(UUID id) throws RemoteException {
       dataStore.remove(id);
    }
}
