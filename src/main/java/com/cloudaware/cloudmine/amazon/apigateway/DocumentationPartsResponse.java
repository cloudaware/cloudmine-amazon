package com.cloudaware.cloudmine.amazon.apigateway;

import com.amazonaws.services.apigateway.model.DocumentationPart;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DocumentationPartsResponse extends AmazonResponse {

    private List<DocumentationPart> items;

    public List<DocumentationPart> getItems() {
        return items;
    }

    public void setItems(final List<DocumentationPart> items) {
        this.items = items;
    }
}
