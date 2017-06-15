package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.CreateVolumePermission;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class SnapshotAttributeResponse extends AmazonResponse {

    private List<CreateVolumePermission> createVolumePermissions;

    public List<CreateVolumePermission> getCreateVolumePermissions() {
        return createVolumePermissions;
    }

    public void setCreateVolumePermissions(final List<CreateVolumePermission> createVolumePermissions) {
        this.createVolumePermissions = createVolumePermissions;
    }
}
