package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DeleteTagsRequest;
import com.amazonaws.services.ec2.model.DescribeAccountAttributesRequest;
import com.amazonaws.services.ec2.model.DescribeAccountAttributesResult;
import com.amazonaws.services.ec2.model.DescribeAddressesRequest;
import com.amazonaws.services.ec2.model.DescribeAddressesResult;
import com.amazonaws.services.ec2.model.DescribeCustomerGatewaysRequest;
import com.amazonaws.services.ec2.model.DescribeCustomerGatewaysResult;
import com.amazonaws.services.ec2.model.DescribeDhcpOptionsRequest;
import com.amazonaws.services.ec2.model.DescribeDhcpOptionsResult;
import com.amazonaws.services.ec2.model.DescribeFlowLogsRequest;
import com.amazonaws.services.ec2.model.DescribeFlowLogsResult;
import com.amazonaws.services.ec2.model.DescribeImageAttributeRequest;
import com.amazonaws.services.ec2.model.DescribeImageAttributeResult;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.DescribeInstanceAttributeRequest;
import com.amazonaws.services.ec2.model.DescribeInstanceAttributeResult;
import com.amazonaws.services.ec2.model.DescribeInstanceStatusRequest;
import com.amazonaws.services.ec2.model.DescribeInstanceStatusResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DescribeInternetGatewaysRequest;
import com.amazonaws.services.ec2.model.DescribeInternetGatewaysResult;
import com.amazonaws.services.ec2.model.DescribeKeyPairsRequest;
import com.amazonaws.services.ec2.model.DescribeKeyPairsResult;
import com.amazonaws.services.ec2.model.DescribeNatGatewaysRequest;
import com.amazonaws.services.ec2.model.DescribeNatGatewaysResult;
import com.amazonaws.services.ec2.model.DescribeNetworkAclsRequest;
import com.amazonaws.services.ec2.model.DescribeNetworkAclsResult;
import com.amazonaws.services.ec2.model.DescribeNetworkInterfacesRequest;
import com.amazonaws.services.ec2.model.DescribeNetworkInterfacesResult;
import com.amazonaws.services.ec2.model.DescribePlacementGroupsRequest;
import com.amazonaws.services.ec2.model.DescribePlacementGroupsResult;
import com.amazonaws.services.ec2.model.DescribeReservedInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeReservedInstancesResult;
import com.amazonaws.services.ec2.model.DescribeRouteTablesRequest;
import com.amazonaws.services.ec2.model.DescribeRouteTablesResult;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsRequest;
import com.amazonaws.services.ec2.model.DescribeSecurityGroupsResult;
import com.amazonaws.services.ec2.model.DescribeSnapshotAttributeRequest;
import com.amazonaws.services.ec2.model.DescribeSnapshotAttributeResult;
import com.amazonaws.services.ec2.model.DescribeSnapshotsRequest;
import com.amazonaws.services.ec2.model.DescribeSnapshotsResult;
import com.amazonaws.services.ec2.model.DescribeSubnetsRequest;
import com.amazonaws.services.ec2.model.DescribeSubnetsResult;
import com.amazonaws.services.ec2.model.DescribeVolumeStatusRequest;
import com.amazonaws.services.ec2.model.DescribeVolumeStatusResult;
import com.amazonaws.services.ec2.model.DescribeVolumesRequest;
import com.amazonaws.services.ec2.model.DescribeVolumesResult;
import com.amazonaws.services.ec2.model.DescribeVpcsRequest;
import com.amazonaws.services.ec2.model.DescribeVpcsResult;
import com.amazonaws.services.ec2.model.DescribeVpnConnectionsRequest;
import com.amazonaws.services.ec2.model.DescribeVpnConnectionsResult;
import com.amazonaws.services.ec2.model.DescribeVpnGatewaysRequest;
import com.amazonaws.services.ec2.model.DescribeVpnGatewaysResult;
import com.amazonaws.services.ec2.model.RebootInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesResult;
import com.cloudaware.cloudmine.amazon.AmazonResponse;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "ec2",
        canonicalName = "Ec2",
        title = "Amazon EC2",
        description = "Virtual Servers in the Cloud",
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
public final class Ec2Api {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "accountAttributes.list",
            path = "{region}/account-attributes"
    )
    public AccountAttributesResponse accountAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeAccountAttributesRequest.class, AccountAttributesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeAccountAttributesResult result = client.describeAccountAttributes(request);
            response.setAccountAttributes(result.getAccountAttributes());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dhcpOptions.list",
            path = "{region}/dhcp-options"
    )
    public DhcpOptionsResponse dhcpOptionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeDhcpOptionsRequest.class, DhcpOptionsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeDhcpOptionsResult result = client.describeDhcpOptions(request);
            response.setDhcpOptions(result.getDhcpOptions());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "elasticIps.list",
            path = "{region}/elastic-ips"
    )
    public ElasticIpsResponse elasticIpsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeAddressesRequest.class, ElasticIpsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeAddressesResult result = client.describeAddresses(request);
            response.setElasticIps(result.getAddresses());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "flowLogs.list",
            path = "{region}/flow-logs"
    )
    public FlowLogsResponse flowLogsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeFlowLogsRequest.class, FlowLogsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeFlowLogsResult result = client.describeFlowLogs(request.withNextToken(page));
            response.setFlowLogs(result.getFlowLogs());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "images.list",
            path = "{region}/images"
    )
    public ImagesResponse imagesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("owner") final String owner
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeImagesRequest.class, ImagesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeImagesResult result = client.describeImages(request.withOwners(owner));
            response.setImages(result.getImages());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "images.attributes.get",
            path = "{region}/images/{imageId}/attributes/{attributeName}"
    )
    public ImageAttributeResponse imagesAttributesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("imageId") final String imageId,
            @Named("attributeName") final String attributeName
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeImageAttributeRequest.class, ImageAttributeResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeImageAttributeResult result = client.describeImageAttribute(request.withImageId(imageId).withAttribute(attributeName));
            response.setImageAttribute(result.getImageAttribute());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "instances.attributes.get",
            path = "{region}/instances/{instanceId}/attributes/{attributeName}"
    )
    public InstanceAttributeResponse instancesAttributesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("instanceId") final String instanceId,
            @Named("attributeName") final String attributeName
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeInstanceAttributeRequest.class, InstanceAttributeResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeInstanceAttributeResult result = client.describeInstanceAttribute(
                    request
                            .withInstanceId(instanceId)
                            .withAttribute(attributeName)
            );
            response.setInstanceAttribute(result.getInstanceAttribute());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "instances.list",
            path = "{region}/instances"
    )
    public InstancesResponse instancesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("instanceId") @Nullable final List<String> instanceIds,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeInstancesRequest.class, InstancesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeInstancesResult result = client.describeInstances(
                    request
                            .withInstanceIds(instanceIds)
                            .withNextToken(page)
            );
            response.setReservations(result.getReservations());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "instances.run",
            path = "{region}/instances"
    )
    public ReservationResponse instancesRun(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final InstancesRequest request
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(RunInstancesRequest.class, ReservationResponse.class, credentials, region).execute((client, runInstancesRequest, response) -> {
            runInstancesRequest.setImageId(request.getImageId());
            runInstancesRequest.setMinCount(request.getMinCount());
            runInstancesRequest.setMaxCount(request.getMaxCount());
            runInstancesRequest.setKeyName(request.getKeyName());
            runInstancesRequest.setSecurityGroups(request.getSecurityGroups());
            runInstancesRequest.setSecurityGroupIds(request.getSecurityGroupIds());
            runInstancesRequest.setUserData(request.getUserData());
            runInstancesRequest.setInstanceType(request.getInstanceType());
            runInstancesRequest.setPlacement(request.getPlacement());
            runInstancesRequest.setKernelId(request.getKernelId());
            runInstancesRequest.setRamdiskId(request.getRamdiskId());
            runInstancesRequest.setBlockDeviceMappings(request.getBlockDeviceMappings());
            runInstancesRequest.setMonitoring(request.getMonitoring());
            runInstancesRequest.setSubnetId(request.getSubnetId());
            runInstancesRequest.setDisableApiTermination(request.getDisableApiTermination());
            runInstancesRequest.setInstanceInitiatedShutdownBehavior(request.getInstanceInitiatedShutdownBehavior());
            runInstancesRequest.setPrivateIpAddress(request.getPrivateIpAddress());
            runInstancesRequest.setIpv6Addresses(request.getIpv6Addresses());
            runInstancesRequest.setIpv6AddressCount(request.getIpv6AddressCount());
            runInstancesRequest.setClientToken(request.getClientToken());
            runInstancesRequest.setAdditionalInfo(request.getAdditionalInfo());
            runInstancesRequest.setNetworkInterfaces(request.getNetworkInterfaces());
            runInstancesRequest.setIamInstanceProfile(request.getIamInstanceProfile());
            runInstancesRequest.setEbsOptimized(request.getEbsOptimized());
            final RunInstancesResult result = client.runInstances(runInstancesRequest);
            response.setReservation(result.getReservation());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "instances.start",
            path = "{region}/instances/{instanceId}/start"
    )
    public InstanceStateChangeResponse instancesStart(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("instanceId") final String instanceId
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(StartInstancesRequest.class, InstanceStateChangeResponse.class, credentials, region).execute((client, request, response) -> {
            final StartInstancesResult result = client.startInstances(
                    request
                            .withInstanceIds(instanceId)
            );
            response.setInstanceStateChange(result.getStartingInstances().get(0));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "instances.stop",
            path = "{region}/instances/{instanceId}/stop"
    )
    public InstanceStateChangeResponse instancesStop(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("instanceId") final String instanceId
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(StopInstancesRequest.class, InstanceStateChangeResponse.class, credentials, region).execute((client, request, response) -> {
            final StopInstancesResult result = client.stopInstances(
                    request
                            .withInstanceIds(instanceId)
            );
            response.setInstanceStateChange(result.getStoppingInstances().get(0));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "instances.reboot",
            path = "{region}/instances/{instanceId}/reboot"
    )
    public AmazonResponse instancesReboot(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("instanceId") final String instanceId
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(RebootInstancesRequest.class, AmazonResponse.class, credentials, region).execute((client, request, response) -> {
            client.rebootInstances(request.withInstanceIds(instanceId));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "instanceStatuses.list",
            path = "{region}/instance-statuses"
    )
    public InstanceStatusesResponse instanceStatusesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeInstanceStatusRequest.class, InstanceStatusesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeInstanceStatusResult result = client.describeInstanceStatus(request.withNextToken(page));
            response.setInstanceStatuses(result.getInstanceStatuses());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "internetGateways.list",
            path = "{region}/internet-gateways"
    )
    public InternetGatewaysResponse internetGatewaysList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeInternetGatewaysRequest.class, InternetGatewaysResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeInternetGatewaysResult result = client.describeInternetGateways(request);
            response.setInternetGateways(result.getInternetGateways());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "keyPairs.list",
            path = "{region}/key-pairs"
    )
    public KeyPairsResponse keyPairsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("keyName") @Nullable final List<String> keyNames
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeKeyPairsRequest.class, KeyPairsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeKeyPairsResult result = client.describeKeyPairs(request.withKeyNames(keyNames));
            response.setKeyPairs(result.getKeyPairs());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "keyPairs.create",
            path = "{region}/key-pairs"
    )
    public KeyPairResponse keyPairsCreate(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("keyName") final String keyName
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(CreateKeyPairRequest.class, KeyPairResponse.class, credentials, region).execute((client, request, response) -> {
            final CreateKeyPairResult result = client.createKeyPair(request.withKeyName(keyName));
            response.setKeyPair(result.getKeyPair());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "networkAcls.list",
            path = "{region}/network-acls"
    )
    public NetworkAclsResponse networkAclsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeNetworkAclsRequest.class, NetworkAclsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeNetworkAclsResult result = client.describeNetworkAcls(request);
            response.setNetworkAcls(result.getNetworkAcls());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "networkInterfaces.list",
            path = "{region}/network-interfaces"
    )
    public NetworkInterfacesResponse networkInterfacesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeNetworkInterfacesRequest.class, NetworkInterfacesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeNetworkInterfacesResult result = client.describeNetworkInterfaces(request);
            response.setNetworkInterfaces(result.getNetworkInterfaces());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "placementGroups.list",
            path = "{region}/placement-groups"
    )
    public PlacementGroupsResponse placementGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribePlacementGroupsRequest.class, PlacementGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribePlacementGroupsResult result = client.describePlacementGroups(request);
            response.setPlacementGroups(result.getPlacementGroups());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "reservedInstances.list",
            path = "{region}/reserved-instances"
    )
    public ReservedInstancesResponse reservedInstancesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeReservedInstancesRequest.class, ReservedInstancesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeReservedInstancesResult result = client.describeReservedInstances(request);
            response.setReservedInstances(result.getReservedInstances());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "routeTables.list",
            path = "{region}/route-tables"
    )
    public RouteTablesResponse routeTablesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeRouteTablesRequest.class, RouteTablesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeRouteTablesResult result = client.describeRouteTables(request);
            response.setRouteTables(result.getRouteTables());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "securityGroups.list",
            path = "{region}/security-groups"
    )
    public SecurityGroupsResponse securityGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeSecurityGroupsRequest.class, SecurityGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeSecurityGroupsResult result = client.describeSecurityGroups(request);
            response.setSecurityGroups(result.getSecurityGroups());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "snapshots.list",
            path = "{region}/snapshots"
    )
    public SnapshotsResponse ownedBySelf(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("owner") final String owner,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeSnapshotsRequest.class, SnapshotsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeSnapshotsResult result = client.describeSnapshots(
                    request
                            .withOwnerIds(owner)
                            .withNextToken(page)
            );
            response.setSnapshots(result.getSnapshots());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "subnets.list",
            path = "{region}/subnets"
    )
    public SubnetsResponse subnetsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeSubnetsRequest.class, SubnetsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeSubnetsResult result = client.describeSubnets(request);
            response.setSubnets(result.getSubnets());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "snapshots.attributes.get",
            path = "{region}/snapshots/{snapshotId}/attributes/{attributeName}"
    )
    public SnapshotAttributeResponse snapshotsAttributesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("snapshotId") final String snapshotId,
            @Named("attributeName") final String attributeName
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeSnapshotAttributeRequest.class, SnapshotAttributeResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeSnapshotAttributeResult result = client.describeSnapshotAttribute(request.withSnapshotId(snapshotId).withAttribute(attributeName));
            response.setCreateVolumePermissions(result.getCreateVolumePermissions());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.create",
            path = "{region}/tags/create"
    )
    public AmazonResponse tagsCreate(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest tagsRequest
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(CreateTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, request, response) -> {
            request.withResources(tagsRequest.getResources());
            request.withTags(tagsRequest.getTags());
            client.createTags(request);
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.delete",
            path = "{region}/tags/delete"
    )
    public AmazonResponse tagsDelete(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest tagsRequest
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DeleteTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, request, response) -> {
            request.withResources(tagsRequest.getResources());
            request.withTags(tagsRequest.getTags());
            client.deleteTags(request);
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "volumes.list",
            path = "{region}/volumes"
    )
    public VolumesResponse volumesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeVolumesRequest.class, VolumesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeVolumesResult result = client.describeVolumes(request.withNextToken(page));
            response.setVolumes(result.getVolumes());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "volumeStatuses.list",
            path = "{region}/volume-statuses"
    )
    public VolumeStatusesResponse volumeStatusesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeVolumeStatusRequest.class, VolumeStatusesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeVolumeStatusResult result = client.describeVolumeStatus(request.withNextToken(page));
            response.setVolumeStatuses(result.getVolumeStatuses());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "vpcs.list",
            path = "{region}/vpcs"
    )
    public VpcsResponse vpcsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeVpcsRequest.class, VpcsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeVpcsResult result = client.describeVpcs(request);
            response.setVpcs(result.getVpcs());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "vpnConnections.list",
            path = "{region}/vpn-connections"
    )
    public VpnConnectionsResponse vpnConnectionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeVpnConnectionsRequest.class, VpnConnectionsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeVpnConnectionsResult result = client.describeVpnConnections(request);
            response.setVpnConnections(result.getVpnConnections());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "vpnGateways.list",
            path = "{region}/vpn-gateways"
    )
    public VpnGatewaysResponse vpnGatewaysList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeVpnGatewaysRequest.class, VpnGatewaysResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeVpnGatewaysResult result = client.describeVpnGateways(request);
            response.setVpnGateways(result.getVpnGateways());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "customerGateways.list",
            path = "{region}/customer-gateways"
    )
    public CustomerGatewaysResponse customerGatewaysList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeCustomerGatewaysRequest.class, CustomerGatewaysResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeCustomerGatewaysResult result = client.describeCustomerGateways(request);
            response.setCustomerGateways(result.getCustomerGateways());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "natGateways.list",
            path = "{region}/nat-gateways"
    )
    public NatGatewaysResponse natGatewaysList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return Ec2Caller.get(DescribeNatGatewaysRequest.class, NatGatewaysResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeNatGatewaysResult result = client.describeNatGateways(request.withNextToken(page));
            response.setNatGateways(result.getNatGateways());
            response.setNextPage(page);
        });
    }
}
