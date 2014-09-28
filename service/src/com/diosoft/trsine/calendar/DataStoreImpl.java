package com.diosoft.trsine.calendar;

import com.diosoft.trsine.calendar.common.Event;
import com.diosoft.trsine.calendar.commonAdapter.EventAdapter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class DataStoreImpl implements DataStore {
    Map<UUID, Event> eventMap = new HashMap<UUID, Event>();

    @Override
    public void addEvent(Event event) {
        if (!(eventMap.containsKey(event.getId()))) {
                Event put = eventMap.put(event.getId(), event);
                if (put == null) {
                    saveAsXml(event);
                } else {
                    //remove old
                    eventMap.remove(eventMap.get(event.getId()));
                    //add new
                    eventMap.put(event.getId(), event);
                }
        } else {
            System.out.println("Введите событие!");
        }
    }
    @Override
    public void remove(UUID id) {
        deleteXml(eventMap.get(id));
        eventMap.remove(id);
    }
    @Override
    public Event getEvent(UUID id) {
        return eventMap.get(id);
    }
    @Override
    public void editEvent(Event oldEvent, Event newEvent) {
        remove(oldEvent.getId());
        addEvent(newEvent);
    }
    @Override
    public List<Event> getEventBySubTitle(String subTitle) {
        if (subTitle == null) {
            throw new IllegalArgumentException();
        }
        if (subTitle.equals("")) {
            return new ArrayList<Event>();
        }
        List<Event> result = new ArrayList<Event>();
        for (Map.Entry<UUID, Event> entry : eventMap.entrySet()) {
            if (entry.getValue().getTitle().startsWith(subTitle)) {
                result.add(entry.getValue());
            }
        }
        return result;
    }

    private void saveAsXml (Event event) {
        try {
            File file = new File("C:\\Users\\VovaASUS\\Desktop\\Java\\IdeaProjects\\calendar\\xmlOutput\\" + event.getTitle() + "_" + event.getId() + ".xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(EventAdapter.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            EventAdapter jaxbElement = new EventAdapter(event);
            jaxbMarshaller.marshal(jaxbElement, file);
            jaxbMarshaller.marshal(jaxbElement, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    private void deleteXml(Event event) {
        Path path = Paths.get("C:\\Users\\VovaASUS\\Desktop\\Java\\IdeaProjects\\calendar\\xmlOutput\\" + event.getTitle() + "_" + event.getId() + ".xml");
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void initEvents (){
        List<Event> result = new ArrayList<Event>();

    }
}