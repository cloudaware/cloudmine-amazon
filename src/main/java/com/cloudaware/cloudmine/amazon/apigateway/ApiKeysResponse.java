package com.cloudaware.cloudmine.amazon.apigateway;

import com.amazonaws.services.apigateway.model.ApiKey;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ApiKeysResponse extends AmazonResponse {

    private List<ApiKey> items;

    public List<ApiKey> getItems() {
        return items;
    }

    public void setItems(final List<ApiKey> items) {
        this.items = items;
    }
}
