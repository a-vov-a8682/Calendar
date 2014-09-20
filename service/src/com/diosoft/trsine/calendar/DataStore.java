package com.diosoft.trsine.calendar;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface DataStore{

    void addEvent(Event event);
    void remove(UUID id);
    Event getEvent(String name);
    }