package com.cloudaware.cloudmine.amazon.s3;

import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 22:17
 */
public final class LocationResponse extends AmazonResponse {
    private String location;

    public LocationResponse(final AmazonException exception) {
        super(exception);
    }

    public LocationResponse(final String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(final String location) {
        this.location = location;
    }
}
