package com.diosoft.trsine.calendar.commonAdapter;

import com.diosoft.trsine.calendar.common.Event;
import com.diosoft.trsine.calendar.common.EventType;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

@XmlRootElement
public class EventAdapter {
    private String title;
    private String description;
    private List<String> attenders;
    private UUID id;
    private GregorianCalendar startTime;
    private GregorianCalendar endTime;
    private EventType eventType;

    public EventAdapter (Event event){
        this.title = event.getTitle();
        this.description = event.getDescription();
        this.attenders = event.getAttenders();
        this.id = event.getId();
        this.startTime = event.getStartTime();
        this.endTime = event.getEndTime();
        this.eventType = event.getEventType();
    }

    public EventAdapter() {
    }

    public String getTitle() {
        return title;
    }
    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getAttenders() {
        return attenders;
    }
    @XmlElement
    public void setAttenders(List<String> attenders) {
        this.attenders = attenders;
    }

    public UUID getId() {
        return id;
    }
    @XmlElement
    public void setId(UUID id) {
        this.id = id;
    }

    public GregorianCalendar getStartTime() {
        return startTime;
    }
    @XmlElement
    public void setStartTime(GregorianCalendar startTime) {
        this.startTime = startTime;
    }

    public GregorianCalendar getEndTime() {
        return endTime;
    }
    @XmlElement
    public void setEndTime(GregorianCalendar endTime) {
        this.endTime = endTime;
    }

    public EventType getEventType() {
        return eventType;
    }
    @XmlElement
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }
}
