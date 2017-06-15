package com.cloudaware.cloudmine.amazon.organizations;

import com.amazonaws.services.organizations.model.CreateAccountStatus;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class AccountCreateResponse extends AmazonResponse {

    private CreateAccountStatus createAccountStatus;

    public CreateAccountStatus getCreateAccountStatus() {
        return createAccountStatus;
    }

    public void setCreateAccountStatus(final CreateAccountStatus createAccountStatus) {
        this.createAccountStatus = createAccountStatus;
    }
}
