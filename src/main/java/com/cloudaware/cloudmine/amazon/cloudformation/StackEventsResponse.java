package com.cloudaware.cloudmine.amazon.cloudformation;

import com.amazonaws.services.cloudformation.model.StackEvent;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 15:40
 */
public final class StackEventsResponse extends AmazonResponse {

    private List<StackEvent> stackEvents;

    public List<StackEvent> getStackEvents() {
        return stackEvents;
    }

    public void setStackEvents(final List<StackEvent> stackEvents) {
        this.stackEvents = stackEvents;
    }
}
