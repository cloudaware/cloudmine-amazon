package com.cloudaware.cloudmine.amazon.sts;

import com.amazonaws.services.securitytoken.model.AssumeRoleResult;
import com.amazonaws.services.securitytoken.model.GetCallerIdentityRequest;
import com.amazonaws.services.securitytoken.model.GetCallerIdentityResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "sts",
        canonicalName = "Sts",
        title = "AWS Security Token Service (STS)",
        description = "Temporary Credentials",
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
public final class StsApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "roles.assume",
            path = "{partition}/roles/assume"
    )
    public AssumeRoleResponse rolesAssume(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition,
            final AssumeRoleRequest request
    ) throws AmazonUnparsedException {
        return StsCaller.get(com.amazonaws.services.securitytoken.model.AssumeRoleRequest.class, AssumeRoleResponse.class, credentials, partition)
                .execute((client, assumeRoleRequest, response) -> {
            final AssumeRoleResult result = client.assumeRole(
                    assumeRoleRequest
                            .withDurationSeconds(request.getDurationSeconds())
                            .withExternalId(request.getExternalId())
                            .withPolicy(request.getPolicy())
                            .withRoleArn(request.getRoleArn())
                            .withRoleSessionName(request.getRoleSessionName())
                            .withSerialNumber(request.getSerialNumber())
                            .withTokenCode(request.getTokenCode())
            );
            response.setCredentials(result.getCredentials());
            response.setAssumedRoleUser(result.getAssumedRoleUser());
            response.setPackedPolicySize(result.getPackedPolicySize());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "callerIdentity.get",
            path = "{partition}/caller-identity"
    )
    public CallerIdentityResponse asd(
            @Named("credentials") final String credentials,
            @Named("partition") final String partition
    ) throws AmazonUnparsedException {
        return StsCaller.get(GetCallerIdentityRequest.class, CallerIdentityResponse.class, credentials, partition).execute((client, request, response) -> {
            final GetCallerIdentityResult result = client.getCallerIdentity(request);
            response.setUserId(result.getUserId());
            response.setAccount(result.getAccount());
            response.setArn(result.getArn());
        });
    }
}
