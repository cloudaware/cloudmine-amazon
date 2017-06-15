package com.cloudaware.cloudmine.amazon.cloudtrail;

import com.amazonaws.services.cloudtrail.model.GetTrailStatusResult;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 17:08
 */
public final class TrailStatusResponse extends AmazonResponse {
    private GetTrailStatusResult trailStatus;

    public GetTrailStatusResult getTrailStatus() {
        return trailStatus;
    }

    public void setTrailStatus(final GetTrailStatusResult trailStatus) {
        this.trailStatus = trailStatus;
    }
}
