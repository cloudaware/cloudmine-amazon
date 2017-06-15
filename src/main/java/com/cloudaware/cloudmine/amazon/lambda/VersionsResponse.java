package com.cloudaware.cloudmine.amazon.lambda;

import com.amazonaws.services.lambda.model.FunctionConfiguration;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 17:48
 */
public final class VersionsResponse extends AmazonResponse {
    private List<FunctionConfiguration> verions;

    public List<FunctionConfiguration> getVerions() {
        return verions;
    }

    public void setVerions(final List<FunctionConfiguration> verions) {
        this.verions = verions;
    }
}
