package com.diosoft.trsine.calendar;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface DataStore {
    Event searchById(UUID id);
    Map <String, List<UUID>> searchByTitle(String title);
    Map <GregorianCalendar, List<UUID>> searchByDate(GregorianCalendar date);

}
