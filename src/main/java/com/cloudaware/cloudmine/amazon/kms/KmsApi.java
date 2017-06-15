package com.cloudaware.cloudmine.amazon.kms;

import com.amazonaws.services.kms.model.DescribeKeyRequest;
import com.amazonaws.services.kms.model.DescribeKeyResult;
import com.amazonaws.services.kms.model.GetKeyPolicyRequest;
import com.amazonaws.services.kms.model.GetKeyPolicyResult;
import com.amazonaws.services.kms.model.GetKeyRotationStatusRequest;
import com.amazonaws.services.kms.model.GetKeyRotationStatusResult;
import com.amazonaws.services.kms.model.ListAliasesRequest;
import com.amazonaws.services.kms.model.ListAliasesResult;
import com.amazonaws.services.kms.model.ListGrantsRequest;
import com.amazonaws.services.kms.model.ListGrantsResult;
import com.amazonaws.services.kms.model.ListKeyPoliciesRequest;
import com.amazonaws.services.kms.model.ListKeyPoliciesResult;
import com.amazonaws.services.kms.model.ListKeysRequest;
import com.amazonaws.services.kms.model.ListKeysResult;
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
        name = "kms",
        canonicalName = "Kms",
        title = "AWS Key Management Service",
        description = "Managed Creation and Control of Encryption Keys",
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
public final class KmsApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "aliases.list",
            path = "{region}/aliases"
    )
    public AliasesResponse aliasesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return KmsCaller.get(ListAliasesRequest.class, AliasesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListAliasesResult result = client.listAliases(request.withMarker(page));
            response.setAliases(result.getAliases());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "keys.list",
            path = "{region}/keys"
    )
    public KeysResponse keysList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return KmsCaller.get(ListKeysRequest.class, KeysResponse.class, credentials, region).execute((client, request, response) -> {
            final ListKeysResult result = client.listKeys(request.withMarker(page));
            response.setKeys(result.getKeys());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "keys.get",
            path = "{region}/keys/{keyId}"
    )
    public KeyResponse keysGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("keyId") final String keyId
    ) throws AmazonUnparsedException {
        return KmsCaller.get(DescribeKeyRequest.class, KeyResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeKeyResult result = client.describeKey(request.withKeyId(keyId));
            response.setKey(result.getKeyMetadata());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "keys.grants.list",
            path = "{region}/keys/{keyId}/grants"
    )
    public GrantsResponse keysGrantsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("keyId") final String keyId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return KmsCaller.get(ListGrantsRequest.class, GrantsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListGrantsResult result = client.listGrants(
                    request
                            .withKeyId(keyId)
                            .withMarker(page)
            );
            response.setGrants(result.getGrants());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "keys.policies.list",
            path = "{region}/keys/{keyId}/policies"
    )
    public PoliciesResponse keysPoliciesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("keyId") final String keyId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return KmsCaller.get(ListKeyPoliciesRequest.class, PoliciesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListKeyPoliciesResult result = client.listKeyPolicies(
                    request
                            .withKeyId(keyId)
                            .withMarker(page)
            );
            response.setPolicyNames(result.getPolicyNames());
            response.setNextPage(result.getNextMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "keys.policies.get",
            path = "{region}/keys/{keyId}/policies/{policyName}"
    )
    public PolicyResponse keysPoliciesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("keyId") final String keyId,
            @Named("policyName") final String policyName
    ) throws AmazonUnparsedException {
        return KmsCaller.get(GetKeyPolicyRequest.class, PolicyResponse.class, credentials, region).execute((client, request, response) -> {
            final GetKeyPolicyResult result = client.getKeyPolicy(
                    request
                            .withKeyId(keyId)
                            .withPolicyName(policyName)
            );
            response.setPolicy(result.getPolicy());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "keys.rotationStatus.get",
            path = "{region}/keys/{keyId}/rotation-status"
    )
    public RotationStatusResponse keysGotationStatusGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("keyId") final String keyId
    ) throws AmazonUnparsedException {
        return KmsCaller.get(GetKeyRotationStatusRequest.class, RotationStatusResponse.class, credentials, region).execute((client, request, response) -> {
            final GetKeyRotationStatusResult result = client.getKeyRotationStatus(
                    request
                            .withKeyId(keyId)
            );
            response.setKeyRotationEnabled(result == null ? false : result.getKeyRotationEnabled());
        });
    }
}
