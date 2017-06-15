package com.cloudaware.cloudmine.amazon.directoryservice;

import com.amazonaws.services.directory.model.EventTopic;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class EventTopicsResponse extends AmazonResponse {
    private List<EventTopic> eventTopics;

    public List<EventTopic> getEventTopics() {
        return eventTopics;
    }

    public void setEventTopics(final List<EventTopic> eventTopics) {
        this.eventTopics = eventTopics;
    }
}
