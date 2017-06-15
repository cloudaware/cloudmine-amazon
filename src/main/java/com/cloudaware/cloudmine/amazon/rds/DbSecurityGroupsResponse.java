package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.DBSecurityGroup;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 19:21
 */
public final class DbSecurityGroupsResponse extends AmazonResponse {
    private List<DBSecurityGroup> dbSecurityGroups;

    public List<DBSecurityGroup> getDbSecurityGroups() {
        return dbSecurityGroups;
    }

    public void setDbSecurityGroups(final List<DBSecurityGroup> dbSecurityGroups) {
        this.dbSecurityGroups = dbSecurityGroups;
    }
}
