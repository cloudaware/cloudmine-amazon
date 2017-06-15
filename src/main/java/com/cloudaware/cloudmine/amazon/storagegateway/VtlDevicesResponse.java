package com.cloudaware.cloudmine.amazon.storagegateway;

import com.amazonaws.services.storagegateway.model.VTLDevice;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 02:52
 */
public final class VtlDevicesResponse extends AmazonResponse {
    private List<VTLDevice> vtlDevices;

    public List<VTLDevice> getVtlDevices() {
        return vtlDevices;
    }

    public void setVtlDevices(final List<VTLDevice> vtlDevices) {
        this.vtlDevices = vtlDevices;
    }
}
