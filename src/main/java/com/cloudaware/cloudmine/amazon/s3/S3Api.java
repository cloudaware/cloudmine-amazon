package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.BucketPolicy;
import com.amazonaws.services.s3.model.BucketTaggingConfiguration;
import com.amazonaws.services.s3.model.GetBucketAccelerateConfigurationRequest;
import com.amazonaws.services.s3.model.GetBucketVersioningConfigurationRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.SetBucketTaggingConfigurationRequest;
import com.amazonaws.services.s3.model.TagSet;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.ClientWrapper;
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
        name = "s3",
        canonicalName = "S3",
        title = "Amazon S3",
        description = "Scalable Storage in the Cloud",
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
public final class S3Api {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.list",
            path = "{region}/bucket"
    )
    public BucketsResponse bucketsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final List<Bucket> out = clientWrapper.getClient().listBuckets();
            return new BucketsResponse(out);
        } catch (Throwable t) {
            return new BucketsResponse(AmazonResponse.parse(t, "s3:ListBuckets"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.loggingConfiguration.get",
            path = "{region}/bucket/{bucketName}/logging-configuration"
    )
    public LoggingConfigurationResponse bucketsLoggingConfigurationGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final com.amazonaws.services.s3.model.BucketLoggingConfiguration loggingConfiguration = clientWrapper.getClient().getBucketLoggingConfiguration(bucketName);
            return new LoggingConfigurationResponse(loggingConfiguration);
        } catch (Throwable t) {
            return new LoggingConfigurationResponse(AmazonResponse.parse(t, "s3:GetBucketLoggingConfiguration"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.policy.get",
            path = "{region}/bucket/{bucketName}/policy"
    )
    public PolicyResponse bucketsPolicyGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final BucketPolicy bucketPolicy = clientWrapper.getClient().getBucketPolicy(bucketName);
            return new PolicyResponse(bucketPolicy.getPolicyText());
        } catch (Throwable t) {
            return new PolicyResponse(AmazonResponse.parse(t, "s3:GetBucketPolicy"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.location.get",
            path = "{region}/bucket/{bucketName}/location"
    )
    public LocationResponse bucketsLocationGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final String location = clientWrapper.getClient().getBucketLocation(bucketName);
            return new LocationResponse(location);
        } catch (Throwable t) {
            return new LocationResponse(AmazonResponse.parse(t, "s3:GetBucketLocation"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.accelerateConfiguration.get",
            path = "{region}/bucket/{bucketName}/accelerate-configuration"
    )
    public AccelerateConfigurationResponse bucketsAccelerateConfigurationGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final com.amazonaws.services.s3.model.BucketAccelerateConfiguration configuration = clientWrapper.getClient()
                    .getBucketAccelerateConfiguration(new GetBucketAccelerateConfigurationRequest(bucketName));
            return new AccelerateConfigurationResponse(configuration);
        } catch (Throwable t) {
            return new AccelerateConfigurationResponse(AmazonResponse.parse(t, "s3:GetBucketAccelerateConfiguration"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.versioningConfiguration.get",
            path = "{region}/bucket/{bucketName}/versioning-configuration"
    )
    public VersioningConfigurationResponse bucketsVersioningConfigurationGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final com.amazonaws.services.s3.model.BucketVersioningConfiguration configuration = clientWrapper.getClient()
                    .getBucketVersioningConfiguration(new GetBucketVersioningConfigurationRequest(bucketName));
            return new VersioningConfigurationResponse(configuration);
        } catch (Throwable t) {
            return new VersioningConfigurationResponse(AmazonResponse.parse(t, "s3:GetBucketVersioningConfiguration"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.tags.get",
            path = "{region}/bucket/{bucketName}/tags"
    )
    public TagsResponse bucketsTagsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final BucketTaggingConfiguration tagging = clientWrapper.getClient().getBucketTaggingConfiguration(bucketName);
            return new TagsResponse(tagging == null ? null : tagging.getAllTagSets());
        } catch (Throwable t) {
            return new TagsResponse(AmazonResponse.parse(t, "s3:GetBucketTaggingConfiguration"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "buckets.tags.set",
            path = "{region}/bucket/{bucketName}/tags"
    )
    public AmazonResponse bucketsTagsSet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final BucketTaggingConfiguration configuration = new BucketTaggingConfiguration();
            if (request.getTags() != null && request.getTags().size() != 0) {
                configuration.withTagSets(new TagSet(request.getTags()));
            }
            clientWrapper.getClient().setBucketTaggingConfiguration(new SetBucketTaggingConfigurationRequest(bucketName, configuration));
            return new AmazonResponse();
        } catch (Throwable t) {
            return new AmazonResponse(AmazonResponse.parse(t, "s3:SetBucketTaggingConfiguration"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.objects.list",
            path = "{region}/bucket/{bucketName}/objects"
    )
    public ObjectsResponse bucketsObjectsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName,
            @Named("prefix") @Nullable final String prefix,
            @Named("delimiter") @Nullable final String delimiter,
            @Named("page") @Nullable final String page,
            @Named("pageSize") @Nullable final Integer pageSize
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final com.amazonaws.services.s3.model.ObjectListing objectListing = clientWrapper.getClient().listObjects(
                    new ListObjectsRequest().withBucketName(bucketName)
                            .withPrefix(prefix)
                            .withDelimiter(delimiter)
                            .withMarker(page)
                            .withMaxKeys(pageSize)
            );
            return new ObjectsResponse(objectListing, objectListing.getNextMarker());
        } catch (Throwable t) {
            return new ObjectsResponse(AmazonResponse.parse(t, "s3:ListObjects"));
        }
    }
}
