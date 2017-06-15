package com.cloudaware.cloudmine.amazon.cloudtrail;

import com.amazonaws.services.cloudtrail.model.Event;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class LookupResponse extends AmazonResponse {
    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(final List<Event> events) {
        this.events = events;
    }

}
