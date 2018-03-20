package com.cloudaware.cloudmine.amazon.costexplorer;

import com.amazonaws.services.costexplorer.model.ResultByTime;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ResultsByTimeResponse extends AmazonResponse {
    private List<ResultByTime> resultsByTime;

    public List<ResultByTime> getResultsByTime() {
        return resultsByTime;
    }

    public void setResultsByTime(final List<ResultByTime> resultsByTime) {
        this.resultsByTime = resultsByTime;
    }
}
