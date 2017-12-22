package com.cloudaware.cloudmine.amazon.efs;

import java.util.Map;

/**
 * User: tolstikov
 * Date: 2/18/16
 */
public final class TagsRequest {

    private String fileSystemId;
    private Map<String, String> tags;

    public TagsRequest(final String fileSystemId, final Map<String, String> tags) {
        this.fileSystemId = fileSystemId;
        this.tags = tags;
    }

    public TagsRequest() {
    }

    public String getFileSystemId() {
        return fileSystemId;
    }

    public void setFileSystemId(final String fileSystemId) {
        this.fileSystemId = fileSystemId;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(final Map<String, String> tags) {
        this.tags = tags;
    }
}
