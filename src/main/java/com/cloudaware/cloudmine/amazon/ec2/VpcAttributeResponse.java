package com.cloudaware.cloudmine.amazon.ec2;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class VpcAttributeResponse extends AmazonResponse {

    private Boolean enableDnsSupport;
    private Boolean enableDnsHostnames;

    public Boolean getEnableDnsSupport() {
        return enableDnsSupport;
    }

    public void setEnableDnsSupport(final Boolean enableDnsSupport) {
        this.enableDnsSupport = enableDnsSupport;
    }

    public Boolean getEnableDnsHostnames() {
        return enableDnsHostnames;
    }

    public void setEnableDnsHostnames(final Boolean enableDnsHostnames) {
        this.enableDnsHostnames = enableDnsHostnames;
    }
}
