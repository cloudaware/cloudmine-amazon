package com.cloudaware.cloudmine.amazon.cloudtrail;

import com.amazonaws.services.cloudtrail.model.Trail;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 17:07
 */
public final class TrailsResponse extends AmazonResponse {
    private List<Trail> trails;

    public List<Trail> getTrails() {
        return trails;
    }

    public void setTrails(final List<Trail> trails) {
        this.trails = trails;
    }
}
