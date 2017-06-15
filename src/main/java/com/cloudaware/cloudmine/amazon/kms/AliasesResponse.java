package com.cloudaware.cloudmine.amazon.kms;

import com.amazonaws.services.kms.model.AliasListEntry;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 16:55
 */
public final class AliasesResponse extends AmazonResponse {
    private List<AliasListEntry> aliases;

    public List<AliasListEntry> getAliases() {
        return aliases;
    }

    public void setAliases(final List<AliasListEntry> aliases) {
        this.aliases = aliases;
    }
}
