package com.cloudaware.cloudmine.amazon.emr;

import com.amazonaws.services.elasticmapreduce.model.Command;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 17:36
 */
public final class BootstrapActionsResponse extends AmazonResponse {
    private List<Command> bootstrapActions;

    public List<Command> getBootstrapActions() {
        return bootstrapActions;
    }

    public void setBootstrapActions(final List<Command> bootstrapActions) {
        this.bootstrapActions = bootstrapActions;
    }
}
