package com.cloudaware.cloudmine.amazon.storagegateway;

import com.amazonaws.services.storagegateway.model.VolumeRecoveryPointInfo;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 02:49
 */
public final class VolumeRecoveryPointsResponse extends AmazonResponse {
    private List<VolumeRecoveryPointInfo> volumeRecoveryPoints;

    public List<VolumeRecoveryPointInfo> getVolumeRecoveryPoints() {
        return volumeRecoveryPoints;
    }

    public void setVolumeRecoveryPoints(final List<VolumeRecoveryPointInfo> volumeRecoveryPoints) {
        this.volumeRecoveryPoints = volumeRecoveryPoints;
    }
}
