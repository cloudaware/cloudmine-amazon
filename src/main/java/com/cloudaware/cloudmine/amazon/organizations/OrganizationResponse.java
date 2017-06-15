package com.cloudaware.cloudmine.amazon.organizations;

import com.amazonaws.services.organizations.model.Organization;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class OrganizationResponse extends AmazonResponse {
    private Organization organization;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(final Organization organization) {
        this.organization = organization;
    }
}
