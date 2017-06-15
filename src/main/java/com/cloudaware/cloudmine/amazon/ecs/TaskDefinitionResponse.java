package com.cloudaware.cloudmine.amazon.ecs;

import com.amazonaws.services.ecs.model.TaskDefinition;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.21.17
 * Time: 15:09
 */
public final class TaskDefinitionResponse extends AmazonResponse {
    private TaskDefinition taskDefinition;

    public TaskDefinition getTaskDefinition() {
        return taskDefinition;
    }

    public void setTaskDefinition(final TaskDefinition taskDefinition) {
        this.taskDefinition = taskDefinition;
    }
}
