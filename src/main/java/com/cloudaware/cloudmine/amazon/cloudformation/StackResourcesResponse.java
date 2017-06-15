package com.cloudaware.cloudmine.amazon.cloudformation;

import com.amazonaws.services.cloudformation.model.StackResourceSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 15:43
 */
public final class StackResourcesResponse extends AmazonResponse {

    private List<StackResourceSummary> stackResources;

    public List<StackResourceSummary> getStackResources() {
        return stackResources;
    }

    public void setStackResources(final List<StackResourceSummary> stackResources) {
        this.stackResources = stackResources;
    }
}
