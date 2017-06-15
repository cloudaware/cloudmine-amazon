package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.VolumeStatusItem;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 19:14
 */
public final class VolumeStatusesResponse extends AmazonResponse {
    private List<VolumeStatusItem> volumeStatuses;

    public List<VolumeStatusItem> getVolumeStatuses() {
        return volumeStatuses;
    }

    public void setVolumeStatuses(final List<VolumeStatusItem> volumeStatuses) {
        this.volumeStatuses = volumeStatuses;
    }
}
