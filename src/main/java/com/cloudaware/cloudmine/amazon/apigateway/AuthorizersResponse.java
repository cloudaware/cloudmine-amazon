package com.cloudaware.cloudmine.amazon.apigateway;

import com.amazonaws.services.apigateway.model.Authorizer;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class AuthorizersResponse extends AmazonResponse {

    private List<Authorizer> items;

    public List<Authorizer> getItems() {
        return items;
    }

    public void setItems(final List<Authorizer> items) {
        this.items = items;
    }
}
