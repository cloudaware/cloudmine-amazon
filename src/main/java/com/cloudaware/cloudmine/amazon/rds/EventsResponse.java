package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.Event;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 19:25
 */
public final class EventsResponse extends AmazonResponse {
    private List<Event> events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(final List<Event> events) {
        this.events = events;
    }
}
