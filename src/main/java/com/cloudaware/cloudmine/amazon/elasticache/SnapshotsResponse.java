package com.cloudaware.cloudmine.amazon.elasticache;

import com.amazonaws.services.elasticache.model.Snapshot;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.21.17
 * Time: 15:22
 */
public final class SnapshotsResponse extends AmazonResponse {
    private List<Snapshot> snapshots;

    public List<Snapshot> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(final List<Snapshot> snapshots) {
        this.snapshots = snapshots;
    }
}
