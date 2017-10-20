package com.cloudaware.cloudmine.amazon.athena;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class QueryExecutionIdsResponse extends AmazonResponse {

    private List<String> queryExecutionIds;

    public List<String> getQueryExecutionIds() {
        return queryExecutionIds;
    }

    public void setQueryExecutionIds(final List<String> queryExecutionIds) {
        this.queryExecutionIds = queryExecutionIds;
    }
}
