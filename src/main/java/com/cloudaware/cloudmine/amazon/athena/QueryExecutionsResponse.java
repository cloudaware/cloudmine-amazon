package com.cloudaware.cloudmine.amazon.athena;

import com.amazonaws.services.athena.model.QueryExecution;
import com.amazonaws.services.athena.model.UnprocessedQueryExecutionId;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class QueryExecutionsResponse extends AmazonResponse {

    private List<QueryExecution> queryExecutions;
    private List<UnprocessedQueryExecutionId> unprocessedQueryExecutionIds;

    public List<QueryExecution> getQueryExecutions() {
        return queryExecutions;
    }

    public void setQueryExecutions(final List<QueryExecution> queryExecutions) {
        this.queryExecutions = queryExecutions;
    }

    public List<UnprocessedQueryExecutionId> getUnprocessedQueryExecutionIds() {
        return unprocessedQueryExecutionIds;
    }

    public void setUnprocessedQueryExecutionIds(final List<UnprocessedQueryExecutionId> unprocessedQueryExecutionIds) {
        this.unprocessedQueryExecutionIds = unprocessedQueryExecutionIds;
    }
}
