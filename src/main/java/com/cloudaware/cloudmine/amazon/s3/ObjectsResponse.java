package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.ObjectListing;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 22:31
 */
public final class ObjectsResponse extends AmazonResponse {
    private ObjectListing objects;

    public ObjectsResponse(final AmazonException exception) {
        super(exception);
    }

    public ObjectsResponse(final ObjectListing objects, final String nextPage) {
        super(nextPage);
        this.objects = objects;
    }

    public ObjectListing getObjects() {
        return objects;
    }

    public void setObjects(final ObjectListing objects) {
        this.objects = objects;
    }
}
