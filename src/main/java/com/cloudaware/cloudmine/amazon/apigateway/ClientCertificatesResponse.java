package com.cloudaware.cloudmine.amazon.apigateway;

import com.amazonaws.services.apigateway.model.ClientCertificate;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ClientCertificatesResponse extends AmazonResponse {

    private List<ClientCertificate> items;

    public List<ClientCertificate> getItems() {
        return items;
    }

    public void setItems(final List<ClientCertificate> items) {
        this.items = items;
    }
}
