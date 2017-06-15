package com.cloudaware.cloudmine.amazon.directoryservice;

import com.amazonaws.services.directory.model.DirectoryLimits;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class DirectoryLimitsResponse extends AmazonResponse {
    private DirectoryLimits directoryLimits;

    public DirectoryLimits getDirectoryLimits() {
        return directoryLimits;
    }

    public void setDirectoryLimits(final DirectoryLimits directoryLimits) {
        this.directoryLimits = directoryLimits;
    }
}
