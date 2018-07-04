package com.cloudaware.cloudmine.amazon.certificatemanager;

import com.amazonaws.services.certificatemanager.model.CertificateDetail;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class CertificateDetailsResponse extends AmazonResponse {
    private CertificateDetail certificateDetail;

    public CertificateDetail getCertificateDetail() {
        return certificateDetail;
    }

    public void setCertificateDetail(final CertificateDetail certificateDetail) {
        this.certificateDetail = certificateDetail;
    }
}
