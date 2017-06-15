package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.SecurityGroup;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 18:56
 */
public final class SecurityGroupsResponse extends AmazonResponse {
    private List<SecurityGroup> securityGroups;

    public List<SecurityGroup> getSecurityGroups() {
        return securityGroups;
    }

    public void setSecurityGroups(final List<SecurityGroup> securityGroups) {
        this.securityGroups = securityGroups;
    }
}
