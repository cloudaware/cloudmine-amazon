package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.User;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

/**
 * User: urmuzov
 * Date: 03.22.17
 * Time: 23:25
 */
public final class UserResponse extends AmazonResponse {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
