package com.cloudaware.cloudmine.amazon.cloudformation;

import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 15:36
 */
public final class StackTemplateResponse extends AmazonResponse {
    private String templateBody;

    public String getTemplateBody() {
        return templateBody;
    }

    public void setTemplateBody(final String templateBody) {
        this.templateBody = templateBody;
    }
}
