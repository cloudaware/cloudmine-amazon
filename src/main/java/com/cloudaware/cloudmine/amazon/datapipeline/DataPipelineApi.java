package com.cloudaware.cloudmine.amazon.datapipeline;

import com.amazonaws.services.datapipeline.model.AddTagsRequest;
import com.amazonaws.services.datapipeline.model.DescribeObjectsRequest;
import com.amazonaws.services.datapipeline.model.DescribeObjectsResult;
import com.amazonaws.services.datapipeline.model.DescribePipelinesRequest;
import com.amazonaws.services.datapipeline.model.DescribePipelinesResult;
import com.amazonaws.services.datapipeline.model.GetPipelineDefinitionRequest;
import com.amazonaws.services.datapipeline.model.GetPipelineDefinitionResult;
import com.amazonaws.services.datapipeline.model.ListPipelinesRequest;
import com.amazonaws.services.datapipeline.model.ListPipelinesResult;
import com.amazonaws.services.datapipeline.model.RemoveTagsRequest;
import com.amazonaws.services.datapipeline.model.Tag;
import com.cloudaware.cloudmine.amazon.AmazonResponse;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.common.collect.Lists;

import java.util.List;

@Api(
        name = "datapipeline",
        canonicalName = "DataPipeline",
        title = "AWS Data Pipeline",
        description = "Orchestration for Data-Driven Workflows",
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
public final class DataPipelineApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "pipelines.list",
            path = "{region}/pipelines"
    )
    public PipelinesResponse pipelinesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return DataPipelineCaller.get(ListPipelinesRequest.class, PipelinesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListPipelinesResult result = client.listPipelines(request.withMarker(page));
            response.setPipelines(result.getPipelineIdList());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "pipelineDescriptions.list",
            path = "{region}/pipeline-descriptions"
    )
    public PipelineDescriptionsResponse pipelineDescriptionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("pipelineId") final List<String> pipelineIds
    ) throws AmazonUnparsedException {
        return DataPipelineCaller.get(DescribePipelinesRequest.class, PipelineDescriptionsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribePipelinesResult result = client.describePipelines(request.withPipelineIds(pipelineIds));
            response.setPipelineDescriptions(result.getPipelineDescriptionList());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "pipelines.get",
            path = "{region}/pipelines/{pipelineId}"
    )
    public PipelineDefinitionResponse pipelinesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("pipelineId") final String pipelineId,
            @Named("version") @Nullable final String version
    ) throws AmazonUnparsedException {
        return DataPipelineCaller.get(GetPipelineDefinitionRequest.class, PipelineDefinitionResponse.class, credentials, region).execute((client, request, response) -> {
            final GetPipelineDefinitionResult result = client.getPipelineDefinition(request.withPipelineId(pipelineId).withVersion(version));
            response.setPipelineObjects(result.getPipelineObjects());
            response.setParameterObjects(result.getParameterObjects());
            response.setParameterValues(result.getParameterValues());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "pipelines.objects.list",
            path = "{region}/pipelines/{pipelineId}/objects"
    )
    public PipelineObjectsResponse pipelineObjectsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page,
            @Named("pipelineId") final String pipelineId,
            @Named("objectId") final List<String> objectIds
    ) throws AmazonUnparsedException {
        return DataPipelineCaller.get(DescribeObjectsRequest.class, PipelineObjectsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeObjectsResult result = client.describeObjects(request.withPipelineId(pipelineId).withObjectIds(objectIds).withMarker(page));
            response.setPipelineObjects(result.getPipelineObjects());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "pipelines.tags.create",
            path = "{region}/pipelines/tags/create"
    )
    public AmazonResponse createTags(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return DataPipelineCaller.get(AddTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            final List<Tag> tags = Lists.newArrayList();
            for (final String key : request.getTags().keySet()) {
                final Tag tag = new Tag();
                tag.setKey(key);
                tag.setValue(request.getTags().get(key));
                tags.add(tag);
            }
            client.addTags(r.withPipelineId(request.getPipelineId()).withTags(tags));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "pipelines.tags.delete",
            path = "{region}/pipelines/tags/detele"
    )
    public AmazonResponse tagsDelete(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return DataPipelineCaller.get(RemoveTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.removeTags(r.withPipelineId(request.getPipelineId()).withTagKeys(request.getTags().keySet()));
        });
    }
}
