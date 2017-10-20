package com.cloudaware.cloudmine.amazon.athena;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class NamedQueryIdsResponse extends AmazonResponse {

    private List<String> namedQueryIds;

    public List<String> getNamedQueryIds() {
        return namedQueryIds;
    }

    public void setNamedQueryIds(final List<String> namedQueryIds) {
        this.namedQueryIds = namedQueryIds;
    }
}
