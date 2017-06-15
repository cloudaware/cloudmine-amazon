package com.cloudaware.cloudmine.amazon.ssm;

import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.model.DescribeInstanceInformationRequest;
import com.amazonaws.services.simplesystemsmanagement.model.DescribeInstanceInformationResult;
import com.amazonaws.services.simplesystemsmanagement.model.InstanceInformationStringFilter;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.ClientWrapper;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.client.util.Lists;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

import java.util.List;

@Api(
        name = "ssm",
        canonicalName = "Ssm",
        title = "Amazon EC2 Systems Manager",
        description = "Easily configure and manage Amazon EC2 and on-premises systems",
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
public final class SsmApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "instanceInformation.list",
            path = "{region}/instance-information"
    )
    public InstanceInformationResponse instanceInformationList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page,
            @Nullable final InstanceInformationRequest request
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSSimpleSystemsManagement> clientWrapper = new AmazonClientHelper(credentials).getSsm(region)) {
            final List<InstanceInformationStringFilter> filters = Lists.newArrayList();
            if (request.getFilters() != null) {
                request.getFilters().forEach((k, v) -> {
                    final InstanceInformationStringFilter filter = new InstanceInformationStringFilter();
                    filter.setKey(k);
                    filter.setValues(v);
                    filters.add(filter);
                });
            }
            final DescribeInstanceInformationResult result = clientWrapper.getClient()
                    .describeInstanceInformation(
                            new DescribeInstanceInformationRequest()
                                    .withFilters(filters)
                                    .withNextToken(page)
                    );
            return new InstanceInformationResponse(result.getInstanceInformationList(), result.getNextToken());
        } catch (Throwable t) {
            return new InstanceInformationResponse(AmazonResponse.parse(t));
        }
    }
}
