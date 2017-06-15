package com.cloudaware.cloudmine.amazon.elasticbeanstalk;

import com.amazonaws.services.elasticbeanstalk.model.SolutionStackDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 16:44
 */
public final class SolutionStacksResponse extends AmazonResponse {
    private List<SolutionStackDescription> solutionStacks;

    public List<SolutionStackDescription> getSolutionStacks() {
        return solutionStacks;
    }

    public void setSolutionStacks(final List<SolutionStackDescription> solutionStacks) {
        this.solutionStacks = solutionStacks;
    }
}
