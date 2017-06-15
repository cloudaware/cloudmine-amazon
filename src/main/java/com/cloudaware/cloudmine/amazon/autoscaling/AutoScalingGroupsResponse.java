package com.cloudaware.cloudmine.amazon.autoscaling;

import com.amazonaws.services.autoscaling.model.AutoScalingGroup;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 13:33
 */
public final class AutoScalingGroupsResponse extends AmazonResponse {
    private List<AutoScalingGroup> autoScalingGroups;

    public List<AutoScalingGroup> getAutoScalingGroups() {
        return autoScalingGroups;
    }

    public void setAutoScalingGroups(final List<AutoScalingGroup> autoScalingGroups) {
        this.autoScalingGroups = autoScalingGroups;
    }
}
