package com.cloudaware.cloudmine.amazon.storagegateway;

import com.amazonaws.services.storagegateway.model.Tape;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 02:50
 */
public final class TapesResponse extends AmazonResponse {
    private List<Tape> tapes;

    public List<Tape> getTapes() {
        return tapes;
    }

    public void setTapes(final List<Tape> tapes) {
        this.tapes = tapes;
    }
}
