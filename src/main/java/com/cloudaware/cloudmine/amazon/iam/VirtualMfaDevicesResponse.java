package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.VirtualMFADevice;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 06:56
 */
public final class VirtualMfaDevicesResponse extends AmazonResponse {
    private List<VirtualMFADevice> virtualMfaDevices;

    public List<VirtualMFADevice> getVirtualMfaDevices() {
        return virtualMfaDevices;
    }

    public void setVirtualMfaDevices(final List<VirtualMFADevice> virtualMfaDevices) {
        this.virtualMfaDevices = virtualMfaDevices;
    }
}
