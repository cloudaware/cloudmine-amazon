package com.cloudaware.cloudmine.amazon.storagegateway;

import com.amazonaws.services.storagegateway.model.DescribeBandwidthRateLimitRequest;
import com.amazonaws.services.storagegateway.model.DescribeBandwidthRateLimitResult;
import com.amazonaws.services.storagegateway.model.DescribeCachediSCSIVolumesRequest;
import com.amazonaws.services.storagegateway.model.DescribeCachediSCSIVolumesResult;
import com.amazonaws.services.storagegateway.model.DescribeStorediSCSIVolumesRequest;
import com.amazonaws.services.storagegateway.model.DescribeStorediSCSIVolumesResult;
import com.amazonaws.services.storagegateway.model.DescribeTapeArchivesRequest;
import com.amazonaws.services.storagegateway.model.DescribeTapeArchivesResult;
import com.amazonaws.services.storagegateway.model.DescribeTapeRecoveryPointsRequest;
import com.amazonaws.services.storagegateway.model.DescribeTapeRecoveryPointsResult;
import com.amazonaws.services.storagegateway.model.DescribeTapesRequest;
import com.amazonaws.services.storagegateway.model.DescribeTapesResult;
import com.amazonaws.services.storagegateway.model.DescribeVTLDevicesRequest;
import com.amazonaws.services.storagegateway.model.DescribeVTLDevicesResult;
import com.amazonaws.services.storagegateway.model.ListGatewaysRequest;
import com.amazonaws.services.storagegateway.model.ListGatewaysResult;
import com.amazonaws.services.storagegateway.model.ListLocalDisksRequest;
import com.amazonaws.services.storagegateway.model.ListLocalDisksResult;
import com.amazonaws.services.storagegateway.model.ListVolumeRecoveryPointsRequest;
import com.amazonaws.services.storagegateway.model.ListVolumeRecoveryPointsResult;
import com.amazonaws.services.storagegateway.model.ListVolumesRequest;
import com.amazonaws.services.storagegateway.model.ListVolumesResult;
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
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "storagegateway",
        canonicalName = "StorageGateway",
        title = "AWS Storage Gateway",
        description = "Hybrid Storage Integration",
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
public final class StorageGatewayApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "gateways.list",
            path = "{region}/gateways"
    )
    public GatewaysResponse gatewaysList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return StorageGatewayCaller.get(ListGatewaysRequest.class, GatewaysResponse.class, credentials, region).execute((client, request, response) -> {
            final ListGatewaysResult result = client.listGateways(request.withMarker(page));
            response.setGateways(result.getGateways());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "gateways.bandwidthRateLimit.get",
            path = "{region}/gateways/GATEWAY_ARN/bandwidth-rate-limit"
    )
    public BandwidthRateLimitResponse gatewaysBandwidthRateLimitGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("gatewayArn") final String gatewayArn
    ) throws AmazonUnparsedException {
        return StorageGatewayCaller.get(DescribeBandwidthRateLimitRequest.class, BandwidthRateLimitResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeBandwidthRateLimitResult result = client.describeBandwidthRateLimit(request.withGatewayARN(gatewayArn));
            response.setAverageUploadRateLimitInBitsPerSec(result.getAverageUploadRateLimitInBitsPerSec());
            response.setAverageDownloadRateLimitInBitsPerSec(result.getAverageDownloadRateLimitInBitsPerSec());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "gateways.localDisks.list",
            path = "{region}/gateways/GATEWAY_ARN/local-disks"
    )
    public LocalDisksResponse gatewaysLocalDisksList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("gatewayArn") final String gatewayArn
    ) throws AmazonUnparsedException {
        return StorageGatewayCaller.get(ListLocalDisksRequest.class, LocalDisksResponse.class, credentials, region).execute((client, request, response) -> {
            final ListLocalDisksResult result = client.listLocalDisks(request.withGatewayARN(gatewayArn));
            response.setDisks(result.getDisks());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "gateways.volumes.list",
            path = "{region}/gateways/GATEWAY_ARN/volumes"
    )
    public VolumesResponse gatewaysVolumesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("gatewayArn") final String gatewayArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return StorageGatewayCaller.get(ListVolumesRequest.class, VolumesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListVolumesResult result = client.listVolumes(request.withGatewayARN(gatewayArn).withMarker(page));
            response.setVolumes(result.getVolumeInfos());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "cachedVolumes.list",
            path = "{region}/cached-volumes"
    )
    public CachedVolumesResponse cachedVolumesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("volumeArn") final List<String> volumeArns
    ) throws AmazonUnparsedException {
        return StorageGatewayCaller.get(DescribeCachediSCSIVolumesRequest.class, CachedVolumesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeCachediSCSIVolumesResult result = client.describeCachediSCSIVolumes(request.withVolumeARNs(volumeArns));
            response.setCachedVolumes(result.getCachediSCSIVolumes());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "storedVolumes.list",
            path = "{region}/stored-volumes"
    )
    public StoredVolumesResponse gatewaysStoredVolumesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("volumeArn") final List<String> volumeArns
    ) throws AmazonUnparsedException {
        return StorageGatewayCaller.get(DescribeStorediSCSIVolumesRequest.class, StoredVolumesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeStorediSCSIVolumesResult result = client.describeStorediSCSIVolumes(request.withVolumeARNs(volumeArns));
            response.setStoredVolumes(result.getStorediSCSIVolumes());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "gateways.volumeRecoveryPoints.list",
            path = "{region}/gateways/GATEWAY_ARN/volume-recovery-points"
    )
    public VolumeRecoveryPointsResponse gatewaysVolumeRecoveryPointsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("gatewayArn") final String gatewayArn
    ) throws AmazonUnparsedException {
        return StorageGatewayCaller.get(ListVolumeRecoveryPointsRequest.class, VolumeRecoveryPointsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListVolumeRecoveryPointsResult result = client.listVolumeRecoveryPoints(request.withGatewayARN(gatewayArn));
            response.setVolumeRecoveryPoints(result.getVolumeRecoveryPointInfos());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "gateways.tapes.list",
            path = "{region}/gateways/GATEWAY_ARN/tapes"
    )
    public TapesResponse gatewaysTapesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("gatewayArn") final String gatewayArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return StorageGatewayCaller.get(DescribeTapesRequest.class, TapesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeTapesResult result = client.describeTapes(request.withGatewayARN(gatewayArn).withMarker(page));
            response.setTapes(result.getTapes());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "gateways.tapeRecoveryPoints.list",
            path = "{region}/gateways/GATEWAY_ARN/tape-recovery-points"
    )
    public TapeRecoveryPointsResponse recoveryPointInfos(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("gatewayArn") final String gatewayArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return StorageGatewayCaller.get(DescribeTapeRecoveryPointsRequest.class, TapeRecoveryPointsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeTapeRecoveryPointsResult result = client.describeTapeRecoveryPoints(request.withGatewayARN(gatewayArn).withMarker(page));
            response.setTapeRecoveryPoints(result.getTapeRecoveryPointInfos());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "tapeArchives.list",
            path = "{region}/tape-archives"
    )
    public TapeArchivesResponse tapeArchivesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return StorageGatewayCaller.get(DescribeTapeArchivesRequest.class, TapeArchivesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeTapeArchivesResult result = client.describeTapeArchives(request.withMarker(page));
            response.setTapeArchives(result.getTapeArchives());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "gateways.vtlDevices.list",
            path = "{region}/gateways/GATEWAY_ARN/vtl-devices"
    )
    public VtlDevicesResponse gatewaysVtlDevicesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("gatewayArn") final String gatewayArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return StorageGatewayCaller.get(DescribeVTLDevicesRequest.class, VtlDevicesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeVTLDevicesResult result = client.describeVTLDevices(request.withGatewayARN(gatewayArn).withMarker(page));
            response.setVtlDevices(result.getVTLDevices());
            response.setNextPage(result.getMarker());
        });
    }
}
