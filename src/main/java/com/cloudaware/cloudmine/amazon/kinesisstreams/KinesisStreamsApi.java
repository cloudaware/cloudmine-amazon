package com.cloudaware.cloudmine.amazon.kinesisstreams;

import com.amazonaws.services.kinesis.model.AddTagsToStreamRequest;
import com.amazonaws.services.kinesis.model.DescribeStreamRequest;
import com.amazonaws.services.kinesis.model.DescribeStreamResult;
import com.amazonaws.services.kinesis.model.ListStreamsRequest;
import com.amazonaws.services.kinesis.model.ListStreamsResult;
import com.amazonaws.services.kinesis.model.ListTagsForStreamRequest;
import com.amazonaws.services.kinesis.model.ListTagsForStreamResult;
import com.amazonaws.services.kinesis.model.RemoveTagsFromStreamRequest;
import com.cloudaware.cloudmine.amazon.AmazonResponse;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "kinesisstreams",
        canonicalName = "KinesisStreams",
        title = "Amazon Kinesis Streams",
        description = "Amazon Kinesis Streams is a managed service that scales elastically for real time processing of streaming big data",
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
public final class KinesisStreamsApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "streamNames.list",
            path = "{region}/stream-names"
    )
    public StreamNamesResponse streamNamesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return KinesisStreamsCaller.get(ListStreamsRequest.class, StreamNamesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListStreamsResult result = client.listStreams(request.withExclusiveStartStreamName(page));
            response.setStreamNames(result.getStreamNames());
            if (result.isHasMoreStreams()) {
                response.setNextPage(result.getStreamNames().get(result.getStreamNames().size() - 1));
            }
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "streams.get",
            path = "{region}/streams/{streamName}"
    )
    public StreamResponse streamsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("streamName") final String streamName
    ) throws AmazonUnparsedException {
        return KinesisStreamsCaller.get(DescribeStreamRequest.class, StreamResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeStreamResult result = client.describeStream(
                    request
                            .withStreamName(streamName)
                            .withLimit(10000)
            );
            response.setStream(result.getStreamDescription());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "tags.list",
            path = "{region}/tags"
    )
    public TagsResponse tagsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("streamName") final String streamName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return KinesisStreamsCaller.get(ListTagsForStreamRequest.class, TagsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTagsForStreamResult result = client.listTagsForStream(
                    request
                            .withStreamName(streamName)
                            .withExclusiveStartTagKey(page)
            );
            if (result.isHasMoreTags()) {
                response.setNextPage(result.getTags().get(result.getTags().size() - 1).getKey());
            }

            response.setTags(result.getTags());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.create",
            path = "{region}/tags/create"
    )
    public AmazonResponse createTags(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return KinesisStreamsCaller.get(AddTagsToStreamRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.addTagsToStream(r.withStreamName(request.getStreamName()).withTags(request.getTags()));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "tags.delete",
            path = "{region}/tags/detele"
    )
    public AmazonResponse tagsDelete(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return KinesisStreamsCaller.get(RemoveTagsFromStreamRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.removeTagsFromStream(r.withStreamName(request.getStreamName()).withTagKeys(request.getTags().keySet()));
        });
    }
}
