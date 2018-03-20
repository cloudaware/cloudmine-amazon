package com.cloudaware.cloudmine.amazon.costexplorer;

import com.amazonaws.services.costexplorer.model.Expression;
import com.amazonaws.services.costexplorer.model.GroupDefinition;

import java.util.Set;

public final class ReservationUtilizationRequest {
    private String granularity;

    private Set<GroupDefinition> groupDefinitions;

    private Expression filter;

    public String getGranularity() {
        return granularity;
    }

    public void setGranularity(final String granularity) {
        this.granularity = granularity;
    }

    public Set<GroupDefinition> getGroupDefinitions() {
        return groupDefinitions;
    }

    public void setGroupDefinitions(final Set<GroupDefinition> groupDefinitions) {
        this.groupDefinitions = groupDefinitions;
    }

    public Expression getFilter() {
        return filter;
    }

    public void setFilter(final Expression filter) {
        this.filter = filter;
    }
}
