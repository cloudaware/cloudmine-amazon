package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.InstanceStatus;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 17:51
 */
public final class InstanceStatusesResponse extends AmazonResponse {
    private List<InstanceStatus> instanceStatuses;

    public List<InstanceStatus> getInstanceStatuses() {
        return instanceStatuses;
    }

    public void setInstanceStatuses(final List<InstanceStatus> instanceStatuses) {
        this.instanceStatuses = instanceStatuses;
    }
}
