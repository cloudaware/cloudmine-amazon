package com.cloudaware.cloudmine.amazon.route53;

import com.amazonaws.services.route53.model.ChangeTagsForResourceRequest;
import com.amazonaws.services.route53.model.InvalidArgumentException;
import com.amazonaws.services.route53.model.ListHostedZonesRequest;
import com.amazonaws.services.route53.model.ListHostedZonesResult;
import com.amazonaws.services.route53.model.ListResourceRecordSetsRequest;
import com.amazonaws.services.route53.model.ListResourceRecordSetsResult;
import com.amazonaws.services.route53.model.ListTagsForResourceRequest;
import com.amazonaws.services.route53.model.ListTagsForResourceResult;
import com.amazonaws.services.route53.model.Tag;
import com.cloudaware.cloudmine.amazon.AmazonResponse;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "route53",
        canonicalName = "Route53",
        title = "Amazon Route 53",
        description = "Scalable Domain Name System",
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
public final class Route53Api {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "hostedZones.list",
            path = "hosted-zones"
    )
    public HostedZonesResponse hostedZonesList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return Route53Caller.get(ListHostedZonesRequest.class, HostedZonesResponse.class, credentials).execute((client, request, response) -> {
            final ListHostedZonesResult result = client.listHostedZones(request.withMarker(page));
            response.setHostedZones(result.getHostedZones());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "hostedZones.resourceRecordSets.list",
            path = "hosted-zones/{hostedZoneId}/resource-record-sets"
    )
    public ResourceRecordSetsResponse hostedZonesResourceRecordSetsList(
            @Named("credentials") final String credentials,
            @Named("hostedZoneId") final String hostedZoneId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return Route53Caller.get(ListResourceRecordSetsRequest.class, ResourceRecordSetsResponse.class, credentials).execute((client, request, response) -> {
            request.withHostedZoneId(hostedZoneId);
            if (page != null) {
                final String[] parts = page.split("::");
                if (parts.length != 3) {
                    throw new InvalidArgumentException("Incorrect 'page' parameter format");
                }
                request.setStartRecordType("null".equals(parts[0]) ? null : parts[0]);
                request.setStartRecordName("null".equals(parts[1]) ? null : parts[1]);
                request.setStartRecordIdentifier("null".equals(parts[2]) ? null : parts[2]);
            }

            final ListResourceRecordSetsResult result = client.listResourceRecordSets(request);
            response.setResourceRecordSets(result.getResourceRecordSets());
            response.setNextPage(
                    result.getNextRecordType() == null && result.getNextRecordName() == null && result.getNextRecordIdentifier() == null
                            ? null
                            : result.getNextRecordType() + "::" + result.getNextRecordName() + "::" + result.getNextRecordIdentifier()
            );
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "tags.list",
            path = "tags/{resourceType}/{resourceId}"
    )
    public TagsResponse tagsList(
            @Named("credentials") final String credentials,
            @Named("resourceType") final String resourceType,
            @Named("resourceId") final String resourceId
    ) throws AmazonUnparsedException {
        return Route53Caller.get(ListTagsForResourceRequest.class, TagsResponse.class, credentials).execute((client, request, response) -> {
            final ListTagsForResourceResult result = client.listTagsForResource(request.withResourceType(resourceType).withResourceId(resourceId));
            response.setTags(result.getResourceTagSet());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.change",
            path = "tags/{resourceType}/{resourceId}"
    )
    public AmazonResponse tagsChange(
            @Named("credentials") final String credentials,
            @Named("resourceType") final String resourceType,
            @Named("resourceId") final String resourceId,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return Route53Caller.get(ChangeTagsForResourceRequest.class, AmazonResponse.class, credentials).execute((client, r, response) -> {
            r.withResourceType(resourceType);
            r.withResourceId(resourceId);
            if (request.getAddTags() != null && request.getAddTags().size() > 0) {
                final Map<String, String> addTagsRequest = request.getAddTags();
                final List<Tag> addTags = Lists.newArrayList();
                for (final String key : addTagsRequest.keySet()) {
                    final Tag t = new Tag();
                    t.setKey(key);
                    t.setValue(addTagsRequest.get(key));
                    addTags.add(t);
                }
                r.withAddTags(addTags);
            }
            if (request.getRemoveTagKeys() != null && request.getRemoveTagKeys().size() > 0) {
                r.withRemoveTagKeys(request.getRemoveTagKeys());
            }

            client.changeTagsForResource(r);
        });
    }
}
