package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.DBSnapshot;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 19:22
 */
public final class DbSnapshotsResponse extends AmazonResponse {
    private List<DBSnapshot> dbSnapshots;

    public List<DBSnapshot> getDbSnapshots() {
        return dbSnapshots;
    }

    public void setDbSnapshots(final List<DBSnapshot> dbSnapshots) {
        this.dbSnapshots = dbSnapshots;
    }
}
