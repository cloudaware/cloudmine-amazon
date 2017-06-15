package com.cloudaware.cloudmine.amazon.kms;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 17:31
 */
public final class RotationStatusResponse extends AmazonResponse {
    private Boolean keyRotationEnabled;

    public Boolean getKeyRotationEnabled() {
        return keyRotationEnabled;
    }

    public void setKeyRotationEnabled(final Boolean keyRotationEnabled) {
        this.keyRotationEnabled = keyRotationEnabled;
    }
}
