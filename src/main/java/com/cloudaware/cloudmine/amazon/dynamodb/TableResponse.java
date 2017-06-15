package com.cloudaware.cloudmine.amazon.dynamodb;

import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 17:25
 */
public final class TableResponse extends AmazonResponse {
    private TableDescription table;

    public TableDescription getTable() {
        return table;
    }

    public void setTable(final TableDescription table) {
        this.table = table;
    }
}
