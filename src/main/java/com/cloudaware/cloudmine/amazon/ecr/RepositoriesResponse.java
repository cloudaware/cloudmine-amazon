package com.cloudaware.cloudmine.amazon.ecr;

import com.amazonaws.services.ecr.model.Repository;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class RepositoriesResponse extends AmazonResponse {
    private List<Repository> repositories;

    public List<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(final List<Repository> repositories) {
        this.repositories = repositories;
    }
}
