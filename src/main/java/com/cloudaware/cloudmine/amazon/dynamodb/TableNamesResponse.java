package com.cloudaware.cloudmine.amazon.dynamodb;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 17:24
 */
public final class TableNamesResponse extends AmazonResponse {
    private List<String> tableNames;

    public List<String> getTableNames() {
        return tableNames;
    }

    public void setTableNames(final List<String> tableNames) {
        this.tableNames = tableNames;
    }
}
