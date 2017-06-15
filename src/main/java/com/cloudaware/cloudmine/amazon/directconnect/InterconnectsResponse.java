package com.cloudaware.cloudmine.amazon.directconnect;

import com.amazonaws.services.directconnect.model.Interconnect;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 17:17
 */
public final class InterconnectsResponse extends AmazonResponse {
    private List<Interconnect> interconnects;

    public List<Interconnect> getInterconnects() {
        return interconnects;
    }

    public void setInterconnects(final List<Interconnect> interconnects) {
        this.interconnects = interconnects;
    }
}
