package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.Vpc;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 20:32
 */
public final class VpcsResponse extends AmazonResponse {
    private List<Vpc> vpcs;

    public List<Vpc> getVpcs() {
        return vpcs;
    }

    public void setVpcs(final List<Vpc> vpcs) {
        this.vpcs = vpcs;
    }
}
