package com.cloudaware.cloudmine.amazon.batch;

import com.amazonaws.services.batch.model.ComputeEnvironmentDetail;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ComputeEnvironmentsResponse extends AmazonResponse {

    private List<ComputeEnvironmentDetail> computeEnvironments;

    public List<ComputeEnvironmentDetail> getComputeEnvironments() {
        return computeEnvironments;
    }

    public void setComputeEnvironments(final List<ComputeEnvironmentDetail> computeEnvironments) {
        this.computeEnvironments = computeEnvironments;
    }
}
