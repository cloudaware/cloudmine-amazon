package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.DBClusterParameterGroup;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 19:24
 */
public final class DbClusterParameterGroupsResponse extends AmazonResponse {
    private List<DBClusterParameterGroup> dbClusterParameterGroups;

    public List<DBClusterParameterGroup> getDbClusterParameterGroups() {
        return dbClusterParameterGroups;
    }

    public void setDbClusterParameterGroups(final List<DBClusterParameterGroup> dbClusterParameterGroups) {
        this.dbClusterParameterGroups = dbClusterParameterGroups;
    }
}
