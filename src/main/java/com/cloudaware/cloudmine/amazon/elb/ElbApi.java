package com.cloudaware.cloudmine.amazon.elb;

import com.amazonaws.services.elasticloadbalancing.model.AddTagsRequest;
import com.amazonaws.services.elasticloadbalancing.model.DescribeInstanceHealthRequest;
import com.amazonaws.services.elasticloadbalancing.model.DescribeInstanceHealthResult;
import com.amazonaws.services.elasticloadbalancing.model.DescribeLoadBalancerAttributesRequest;
import com.amazonaws.services.elasticloadbalancing.model.DescribeLoadBalancerAttributesResult;
import com.amazonaws.services.elasticloadbalancing.model.DescribeLoadBalancerPoliciesRequest;
import com.amazonaws.services.elasticloadbalancing.model.DescribeLoadBalancerPoliciesResult;
import com.amazonaws.services.elasticloadbalancing.model.DescribeLoadBalancersRequest;
import com.amazonaws.services.elasticloadbalancing.model.DescribeLoadBalancersResult;
import com.amazonaws.services.elasticloadbalancing.model.DescribeTagsRequest;
import com.amazonaws.services.elasticloadbalancing.model.DescribeTagsResult;
import com.amazonaws.services.elasticloadbalancing.model.Instance;
import com.amazonaws.services.elasticloadbalancing.model.RemoveTagsRequest;
import com.amazonaws.services.elasticloadbalancing.model.Tag;
import com.amazonaws.services.elasticloadbalancing.model.TagKeyOnly;
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

import java.util.Collection;
import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "elb",
        canonicalName = "Elb",
        title = "Elastic Load Balancing",
        description = "High Scale Load Balancing",
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
public final class ElbApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "loadBalancers.list",
            path = "{region}/load-balancers"
    )
    public LoadBalancersResponse loadBalancersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("loadBalancerName") @Nullable final List<String> loadBalancerNames,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ElbCaller.get(DescribeLoadBalancersRequest.class, LoadBalancersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeLoadBalancersResult result = client.describeLoadBalancers(
                    request
                            .withLoadBalancerNames(loadBalancerNames)
                            .withMarker(page)
            );
            response.setLoadBalancers(result.getLoadBalancerDescriptions());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "loadBalancers.instanceHealth.list",
            path = "{region}/load-balancers/{loadBalancerName}/instance-health"
    )
    public InstanceStatesResponse loadBalancersInstanceHealthList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("loadBalancerName") final String loadBalancerName,
            @Named("instanceId") final Collection<String> instanceIds
    ) throws AmazonUnparsedException {
        return ElbCaller.get(DescribeInstanceHealthRequest.class, InstanceStatesResponse.class, credentials, region).execute((client, request, response) -> {
            final List<Instance> instances = Lists.newArrayList();
            for (final String instanceId : instanceIds) {
                instances.add(new Instance(instanceId));
            }

            final DescribeInstanceHealthResult result = client.describeInstanceHealth(
                    request
                            .withLoadBalancerName(loadBalancerName)
                            .withInstances(instances)
            );
            response.setInstanceStates(result.getInstanceStates());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "loadBalancers.attributes.get",
            path = "{region}/load-balancers/{loadBalancerName}/attributes"
    )
    public LoadBalancerAttributesResponse loadBalancersAttributesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("loadBalancerName") final String loadBalancerName
    ) throws AmazonUnparsedException {
        return ElbCaller.get(DescribeLoadBalancerAttributesRequest.class, LoadBalancerAttributesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeLoadBalancerAttributesResult result = client.describeLoadBalancerAttributes(
                    request
                            .withLoadBalancerName(loadBalancerName)
            );
            response.setAttributes(result.getLoadBalancerAttributes());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "tags.list",
            path = "{region}/tags"
    )
    public TagsResponse tagsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("loadBalancerName") final List<String> loadBalancerNames
    ) throws AmazonUnparsedException {
        return ElbCaller.get(DescribeTagsRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeTagsResult result = client.describeTags(
                    request
                            .withLoadBalancerNames(loadBalancerNames)
            );
            response.setTags(result.getTagDescriptions());
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
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return ElbCaller.get(AddTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, addTagsRequest, response) -> {
            addTagsRequest.withLoadBalancerNames(request.getLoadBalancerNames());
            final List<Tag> tags = Lists.newArrayList();
            for (final String key : request.getTags().keySet()) {
                tags.add(new Tag().withKey(key).withValue(request.getTags().get(key)));
            }

            addTagsRequest.withTags(tags);
            client.addTags(addTagsRequest);
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.remove",
            path = "{region}/tags/remove"
    )
    public AmazonResponse tagsRemove(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return ElbCaller.get(RemoveTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, removeTagsRequest, response) -> {
            removeTagsRequest.withLoadBalancerNames(request.getLoadBalancerNames());
            final List<TagKeyOnly> tags = Lists.newArrayList();
            for (final String key : request.getTags().keySet()) {
                tags.add(new TagKeyOnly().withKey(key));
            }

            removeTagsRequest.withTags(tags);
            client.removeTags(removeTagsRequest);
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "policies.list",
            path = "{region}/policies"
    )
    public PoliciesResponse policiesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("loadBalancerName") @Nullable final String loadBalancerName
    ) throws AmazonUnparsedException {
        return ElbCaller.get(DescribeLoadBalancerPoliciesRequest.class, PoliciesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeLoadBalancerPoliciesResult result = client.describeLoadBalancerPolicies(
                    request
                            .withLoadBalancerName(loadBalancerName)
            );
            response.setPolicies(result.getPolicyDescriptions());
        });
    }
}
