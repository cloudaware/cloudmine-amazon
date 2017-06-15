package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.ServerCertificateMetadata;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 04:02
 */
public final class ServerCertificatesResponse extends AmazonResponse {
    private List<ServerCertificateMetadata> serverCertificates;

    public List<ServerCertificateMetadata> getServerCertificates() {
        return serverCertificates;
    }

    public void setServerCertificates(final List<ServerCertificateMetadata> serverCertificates) {
        this.serverCertificates = serverCertificates;
    }
}
