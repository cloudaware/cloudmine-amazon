package com.cloudaware.cloudmine.amazon.costexplorer;

import com.amazonaws.services.costexplorer.model.Expression;
import com.amazonaws.services.costexplorer.model.GroupDefinition;

import java.util.Set;

public final class CostAndUsageRequest {
    private Set<GroupDefinition> groupDefinitions;

    private Expression filter;

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
