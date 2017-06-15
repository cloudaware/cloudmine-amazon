package com.cloudaware.cloudmine.amazon.sts;

import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class CallerIdentityResponse extends AmazonResponse {
    private String userId;
    private String account;
    private String arn;

    public CallerIdentityResponse() {
    }

    public CallerIdentityResponse(final AmazonException exception) {
        super(exception);
    }

    public CallerIdentityResponse(final String userId, final String account, final String arn) {
        this.userId = userId;
        this.account = account;
        this.arn = arn;
    }

    public String getUserId() {
        return userId;
    }

    public String getAccount() {
        return account;
    }

    public String getArn() {
        return arn;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public void setAccount(final String account) {
        this.account = account;
    }

    public void setArn(final String arn) {
        this.arn = arn;
    }
}
