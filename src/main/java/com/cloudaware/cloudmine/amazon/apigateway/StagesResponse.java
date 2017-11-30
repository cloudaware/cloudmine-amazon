package com.cloudaware.cloudmine.amazon.apigateway;

import com.amazonaws.services.apigateway.model.Stage;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class StagesResponse extends AmazonResponse {

    private List<Stage> items;

    public List<Stage> getItems() {
        return items;
    }

    public void setItems(final List<Stage> items) {
        this.items = items;
    }
}
