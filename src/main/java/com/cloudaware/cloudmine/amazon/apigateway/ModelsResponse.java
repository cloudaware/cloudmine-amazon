package com.cloudaware.cloudmine.amazon.apigateway;

import com.amazonaws.services.apigateway.model.Model;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ModelsResponse extends AmazonResponse {

    private List<Model> items;

    public List<Model> getItems() {
        return items;
    }

    public void setItems(final List<Model> items) {
        this.items = items;
    }
}
