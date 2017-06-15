package com.cloudaware.cloudmine.amazon.workspaces;

import com.amazonaws.services.workspaces.model.WorkspaceBundle;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 03:46
 */
public final class WorkspaceBundlesResponse extends AmazonResponse {
    private List<WorkspaceBundle> workspaceBundles;

    public List<WorkspaceBundle> getWorkspaceBundles() {
        return workspaceBundles;
    }

    public void setWorkspaceBundles(final List<WorkspaceBundle> workspaceBundles) {
        this.workspaceBundles = workspaceBundles;
    }
}
