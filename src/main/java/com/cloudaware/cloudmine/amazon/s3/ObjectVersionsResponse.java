package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.S3VersionSummary;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ObjectVersionsResponse extends AmazonResponse {
    private List<S3VersionSummary> versionsSummaries;

    ObjectVersionsResponse(final AmazonException exception) {
        super(exception);
    }

    ObjectVersionsResponse(final List<S3VersionSummary> summaries, final String nextPage) {
        super(nextPage);
        this.versionsSummaries = summaries;
    }

    public List<S3VersionSummary> getVersionsSummaries() {
        return versionsSummaries;
    }

    public void setVersionsSummaries(final List<S3VersionSummary> versionsSummaries) {
        this.versionsSummaries = versionsSummaries;
    }
}
