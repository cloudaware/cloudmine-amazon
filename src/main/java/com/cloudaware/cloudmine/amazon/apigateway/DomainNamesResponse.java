package com.cloudaware.cloudmine.amazon.apigateway;

import com.amazonaws.services.apigateway.model.DomainName;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DomainNamesResponse extends AmazonResponse {

    private List<DomainName> items;

    public List<DomainName> getItems() {
        return items;
    }

    public void setItems(final List<DomainName> items) {
        this.items = items;
    }
}
