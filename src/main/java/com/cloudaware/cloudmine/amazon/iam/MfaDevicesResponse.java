package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.MFADevice;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 04:59
 */
public final class MfaDevicesResponse extends AmazonResponse {
    private List<MFADevice> mfaDevices;

    public List<MFADevice> getMfaDevices() {
        return mfaDevices;
    }

    public void setMfaDevices(final List<MFADevice> mfaDevices) {
        this.mfaDevices = mfaDevices;
    }
}
