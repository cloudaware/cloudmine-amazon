package com.cloudaware.cloudmine.amazon.cloudsearch;

import com.amazonaws.services.cloudsearchv2.model.ScalingParametersStatus;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 16:53
 */
public final class ScalingParametersResponse extends AmazonResponse {
    private ScalingParametersStatus scalingParameters;

    public ScalingParametersStatus getScalingParameters() {
        return scalingParameters;
    }

    public void setScalingParameters(final ScalingParametersStatus scalingParameters) {
        this.scalingParameters = scalingParameters;
    }
}
