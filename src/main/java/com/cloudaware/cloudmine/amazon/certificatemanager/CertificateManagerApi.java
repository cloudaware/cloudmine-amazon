package com.cloudaware.cloudmine.amazon.certificatemanager;

import com.amazonaws.services.certificatemanager.model.AddTagsToCertificateRequest;
import com.amazonaws.services.certificatemanager.model.DescribeCertificateRequest;
import com.amazonaws.services.certificatemanager.model.DescribeCertificateResult;
import com.amazonaws.services.certificatemanager.model.ListCertificatesRequest;
import com.amazonaws.services.certificatemanager.model.ListCertificatesResult;
import com.amazonaws.services.certificatemanager.model.ListTagsForCertificateRequest;
import com.amazonaws.services.certificatemanager.model.ListTagsForCertificateResult;
import com.amazonaws.services.certificatemanager.model.RemoveTagsFromCertificateRequest;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "certificatemanager",
        canonicalName = "CertificateManager",
        title = "AWS Certificate Manager",
        description = "AWS Certificate Manager (ACM) makes it easy to provision, manage, and deploy SSL/TLS certificates on AWS-managed resources.",
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
public final class CertificateManagerApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "certificates.list",
            path = "{region}/certificates"
    )
    public CertificateSummariesResponse certificatesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CertificateManagerCaller.get(ListCertificatesRequest.class, CertificateSummariesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListCertificatesResult result = client.listCertificates(request.withNextToken(page));
            response.setCertificateSummaries(result.getCertificateSummaryList());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "certificates.details.get",
            path = "{region}/certificates/{certificateArn}/details"
    )
    public CertificateDetailsResponse certificatesDetailsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("certificateArn") final String certificateArn
    ) throws AmazonUnparsedException {
        return CertificateManagerCaller.get(DescribeCertificateRequest.class, CertificateDetailsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeCertificateResult result = client.describeCertificate(request.withCertificateArn(certificateArn));
            response.setCertificateDetail(result.getCertificate());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "certificates.tags.list",
            path = "{region}/certificates/{certificateArn}/tags"
    )
    public TagsResponse certificatesTagsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("certificateArn") final String certificateArn
    ) throws AmazonUnparsedException {
        return CertificateManagerCaller.get(ListTagsForCertificateRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTagsForCertificateResult result = client.listTagsForCertificate(request.withCertificateArn(certificateArn));
            response.setTags(result.getTags());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.PUT,
            name = "certificates.tags.add",
            path = "{region}/certificates/{certificateArn}/tags"
    )
    public TagsResponse certificatesTagsAdd(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("certificateArn") final String certificateArn,
            final TagsRequest tagsRequest
    ) throws AmazonUnparsedException {
        return CertificateManagerCaller.get(AddTagsToCertificateRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            client.addTagsToCertificate(request.withCertificateArn(certificateArn).withTags(tagsRequest.getTags()));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.DELETE,
            name = "certificates.tags.remove",
            path = "{region}/certificates/{certificateArn}/tags"
    )
    public TagsResponse certificatesTagsRemove(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("certificateArn") final String certificateArn,
            final TagsRequest tagsRequest
    ) throws AmazonUnparsedException {
        return CertificateManagerCaller.get(RemoveTagsFromCertificateRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            client.removeTagsFromCertificate(request.withCertificateArn(certificateArn).withTags(tagsRequest.getTags()));
        });
    }
}
