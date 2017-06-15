package com.cloudaware.cloudmine.amazon.cloudsearch;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Map;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 16:45
 */
public final class DomainNamesResponse extends AmazonResponse {
    private Map<String, String> domainNames;

    public Map<String, String> getDomainNames() {
        return domainNames;
    }

    public void setDomainNames(final Map<String, String> domainNames) {
        this.domainNames = domainNames;
    }
}
