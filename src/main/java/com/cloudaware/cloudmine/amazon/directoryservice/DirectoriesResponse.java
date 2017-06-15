package com.cloudaware.cloudmine.amazon.directoryservice;

import com.amazonaws.services.directory.model.DirectoryDescription;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DirectoriesResponse extends AmazonResponse {
    private List<DirectoryDescription> directories;

    public List<DirectoryDescription> getDirectories() {
        return directories;
    }

    public void setDirectories(final List<DirectoryDescription> directories) {
        this.directories = directories;
    }
}
