package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.AccountAttribute;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 2016-05-26
 * Time: 20:33
 */
public final class AccountAttributesResponse extends AmazonResponse {
    private List<AccountAttribute> accountAttributes;

    public List<AccountAttribute> getAccountAttributes() {
        return accountAttributes;
    }

    public void setAccountAttributes(final List<AccountAttribute> accountAttributes) {
        this.accountAttributes = accountAttributes;
    }
}
