package com.cloudaware.cloudmine.amazon.codebuild;

import com.amazonaws.services.codebuild.model.Project;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: tolstikov
 * Date: 2/18/16
 */
public final class ProjectUpdateResponse extends AmazonResponse {

    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(final Project project) {
        this.project = project;
    }
}
