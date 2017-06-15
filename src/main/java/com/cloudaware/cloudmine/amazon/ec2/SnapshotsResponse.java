package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.Snapshot;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 18:57
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
