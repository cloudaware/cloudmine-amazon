package com.cloudaware.cloudmine.amazon.organizations;

import com.amazonaws.services.organizations.model.OrganizationalUnit;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class OrganizationalUnitsResponse extends AmazonResponse {
    private List<OrganizationalUnit> organizationalUnits;

    public List<OrganizationalUnit> getOrganizationalUnits() {
        return organizationalUnits;
    }

    public void setOrganizationalUnits(final List<OrganizationalUnit> organizationalUnits) {
        this.organizationalUnits = organizationalUnits;
    }
}
