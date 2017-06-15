package com.cloudaware.cloudmine.amazon.directconnect;

import com.amazonaws.services.directconnect.model.DescribeConnectionsOnInterconnectRequest;
import com.amazonaws.services.directconnect.model.DescribeConnectionsOnInterconnectResult;
import com.amazonaws.services.directconnect.model.DescribeConnectionsRequest;
import com.amazonaws.services.directconnect.model.DescribeConnectionsResult;
import com.amazonaws.services.directconnect.model.DescribeInterconnectsRequest;
import com.amazonaws.services.directconnect.model.DescribeInterconnectsResult;
import com.amazonaws.services.directconnect.model.DescribeVirtualInterfacesRequest;
import com.amazonaws.services.directconnect.model.DescribeVirtualInterfacesResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "directconnect",
        canonicalName = "DirectConnect",
        title = "AWS Direct Connect",
        description = "Dedicated Network Connection to AWS",
        namespace = @ApiNamespace(
                ownerDomain = "cloudaware.com",
                ownerName = "CloudAware",
                packagePath = "cloudmine/amazon"
        ),
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
        apiKeyRequired = AnnotationBoolean.TRUE
)
public final class DirectConnectApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "connections.list",
            path = "{region}/connections"
    )
    public ConnectionsResponse connectionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("connectionId") @Nullable final String connectionId
    ) throws AmazonUnparsedException {
        return DirectConnectCaller.get(DescribeConnectionsRequest.class, ConnectionsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeConnectionsResult result = client.describeConnections(request.withConnectionId(connectionId));
            response.setConnections(result.getConnections());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "interconnects.list",
            path = "{region}/interconnects"
    )
    public InterconnectsResponse interconnectsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("interconnectId") @Nullable final String interconnectId
    ) throws AmazonUnparsedException {
        return DirectConnectCaller.get(DescribeInterconnectsRequest.class, InterconnectsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeInterconnectsResult result = client.describeInterconnects(request.withInterconnectId(interconnectId));
            response.setInterconnects(result.getInterconnects());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "interconnects.connections.list",
            path = "{region}/interconnects/{interconnectId}/connections"
    )
    public ConnectionsResponse interconnectsLonnectionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("interconnectId") final String interconnectId
    ) throws AmazonUnparsedException {
        return DirectConnectCaller.get(DescribeConnectionsOnInterconnectRequest.class, ConnectionsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeConnectionsOnInterconnectResult result = client.describeConnectionsOnInterconnect(request.withInterconnectId(interconnectId));
            response.setConnections(result.getConnections());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "virtualInterfaces.list",
            path = "{region}/virtual-interfaces"
    )
    public VirtualInterfacesResponse virtualInterfacesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("connectionId") @Nullable final String connectionId,
            @Named("virtualInterfaceId") @Nullable final String virtualInterfaceId
    ) throws AmazonUnparsedException {
        return DirectConnectCaller.get(DescribeVirtualInterfacesRequest.class, VirtualInterfacesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeVirtualInterfacesResult result = client.describeVirtualInterfaces(
                    request
                            .withConnectionId(connectionId)
                            .withVirtualInterfaceId(virtualInterfaceId)
            );
            response.setVirtualInterfaces(result.getVirtualInterfaces());
        });
    }
}
