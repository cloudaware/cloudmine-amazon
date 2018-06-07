package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.VpcClassicLink;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class VpcClassicLinksResponse extends AmazonResponse {

    private List<VpcClassicLink> vpcClassicLinks;

    public List<VpcClassicLink> getVpcClassicLinks() {
        return vpcClassicLinks;
    }

    public void setVpcClassicLinks(final List<VpcClassicLink> vpcClassicLinks) {
        this.vpcClassicLinks = vpcClassicLinks;
    }
}
