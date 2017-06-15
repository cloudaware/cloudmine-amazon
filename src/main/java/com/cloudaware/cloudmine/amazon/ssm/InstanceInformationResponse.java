package com.cloudaware.cloudmine.amazon.ssm;

import com.amazonaws.services.simplesystemsmanagement.model.InstanceInformation;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class InstanceInformationResponse extends AmazonResponse {

    private List<InstanceInformation> instanceInformation;

    public List<InstanceInformation> getInstanceInformation() {
        return instanceInformation;
    }

    public void setInstanceInformation(final List<InstanceInformation> instanceInformation) {
        this.instanceInformation = instanceInformation;
    }
}
