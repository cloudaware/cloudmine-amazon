package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.InstanceStateChange;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 17:41
 */
public final class InstanceStateChangeResponse extends AmazonResponse {
    private InstanceStateChange instanceStateChange;

    public InstanceStateChange getInstanceStateChange() {
        return instanceStateChange;
    }

    public void setInstanceStateChange(final InstanceStateChange instanceStateChange) {
        this.instanceStateChange = instanceStateChange;
    }
}
