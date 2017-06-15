package com.cloudaware.cloudmine.amazon.storagegateway;

import com.amazonaws.services.storagegateway.model.CachediSCSIVolume;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 02:48
 */
public final class CachedVolumesResponse extends AmazonResponse {
    private List<CachediSCSIVolume> cachedVolumes;

    public List<CachediSCSIVolume> getCachedVolumes() {
        return cachedVolumes;
    }

    public void setCachedVolumes(final List<CachediSCSIVolume> cachedVolumes) {
        this.cachedVolumes = cachedVolumes;
    }
}
