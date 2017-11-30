package com.cloudaware.cloudmine.amazon.apigateway;

import com.amazonaws.services.apigateway.model.UsagePlanKey;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class UsagePlanKeysResponse extends AmazonResponse {

    private List<UsagePlanKey> items;

    public List<UsagePlanKey> getItems() {
        return items;
    }

    public void setItems(final List<UsagePlanKey> items) {
        this.items = items;
    }
}
