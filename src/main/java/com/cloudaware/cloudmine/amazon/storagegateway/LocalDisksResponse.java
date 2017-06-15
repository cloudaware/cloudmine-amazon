package com.cloudaware.cloudmine.amazon.storagegateway;

import com.amazonaws.services.storagegateway.model.Disk;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 02:46
 */
public final class LocalDisksResponse extends AmazonResponse {
    private List<Disk> disks;

    public List<Disk> getDisks() {
        return disks;
    }

    public void setDisks(final List<Disk> disks) {
        this.disks = disks;
    }
}
