package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.DBClusterSnapshotAttribute;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 19:25
 */
public final class DbClusterSnapshotAttributesResponse extends AmazonResponse {

    private List<DBClusterSnapshotAttribute> dbClusterSnapshotAttributes;

    public List<DBClusterSnapshotAttribute> getDbClusterSnapshotAttributes() {
        return dbClusterSnapshotAttributes;
    }

    public void setDbClusterSnapshotAttributes(final List<DBClusterSnapshotAttribute> dbClusterSnapshotAttributes) {
        this.dbClusterSnapshotAttributes = dbClusterSnapshotAttributes;
    }
}
