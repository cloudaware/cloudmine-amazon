package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.ConnectionNotification;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class VpcEndpointConnectionNotificationsResponse extends AmazonResponse {

    private List<ConnectionNotification> notifications;

    public List<ConnectionNotification> getNotifications() {
        return notifications;
    }

    public void setNotifications(final List<ConnectionNotification> notifications) {
        this.notifications = notifications;
    }
}
