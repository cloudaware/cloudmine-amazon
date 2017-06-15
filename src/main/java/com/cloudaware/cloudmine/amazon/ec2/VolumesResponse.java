package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.Volume;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 19:08
 */
public final class VolumesResponse extends AmazonResponse {
    private List<Volume> volumes;

    public List<Volume> getVolumes() {
        return volumes;
    }

    public void setVolumes(final List<Volume> volumes) {
        this.volumes = volumes;
    }
}
