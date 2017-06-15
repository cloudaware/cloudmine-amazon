package com.cloudaware.cloudmine.amazon.directconnect;

import com.amazonaws.services.directconnect.model.Connection;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 17:13
 */
public final class ConnectionsResponse extends AmazonResponse {
    private List<Connection> connections;

    public List<Connection> getConnections() {
        return connections;
    }

    public void setConnections(final List<Connection> connections) {
        this.connections = connections;
    }
}
