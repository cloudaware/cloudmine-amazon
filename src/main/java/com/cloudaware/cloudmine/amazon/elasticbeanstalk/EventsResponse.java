package com.cloudaware.cloudmine.amazon.elasticbeanstalk;

import com.amazonaws.services.elasticbeanstalk.model.EventDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 16:42
 */
public final class EventsResponse extends AmazonResponse {
    private List<EventDescription> events;

    public List<EventDescription> getEvents() {
        return events;
    }

    public void setEvents(final List<EventDescription> events) {
        this.events = events;
    }
}
