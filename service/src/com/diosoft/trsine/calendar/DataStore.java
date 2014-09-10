package com.diosoft.trsine.calendar;

import java.util.*;
import com.diosoft.trsine.calendar.CalendarService;

public class DataStore {
    Map<UUID, Event> eventMap = new HashMap<UUID, Event>();
    Map <String, UUID> titleMap = new HashMap<String, UUID>();
    Map <GregorianCalendar, UUID> dateMap = new HashMap<GregorianCalendar, UUID>();



}
