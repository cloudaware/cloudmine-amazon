package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.ReplicationRule;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Map;

public final class ReplicationConfigurationResponse extends AmazonResponse {
    private String roleArn;
    private Map<String, ReplicationRule> rules;

    ReplicationConfigurationResponse(final AmazonException exception) {
        super(exception);
    }

    ReplicationConfigurationResponse(final Map<String, ReplicationRule> rules, final String roleArn) {
        this.roleArn = roleArn;
        this.rules = rules;
    }

    public String getRoleArn() {
        return roleArn;
    }

    public void setRoleArn(final String roleArn) {
        this.roleArn = roleArn;
    }

    public Map<String, ReplicationRule> getRules() {
        return rules;
    }

    public void setRules(final Map<String, ReplicationRule> rules) {
        this.rules = rules;
    }
}
