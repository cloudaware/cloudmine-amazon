package com.cloudaware.cloudmine.amazon.cloudwatchlogs;

import com.amazonaws.services.logs.model.LogGroup;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class LogGroupsResponse extends AmazonResponse {
    private List<LogGroup> logGroups;

    public List<LogGroup> getLogGroups() {
        return logGroups;
    }

    public void setLogGroups(final List<LogGroup> logGroups) {
        this.logGroups = logGroups;
    }
}
