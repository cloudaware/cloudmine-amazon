package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.Group;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 01:52
 */
public final class GroupsResponse extends AmazonResponse {
    private List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(final List<Group> groups) {
        this.groups = groups;
    }
}
