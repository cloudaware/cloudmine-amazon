package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.SAMLProviderListEntry;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 06:46
 */
public final class SamlProvidersResponse extends AmazonResponse {
    private List<SAMLProviderListEntry> samlProviders;

    public List<SAMLProviderListEntry> getSamlProviders() {
        return samlProviders;
    }

    public void setSamlProviders(final List<SAMLProviderListEntry> samlProviders) {
        this.samlProviders = samlProviders;
    }
}
