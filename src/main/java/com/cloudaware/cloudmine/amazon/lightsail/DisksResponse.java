package com.cloudaware.cloudmine.amazon.lightsail;

import com.amazonaws.services.lightsail.model.Disk;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DisksResponse extends AmazonResponse {

    private List<Disk> disks;

    public List<Disk> getDisks() {
        return disks;
    }

    public void setDisks(final List<Disk> disks) {
        this.disks = disks;
    }
}
