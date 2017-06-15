package com.cloudaware.cloudmine.amazon.workspaces;

import com.amazonaws.services.workspaces.model.WorkspaceDirectory;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 03:45
 */
public final class WorkspaceDirectoriesResponse extends AmazonResponse {
    private List<WorkspaceDirectory> workspaceDirectories;

    public List<WorkspaceDirectory> getWorkspaceDirectories() {
        return workspaceDirectories;
    }

    public void setWorkspaceDirectories(final List<WorkspaceDirectory> workspaceDirectories) {
        this.workspaceDirectories = workspaceDirectories;
    }
}
