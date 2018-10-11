package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.BucketCrossOriginConfiguration;
import com.amazonaws.services.s3.model.BucketNotificationConfiguration;
import com.amazonaws.services.s3.model.BucketPolicy;
import com.amazonaws.services.s3.model.BucketReplicationConfiguration;
import com.amazonaws.services.s3.model.BucketTaggingConfiguration;
import com.amazonaws.services.s3.model.GetBucketAccelerateConfigurationRequest;
import com.amazonaws.services.s3.model.GetBucketAclRequest;
import com.amazonaws.services.s3.model.GetBucketVersioningConfigurationRequest;
import com.amazonaws.services.s3.model.GetObjectAclRequest;
import com.amazonaws.services.s3.model.GetObjectTaggingRequest;
import com.amazonaws.services.s3.model.GetObjectTaggingResult;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ListBucketAnalyticsConfigurationsRequest;
import com.amazonaws.services.s3.model.ListBucketAnalyticsConfigurationsResult;
import com.amazonaws.services.s3.model.ListBucketInventoryConfigurationsRequest;
import com.amazonaws.services.s3.model.ListBucketInventoryConfigurationsResult;
import com.amazonaws.services.s3.model.ListBucketMetricsConfigurationsRequest;
import com.amazonaws.services.s3.model.ListBucketMetricsConfigurationsResult;
import com.amazonaws.services.s3.model.ListMultipartUploadsRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ListPartsRequest;
import com.amazonaws.services.s3.model.ListVersionsRequest;
import com.amazonaws.services.s3.model.MultipartUploadListing;
import com.amazonaws.services.s3.model.ObjectTagging;
import com.amazonaws.services.s3.model.PartListing;
import com.amazonaws.services.s3.model.SetBucketTaggingConfigurationRequest;
import com.amazonaws.services.s3.model.SetObjectTaggingRequest;
import com.amazonaws.services.s3.model.Tag;
import com.amazonaws.services.s3.model.TagSet;
import com.amazonaws.services.s3.model.VersionListing;
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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.stream.Collectors;

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
            name = "buckets.acl.get",
            path = "{region}/bucket/{bucketName}/acl"
    )
    public AclResponse bucketsAclGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final AccessControlList acl = clientWrapper.getClient()
                    .getBucketAcl(new GetBucketAclRequest(bucketName));
            final AccessControlListParsableByGoogleParser aclParseable = new AccessControlListParsableByGoogleParser();
            aclParseable.setRequesterCharged(acl.isRequesterCharged());
            aclParseable.setGrants(Lists.newArrayList());
            if (acl.getGrantsAsList() != null) {
                processGrants(acl, aclParseable);
            }

            return new AclResponse(aclParseable);
        } catch (Throwable t) {
            return new AclResponse(AmazonResponse.parse(t, "s3:GetBucketAclRequest"));
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

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.encryptionConfiguration.get",
            path = "{region}/bucket/{bucketName}/encryption-configuration"
    )
    public EncryptionConfigurationResponse bucketsEncryptionConfigurationGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final com.amazonaws.services.s3.model.GetBucketEncryptionResult bucketEncryptionResult = clientWrapper.getClient().getBucketEncryption(bucketName);
            return new EncryptionConfigurationResponse(bucketEncryptionResult.getServerSideEncryptionConfiguration());
        } catch (Throwable t) {
            return new EncryptionConfigurationResponse(AmazonResponse.parse(t, "s3:GetEncryptionConfiguration"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.websiteConfiguration.get",
            path = "{region}/bucket/{bucketName}/website-configuration"
    )
    public WebsiteConfigurationResponse bucketsWebsiteConfigurationGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final com.amazonaws.services.s3.model.BucketWebsiteConfiguration result = clientWrapper.getClient().getBucketWebsiteConfiguration(bucketName);
            if (result != null) {
                return new WebsiteConfigurationResponse(
                        result.getErrorDocument(),
                        result.getRedirectAllRequestsTo(),
                        result.getIndexDocumentSuffix(),
                        result.getRoutingRules()
                );
            }

            return new WebsiteConfigurationResponse();
        } catch (Throwable t) {
            return new WebsiteConfigurationResponse(AmazonResponse.parse(t, "s3:GetBucketWebsite"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.lifecycleConfiguration.get",
            path = "{region}/bucket/{bucketName}/lifecycle-configuration"
    )
    public LifecycleConfigurationResponse bucketsLifecycleConfigurationGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final com.amazonaws.services.s3.model.BucketLifecycleConfiguration result = clientWrapper.getClient().getBucketLifecycleConfiguration(bucketName);
            if (result != null) {
                return new LifecycleConfigurationResponse(
                        result.getRules()
                );
            }

            return new LifecycleConfigurationResponse();
        } catch (Throwable t) {
            return new LifecycleConfigurationResponse(AmazonResponse.parse(t, "s3:GetLifecycleConfiguration"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.analyticsConfiguration.list",
            path = "{region}/bucket/analytics-configuration"
    )
    public AnalyticsConfigurationsResponse analyticsConfigurationsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final ListBucketAnalyticsConfigurationsResult result = clientWrapper.getClient().listBucketAnalyticsConfigurations(
                    new ListBucketAnalyticsConfigurationsRequest()
                            .withBucketName(bucketName)
                            .withContinuationToken(page)
            );
            if (result != null) {
                return new AnalyticsConfigurationsResponse(result.getAnalyticsConfigurationList(), result.getNextContinuationToken());
            }
            return new AnalyticsConfigurationsResponse(null, null);
        } catch (Throwable t) {
            return new AnalyticsConfigurationsResponse(AmazonResponse.parse(t, "s3:ListAnalyticsConfigurations"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.crossOriginConfiguration.get",
            path = "{region}/bucket/{bucketName}/cross-origin-configuration"
    )
    public CrossOriginConfigurationResponse crossOriginConfigurationGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final BucketCrossOriginConfiguration crossOriginConfiguration = clientWrapper.getClient().getBucketCrossOriginConfiguration(bucketName);
            if (crossOriginConfiguration != null) {
                return new CrossOriginConfigurationResponse(
                        crossOriginConfiguration.getRules().stream().map(rule -> {
                            final CorsRule corsRule = new CorsRule();
                            corsRule.setAllowedHeaders(rule.getAllowedHeaders());
                            corsRule.setAllowedMethods(rule.getAllowedMethods());
                            corsRule.setAllowedOrigins(rule.getAllowedOrigins());
                            corsRule.setExposedHeaders(rule.getExposedHeaders());
                            corsRule.setId(rule.getId());
                            corsRule.setMaxAgeSeconds(rule.getMaxAgeSeconds());
                            return corsRule;
                        }).collect(Collectors.toList())
                );
            }
            return new CrossOriginConfigurationResponse(Lists.newArrayList());
        } catch (Throwable t) {
            return new CrossOriginConfigurationResponse(AmazonResponse.parse(t, "s3:GetCrossOriginConfiguration"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.inventoryConfiguration.list",
            path = "{region}/bucket/inventory-configuration"
    )
    public InventoryConfigurationsResponse inventoryConfigurationsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final ListBucketInventoryConfigurationsResult result = clientWrapper.getClient().listBucketInventoryConfigurations(
                    new ListBucketInventoryConfigurationsRequest()
                            .withBucketName(bucketName)
                            .withContinuationToken(page)
            );
            if (result != null) {
                return new InventoryConfigurationsResponse(result.getInventoryConfigurationList(), result.getNextContinuationToken());
            }
            return new InventoryConfigurationsResponse(null, null);
        } catch (Throwable t) {
            return new InventoryConfigurationsResponse(AmazonResponse.parse(t, "s3:ListInventoryConfigurations"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.metricsConfiguration.list",
            path = "{region}/bucket/metrics-configuration"
    )
    public MetricsConfigurationsResponse metricsConfigurationsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final ListBucketMetricsConfigurationsResult result = clientWrapper.getClient().listBucketMetricsConfigurations(
                    new ListBucketMetricsConfigurationsRequest()
                            .withBucketName(bucketName)
                            .withContinuationToken(page)
            );
            if (result != null) {
                return new MetricsConfigurationsResponse(result.getMetricsConfigurationList(), result.getNextContinuationToken());
            }
            return new MetricsConfigurationsResponse(null, null);
        } catch (Throwable t) {
            return new MetricsConfigurationsResponse(AmazonResponse.parse(t, "s3:ListMetricsConfigurations"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.multipartUploads.list",
            path = "{region}/bucket/multipart-uploads"
    )
    public MultipartUploadsResponse multipartUploadsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName,
            @Named("prefix") @Nullable final String prefix,
            @Named("delimiter") @Nullable final String delimiter,
            @Named("pageSize") @Nullable final Integer pageSize,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final MultipartUploadListing result = clientWrapper.getClient().listMultipartUploads(
                    new ListMultipartUploadsRequest(bucketName)
                            .withKeyMarker(page)
                            .withBucketName(bucketName)
                            .withDelimiter(delimiter)
                            .withMaxUploads(pageSize)
                            .withPrefix(prefix)
            );
            if (result != null) {
                return new MultipartUploadsResponse(result.getMultipartUploads(), result.getNextKeyMarker());
            }
            return new MultipartUploadsResponse(null, null);
        } catch (Throwable t) {
            return new MultipartUploadsResponse(AmazonResponse.parse(t, "s3:ListMultipartUploads"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.notificationConfiguration.get",
            path = "{region}/bucket/{bucketName}/notification-configuration"
    )
    public NotificationConfigurationResponse notificationConfigurationGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final BucketNotificationConfiguration result = clientWrapper.getClient().getBucketNotificationConfiguration(bucketName);
            if (result != null) {
                return new NotificationConfigurationResponse(result.getConfigurations());
            }
            return new NotificationConfigurationResponse(Maps.newHashMap());
        } catch (Throwable t) {
            return new NotificationConfigurationResponse(AmazonResponse.parse(t, "s3:GetNotificationConfiguration"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.objectVersions.list",
            path = "{region}/bucket/object-versions"
    )
    public ObjectVersionsResponse objectVersionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName,
            @Named("prefix") @Nullable final String prefix,
            @Named("delimiter") @Nullable final String delimiter,
            @Named("pageSize") @Nullable final Integer pageSize,
            @Named("page") @Nullable final String page

    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final VersionListing result = clientWrapper.getClient().listVersions(
                    new ListVersionsRequest()
                            .withBucketName(bucketName)
                            .withDelimiter(delimiter)
                            .withKeyMarker(page)
                            .withMaxResults(pageSize)
                            .withPrefix(prefix)
            );
            if (result != null) {
                return new ObjectVersionsResponse(result.getVersionSummaries(), result.getNextKeyMarker());
            }
            return new ObjectVersionsResponse(null, null);
        } catch (Throwable t) {
            return new ObjectVersionsResponse(AmazonResponse.parse(t, "s3:ListObjectVersions"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.replicationConfiguration.get",
            path = "{region}/bucket/{bucketName}/replication-configuration"
    )
    public ReplicationConfigurationResponse replicationConfigurationGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final BucketReplicationConfiguration result = clientWrapper.getClient().getBucketReplicationConfiguration(bucketName);
            if (result != null) {
                return new ReplicationConfigurationResponse(result.getRules(), result.getRoleARN());
            }
            return new ReplicationConfigurationResponse(null, null);
        } catch (Throwable t) {
            return new ReplicationConfigurationResponse(AmazonResponse.parse(t, "s3:GetReplicationConfiguration"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.objects.parts.list",
            path = "{region}/bucket/{bucketName}/objects/{key}/parts"
    )
    public PartsResponse partsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName,
            @Named("key") final String key,
            @Named("uploadId") final String uploadId,
            @Named("page") @Nullable final String page,
            @Named("pageSize") @Nullable final Integer pageSize
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final PartListing result = clientWrapper.getClient().listParts(
                    new ListPartsRequest(bucketName, key, uploadId).withMaxParts(pageSize).withPartNumberMarker(Integer.parseInt(page))
            );
            if (result != null) {
                return new PartsResponse(result, result.getNextPartNumberMarker().toString());
            }
            return new PartsResponse(null, null);
        } catch (Throwable t) {
            return new PartsResponse(AmazonResponse.parse(t, "s3:ListParts"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.objects.acl.get",
            path = "{region}/bucket/{bucketName}/objects/{key}/acl"
    )
    public AclResponse objectAclGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName,
            @Named("key") final String key,
            @Named("versionId") @Nullable final String versionId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final AccessControlList acl = clientWrapper.getClient()
                    .getObjectAcl(new GetObjectAclRequest(bucketName, key, versionId));
            final AccessControlListParsableByGoogleParser aclParseable = new AccessControlListParsableByGoogleParser();
            aclParseable.setRequesterCharged(acl.isRequesterCharged());
            aclParseable.setGrants(Lists.newArrayList());

            if (acl.getGrantsAsList() == null) {
                return new AclResponse(aclParseable);
            }

            processGrants(acl, aclParseable);

            return new AclResponse(aclParseable);
        } catch (Throwable t) {
            return new AclResponse(AmazonResponse.parse(t, "s3:GetObjectAcl"));
        }
    }

    private void processGrants(final AccessControlList acl, final AccessControlListParsableByGoogleParser aclParseable) {
        for (final com.amazonaws.services.s3.model.Grant grant : acl.getGrantsAsList()) {
            if (grant.getGrantee() instanceof GroupGrantee) {
                final GroupGrantee originalGrantee = (GroupGrantee) grant.getGrantee();
                final com.cloudaware.cloudmine.amazon.s3.GroupGrantee grantee = new com.cloudaware.cloudmine.amazon.s3.GroupGrantee();
                grantee.setGroupName(originalGrantee.name());
                grantee.setIdentifier(originalGrantee.getIdentifier());
                final com.amazonaws.services.s3.model.Grant awsGrant = new com.amazonaws.services.s3.model.Grant(grantee, grant.getPermission());
                aclParseable.getGrants().add(new Grant(awsGrant));
            } else {
                aclParseable.getGrants().add(new Grant(grant));
            }
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buckets.objects.tags.get",
            path = "{region}/bucket/{bucketName}/objects/{key}/tags"
    )
    public ObjectTagsResponse objectTagsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName,
            @Named("key") final String key,
            @Named("versionId") @Nullable final String versionId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            final GetObjectTaggingResult result = clientWrapper.getClient().getObjectTagging(
                    new GetObjectTaggingRequest(bucketName, key, versionId)
            );
            return new ObjectTagsResponse(result == null ? null : result.getTagSet());
        } catch (Throwable t) {
            return new ObjectTagsResponse(AmazonResponse.parse(t, "s3:GetObjectTagging"));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "buckets.objects.tags.set",
            path = "{region}/bucket/{bucketName}/objects/{key}/tags"
    )
    public AmazonResponse objectTagsSet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("bucketName") final String bucketName,
            @Named("key") final String key,
            @Named("versionId") @Nullable final String versionId,
            final TagsRequest tagsRequest
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AmazonS3> clientWrapper = new AmazonClientHelper(credentials).getS3(region)) {
            if (tagsRequest.getTags() == null || tagsRequest.getTags().size() == 0) {
                return new AmazonResponse();
            }
            clientWrapper.getClient().setObjectTagging(
                    new SetObjectTaggingRequest(
                            bucketName,
                            key,
                            versionId,
                            new ObjectTagging(
                                    tagsRequest.getTags().entrySet().stream()
                                            .map(entry -> new Tag(entry.getKey(), entry.getValue()))
                                            .collect(Collectors.toList())
                            )
                    )
            );
            return new AmazonResponse();
        } catch (Throwable t) {
            return new AmazonResponse(AmazonResponse.parse(t, "s3:SetObjectTagging"));
        }
    }
}
