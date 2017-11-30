package com.cloudaware.cloudmine.amazon.apigateway;

import com.amazonaws.services.apigateway.model.Resource;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ResourcesResponse extends AmazonResponse {

    private List<Resource> items;

    public List<Resource> getItems() {
        return items;
    }

    public void setItems(final List<Resource> items) {
        this.items = items;
    }
}
