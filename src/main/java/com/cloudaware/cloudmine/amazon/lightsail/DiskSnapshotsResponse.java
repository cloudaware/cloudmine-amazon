package com.cloudaware.cloudmine.amazon.lightsail;

import com.amazonaws.services.lightsail.model.DiskSnapshot;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DiskSnapshotsResponse extends AmazonResponse {

    private List<DiskSnapshot> diskSnapshots;

    public List<DiskSnapshot> getDiskSnapshots() {
        return diskSnapshots;
    }

    public void setDiskSnapshots(final List<DiskSnapshot> diskSnapshots) {
        this.diskSnapshots = diskSnapshots;
    }
}
