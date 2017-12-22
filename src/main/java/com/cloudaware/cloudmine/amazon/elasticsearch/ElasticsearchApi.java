package com.cloudaware.cloudmine.amazon.elasticsearch;

import com.amazonaws.services.elasticsearch.model.AddTagsRequest;
import com.amazonaws.services.elasticsearch.model.DescribeElasticsearchDomainConfigRequest;
import com.amazonaws.services.elasticsearch.model.DescribeElasticsearchDomainConfigResult;
import com.amazonaws.services.elasticsearch.model.DescribeElasticsearchDomainsRequest;
import com.amazonaws.services.elasticsearch.model.DescribeElasticsearchDomainsResult;
import com.amazonaws.services.elasticsearch.model.ListDomainNamesRequest;
import com.amazonaws.services.elasticsearch.model.ListDomainNamesResult;
import com.amazonaws.services.elasticsearch.model.ListTagsRequest;
import com.amazonaws.services.elasticsearch.model.ListTagsResult;
import com.amazonaws.services.elasticsearch.model.RemoveTagsRequest;
import com.amazonaws.services.elasticsearch.model.Tag;
import com.cloudaware.cloudmine.amazon.AmazonResponse;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.common.collect.Lists;

import java.util.List;

@Api(
        name = "elasticsearch",
        canonicalName = "Elasticsearch",
        title = "Amazon Elasticsearch Service",
        description = "Amazon Elasticsearch Service",
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
public final class ElasticsearchApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domainNames.list",
            path = "{region}/domain-names"
    )
    public DomainNamesResponse domainNamesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return ElasticsearchCaller.get(ListDomainNamesRequest.class, DomainNamesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListDomainNamesResult result = client.listDomainNames(request);
            response.setDomainNames(result.getDomainNames());
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
            @Named("domainNames") final List<String> domainNames
    ) throws AmazonUnparsedException {
        return ElasticsearchCaller.get(DescribeElasticsearchDomainsRequest.class, DomainsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeElasticsearchDomainsResult result = client.describeElasticsearchDomains(request.withDomainNames(domainNames));
            response.setDomains(result.getDomainStatusList());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.config.get",
            path = "{region}/domains/{domainName}/config"
    )
    public DomainConfigResponse domainsConfigGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("domainName") final String domainName
    ) throws AmazonUnparsedException {
        return ElasticsearchCaller.get(DescribeElasticsearchDomainConfigRequest.class, DomainConfigResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeElasticsearchDomainConfigResult result = client.describeElasticsearchDomainConfig(request.withDomainName(domainName));
            response.setDomainConfig(result.getDomainConfig());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "tags.get",
            path = "{region}/tags/ARN"
    )
    public TagsResponse tagsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("arn") final String arn
    ) throws AmazonUnparsedException {
        return ElasticsearchCaller.get(ListTagsRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTagsResult result = client.listTags(request.withARN(arn));
            response.setTags(result.getTagList());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.create",
            path = "{region}/tags/create"
    )
    public AmazonResponse createTags(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return ElasticsearchCaller.get(AddTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            final List<Tag> tags = Lists.newArrayList();
            for (final String key : request.getTags().keySet()) {
                final Tag tag = new Tag();
                tag.setKey(key);
                tag.setValue(request.getTags().get(key));
                tags.add(tag);
            }
            client.addTags(r.withARN(request.getArn()).withTagList(tags));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.delete",
            path = "{region}/tags/detele"
    )
    public AmazonResponse tagsDelete(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return ElasticsearchCaller.get(RemoveTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.removeTags(r.withARN(request.getArn()).withTagKeys(request.getTags().keySet()));
        });
    }

}
