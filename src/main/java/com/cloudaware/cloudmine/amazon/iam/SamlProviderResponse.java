package com.cloudaware.cloudmine.amazon.iam;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Date;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 06:48
 */
public final class SamlProviderResponse extends AmazonResponse {
    private String metadataDocument;
    private Date createDate;
    private Date validUntil;

    public String getMetadataDocument() {
        return metadataDocument;
    }

    public void setMetadataDocument(final String metadataDocument) {
        this.metadataDocument = metadataDocument;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(final Date createDate) {
        this.createDate = createDate;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(final Date validUntil) {
        this.validUntil = validUntil;
    }
}
