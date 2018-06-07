package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.AllowedPrincipal;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class VpcEndpointServicePermissionsResponse extends AmazonResponse {

    private List<AllowedPrincipal> permissions;

    public List<AllowedPrincipal> getPermissions() {
        return permissions;
    }

    public void setPermissions(final List<AllowedPrincipal> permissions) {
        this.permissions = permissions;
    }
}
