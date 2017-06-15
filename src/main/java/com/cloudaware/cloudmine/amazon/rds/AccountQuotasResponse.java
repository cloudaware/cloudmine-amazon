package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.AccountQuota;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 19:27
 */
public final class AccountQuotasResponse extends AmazonResponse {
    private List<AccountQuota> accountQuotas;

    public List<AccountQuota> getAccountQuotas() {
        return accountQuotas;
    }

    public void setAccountQuotas(final List<AccountQuota> accountQuotas) {
        this.accountQuotas = accountQuotas;
    }
}
