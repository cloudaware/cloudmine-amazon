package com.cloudaware.cloudmine.amazon.cloudformation;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 5/27/13
 * Time: 11:48 AM
 */
public final class StackIdResponse extends AmazonResponse {

    private String stackId;

    public String getStackId() {
        return stackId;
    }

    public void setStackId(final String stackId) {
        this.stackId = stackId;
    }
}
