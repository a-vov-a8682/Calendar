package com.diosoft.trsine.calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.UUID;

public class Event {

    private final String title;
    private final String description;
    private final List<String> attenders;
    private final GregorianCalendar date;
    private final UUID id;
    private final String email;

    private Event(Builder builder){
        this.description = builder.description;
        this.attenders = builder.attenders;
        this.date = builder.date;
        this.title = builder.title;
        this.id = builder.id;
        this.email = builder.email;
    }

    public String getDescription() {
        return description;
    }
    public List<String> getAttenders() {
        return attenders;
    }
    public GregorianCalendar getDate() {
        return date;
    }
    public String getTitle() {
        return title;
    }
    public UUID getId() {
        return id;
    }
    public String getEmail(){
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (attenders != null ? !attenders.equals(event.attenders) : event.attenders != null) return false;
        if (date != null ? !date.equals(event.date) : event.date != null) return false;
        if (description != null ? !description.equals(event.description) : event.description != null) return false;
        if (id != null ? !id.equals(event.id) : event.id != null) return false;
        if (title != null ? !title.equals(event.title) : event.title != null) return false;

        return true;
    }
    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (attenders != null ? attenders.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return new StringBuffer("Event{").append("Title = ").append(title + "\n")
                .append(", description = ").append(description + "\n")
                .append(", attenders = ").append(attenders + "\n")
                .append(", date = ").append(date.get(Calendar.DATE)).append(".").append(date.get(Calendar.MONTH)).append(".").append(date.get(Calendar.YEAR) + "\n")
                .append(", id = ").append(id).append("} \n").toString();
    }

    public static class Builder{
        private String title;
        private String description;
        private List<String> attenders;
        private GregorianCalendar date;
        private UUID id;
        private String email;

        public Builder(){
        }
        public Builder(Event original){
            this.description = original.description;
            this.attenders = original.attenders;
            this.date = original.date;
            this.title = original.title;
            this.id = original.id;
            this.email = original.email;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }
        public Builder attenders(List<String> attenders){
            this.attenders = attenders;
            return this;
        }
        public Builder date(GregorianCalendar date){
            this.date = date;
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
        public Builder email(String email){
            this.email = email;
            return this;
        }
        public Event build(){
            return new Event(this);
        }
    }
}
