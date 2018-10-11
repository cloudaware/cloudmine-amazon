package com.cloudaware.cloudmine.amazon.s3;

import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class CrossOriginConfigurationResponse extends AmazonResponse {
    private List<CorsRule> rules;

    CrossOriginConfigurationResponse(final AmazonException exception) {
        super(exception);
    }

    CrossOriginConfigurationResponse(final List<CorsRule> rules) {
        this.rules = rules;
    }

    public List<CorsRule> getRules() {
        return this.rules;
    }

    public void setRules(final List<CorsRule> rules) {
        this.rules = rules;
    }
}
