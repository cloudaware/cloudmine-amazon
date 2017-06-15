package com.cloudaware.cloudmine.amazon.directoryservice;

import com.amazonaws.services.directory.model.Trust;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class TrustsResponse extends AmazonResponse {
    private List<Trust> trusts;

    public List<Trust> getTrusts() {
        return trusts;
    }

    public void setTrusts(final List<Trust> trusts) {
        this.trusts = trusts;
    }
}
