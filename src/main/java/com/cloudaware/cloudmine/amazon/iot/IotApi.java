package com.cloudaware.cloudmine.amazon.iot;

import com.amazonaws.services.iot.model.DescribeCertificateRequest;
import com.amazonaws.services.iot.model.DescribeCertificateResult;
import com.amazonaws.services.iot.model.GetPolicyRequest;
import com.amazonaws.services.iot.model.GetPolicyResult;
import com.amazonaws.services.iot.model.GetTopicRuleRequest;
import com.amazonaws.services.iot.model.GetTopicRuleResult;
import com.amazonaws.services.iot.model.ListCertificatesRequest;
import com.amazonaws.services.iot.model.ListCertificatesResult;
import com.amazonaws.services.iot.model.ListPoliciesRequest;
import com.amazonaws.services.iot.model.ListPoliciesResult;
import com.amazonaws.services.iot.model.ListPolicyPrincipalsRequest;
import com.amazonaws.services.iot.model.ListPolicyPrincipalsResult;
import com.amazonaws.services.iot.model.ListThingPrincipalsRequest;
import com.amazonaws.services.iot.model.ListThingPrincipalsResult;
import com.amazonaws.services.iot.model.ListThingTypesRequest;
import com.amazonaws.services.iot.model.ListThingTypesResult;
import com.amazonaws.services.iot.model.ListThingsRequest;
import com.amazonaws.services.iot.model.ListThingsResult;
import com.amazonaws.services.iot.model.ListTopicRulesRequest;
import com.amazonaws.services.iot.model.ListTopicRulesResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "iot",
        canonicalName = "Iot",
        title = "AWS IoT",
        description = "AWS IoT provides secure, bi-directional communication between Internet-connected things and the AWS cloud",
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
public final class IotApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "things.list",
            path = "{region}/things"
    )
    public ThingsResponse thingsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IotCaller.get(ListThingsRequest.class, ThingsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListThingsResult result = client.listThings(request.withNextToken(page));
            response.setThings(result.getThings());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "things.principals.list",
            path = "{region}/things/{thingName}/principals"
    )
    public PrincipalsResponse thingsPrincipalsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("thingName") final String thingName
    ) throws AmazonUnparsedException {
        return IotCaller.get(ListThingPrincipalsRequest.class, PrincipalsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListThingPrincipalsResult result = client.listThingPrincipals(request.withThingName(thingName));
            response.setPrincipals(result.getPrincipals());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "thingTypes.list",
            path = "{region}/thing-types"
    )
    public ThingTypesResponse thingTypesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IotCaller.get(ListThingTypesRequest.class, ThingTypesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListThingTypesResult result = client.listThingTypes(request.withNextToken(page));
            response.setThingTypes(result.getThingTypes());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "certificates.list",
            path = "{region}/certificates"
    )
    public CertificatesResponse certificatesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IotCaller.get(ListCertificatesRequest.class, CertificatesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListCertificatesResult result = client.listCertificates(request.withMarker(page));
            response.setCertificates(result.getCertificates());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "certificates.get",
            path = "{region}/certificates/{certificateId}"
    )
    public CertificateDetailsResponse certificatesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("certificateId") final String certificateId
    ) throws AmazonUnparsedException {
        return IotCaller.get(DescribeCertificateRequest.class, CertificateDetailsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeCertificateResult result = client.describeCertificate(request.withCertificateId(certificateId));
            response.setCertificate(result.getCertificateDescription());
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
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IotCaller.get(ListPoliciesRequest.class, PoliciesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListPoliciesResult result = client.listPolicies(request.withMarker(page));
            response.setPolicies(result.getPolicies());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "policies.get",
            path = "{region}/policies/{policyName}"
    )
    public PolicyDetailsResponse policiesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("policyName") final String policyName
    ) throws AmazonUnparsedException {
        return IotCaller.get(GetPolicyRequest.class, PolicyDetailsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetPolicyResult result = client.getPolicy(request.withPolicyName(policyName));
            response.setDefaultVersionId(result.getDefaultVersionId());
            response.setPolicyArn(result.getPolicyArn());
            response.setPolicyDocument(result.getPolicyDocument());
            response.setPolicyName(result.getPolicyName());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "policies.principals.list",
            path = "{region}/policies/{policyName}/principals"
    )
    public PrincipalsResponse policiesPrincipalsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("policyName") final String policyName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IotCaller.get(ListPolicyPrincipalsRequest.class, PrincipalsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListPolicyPrincipalsResult result = client.listPolicyPrincipals(request.withPolicyName(policyName).withMarker(page));
            response.setPrincipals(result.getPrincipals());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "rules.list",
            path = "{region}/rules"
    )
    public RulesResponse rulesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IotCaller.get(ListTopicRulesRequest.class, RulesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTopicRulesResult result = client.listTopicRules(request.withNextToken(page));
            response.setRules(result.getRules());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "rules.get",
            path = "{region}/rules/{ruleName}"
    )
    public RuleDetailsResponse rulesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("ruleName") final String ruleName
    ) throws AmazonUnparsedException {
        return IotCaller.get(GetTopicRuleRequest.class, RuleDetailsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetTopicRuleResult result = client.getTopicRule(request.withRuleName(ruleName));
            response.setRule(result.getRule());
            response.setRuleArn(result.getRuleArn());
        });
    }
}
