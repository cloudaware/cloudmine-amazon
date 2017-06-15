package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.DBSnapshotAttribute;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 19:25
 */
public final class DbSnapshotAttributesResponse extends AmazonResponse {

    private List<DBSnapshotAttribute> dbSnapshotAttributes;

    public List<DBSnapshotAttribute> getDbSnapshotAttributes() {
        return dbSnapshotAttributes;
    }

    public void setDbSnapshotAttributes(final List<DBSnapshotAttribute> dbSnapshotAttributes) {
        this.dbSnapshotAttributes = dbSnapshotAttributes;
    }
}
