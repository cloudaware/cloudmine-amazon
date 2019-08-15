package com.cloudaware.cloudmine.amazon.ec2;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class GetEbsEncryptionByDefaultResponse extends AmazonResponse {
    private Boolean ebsEncryptionByDefault;

    public Boolean getEbsEncryptionByDefault() {
        return ebsEncryptionByDefault;
    }

    public void setEbsEncryptionByDefault(final Boolean ebsEncryptionByDefault) {
        this.ebsEncryptionByDefault = ebsEncryptionByDefault;
    }
}
