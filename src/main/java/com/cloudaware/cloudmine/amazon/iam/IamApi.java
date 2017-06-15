package com.cloudaware.cloudmine.amazon.iam;

import com.amazonaws.services.identitymanagement.model.CreateAccessKeyRequest;
import com.amazonaws.services.identitymanagement.model.CreateAccessKeyResult;
import com.amazonaws.services.identitymanagement.model.GetAccessKeyLastUsedRequest;
import com.amazonaws.services.identitymanagement.model.GetAccessKeyLastUsedResult;
import com.amazonaws.services.identitymanagement.model.GetAccountPasswordPolicyRequest;
import com.amazonaws.services.identitymanagement.model.GetAccountPasswordPolicyResult;
import com.amazonaws.services.identitymanagement.model.GetGroupPolicyRequest;
import com.amazonaws.services.identitymanagement.model.GetGroupPolicyResult;
import com.amazonaws.services.identitymanagement.model.GetLoginProfileRequest;
import com.amazonaws.services.identitymanagement.model.GetLoginProfileResult;
import com.amazonaws.services.identitymanagement.model.GetPolicyVersionRequest;
import com.amazonaws.services.identitymanagement.model.GetPolicyVersionResult;
import com.amazonaws.services.identitymanagement.model.GetRolePolicyRequest;
import com.amazonaws.services.identitymanagement.model.GetRolePolicyResult;
import com.amazonaws.services.identitymanagement.model.GetSAMLProviderRequest;
import com.amazonaws.services.identitymanagement.model.GetSAMLProviderResult;
import com.amazonaws.services.identitymanagement.model.GetServerCertificateRequest;
import com.amazonaws.services.identitymanagement.model.GetServerCertificateResult;
import com.amazonaws.services.identitymanagement.model.GetUserPolicyRequest;
import com.amazonaws.services.identitymanagement.model.GetUserPolicyResult;
import com.amazonaws.services.identitymanagement.model.GetUserRequest;
import com.amazonaws.services.identitymanagement.model.GetUserResult;
import com.amazonaws.services.identitymanagement.model.ListAccessKeysRequest;
import com.amazonaws.services.identitymanagement.model.ListAccessKeysResult;
import com.amazonaws.services.identitymanagement.model.ListAttachedGroupPoliciesRequest;
import com.amazonaws.services.identitymanagement.model.ListAttachedGroupPoliciesResult;
import com.amazonaws.services.identitymanagement.model.ListAttachedRolePoliciesRequest;
import com.amazonaws.services.identitymanagement.model.ListAttachedRolePoliciesResult;
import com.amazonaws.services.identitymanagement.model.ListAttachedUserPoliciesRequest;
import com.amazonaws.services.identitymanagement.model.ListAttachedUserPoliciesResult;
import com.amazonaws.services.identitymanagement.model.ListGroupPoliciesRequest;
import com.amazonaws.services.identitymanagement.model.ListGroupPoliciesResult;
import com.amazonaws.services.identitymanagement.model.ListGroupsForUserRequest;
import com.amazonaws.services.identitymanagement.model.ListGroupsForUserResult;
import com.amazonaws.services.identitymanagement.model.ListGroupsRequest;
import com.amazonaws.services.identitymanagement.model.ListGroupsResult;
import com.amazonaws.services.identitymanagement.model.ListMFADevicesRequest;
import com.amazonaws.services.identitymanagement.model.ListMFADevicesResult;
import com.amazonaws.services.identitymanagement.model.ListPoliciesRequest;
import com.amazonaws.services.identitymanagement.model.ListPoliciesResult;
import com.amazonaws.services.identitymanagement.model.ListPolicyVersionsRequest;
import com.amazonaws.services.identitymanagement.model.ListPolicyVersionsResult;
import com.amazonaws.services.identitymanagement.model.ListRolePoliciesRequest;
import com.amazonaws.services.identitymanagement.model.ListRolePoliciesResult;
import com.amazonaws.services.identitymanagement.model.ListRolesRequest;
import com.amazonaws.services.identitymanagement.model.ListRolesResult;
import com.amazonaws.services.identitymanagement.model.ListSAMLProvidersRequest;
import com.amazonaws.services.identitymanagement.model.ListSAMLProvidersResult;
import com.amazonaws.services.identitymanagement.model.ListServerCertificatesRequest;
import com.amazonaws.services.identitymanagement.model.ListServerCertificatesResult;
import com.amazonaws.services.identitymanagement.model.ListSigningCertificatesRequest;
import com.amazonaws.services.identitymanagement.model.ListSigningCertificatesResult;
import com.amazonaws.services.identitymanagement.model.ListUserPoliciesRequest;
import com.amazonaws.services.identitymanagement.model.ListUserPoliciesResult;
import com.amazonaws.services.identitymanagement.model.ListUsersRequest;
import com.amazonaws.services.identitymanagement.model.ListUsersResult;
import com.amazonaws.services.identitymanagement.model.ListVirtualMFADevicesRequest;
import com.amazonaws.services.identitymanagement.model.ListVirtualMFADevicesResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

