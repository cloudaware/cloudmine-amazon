package com.cloudaware.cloudmine.amazon.lightsail;

import com.amazonaws.services.lightsail.model.InstanceSnapshot;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class InstanceSnapshotsResponse extends AmazonResponse {

    private List<InstanceSnapshot> instanceSnapshots;

    public List<InstanceSnapshot> getInstanceSnapshots() {
        return instanceSnapshots;
    }

    public void setInstanceSnapshots(final List<InstanceSnapshot> instanceSnapshots) {
        this.instanceSnapshots = instanceSnapshots;
    }
}
