package com.cloudaware.cloudmine.amazon.athena;

import com.amazonaws.services.athena.model.ResultSet;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class QueryResultsResponse extends AmazonResponse {

    private ResultSet resultSet;

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(final ResultSet resultSet) {
        this.resultSet = resultSet;
    }
}
