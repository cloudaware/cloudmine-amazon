package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.User;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 23:22
 */
public final class UsersResponse extends AmazonResponse {
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(final List<User> users) {
        this.users = users;
    }
}
