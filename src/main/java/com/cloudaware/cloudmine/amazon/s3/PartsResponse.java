package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.PartListing;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class PartsResponse extends AmazonResponse {
    private PartListing partListing;

    PartsResponse(final AmazonException exception) {
        super(exception);
    }

    PartsResponse(final PartListing listing, final String nextPage) {
        super(nextPage);
        this.partListing = listing;
    }

    public PartListing getPartListing() {
        return partListing;
    }

    public void setPartListing(final PartListing partListing) {
        this.partListing = partListing;
    }
}
