package com.cloudaware.cloudmine.amazon.cloudsearch;

import com.amazonaws.services.cloudsearchv2.model.DomainStatus;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 16:48
 */
public final class DomainsResponse extends AmazonResponse {
    private List<DomainStatus> domains;

    public List<DomainStatus> getDomains() {
        return domains;
    }

    public void setDomains(final List<DomainStatus> domains) {
        this.domains = domains;
    }
}
