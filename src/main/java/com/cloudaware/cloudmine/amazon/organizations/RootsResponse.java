package com.cloudaware.cloudmine.amazon.organizations;

import com.amazonaws.services.organizations.model.Root;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class RootsResponse extends AmazonResponse {

    private List<Root> roots;

    public List<Root> getRoots() {
        return roots;
    }

    public void setRoots(final List<Root> roots) {
        this.roots = roots;
    }
}
