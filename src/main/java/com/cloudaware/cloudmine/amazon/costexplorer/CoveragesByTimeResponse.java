package com.cloudaware.cloudmine.amazon.costexplorer;

import com.amazonaws.services.costexplorer.model.CoverageByTime;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class CoveragesByTimeResponse extends AmazonResponse {
    private List<CoverageByTime> coverageByTime;

    public List<CoverageByTime> getCoverageByTime() {
        return coverageByTime;
    }

    public void setCoverageByTime(final List<CoverageByTime> coverageByTime) {
        this.coverageByTime = coverageByTime;
    }
}
