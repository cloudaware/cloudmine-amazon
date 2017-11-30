package com.cloudaware.cloudmine.amazon.apigateway;

import com.amazonaws.services.apigateway.model.Deployment;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DeploymentsResponse extends AmazonResponse {

    private List<Deployment> items;

    public List<Deployment> getItems() {
        return items;
    }

    public void setItems(final List<Deployment> items) {
        this.items = items;
    }
}
