package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.InternetGateway;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 17:55
 */
public final class InternetGatewaysResponse extends AmazonResponse {

    private List<InternetGateway> internetGateways;

    public List<InternetGateway> getInternetGateways() {
        return internetGateways;
    }

    public void setInternetGateways(final List<InternetGateway> internetGateways) {
        this.internetGateways = internetGateways;
    }
}
