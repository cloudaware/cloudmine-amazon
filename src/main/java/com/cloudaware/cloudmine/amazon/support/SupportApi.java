package com.cloudaware.cloudmine.amazon.support;

import com.amazonaws.services.support.model.DescribeCasesRequest;
import com.amazonaws.services.support.model.DescribeCasesResult;
import com.amazonaws.services.support.model.DescribeTrustedAdvisorCheckResultRequest;
import com.amazonaws.services.support.model.DescribeTrustedAdvisorCheckResultResult;
import com.amazonaws.services.support.model.DescribeTrustedAdvisorChecksRequest;
import com.amazonaws.services.support.model.DescribeTrustedAdvisorChecksResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "support",
        canonicalName = "Support",
        title = "AWS Support",
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
public final class SupportApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "cases.list",
            path = "cases"
    )
    public CasesResponse casesList(
            @Named("credentials") final String credentials,
            @Named("includeResolved") @Nullable final Boolean includeResolved,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return SupportCaller.get(DescribeCasesRequest.class, CasesResponse.class, credentials).execute((client, request, response) -> {
            final DescribeCasesResult result = client.describeCases(request.withIncludeResolvedCases(includeResolved).withNextToken(page));
            response.setCases(result.getCases());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "trustedAdvisorChecks.list",
            path = "trusted-advisor-checks"
    )
    public TrustedAdvisorChecksResponse trustedAdvisorChecksList(
            @Named("credentials") final String credentials
    ) throws AmazonUnparsedException {
        return SupportCaller.get(DescribeTrustedAdvisorChecksRequest.class, TrustedAdvisorChecksResponse.class, credentials).execute((client, request, response) -> {
            final DescribeTrustedAdvisorChecksResult result = client.describeTrustedAdvisorChecks(request.withLanguage("en"));
            response.setTrustedAdvisorChecks(result.getChecks());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "trustedAdvisorChecks.results.get",
            path = "trusted-advisor-checks/{checkId}"
    )
    public TrustedAdvisorCheckResultResponse trustedAdvisorChecksResultsGet(
            @Named("credentials") final String credentials,
            @Named("checkId") final String checkId
    ) throws AmazonUnparsedException {
        return SupportCaller.get(DescribeTrustedAdvisorCheckResultRequest.class, TrustedAdvisorCheckResultResponse.class, credentials).execute((client, request, response) -> {
            final DescribeTrustedAdvisorCheckResultResult result = client.describeTrustedAdvisorCheckResult(request.withCheckId(checkId));
            response.setTrustedAdvisorCheckResult(result.getResult());
        });
    }

}
