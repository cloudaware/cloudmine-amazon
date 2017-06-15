package com.cloudaware.cloudmine.amazon.cloudsearch;

import com.amazonaws.services.cloudsearchv2.model.DescribeAnalysisSchemesRequest;
import com.amazonaws.services.cloudsearchv2.model.DescribeAnalysisSchemesResult;
import com.amazonaws.services.cloudsearchv2.model.DescribeAvailabilityOptionsRequest;
import com.amazonaws.services.cloudsearchv2.model.DescribeAvailabilityOptionsResult;
import com.amazonaws.services.cloudsearchv2.model.DescribeDomainsRequest;
import com.amazonaws.services.cloudsearchv2.model.DescribeDomainsResult;
import com.amazonaws.services.cloudsearchv2.model.DescribeExpressionsRequest;
import com.amazonaws.services.cloudsearchv2.model.DescribeExpressionsResult;
import com.amazonaws.services.cloudsearchv2.model.DescribeIndexFieldsRequest;
import com.amazonaws.services.cloudsearchv2.model.DescribeIndexFieldsResult;
import com.amazonaws.services.cloudsearchv2.model.DescribeScalingParametersRequest;
import com.amazonaws.services.cloudsearchv2.model.DescribeScalingParametersResult;
import com.amazonaws.services.cloudsearchv2.model.DescribeServiceAccessPoliciesRequest;
import com.amazonaws.services.cloudsearchv2.model.DescribeServiceAccessPoliciesResult;
import com.amazonaws.services.cloudsearchv2.model.DescribeSuggestersRequest;
import com.amazonaws.services.cloudsearchv2.model.DescribeSuggestersResult;
import com.amazonaws.services.cloudsearchv2.model.ListDomainNamesRequest;
import com.amazonaws.services.cloudsearchv2.model.ListDomainNamesResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "cloudsearch",
        canonicalName = "Cloud Search",
        title = "Amazon CloudSearch",
        description = "Managed Search Service",
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
public final class CloudSearchApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domainNames.list",
            path = "{region}/domain-names"
    )
    public DomainNamesResponse domainNamesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return CloudSearchCaller.get(ListDomainNamesRequest.class, DomainNamesResponse.class, credentials, region).execute((client, request, response) -> {
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
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return CloudSearchCaller.get(DescribeDomainsRequest.class, DomainsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeDomainsResult result = client.describeDomains(request);
            response.setDomains(result.getDomainStatusList());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.suggesters.list",
            path = "{region}/domains/{domainName}/suggesters"
    )
    public SuggestersResponse domainsSuggestersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("domainName") final String domainName
    ) throws AmazonUnparsedException {
        return CloudSearchCaller.get(DescribeSuggestersRequest.class, SuggestersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeSuggestersResult result = client.describeSuggesters(request.withDomainName(domainName));
            response.setSuggesters(result.getSuggesters());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.expressions.list",
            path = "{region}/domains/{domainName}/expressions"
    )
    public ExpressionsResponse domainExpressionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("domainName") final String domainName
    ) throws AmazonUnparsedException {
        return CloudSearchCaller.get(DescribeExpressionsRequest.class, ExpressionsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeExpressionsResult result = client.describeExpressions(request.withDomainName(domainName));
            response.setExpressions(result.getExpressions());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.analysisSchemes.list",
            path = "{region}/domains/{domainName}/analysis-schemes"
    )
    public AnalysisSchemesResponse domainsAnalysisSchemesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("domainName") final String domainName
    ) throws AmazonUnparsedException {
        return CloudSearchCaller.get(DescribeAnalysisSchemesRequest.class, AnalysisSchemesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeAnalysisSchemesResult result = client.describeAnalysisSchemes(request.withDomainName(domainName));
            response.setAnalysisSchemes(result.getAnalysisSchemes());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.indexFields.list",
            path = "{region}/domains/{domainName}/index-fields"
    )
    public IndexFieldsResponse domainsIndexFieldsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("domainName") final String domainName
    ) throws AmazonUnparsedException {
        return CloudSearchCaller.get(DescribeIndexFieldsRequest.class, IndexFieldsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeIndexFieldsResult result = client.describeIndexFields(request.withDomainName(domainName));
            response.setIndexFields(result.getIndexFields());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.scalingParameters.list",
            path = "{region}/domains/{domainName}/scaling-parameters"
    )
    public ScalingParametersResponse domainsScalingParametersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("domainName") final String domainName
    ) throws AmazonUnparsedException {
        return CloudSearchCaller.get(DescribeScalingParametersRequest.class, ScalingParametersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeScalingParametersResult result = client.describeScalingParameters(request.withDomainName(domainName));
            response.setScalingParameters(result.getScalingParameters());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.accessPolicies.list",
            path = "{region}/domains/{domainName}/access-policies"
    )
    public AccessPoliciesResponse domainsAccessPoliciesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("domainName") final String domainName
    ) throws AmazonUnparsedException {
        return CloudSearchCaller.get(DescribeServiceAccessPoliciesRequest.class, AccessPoliciesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeServiceAccessPoliciesResult result = client.describeServiceAccessPolicies(request.withDomainName(domainName));
            response.setAccessPolicies(result.getAccessPolicies());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "domains.availabilityOptions.list",
            path = "{region}/domains/{domainName}/availability-options"
    )
    public AvailabilityOptionsResponse domainsAvailabilityOptionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("domainName") final String domainName
    ) throws AmazonUnparsedException {
        return CloudSearchCaller.get(DescribeAvailabilityOptionsRequest.class, AvailabilityOptionsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeAvailabilityOptionsResult result = client.describeAvailabilityOptions(request.withDomainName(domainName));
            response.setAvailabilityOptions(result.getAvailabilityOptions());
        });
    }
}
