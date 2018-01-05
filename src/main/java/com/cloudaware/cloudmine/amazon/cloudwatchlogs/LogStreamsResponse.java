package com.cloudaware.cloudmine.amazon.cloudwatchlogs;

import com.amazonaws.services.logs.model.LogStream;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class LogStreamsResponse extends AmazonResponse {
    private List<LogStream> logStreams;

    public List<LogStream> getLogStreams() {
        return logStreams;
    }

    public void setLogStreams(final List<LogStream> logStreams) {
        this.logStreams = logStreams;
    }
}
