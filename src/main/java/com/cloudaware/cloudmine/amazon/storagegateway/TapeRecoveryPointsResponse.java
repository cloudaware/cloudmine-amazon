package com.cloudaware.cloudmine.amazon.storagegateway;

import com.amazonaws.services.storagegateway.model.TapeRecoveryPointInfo;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 02:51
 */
public final class TapeRecoveryPointsResponse extends AmazonResponse {
    private List<TapeRecoveryPointInfo> tapeRecoveryPoints;

    public List<TapeRecoveryPointInfo> getTapeRecoveryPoints() {
        return tapeRecoveryPoints;
    }

    public void setTapeRecoveryPoints(final List<TapeRecoveryPointInfo> tapeRecoveryPoints) {
        this.tapeRecoveryPoints = tapeRecoveryPoints;
    }
}
