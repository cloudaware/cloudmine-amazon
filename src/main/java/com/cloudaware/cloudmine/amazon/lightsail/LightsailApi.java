package com.cloudaware.cloudmine.amazon.lightsail;

import com.amazonaws.services.lightsail.model.GetDiskSnapshotsRequest;
import com.amazonaws.services.lightsail.model.GetDiskSnapshotsResult;
import com.amazonaws.services.lightsail.model.GetDisksRequest;
import com.amazonaws.services.lightsail.model.GetDisksResult;
import com.amazonaws.services.lightsail.model.GetDomainsRequest;
import com.amazonaws.services.lightsail.model.GetDomainsResult;
import com.amazonaws.services.lightsail.model.GetInstanceRequest;
import com.amazonaws.services.lightsail.model.GetInstanceResult;
import com.amazonaws.services.lightsail.model.GetInstanceSnapshotsRequest;
import com.amazonaws.services.lightsail.model.GetInstanceSnapshotsResult;
import com.amazonaws.services.lightsail.model.GetInstancesRequest;
import com.amazonaws.services.lightsail.model.GetInstancesResult;
import com.amazonaws.services.lightsail.model.GetLoadBalancersRequest;
import com.amazonaws.services.lightsail.model.GetLoadBalancersResult;
import com.amazonaws.services.lightsail.model.GetStaticIpsRequest;
import com.amazonaws.services.lightsail.model.GetStaticIpsResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "lightsail",
        canonicalName = "Lightsail",
        title = "Amazon Lightsail",
        description = "Virtual private servers made easy",
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
public final class LightsailApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "instances.list",
            path = "{region}/instances"
    )
    public InstancesResponse instancesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return LightsailCaller.get(GetInstancesRequest.class, InstancesResponse.class, credentials, region).execute((client, request, response) -> {
            final GetInstancesResult result = client.getInstances(request.withPageToken(page));
            response.setInstances(result.getInstances());
            response.setNextPage(result.getNextPageToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "instances.get",
            path = "{region}/instances/{instanceName}"
    )
    public InstanceResponse instancesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("instanceName") final String instanceName
    ) throws AmazonUnparsedException {
        return LightsailCaller.get(GetInstanceRequest.class, InstanceResponse.class, credentials, region).execute((client, request, response) -> {
            final GetInstanceResult result = client.getInstance(request.withInstanceName(instanceName));
            response.setInstance(result.getInstance());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "staticIps.list",
            path = "{region}/static-ips"
    )
    public StaticIpsResponse staticIpsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return LightsailCaller.get(GetStaticIpsRequest.class, StaticIpsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetStaticIpsResult result = client.getStaticIps(request.withPageToken(page));
            response.setStaticIps(result.getStaticIps());
            response.setNextPage(result.getNextPageToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.list",
            path = "{region}/domains"
    )
    public DomainsResponse domainsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return LightsailCaller.get(GetDomainsRequest.class, DomainsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetDomainsResult result = client.getDomains(request.withPageToken(page));
            response.setDomains(result.getDomains());
            response.setNextPage(result.getNextPageToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "loadBalancers.list",
            path = "{region}/load-balancers"
    )
    public LoadBalancersResponse loadBalancersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return LightsailCaller.get(GetLoadBalancersRequest.class, LoadBalancersResponse.class, credentials, region).execute((client, request, response) -> {
            final GetLoadBalancersResult result = client.getLoadBalancers(request.withPageToken(page));
            response.setLoadBalancers(result.getLoadBalancers());
            response.setNextPage(result.getNextPageToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "disks.list",
            path = "{region}/disks"
    )
    public DisksResponse disksList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return LightsailCaller.get(GetDisksRequest.class, DisksResponse.class, credentials, region).execute((client, request, response) -> {
            final GetDisksResult result = client.getDisks(request.withPageToken(page));
            response.setDisks(result.getDisks());
            response.setNextPage(result.getNextPageToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "diskSnapshots.list",
            path = "{region}/disk-snapshots"
    )
    public DiskSnapshotsResponse diskSnapshotsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return LightsailCaller.get(GetDiskSnapshotsRequest.class, DiskSnapshotsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetDiskSnapshotsResult result = client.getDiskSnapshots(request.withPageToken(page));
            response.setDiskSnapshots(result.getDiskSnapshots());
            response.setNextPage(result.getNextPageToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "instanceSnapshots.list",
            path = "{region}/instance-snapshots"
    )
    public InstanceSnapshotsResponse instanceSnapshotsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return LightsailCaller.get(GetInstanceSnapshotsRequest.class, InstanceSnapshotsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetInstanceSnapshotsResult result = client.getInstanceSnapshots(request.withPageToken(page));
            response.setInstanceSnapshots(result.getInstanceSnapshots());
            response.setNextPage(result.getNextPageToken());
        });
    }
}
