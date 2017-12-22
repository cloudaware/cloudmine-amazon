package com.cloudaware.cloudmine.amazon.emr;

import com.amazonaws.services.elasticmapreduce.model.AddJobFlowStepsRequest;
import com.amazonaws.services.elasticmapreduce.model.AddJobFlowStepsResult;
import com.amazonaws.services.elasticmapreduce.model.AddTagsRequest;
import com.amazonaws.services.elasticmapreduce.model.DescribeClusterRequest;
import com.amazonaws.services.elasticmapreduce.model.DescribeClusterResult;
import com.amazonaws.services.elasticmapreduce.model.DescribeStepRequest;
import com.amazonaws.services.elasticmapreduce.model.DescribeStepResult;
import com.amazonaws.services.elasticmapreduce.model.ListBootstrapActionsRequest;
import com.amazonaws.services.elasticmapreduce.model.ListBootstrapActionsResult;
import com.amazonaws.services.elasticmapreduce.model.ListClustersRequest;
import com.amazonaws.services.elasticmapreduce.model.ListClustersResult;
import com.amazonaws.services.elasticmapreduce.model.ListInstanceFleetsRequest;
import com.amazonaws.services.elasticmapreduce.model.ListInstanceFleetsResult;
import com.amazonaws.services.elasticmapreduce.model.ListInstanceGroupsRequest;
import com.amazonaws.services.elasticmapreduce.model.ListInstanceGroupsResult;
import com.amazonaws.services.elasticmapreduce.model.ListInstancesRequest;
import com.amazonaws.services.elasticmapreduce.model.ListInstancesResult;
import com.amazonaws.services.elasticmapreduce.model.ListStepsRequest;
import com.amazonaws.services.elasticmapreduce.model.ListStepsResult;
import com.amazonaws.services.elasticmapreduce.model.RemoveTagsRequest;
import com.amazonaws.services.elasticmapreduce.model.RunJobFlowRequest;
import com.amazonaws.services.elasticmapreduce.model.RunJobFlowResult;
import com.amazonaws.services.elasticmapreduce.model.Tag;
import com.amazonaws.services.elasticmapreduce.model.TerminateJobFlowsRequest;
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

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "emr",
        canonicalName = "Emr",
        title = "Amazon EMR",
        description = "Hosted Hadoop Framework",
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
public final class EmrApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.list",
            path = "{region}/cluster"
    )
    public ClustersResponse clustersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return EmrCaller.get(ListClustersRequest.class, ClustersResponse.class, credentials, region).execute((client, request, response) -> {
            final ListClustersResult result = client.listClusters(request.withMarker(page));
            response.setClusters(result.getClusters());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.get",
            path = "{region}/clusters/{clusterId}"
    )
    public ClusterResponse clustersGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterId") final String clusterId
    ) throws AmazonUnparsedException {
        return EmrCaller.get(DescribeClusterRequest.class, ClusterResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeClusterResult result = client.describeCluster(request.withClusterId(clusterId));
            response.setCluster(result.getCluster());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.bootstrapActions.list",
            path = "{region}/clusters/{clusterId}/bootstrap-actions"
    )
    public BootstrapActionsResponse clustersBootstrapActionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterId") final String clusterId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return EmrCaller.get(ListBootstrapActionsRequest.class, BootstrapActionsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListBootstrapActionsResult result = client.listBootstrapActions(
                    request
                            .withClusterId(clusterId)
                            .withMarker(page)
            );
            response.setBootstrapActions(result.getBootstrapActions());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.instanceGroups.list",
            path = "{region}/clusters/{clusterId}/instance-groups"
    )
    public InstanceGroupsResponse clustersInstanceGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterId") final String clusterId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return EmrCaller.get(ListInstanceGroupsRequest.class, InstanceGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListInstanceGroupsResult result = client.listInstanceGroups(
                    request
                            .withClusterId(clusterId)
                            .withMarker(page)
            );
            response.setInstanceGroups(result.getInstanceGroups());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.instanceFleets.list",
            path = "{region}/clusters/{clusterId}/instance-fleets"
    )
    public InstanceFleetsResponse clustersInstanceFleetsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterId") final String clusterId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return EmrCaller.get(ListInstanceFleetsRequest.class, InstanceFleetsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListInstanceFleetsResult result = client.listInstanceFleets(
                    request
                            .withClusterId(clusterId)
                            .withMarker(page)
            );
            response.setInstanceFleets(result.getInstanceFleets());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.instances.list",
            path = "{region}/clusters/{clusterId}/instances"
    )
    public InstancesResponse clustersInstancesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterId") final String clusterId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return EmrCaller.get(ListInstancesRequest.class, InstancesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListInstancesResult result = client.listInstances(
                    request
                            .withClusterId(clusterId)
                            .withMarker(page)
            );
            response.setInstances(result.getInstances());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.steps.list",
            path = "{region}/clusters/{clusterId}/steps"
    )
    public StepsResponse clustersStepsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterId") final String clusterId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return EmrCaller.get(ListStepsRequest.class, StepsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListStepsResult result = client.listSteps(
                    request
                            .withClusterId(clusterId)
                            .withMarker(page)
            );
            response.setSteps(result.getSteps());
            response.setNextPage(result.getMarker());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.steps.get",
            path = "{region}/clusters/{clusterId}/steps/{stepId}"
    )
    public StepResponse clustersStepsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterId") final String clusterId,
            @Named("stepId") final String stepId
    ) throws AmazonUnparsedException {
        return EmrCaller.get(DescribeStepRequest.class, StepResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeStepResult result = client.describeStep(
                    request
                            .withClusterId(clusterId)
                            .withStepId(stepId)
            );
            response.setStep(result.getStep());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "clusters.tags.create",
            path = "{region}/clusters/tags/create"
    )
    public AmazonResponse createTags(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return EmrCaller.get(AddTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            final List<Tag> tags = Lists.newArrayList();
            for (final String key : request.getTags().keySet()) {
                final Tag tag = new Tag();
                tag.setKey(key);
                tag.setValue(request.getTags().get(key));
                tags.add(tag);
            }
            client.addTags(r.withResourceId(request.getClusterId()).withTags(tags));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "clusters.tags.delete",
            path = "{region}/clusters/tags/detele"
    )
    public AmazonResponse tagsDelete(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest request
    ) throws AmazonUnparsedException {
        return EmrCaller.get(RemoveTagsRequest.class, AmazonResponse.class, credentials, region).execute((client, r, response) -> {
            client.removeTags(r.withResourceId(request.getClusterId()).withTagKeys(request.getTags().keySet()));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "jobFlows.run",
            path = "{region}/job-flows"
    )
    public JobFlowRunResponse jobFlowsRun(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final JobFlowRunRequest request
    ) throws AmazonUnparsedException {
        return EmrCaller.get(RunJobFlowRequest.class, JobFlowRunResponse.class, credentials, region).execute((client, runJobFlowRequest, response) -> {
            runJobFlowRequest.setName(request.getName());
            runJobFlowRequest.setLogUri(request.getLogUri());
            runJobFlowRequest.setAdditionalInfo(request.getAdditionalInfo());
            runJobFlowRequest.setAmiVersion(request.getAmiVersion());
            runJobFlowRequest.setReleaseLabel(request.getReleaseLabel());
            runJobFlowRequest.setInstances(request.getInstances());
            runJobFlowRequest.setSteps(request.getSteps());
            runJobFlowRequest.setBootstrapActions(request.getBootstrapActions());
            runJobFlowRequest.setSupportedProducts(request.getSupportedProducts());
            runJobFlowRequest.setNewSupportedProducts(request.getNewSupportedProducts());
            runJobFlowRequest.setApplications(request.getApplications());
            runJobFlowRequest.setConfigurations(request.getConfigurations());
            runJobFlowRequest.setVisibleToAllUsers(request.getVisibleToAllUsers());
            runJobFlowRequest.setJobFlowRole(request.getJobFlowRole());
            runJobFlowRequest.setServiceRole(request.getServiceRole());
            runJobFlowRequest.setTags(request.getTags());
            runJobFlowRequest.setSecurityConfiguration(request.getSecurityConfiguration());
            runJobFlowRequest.setAutoScalingRole(request.getAutoScalingRole());
            runJobFlowRequest.setScaleDownBehavior(request.getScaleDownBehavior());
            final RunJobFlowResult result = client.runJobFlow(runJobFlowRequest);
            response.setJobFlowId(result.getJobFlowId());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.DELETE,
            name = "jobFlows.terminate",
            path = "{region}/job-flows"
    )
    public AmazonResponse jobFlowsTerminate(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("jobFlowId") final List<String> jobFlowId
    ) throws AmazonUnparsedException {
        return EmrCaller.get(TerminateJobFlowsRequest.class, AmazonResponse.class, credentials, region).execute((client, request, response) -> {
            client.terminateJobFlows(request.withJobFlowIds(jobFlowId));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "jobFlows.steps.add",
            path = "{region}/job-flows/{jobFlowId}/steps"
    )
    public StepIdsResponse jobFlowsStepsAdd(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("jobFlowId") final String jobFlowId,
            final StepsRequest steps
    ) throws AmazonUnparsedException {
        return EmrCaller.get(AddJobFlowStepsRequest.class, StepIdsResponse.class, credentials, region).execute((client, request, response) -> {
            final AddJobFlowStepsResult result = client.addJobFlowSteps(
                    request
                            .withJobFlowId(jobFlowId)
                            .withSteps(steps.getStepConfigs())
            );
            response.setStepIds(result.getStepIds());
        });
    }

}
