package com.cloudaware.cloudmine.amazon.apigateway;

import com.amazonaws.services.apigateway.model.RestApi;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class RestApisResponse extends AmazonResponse {

    private List<RestApi> items;

    public List<RestApi> getItems() {
        return items;
    }

    public void setItems(final List<RestApi> items) {
        this.items = items;
    }
}
