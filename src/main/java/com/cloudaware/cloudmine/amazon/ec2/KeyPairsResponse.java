package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.KeyPairInfo;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 18:14
 */
public final class KeyPairsResponse extends AmazonResponse {
    private List<KeyPairInfo> keyPairs;

    public List<KeyPairInfo> getKeyPairs() {
        return keyPairs;
    }

    public void setKeyPairs(final List<KeyPairInfo> keyPairs) {
        this.keyPairs = keyPairs;
    }
}
