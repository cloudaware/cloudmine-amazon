package com.cloudaware.cloudmine.amazon.lightsail;

import com.amazonaws.services.lightsail.model.Domain;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DomainsResponse extends AmazonResponse {

    private List<Domain> domains;

    public List<Domain> getDomains() {
        return domains;
    }

    public void setDomains(final List<Domain> domains) {
        this.domains = domains;
    }
}
