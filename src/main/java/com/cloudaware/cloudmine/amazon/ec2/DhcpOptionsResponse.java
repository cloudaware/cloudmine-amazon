package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.DhcpOptions;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 16:43
 */
public final class DhcpOptionsResponse extends AmazonResponse {
    private List<DhcpOptions> dhcpOptions;

    public List<DhcpOptions> getDhcpOptions() {
        return dhcpOptions;
    }

    public void setDhcpOptions(final List<DhcpOptions> dhcpOptions) {
        this.dhcpOptions = dhcpOptions;
    }
}
