package com.cloudaware.cloudmine.amazon.ecs;

import com.amazonaws.services.ecs.model.Task;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.21.17
 * Time: 15:06
 */
public final class TasksResponse extends AmazonResponse {
    private List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(final List<Task> tasks) {
        this.tasks = tasks;
    }
}
