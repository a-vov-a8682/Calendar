package com.diosoft.trsine.calendar.common;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public class Event implements Serializable{

    private final String title;
    private final String description;
    private final List<String> attenders;
    private final GregorianCalendar startTime;
    private final GregorianCalendar endTime;
    private final UUID id;
    private final EventType eventType;

    private Event(Builder builder){
        this.description = builder.description;
        this.attenders = builder.attenders;
        this.title = builder.title;
        this.id = builder.id;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.eventType = builder.eventType;
    }

    public String getDescription() {
        return description;
    }
    public List<String> getAttenders() {
        return attenders;
    }
    public String getTitle() {
        return title;
    }
    public UUID getId() {
        return id;
    }
    public GregorianCalendar getStartTime() {
        return startTime;
    }
    public GregorianCalendar getEndTime() {
        return endTime;
    }
    public EventType eventType() {
        return eventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (attenders != null ? !attenders.equals(event.attenders) : event.attenders != null) return false;
        if (description != null ? !description.equals(event.description) : event.description != null) return false;
        if (endTime != null ? !endTime.equals(event.endTime) : event.endTime != null) return false;
        if (eventType != event.eventType) return false;
        if (id != null ? !id.equals(event.id) : event.id != null) return false;
        if (startTime != null ? !startTime.equals(event.startTime) : event.startTime != null) return false;
        if (title != null ? !title.equals(event.title) : event.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (attenders != null ? attenders.hashCode() : 0);
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (eventType != null ? eventType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", attenders=" + attenders +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", id=" + id +
                ", eventType=" + eventType +
                '}';
    }
    //        @Override
//    public String toString() {
//        return new StringBuilder("Title = ").append(title + "\n")
//                .append(", description = ").append(description + "\n")
//                .append(", attenders = ").append(attenders + "\n")
//                .append(", date = ").append(date.get(Calendar.DATE) + ".").append(date.get(Calendar.MONTH) + ".").append(date.get(Calendar.YEAR) + "\n")
//                .append(", id = ").append(id + "\n").toString();
//    }

    public static class Builder{
        private String title;
        private String description;
        private List<String> attenders;
        private UUID id;
        private GregorianCalendar startTime;
        private GregorianCalendar endTime;
        private EventType eventType;

        public Builder(){
        }
        public Builder(Event original){
            this.description = original.description;
            this.attenders = original.attenders;
            this.title = original.title;
            this.id = original.id;
            this.startTime = original.startTime;
            this.endTime = original.endTime;
            this.eventType = original.eventType;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }
        public Builder attenders(List<String> attenders){
            this.attenders = attenders;
            return this;
        }
        public Builder title(String title){
            this.title = title;
            return this;
        }
        public Builder id(UUID id){
            this.id = id;
            return this;
        }
        public Builder startTime(GregorianCalendar startTime){
            this.startTime = startTime;
            return this;
        }
        public Builder endTime(GregorianCalendar endTime){
            this.endTime = endTime;
            return this;
        }
        public Builder eventType(EventType eventType){
            this.eventType = eventType;
            return this;
        }
        public Event build(){
            return new Event(this);
        }
    }
}
