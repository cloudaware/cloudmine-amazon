package com.cloudaware.cloudmine.amazon.ssm;

import java.util.List;
import java.util.Map;

public final class InstanceInformationRequest {

    private Map<String, List<String>> filters;

    public Map<String, List<String>> getFilters() {
        return filters;
    }

    public void setFilters(final Map<String, List<String>> filters) {
        this.filters = filters;
    }
}
