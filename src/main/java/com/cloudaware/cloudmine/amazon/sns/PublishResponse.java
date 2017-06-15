package com.cloudaware.cloudmine.amazon.sns;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 01:15
 */
public final class PublishResponse extends AmazonResponse {
    private String messageId;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(final String messageId) {
        this.messageId = messageId;
    }
}
