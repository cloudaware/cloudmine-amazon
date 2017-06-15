package com.cloudaware.cloudmine.amazon.cloudfront;

import com.amazonaws.services.cloudfront.model.Invalidation;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 16:35
 */
public final class InvalidationResponse extends AmazonResponse {
    private Invalidation invalidation;

    public Invalidation getInvalidation() {
        return invalidation;
    }

    public void setInvalidation(final Invalidation invalidation) {
        this.invalidation = invalidation;
    }
}
