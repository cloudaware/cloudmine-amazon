package com.cloudaware.cloudmine.amazon.ses;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 8/24/12
 * Time: 3:05 PM
 */
public final class SendResponse extends AmazonResponse {
    private String messageId;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(final String messageId) {
        this.messageId = messageId;
    }
}
