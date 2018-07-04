package com.cloudaware.cloudmine.amazon.certificatemanager;

import com.amazonaws.services.certificatemanager.model.CertificateSummary;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class CertificateSummariesResponse extends AmazonResponse {
    private List<CertificateSummary> certificateSummaries;

    public List<CertificateSummary> getCertificateSummaries() {
        return certificateSummaries;
    }

    public void setCertificateSummaries(final List<CertificateSummary> certificateSummaries) {
        this.certificateSummaries = certificateSummaries;
    }
}
