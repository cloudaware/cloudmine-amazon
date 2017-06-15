package com.cloudaware.cloudmine.amazon.organizations;

import com.amazonaws.services.organizations.model.CreateAccountRequest;
import com.amazonaws.services.organizations.model.CreateAccountResult;
import com.amazonaws.services.organizations.model.DescribeCreateAccountStatusRequest;
import com.amazonaws.services.organizations.model.DescribeCreateAccountStatusResult;
import com.amazonaws.services.organizations.model.DescribeOrganizationRequest;
import com.amazonaws.services.organizations.model.DescribeOrganizationResult;
import com.amazonaws.services.organizations.model.ListAccountsForParentRequest;
import com.amazonaws.services.organizations.model.ListAccountsForParentResult;
import com.amazonaws.services.organizations.model.ListAccountsRequest;
import com.amazonaws.services.organizations.model.ListAccountsResult;
import com.amazonaws.services.organizations.model.ListOrganizationalUnitsForParentRequest;
import com.amazonaws.services.organizations.model.ListOrganizationalUnitsForParentResult;
import com.amazonaws.services.organizations.model.ListPoliciesForTargetRequest;
import com.amazonaws.services.organizations.model.ListPoliciesForTargetResult;
import com.amazonaws.services.organizations.model.ListPoliciesRequest;
import com.amazonaws.services.organizations.model.ListPoliciesResult;
import com.amazonaws.services.organizations.model.ListRootsRequest;
import com.amazonaws.services.organizations.model.ListRootsResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "organizations",
        canonicalName = "Organizations",
        title = "AWS Organizations",
        description = "Policy-based management for multiple AWS accounts",
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
public final class OrganizationsApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "organization.get",
            path = "organization"
    )
    public OrganizationResponse organizationGet(
            @Named("credentials") final String credentials
    ) throws AmazonUnparsedException {
        return OrganizationsCaller.get(DescribeOrganizationRequest.class, OrganizationResponse.class, credentials).execute((client, request, response) -> {
            final DescribeOrganizationResult result = client.describeOrganization(request);
            response.setOrganization(result.getOrganization());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "roots.list",
            path = "roots"
    )
    public RootsResponse rootslist(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return OrganizationsCaller.get(ListRootsRequest.class, RootsResponse.class, credentials).execute((client, request, response) -> {
            final ListRootsResult result = client.listRoots(request.withNextToken(page));
            response.setRoots(result.getRoots());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "accounts.list",
            path = "accounts"
    )
    public AccountsResponse accountsList(
            @Named("credentials") final String credentials,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return OrganizationsCaller.get(ListAccountsRequest.class, AccountsResponse.class, credentials).execute((client, request, response) -> {
            final ListAccountsResult result = client.listAccounts(request.withNextToken(page));
            response.setAccounts(result.getAccounts());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "accounts.createRequests.insert",
            path = "accounts/create-requests"
    )
    public AccountCreateResponse accountsCreate(
            @Named("credentials") final String credentials,
            final AccountRequest request
    ) throws AmazonUnparsedException {
        return OrganizationsCaller.get(CreateAccountRequest.class, AccountCreateResponse.class, credentials).execute((client, r, response) -> {
            final CreateAccountResult result = client.createAccount(
                    r
                            .withAccountName(request.getAccountName())
                            .withEmail(request.getEmail())
                            .withRoleName(request.getRoleName())
                            .withIamUserAccessToBilling(request.getIamUserAccessToBilling())
            );
            response.setCreateAccountStatus(result.getCreateAccountStatus());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "accounts.createRequests.get",
            path = "accounts/create-requests/{requestId}"
    )
    public AccountCreateResponse accountsCreateStatus(
            @Named("credentials") final String credentials,
            @Named("requestId") final String requestId
    ) throws AmazonUnparsedException {
        return OrganizationsCaller.get(DescribeCreateAccountStatusRequest.class, AccountCreateResponse.class, credentials).execute((client, request, response) -> {
            final DescribeCreateAccountStatusResult result = client.describeCreateAccountStatus(request.withCreateAccountRequestId(requestId));
            response.setCreateAccountStatus(result.getCreateAccountStatus());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "parents.accounts.list",
            path = "parents/{parentId}/accounts"
    )
    public AccountsResponse parentsAccountsList(
            @Named("credentials") final String credentials,
            @Named("parentId") final String parentId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return OrganizationsCaller.get(ListAccountsForParentRequest.class, AccountsResponse.class, credentials).execute((client, request, response) -> {
            final ListAccountsForParentResult result = client.listAccountsForParent(
                    request
                            .withParentId(parentId)
                            .withNextToken(page)
            );
            response.setAccounts(result.getAccounts());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "policies.list",
            path = "policies"
    )
    public PoliciesResponse policiesList(
            @Named("credentials") final String credentials,
            @Named("filter") final String filter,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return OrganizationsCaller.get(ListPoliciesRequest.class, PoliciesResponse.class, credentials).execute((client, request, response) -> {
            final ListPoliciesResult result = client.listPolicies(
                    request
                            .withFilter(filter)
                            .withNextToken(page)
            );
            response.setPolicies(result.getPolicies());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "targets.policies.list",
            path = "targets/{targetId}/policies"
    )
    public PoliciesResponse targetsPoliciesList(
            @Named("credentials") final String credentials,
            @Named("targetId") final String targetId,
            @Named("filter") final String filter,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return OrganizationsCaller.get(ListPoliciesForTargetRequest.class, PoliciesResponse.class, credentials).execute((client, request, response) -> {
            final ListPoliciesForTargetResult result = client.listPoliciesForTarget(
                    request
                            .withTargetId(targetId)
                            .withFilter(filter)
                            .withNextToken(page)
            );
            response.setPolicies(result.getPolicies());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "parents.organizationalUnits.list",
            path = "parents/{parentId}/organizational-units"
    )
    public OrganizationalUnitsResponse parentsOrganizationalUnitsList(
            @Named("credentials") final String credentials,
            @Named("parentId") final String parentId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return OrganizationsCaller.get(ListOrganizationalUnitsForParentRequest.class, OrganizationalUnitsResponse.class, credentials).execute((client, request, response) -> {
            final ListOrganizationalUnitsForParentResult result = client.listOrganizationalUnitsForParent(
                    request
                            .withParentId(parentId)
                            .withNextToken(page)
            );
            response.setOrganizationalUnits(result.getOrganizationalUnits());
            response.setNextPage(result.getNextToken());
        });
    }
}
