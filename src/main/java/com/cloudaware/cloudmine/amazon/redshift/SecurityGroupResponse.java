package com.cloudaware.cloudmine.amazon.redshift;

import com.amazonaws.services.redshift.model.ClusterSecurityGroup;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class SecurityGroupResponse extends AmazonResponse {

    private List<ClusterSecurityGroup> securityGroups;

    public List<ClusterSecurityGroup> getSecurityGroups() {
        return securityGroups;
    }

    public void setSecurityGroups(final List<ClusterSecurityGroup> securityGroups) {
        this.securityGroups = securityGroups;
    }
}
