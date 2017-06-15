package com.cloudaware.cloudmine.amazon.sns;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Map;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 01:17
 */
public final class AttributesResponse extends AmazonResponse {
    private Map<String, String> attributes;

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(final Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
