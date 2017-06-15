package com.cloudaware.cloudmine.amazon.organizations;

import com.amazonaws.services.organizations.model.Account;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class AccountsResponse extends AmazonResponse {
    private List<Account> accounts;

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(final List<Account> accounts) {
        this.accounts = accounts;
    }
}
