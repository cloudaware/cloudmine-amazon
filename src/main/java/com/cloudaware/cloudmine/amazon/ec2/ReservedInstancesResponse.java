package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.ReservedInstances;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 18:44
 */
public final class ReservedInstancesResponse extends AmazonResponse {
    private List<ReservedInstances> reservedInstances;

    public List<ReservedInstances> getReservedInstances() {
        return reservedInstances;
    }

    public void setReservedInstances(final List<ReservedInstances> reservedInstances) {
        this.reservedInstances = reservedInstances;
    }
}
