package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.DBSubnetGroup;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 19:22
 */
public final class DbSubnetGroupsResponse extends AmazonResponse {
    private List<DBSubnetGroup> dbSubnetGroups;

    public List<DBSubnetGroup> getDbSubnetGroups() {
        return dbSubnetGroups;
    }

    public void setDbSubnetGroups(final List<DBSubnetGroup> dbSubnetGroups) {
        this.dbSubnetGroups = dbSubnetGroups;
    }
}
