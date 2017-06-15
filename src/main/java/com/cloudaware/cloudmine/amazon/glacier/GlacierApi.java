package com.cloudaware.cloudmine.amazon.glacier;

import com.amazonaws.services.glacier.model.ListVaultsRequest;
import com.amazonaws.services.glacier.model.ListVaultsResult;
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
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "glacier",
        canonicalName = "Glacier",
        title = "Amazon Glacier",
        description = "Low-Cost Archive Storage in the Cloud",
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
public final class GlacierApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "vaults.list",
            path = "{region}/vaults"
    )
    public VaultsResponse vaultsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return GlacierCaller.get(ListVaultsRequest.class, VaultsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListVaultsResult result = client.listVaults(
                    request
                            .withAccountId("-")
                            .withMarker(page)
            );
            response.setVaults(result.getVaultList());
            response.setNextPage(result.getMarker());
        });
    }
}
