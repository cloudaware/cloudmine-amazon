package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.Bucket;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 22:08
 */
public final class BucketsResponse extends AmazonResponse {
    private List<Bucket> buckets;

    public BucketsResponse(final AmazonException exception) {
        super(exception);
    }

    public BucketsResponse(final List<Bucket> buckets) {
        this.buckets = buckets;
    }

    public List<Bucket> getBuckets() {
        return buckets;
    }

    public void setBuckets(final List<Bucket> buckets) {
        this.buckets = buckets;
    }
}
