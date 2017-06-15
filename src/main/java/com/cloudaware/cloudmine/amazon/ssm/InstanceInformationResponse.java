package com.cloudaware.cloudmine.amazon.ssm;

import com.amazonaws.services.simplesystemsmanagement.model.InstanceInformation;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class InstanceInformationResponse extends AmazonResponse {

    private List<InstanceInformation> instanceInformation;

    public InstanceInformationResponse() {
    }

    public InstanceInformationResponse(final AmazonException exception) {
        super(exception);
    }

    public InstanceInformationResponse(final List<InstanceInformation> instanceInformation, final String nextPage) {
        super(nextPage);
        this.instanceInformation = instanceInformation;
    }

    public List<InstanceInformation> getInstanceInformation() {
        return instanceInformation;
    }

    public void setInstanceInformation(final List<InstanceInformation> instanceInformation) {
        this.instanceInformation = instanceInformation;
    }
}
