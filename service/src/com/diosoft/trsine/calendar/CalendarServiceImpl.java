package com.diosoft.trsine.calendar;


import com.diosoft.trsine.calendar.common.Event;
import com.diosoft.trsine.calendar.common.EventType;

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
        }else {
            for (Map.Entry<UUID, Event> entry : dataStore.eventMap.entrySet()) {
                if (entry.getValue().getTitle().equals(title)){
                    result.add(entry.getValue());
                }
            }
            return result;
        }
    }
    @Override
    public List<Event> searchByDate(GregorianCalendar date) {
        List<Event> result = new LinkedList<Event>();
        for(Map.Entry<UUID, Event> entry : dataStore.eventMap.entrySet()){
            int year = entry.getValue().getStartTime().get(GregorianCalendar.YEAR);
            int month = entry.getValue().getStartTime().get(GregorianCalendar.MONTH);
            int day = entry.getValue().getStartTime().get(GregorianCalendar.DATE);

            GregorianCalendar tempDate = new GregorianCalendar(year, month, day);
            if (tempDate.equals(date)){
                result.add(entry.getValue());
            }
        }
        return result;
    }
    @Override
    public Event searchByAttendeeByTime(String attendee, GregorianCalendar time){
        Event result = new Event.Builder().build();
        for (Map.Entry<UUID, Event> entry : dataStore.eventMap.entrySet()) {
            if (entry.getValue().getAttenders() != null){
                if (entry.getValue().getAttenders().contains(attendee)) {
                    if (entry.getValue().getEventType() != EventType.ALLDAY) {
                        long startTime = entry.getValue().getStartTime().getTimeInMillis();
                        long endTime = entry.getValue().getEndTime().getTimeInMillis();
                        if (time.getTimeInMillis() >= startTime && time.getTimeInMillis() <= endTime) {
                            result = entry.getValue();
                        }
                    }
                }
            }
        }
        return result;
    }
    @Override
    public Event getEvent(UUID id) {
        return dataStore.getEvent(id);
    }
    @Override
    public void addEvent(Event event) {
        dataStore.addEvent(event);
        logger.info("Published even on service side \n" + event.getTitle() + "\n");
    }
    @Override
    public void remove(UUID id){
       dataStore.remove(id);
    }
    @Override
    public boolean isFree(String attendee, GregorianCalendar time){
        return searchByAttendeeByTime(attendee, time).getTitle() == null ? true : false;
    }
    @Override
    public Set<GregorianCalendar> bestEventTime(String attendee, GregorianCalendar date) {
        Set<GregorianCalendar> result = new HashSet<GregorianCalendar>();



        return result;
    }
}
