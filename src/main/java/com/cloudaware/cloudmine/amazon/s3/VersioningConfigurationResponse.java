package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.BucketVersioningConfiguration;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 22:23
 */
public final class VersioningConfigurationResponse extends AmazonResponse {
    private BucketVersioningConfiguration versioningConfiguration;

    public VersioningConfigurationResponse(final AmazonException exception) {
        super(exception);
    }

    public VersioningConfigurationResponse(final BucketVersioningConfiguration versioningConfiguration) {
        this.versioningConfiguration = versioningConfiguration;
    }

    public BucketVersioningConfiguration getVersioningConfiguration() {
        return versioningConfiguration;
    }

    public void setVersioningConfiguration(final BucketVersioningConfiguration versioningConfiguration) {
        this.versioningConfiguration = versioningConfiguration;
    }
}