import java.io.UnsupportedEncodingException;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "iam",
        canonicalName = "Iam",
        title = "AWS Identity & Access Management (IAM)",
        description = "Manage User Access and Encryption Keys",
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
public final class IamApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.list",
            path = "{partition}/users"
    )
    public UsersResponse usersList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IamCaller.get(ListUsersRequest.class, UsersResponse.class, credentials, partition).execute((client, request, response) -> {
            final ListUsersResult result = client.listUsers(request.withMarker(page));
            response.setUsers(result.getUsers());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.self",
            path = "{partition}/users/self"
    )
    public UserResponse usersSelf(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition
    ) throws AmazonUnparsedException {
        return IamCaller.get(GetUserRequest.class, UserResponse.class, credentials, partition).execute((client, request, response) -> {
            final GetUserResult result = client.getUser(request);
            response.setUser(result.getUser());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.get",
            path = "{partition}/users/{userName}"
    )
    public UserResponse usersGet(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName
    ) throws AmazonUnparsedException {
        return IamCaller.get(GetUserRequest.class, UserResponse.class, credentials, partition).execute((client, request, response) -> {
            final GetUserResult result = client.getUser(request.withUserName(userName));
            response.setUser(result.getUser());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.groups.list",
            path = "{partition}/users/{userName}/groups"
    )
    public GroupsResponse usersGroupsList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IamCaller.get(ListGroupsForUserRequest.class, GroupsResponse.class, credentials, partition).execute((client, request, response) -> {
            final ListGroupsForUserResult result = client.listGroupsForUser(request.withUserName(userName).withMarker(page));
            response.setGroups(result.getGroups());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.accessKeys.list",
            path = "{partition}/users/{userName}/access-keys"
    )
    public AccessKeysResponse usersAccessKeysList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IamCaller.get(ListAccessKeysRequest.class, AccessKeysResponse.class, credentials, partition).execute((client, request, response) -> {
            final ListAccessKeysResult result = client.listAccessKeys(request.withUserName(userName).withMarker(page));
            response.setAccessKeys(result.getAccessKeyMetadata());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "groups.list",
            path = "{partition}/groups"
    )
    public GroupsResponse groupsList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IamCaller.get(ListGroupsRequest.class, GroupsResponse.class, credentials, partition).execute((client, request, response) -> {
            final ListGroupsResult result = client.listGroups(request.withMarker(page));
            response.setGroups(result.getGroups());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "roles.list",
            path = "{partition}/roles"
    )
    public RolesResponse rolesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IamCaller.get(ListRolesRequest.class, RolesResponse.class, credentials, partition).execute((client, request, response) -> {
            final ListRolesResult result = client.listRoles(request.withMarker(page));
            response.setRoles(result.getRoles());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "accessKeys.lastUsed",
            path = "{partition}/access-keys/{accessKeyId}"
    )
    public AccessKeyLastUsedResponse accessKeyLastUsed(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("accessKeyId") final String accessKeyId
    ) throws AmazonUnparsedException {
        return IamCaller.get(GetAccessKeyLastUsedRequest.class, AccessKeyLastUsedResponse.class, credentials, partition).execute((client, request, response) -> {
            final GetAccessKeyLastUsedResult result = client.getAccessKeyLastUsed(request.withAccessKeyId(accessKeyId));
            response.setAccessKeyLastUsed(
                    result != null && result.getAccessKeyLastUsed() != null
                            ? result.getAccessKeyLastUsed()
                            : null
            );
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "users.accessKeys.create",
            path = "{partition}/users/{userName}"
    )
    public AccessKeyResponse usersAccessKeysCreate(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName
    ) throws AmazonUnparsedException {
        return IamCaller.get(CreateAccessKeyRequest.class, AccessKeyResponse.class, credentials, partition).execute((client, request, response) -> {
            final CreateAccessKeyResult result = client.createAccessKey(request.withUserName(userName));
            response.setAccessKey(result.getAccessKey());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "serverCertificates.list",
            path = "{partition}/server-certificates"
    )
    public ServerCertificatesResponse serverCertificatesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IamCaller.get(ListServerCertificatesRequest.class, ServerCertificatesResponse.class, credentials, partition).execute((client, request, response) -> {
            final ListServerCertificatesResult result = client.listServerCertificates(request.withMarker(page));
            response.setServerCertificates(result.getServerCertificateMetadataList());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "serverCertificates.get",
            path = "{partition}/server-certificates/{serverCertificateName}"
    )
    public ServerCertificateResponse serverCertificatesGet(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("serverCertificateName") final String serverCertificateName
    ) throws AmazonUnparsedException {
        return IamCaller.get(GetServerCertificateRequest.class, ServerCertificateResponse.class, credentials, partition).execute((client, request, response) -> {
            final GetServerCertificateResult result = client.getServerCertificate(request.withServerCertificateName(serverCertificateName));
            response.setServerCertificate(result.getServerCertificate());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.signingCertificates.list",
            path = "{partition}/users/{userName}/signing-certificates"
    )
    public SigningCertificatesResponse usersSigningCertificatesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IamCaller.get(ListSigningCertificatesRequest.class, SigningCertificatesResponse.class, credentials, partition).execute((client, request, response) -> {
            final ListSigningCertificatesResult result = client.listSigningCertificates(
                    request
                            .withUserName(userName)
                            .withMarker(page)
            );
            response.setSigningCertificates(result.getCertificates());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "groups.policies.list",
            path = "{partition}/groups/{groupName}/policies"
    )
    public PolicyNamesResponse groupsPoliciesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("groupName") final String groupName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IamCaller.get(ListGroupPoliciesRequest.class, PolicyNamesResponse.class, credentials, partition).execute((client, request, response) -> {
            final ListGroupPoliciesResult result = client.listGroupPolicies(
                    request
                            .withGroupName(groupName)
                            .withMarker(page)
            );
            response.setPolicyNames(result.getPolicyNames());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "groups.policies.get",
            path = "{partition}/groups/{groupName}/policies/{policyName}"
    )
    public PolicyResponse groupsPoliciesGet(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("groupName") final String groupName,
            @Named("policyName") final String policyName
    ) throws AmazonUnparsedException {
        return IamCaller.get(GetGroupPolicyRequest.class, PolicyResponse.class, credentials, partition).execute((client, request, response) -> {
            final GetGroupPolicyResult result = client.getGroupPolicy(
                    request
                            .withGroupName(groupName)
                            .withPolicyName(policyName)
            );
            response.setPolicyDocument(result.getPolicyDocument());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "groups.attachedPolicies.list",
            path = "{partition}/groups/{groupName}/attached-policies"
    )
    public AttachedPoliciesResponse groupsAttachedPoliciesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("groupName") final String groupName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IamCaller.get(ListAttachedGroupPoliciesRequest.class, AttachedPoliciesResponse.class, credentials, partition).execute((client, request, response) -> {
            final ListAttachedGroupPoliciesResult result = client.listAttachedGroupPolicies(
                    request
                            .withGroupName(groupName)
                            .withMarker(page)
            );
            response.setAttachedPolicies(result.getAttachedPolicies());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.loginProfile.get",
            path = "{partition}/users/{userName}/login-profile"
    )
    public LoginProfileResponse usersLoginProfileGet(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName
    ) throws AmazonUnparsedException {
        return IamCaller.get(GetLoginProfileRequest.class, LoginProfileResponse.class, credentials, partition).execute((client, request, response) -> {
            final GetLoginProfileResult result = client.getLoginProfile(
                    request.withUserName(userName)
            );
            response.setLoginProfile(result.getLoginProfile());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.mfaDevices.list",
            path = "{partition}/users/{userName}/mfa-devices"
    )
    public MfaDevicesResponse usersMfaDevicesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IamCaller.get(ListMFADevicesRequest.class, MfaDevicesResponse.class, credentials, partition).execute((client, request, response) -> {
            final ListMFADevicesResult result = client.listMFADevices(
                    request.withUserName(userName).withMarker(page)
            );
            response.setMfaDevices(result.getMFADevices());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "passwordPolicy.get",
            path = "{partition}/password-policy"
    )
    public PasswordPolicyResponse passwordPolicyGet(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition
    ) throws AmazonUnparsedException {
        return IamCaller.get(GetAccountPasswordPolicyRequest.class, PasswordPolicyResponse.class, credentials, partition).execute((client, request, response) -> {
            final GetAccountPasswordPolicyResult result = client.getAccountPasswordPolicy(request);
            response.setPasswordPolicy(result.getPasswordPolicy());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "policies.list",
            path = "{partition}/policies"
    )
    public PoliciesResponse policiesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("scope") @Nullable final String scope,
            @Named("onlyAttached") @Nullable final Boolean onlyAttached,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IamCaller.get(ListPoliciesRequest.class, PoliciesResponse.class, credentials, partition).execute((client, request, response) -> {
            final ListPoliciesResult result = client.listPolicies(
                    request
                            .withScope(scope)
                            .withOnlyAttached(onlyAttached)
                            .withMarker(page)
            );
            response.setPolicies(result.getPolicies());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "policies.versions.list",
            path = "{partition}/policies/POLICY_ARN/versions"
    )
    public PolicyVersionsResponse policiesVersionsList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("policyArn") final String policyArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IamCaller.get(ListPolicyVersionsRequest.class, PolicyVersionsResponse.class, credentials, partition).execute((client, request, response) -> {
            final ListPolicyVersionsResult result = client.listPolicyVersions(
                    request
                            .withPolicyArn(policyArn)
                            .withMarker(page)
            );
            response.setPolicyVersions(result.getVersions());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "policies.versions.get",
            path = "{partition}/policies/POLICY_ARN/versions/{versionId}"
    )
    public PolicyVersionResponse policiesVersionsGet(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("policyArn") final String policyArn,
            @Named("versionId") final String versionId
    ) throws UnsupportedEncodingException, AmazonUnparsedException {
        return IamCaller.get(GetPolicyVersionRequest.class, PolicyVersionResponse.class, credentials, partition).execute((client, request, response) -> {
            final GetPolicyVersionResult result = client.getPolicyVersion(
                    request
                            .withPolicyArn(policyArn)
                            .withVersionId(versionId)
            );
            response.setPolicyVersion(result.getPolicyVersion());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "roles.policies.list",
            path = "{partition}/roles/{roleName}/policies"
    )
    public PolicyNamesResponse rolesPoliciesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("roleName") final String roleName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IamCaller.get(ListRolePoliciesRequest.class, PolicyNamesResponse.class, credentials, partition).execute((client, request, response) -> {
            final ListRolePoliciesResult result = client.listRolePolicies(
                    request
                            .withRoleName(roleName)
                            .withMarker(page)
            );
            response.setPolicyNames(result.getPolicyNames());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "roles.policies.get",
            path = "{partition}/roles/{roleName}/policies/{policyName}"
    )
    public PolicyResponse rolesPoliciesGet(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("roleName") final String roleName,
            @Named("policyName") final String policyName
    ) throws AmazonUnparsedException {
        return IamCaller.get(GetRolePolicyRequest.class, PolicyResponse.class, credentials, partition).execute((client, request, response) -> {
            final GetRolePolicyResult result = client.getRolePolicy(
                    request
                            .withRoleName(roleName)
                            .withPolicyName(policyName)
            );
            response.setPolicyDocument(result.getPolicyDocument());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "roles.attachedPolicies.list",
            path = "{partition}/roles/{roleName}/attached-policies"
    )
    public AttachedPoliciesResponse rolesAttachedPoliciesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("roleName") final String roleName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IamCaller.get(ListAttachedRolePoliciesRequest.class, AttachedPoliciesResponse.class, credentials, partition).execute((client, request, response) -> {
            final ListAttachedRolePoliciesResult result = client.listAttachedRolePolicies(
                    request
                            .withRoleName(roleName)
                            .withMarker(page)
            );
            response.setAttachedPolicies(result.getAttachedPolicies());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "samlProviders.list",
            path = "{partition}/saml-providers"
    )
    public SamlProvidersResponse samlProvidersList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition
    ) throws AmazonUnparsedException {
        return IamCaller.get(ListSAMLProvidersRequest.class, SamlProvidersResponse.class, credentials, partition).execute((client, request, response) -> {
            final ListSAMLProvidersResult result = client.listSAMLProviders(request);
            response.setSamlProviders(result.getSAMLProviderList());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "samlProviders.get",
            path = "{partition}/saml-providers/ARN"
    )
    public SamlProviderResponse samlProvidersGet(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("arn") final String arn
    ) throws AmazonUnparsedException {
        return IamCaller.get(GetSAMLProviderRequest.class, SamlProviderResponse.class, credentials, partition).execute((client, request, response) -> {
            final GetSAMLProviderResult result = client.getSAMLProvider(request.withSAMLProviderArn(arn));
            response.setMetadataDocument(result.getSAMLMetadataDocument());
            response.setCreateDate(result.getCreateDate());
            response.setValidUntil(result.getValidUntil());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.policies.list",
            path = "{partition}/users/{userName}/policies"
    )
    public PolicyNamesResponse usersPoliciesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IamCaller.get(ListUserPoliciesRequest.class, PolicyNamesResponse.class, credentials, partition).execute((client, request, response) -> {
            final ListUserPoliciesResult result = client.listUserPolicies(request.withUserName(userName).withMarker(page));
            response.setPolicyNames(result.getPolicyNames());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.policies.get",
            path = "{partition}/users/{userName}/policies/{policyName}"
    )
    public PolicyResponse usersPoliciesGet(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName,
            @Named("policyName") final String policyName
    ) throws UnsupportedEncodingException, AmazonUnparsedException {
        return IamCaller.get(GetUserPolicyRequest.class, PolicyResponse.class, credentials, partition).execute((client, request, response) -> {
            final GetUserPolicyResult result = client.getUserPolicy(request.withUserName(userName).withPolicyName(policyName));
            response.setPolicyDocument(result.getPolicyDocument());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "users.attachedPolicies.list",
            path = "{partition}/users/{userName}/attached-policies"
    )
    public AttachedPoliciesResponse usersAttachedPoliciesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("userName") final String userName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IamCaller.get(ListAttachedUserPoliciesRequest.class, AttachedPoliciesResponse.class, credentials, partition).execute((client, request, response) -> {
            final ListAttachedUserPoliciesResult result = client.listAttachedUserPolicies(
                    request
                            .withUserName(userName)
                            .withMarker(page)
            );
            response.setAttachedPolicies(result.getAttachedPolicies());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "virtualMfaDevices.list",
            path = "{partition}/virtual-mfa-devices"
    )
    public VirtualMfaDevicesResponse virtualMfaDevicesList(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return IamCaller.get(ListVirtualMFADevicesRequest.class, VirtualMfaDevicesResponse.class, credentials, partition).execute((client, request, response) -> {
            final ListVirtualMFADevicesResult result = client.listVirtualMFADevices(
                    request
                            .withMarker(page)
            );
            response.setVirtualMfaDevices(result.getVirtualMFADevices());
            response.setNextPage(result.getMarker());
        });
    }
}
