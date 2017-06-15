package com.cloudaware.cloudmine.amazon.kms;

import com.amazonaws.services.kms.model.KeyMetadata;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 17:14
 */
public final class KeyResponse extends AmazonResponse {
    private KeyMetadata key;

    public KeyMetadata getKey() {
        return key;
    }

    public void setKey(final KeyMetadata key) {
        this.key = key;
    }
}
