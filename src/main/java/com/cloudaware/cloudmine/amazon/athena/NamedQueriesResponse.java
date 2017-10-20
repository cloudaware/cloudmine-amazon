package com.cloudaware.cloudmine.amazon.athena;

import com.amazonaws.services.athena.model.NamedQuery;
import com.amazonaws.services.athena.model.UnprocessedNamedQueryId;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class NamedQueriesResponse extends AmazonResponse {

    private List<NamedQuery> namedQueries;
    private List<UnprocessedNamedQueryId> unprocessedNamedQueryIds;

    public List<NamedQuery> getNamedQueries() {
        return namedQueries;
    }

    public void setNamedQueries(final List<NamedQuery> namedQueries) {
        this.namedQueries = namedQueries;
    }

    public List<UnprocessedNamedQueryId> getUnprocessedNamedQueryIds() {
        return unprocessedNamedQueryIds;
    }

    public void setUnprocessedNamedQueryIds(final List<UnprocessedNamedQueryId> unprocessedNamedQueryIds) {
        this.unprocessedNamedQueryIds = unprocessedNamedQueryIds;
    }
}
