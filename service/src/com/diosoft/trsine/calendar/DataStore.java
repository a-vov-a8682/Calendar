package com.diosoft.trsine.calendar;

import java.util.*;

public class DataStore {
    Map<UUID, Event> eventMap = new HashMap<UUID, Event>();
    Map <String, UUID> titleMap = new HashMap<String, UUID>();
    Map <GregorianCalendar, UUID> dateMap = new HashMap<GregorianCalendar, UUID>();
}
