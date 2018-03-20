package com.cloudaware.cloudmine.amazon.costexplorer;

import com.amazonaws.services.costexplorer.model.UtilizationByTime;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class UtilizationsByTimeResponse extends AmazonResponse {
    private List<UtilizationByTime> utilizationsByTime;

    public List<UtilizationByTime> getUtilizationsByTime() {
        return utilizationsByTime;
    }

    public void setUtilizationsByTime(final List<UtilizationByTime> utilizationsByTime) {
        this.utilizationsByTime = utilizationsByTime;
    }
}
