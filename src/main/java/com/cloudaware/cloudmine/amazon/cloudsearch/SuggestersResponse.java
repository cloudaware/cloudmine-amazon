package com.cloudaware.cloudmine.amazon.cloudsearch;

import com.amazonaws.services.cloudsearchv2.model.SuggesterStatus;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 16:49
 */
public final class SuggestersResponse extends AmazonResponse {
    private List<SuggesterStatus> suggesters;

    public List<SuggesterStatus> getSuggesters() {
        return suggesters;
    }

    public void setSuggesters(final List<SuggesterStatus> suggesters) {
        this.suggesters = suggesters;
    }
}
