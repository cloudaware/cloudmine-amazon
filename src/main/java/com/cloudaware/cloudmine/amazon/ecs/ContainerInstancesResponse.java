package com.cloudaware.cloudmine.amazon.ecs;

import com.amazonaws.services.ecs.model.ContainerInstance;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.21.17
 * Time: 14:58
 */
public final class ContainerInstancesResponse extends AmazonResponse {
    private List<ContainerInstance> containerInstances;

    public List<ContainerInstance> getContainerInstances() {
        return containerInstances;
    }

    public void setContainerInstances(final List<ContainerInstance> containerInstances) {
        this.containerInstances = containerInstances;
    }
}
