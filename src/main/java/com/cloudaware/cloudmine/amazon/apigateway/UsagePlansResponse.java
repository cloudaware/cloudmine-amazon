package com.cloudaware.cloudmine.amazon.apigateway;

import com.amazonaws.services.apigateway.model.UsagePlan;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class UsagePlansResponse extends AmazonResponse {

    private List<UsagePlan> items;

    public List<UsagePlan> getItems() {
        return items;
    }

    public void setItems(final List<UsagePlan> items) {
        this.items = items;
    }
}
