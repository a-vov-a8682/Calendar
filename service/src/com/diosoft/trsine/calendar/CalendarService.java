package com.diosoft.trsine.calendar;

import com.diosoft.trsine.calendar.common.Event;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface CalendarService extends Remote {

    Event createEvent (String description, List<String> emails) throws RemoteException;
    List<Event> searchByTitle (String title) throws RemoteException;
    List<Event> searchByDate (GregorianCalendar date) throws RemoteException;
    Event searchByAttendeeByTime (String attendee, GregorianCalendar time) throws RemoteException;
    Event getEvent (UUID id) throws RemoteException;
    void addEvent (Event event) throws RemoteException;
    void remove (UUID id) throws RemoteException;
    boolean isFree (String attendee, GregorianCalendar time) throws RemoteException;
    Set<GregorianCalendar> bestEventTime (String attendee, GregorianCalendar date) throws RemoteException;
    List<Event> getEventBySubTitle (String subTitle) throws RemoteException;
    void editEvent (Event event1, Event event2) throws RemoteException;


}
