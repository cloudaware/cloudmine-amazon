package com.cloudaware.cloudmine.amazon.elbv2;

import com.amazonaws.services.elasticloadbalancingv2.model.DescribeListenersRequest;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeListenersResult;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeLoadBalancerAttributesRequest;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeLoadBalancerAttributesResult;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeLoadBalancersRequest;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeLoadBalancersResult;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeRulesRequest;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeRulesResult;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeSSLPoliciesRequest;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeSSLPoliciesResult;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTagsRequest;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTagsResult;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetGroupAttributesRequest;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetGroupAttributesResult;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetGroupsRequest;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetGroupsResult;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetHealthRequest;
import com.amazonaws.services.elasticloadbalancingv2.model.DescribeTargetHealthResult;
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
        name = "elbv2",
        canonicalName = "ElbV2",
        title = "Elastic Load Balancing v2",
        description = "Application Load Balancers",
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
public final class ElbV2Api {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "loadBalancers.list",
            path = "{region}/load-balancers"
    )
    public LoadBalancersResponse loadBalancersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("loadBalancerArns") @Nullable final List<String> loadBalancerArns,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ElbV2Caller.get(DescribeLoadBalancersRequest.class, LoadBalancersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeLoadBalancersResult result = client.describeLoadBalancers(
                    request
                            .withLoadBalancerArns(loadBalancerArns)
                            .withMarker(page)
            );
            response.setLoadBalancers(result.getLoadBalancers());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "loadBalancers.listeners.list",
            path = "{region}/load-balancers/{loadBalancerArn}/listeners"
    )
    public ListenersResponse loadBalancerListenersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("loadBalancerArn") final String loadBalancerArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ElbV2Caller.get(DescribeListenersRequest.class, ListenersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeListenersResult result = client.describeListeners(
                    request
                            .withLoadBalancerArn(loadBalancerArn)
                            .withMarker(page)
            );
            response.setListeners(result.getListeners());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "loadBalancers.attributes.list",
            path = "{region}/load-balancers/{loadBalancerArn}/attributes"
    )
    public LoadBalancerAttributesResponse loadBalancersAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("loadBalancerArn") final String loadBalancerArn
    ) throws AmazonUnparsedException {
        return ElbV2Caller.get(DescribeLoadBalancerAttributesRequest.class, LoadBalancerAttributesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeLoadBalancerAttributesResult result = client.describeLoadBalancerAttributes(
                    request
                            .withLoadBalancerArn(loadBalancerArn)
            );
            response.setAttributes(result.getAttributes());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "listeners.rules.list",
            path = "{region}/listeners/{listenerArn}/rules"
    )
    public RulesResponse listenersRulesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("listenerArn") final String listenerArn
    ) throws AmazonUnparsedException {
        return ElbV2Caller.get(DescribeRulesRequest.class, RulesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeRulesResult result = client.describeRules(
                    request
                            .withListenerArn(listenerArn)
            );
            response.setRules(result.getRules());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "sslPolicies.list",
            path = "{region}/ssl-policies"
    )
    public SslPoliciesResponse sslPoliciesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("names") @Nullable final List<String> names,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ElbV2Caller.get(DescribeSSLPoliciesRequest.class, SslPoliciesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeSSLPoliciesResult result = client.describeSSLPolicies(
                    request
                            .withNames(names)
                            .withMarker(page)
            );
            response.setSslPolicies(result.getSslPolicies());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "targetGroups.list",
            path = "{region}/target-groups"
    )
    public TargetGroupsResponse targetGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ElbV2Caller.get(DescribeTargetGroupsRequest.class, TargetGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeTargetGroupsResult result = client.describeTargetGroups(
                    request
                            .withMarker(page)
            );
            response.setTargetGroups(result.getTargetGroups());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "targetGroups.attributes.list",
            path = "{region}/target-groups/{targetGroupArn}/attributes"
    )
    public TargetGroupAttributesResponse targetGroupsAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("targetGroupArn") final String targetGroupArn
    ) throws AmazonUnparsedException {
        return ElbV2Caller.get(DescribeTargetGroupAttributesRequest.class, TargetGroupAttributesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeTargetGroupAttributesResult result = client.describeTargetGroupAttributes(
                    request
                            .withTargetGroupArn(targetGroupArn)
            );
            response.setAttributes(result.getAttributes());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "targetGroups.targetHealth.list",
            path = "{region}/target-groups/{targetGroupArn}/target-health"
    )
    public TargetStatesResponse targetGroupsTargetHealthList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("targetGroupArn") final String targetGroupArn
    ) throws AmazonUnparsedException {
        return ElbV2Caller.get(DescribeTargetHealthRequest.class, TargetStatesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeTargetHealthResult result = client.describeTargetHealth(
                    request
                            .withTargetGroupArn(targetGroupArn)
            );
            response.setTargetStates(result.getTargetHealthDescriptions());
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
            @Named("resourceArns") final List<String> resourceArns
    ) throws AmazonUnparsedException {
        return ElbV2Caller.get(DescribeTagsRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeTagsResult result = client.describeTags(
                    request
                            .withResourceArns(resourceArns)
            );
            response.setTags(result.getTagDescriptions());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.add",
            path = "{region}/tags/add"
    )
    public AmazonResponse tagsAdd(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final AddTagsRequest request
    ) throws AmazonUnparsedException {
        return ElbV2Caller.get(com.amazonaws.services.elasticloadbalancingv2.model.AddTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.addTags(r.withResourceArns(request.getArns()).withTags(request.getTags()));
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
            final RemoveTagsRequest request
    ) throws AmazonUnparsedException {
        return ElbV2Caller.get(com.amazonaws.services.elasticloadbalancingv2.model.RemoveTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.removeTags(r.withResourceArns(request.getArns()).withTagKeys(request.getTagKeys()));
        });
    }
}
