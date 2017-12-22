package com.cloudaware.cloudmine.amazon.codedeploy;

import com.amazonaws.services.codedeploy.model.AddTagsToOnPremisesInstancesRequest;
import com.amazonaws.services.codedeploy.model.BatchGetApplicationRevisionsRequest;
import com.amazonaws.services.codedeploy.model.BatchGetApplicationRevisionsResult;
import com.amazonaws.services.codedeploy.model.BatchGetApplicationsRequest;
import com.amazonaws.services.codedeploy.model.BatchGetApplicationsResult;
import com.amazonaws.services.codedeploy.model.BatchGetDeploymentGroupsRequest;
import com.amazonaws.services.codedeploy.model.BatchGetDeploymentGroupsResult;
import com.amazonaws.services.codedeploy.model.BatchGetDeploymentInstancesRequest;
import com.amazonaws.services.codedeploy.model.BatchGetDeploymentInstancesResult;
import com.amazonaws.services.codedeploy.model.BatchGetDeploymentsRequest;
import com.amazonaws.services.codedeploy.model.BatchGetDeploymentsResult;
import com.amazonaws.services.codedeploy.model.BatchGetOnPremisesInstancesRequest;
import com.amazonaws.services.codedeploy.model.BatchGetOnPremisesInstancesResult;
import com.amazonaws.services.codedeploy.model.GetDeploymentConfigRequest;
import com.amazonaws.services.codedeploy.model.GetDeploymentConfigResult;
import com.amazonaws.services.codedeploy.model.ListApplicationRevisionsRequest;
import com.amazonaws.services.codedeploy.model.ListApplicationRevisionsResult;
import com.amazonaws.services.codedeploy.model.ListApplicationsRequest;
import com.amazonaws.services.codedeploy.model.ListApplicationsResult;
import com.amazonaws.services.codedeploy.model.ListDeploymentConfigsRequest;
import com.amazonaws.services.codedeploy.model.ListDeploymentConfigsResult;
import com.amazonaws.services.codedeploy.model.ListDeploymentGroupsRequest;
import com.amazonaws.services.codedeploy.model.ListDeploymentGroupsResult;
import com.amazonaws.services.codedeploy.model.ListDeploymentInstancesRequest;
import com.amazonaws.services.codedeploy.model.ListDeploymentInstancesResult;
import com.amazonaws.services.codedeploy.model.ListDeploymentsRequest;
import com.amazonaws.services.codedeploy.model.ListDeploymentsResult;
import com.amazonaws.services.codedeploy.model.ListGitHubAccountTokenNamesRequest;
import com.amazonaws.services.codedeploy.model.ListGitHubAccountTokenNamesResult;
import com.amazonaws.services.codedeploy.model.ListOnPremisesInstancesRequest;
import com.amazonaws.services.codedeploy.model.ListOnPremisesInstancesResult;
import com.amazonaws.services.codedeploy.model.RemoveTagsFromOnPremisesInstancesRequest;
import com.amazonaws.services.codedeploy.model.Tag;
import com.amazonaws.services.codedeploy.model.TimeRange;
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

import java.util.Date;
import java.util.List;

@Api(
        name = "codedeploy",
        canonicalName = "CodeDeploy",
        title = "AWS CodeDeploy",
        description = "AWS CodeDeploy is a service that automates code deployments to any instance",
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
public final class CodeDeployApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "applicationNames.list",
            path = "{region}/application-names"
    )
    public ApplicationNamesResponse applicationNamesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodeDeployCaller.get(ListApplicationsRequest.class, ApplicationNamesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListApplicationsResult result = client.listApplications(request.withNextToken(page));
            response.setApplicationNames(result.getApplications());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "applications.list",
            path = "{region}/applications"
    )
    public ApplicationsResponse applicationsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("applicationNames") final List<String> applicationNames
    ) throws AmazonUnparsedException {
        return CodeDeployCaller.get(BatchGetApplicationsRequest.class, ApplicationsResponse.class, credentials, region).execute((client, request, response) -> {
            final BatchGetApplicationsResult result = client.batchGetApplications(request.withApplicationNames(applicationNames));
            response.setApplications(result.getApplicationsInfo());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "applications.applicationRevisionLocations.list",
            path = "{region}/applications/{applicationName}/application-revision-locations"
    )
    public ApplicationRevisionLocationsResponse applicationsApplicationRevisionLocationsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("applicationName") final String applicationName,
            @Named("deployed") @Nullable final String deployed,
            @Named("s3Bucket") @Nullable final String s3Bucket,
            @Named("s3KeyPrefix") @Nullable final String s3KeyPrefix,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodeDeployCaller.get(ListApplicationRevisionsRequest.class, ApplicationRevisionLocationsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListApplicationRevisionsResult result = client.listApplicationRevisions(
                    request
                            .withApplicationName(applicationName)
                            .withDeployed(deployed)
                            .withS3Bucket(s3Bucket)
                            .withS3KeyPrefix(s3KeyPrefix)
                            .withNextToken(page)
            );
            response.setRevisionLocations(result.getRevisions());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "applications.applicationRevisions.list",
            path = "{region}/applications/{applicationName}/application-revisions"
    )
    public ApplicationRevisionsResponse applicationsApplicationRevisionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("applicationName") final String applicationName,
            final ApplicationRevisionsRequest applicationRevisionsRequest
    ) throws AmazonUnparsedException {
        return CodeDeployCaller.get(BatchGetApplicationRevisionsRequest.class, ApplicationRevisionsResponse.class, credentials, region).execute((client, request, response) -> {
            final BatchGetApplicationRevisionsResult result = client.batchGetApplicationRevisions(
                    request
                            .withApplicationName(applicationName)
                            .withRevisions(applicationRevisionsRequest.getRevisionLocations())
            );
            response.setApplicationName(result.getApplicationName());
            response.setErrorMessage(result.getErrorMessage());
            response.setRevisions(result.getRevisions());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "applications.deploymentGroupNames.list",
            path = "{region}/applications/{applicationName}/deployment-group-names"
    )
    public DeploymentGroupNamesResponse applicationsDeploymentGroupNamesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("applicationName") final String applicationName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodeDeployCaller.get(ListDeploymentGroupsRequest.class, DeploymentGroupNamesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListDeploymentGroupsResult result = client.listDeploymentGroups(
                    request
                            .withApplicationName(applicationName)
                            .withNextToken(page)
            );
            response.setApplicationName(result.getApplicationName());
            response.setDeploymentGroupNames(result.getDeploymentGroups());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "applications.deploymentGroups.list",
            path = "{region}/applications/{applicationName}/deployment-groups"
    )
    public DeploymentGroupsResponse applicationsDeploymentGroupsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("applicationName") final String applicationName,
            @Named("deploymentGroupNames") final List<String> deploymentGroupNames
    ) throws AmazonUnparsedException {
        return CodeDeployCaller.get(BatchGetDeploymentGroupsRequest.class, DeploymentGroupsResponse.class, credentials, region).execute((client, request, response) -> {
            final BatchGetDeploymentGroupsResult result = client.batchGetDeploymentGroups(
                    request
                            .withApplicationName(applicationName)
                            .withDeploymentGroupNames(deploymentGroupNames)
            );
            response.setErrorMessage(result.getErrorMessage());
            response.setDeploymentGroups(result.getDeploymentGroupsInfo());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "deploymentIds.list",
            path = "{region}/deployment-ids"
    )
    public DeploymentIdsResponse deploymentIdsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("applicationName") @Nullable final String applicationName,
            @Named("deploymentGroupName") @Nullable final String deploymentGroupName,
            @Named("includeOnlyStatuses") @Nullable final List<String> includeOnlyStatuses,
            @Named("startDate") @Nullable final Date startDate,
            @Named("endDate") @Nullable final Date endDate,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodeDeployCaller.get(ListDeploymentsRequest.class, DeploymentIdsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListDeploymentsResult result = client.listDeployments(
                    request
                            .withApplicationName(applicationName)
                            .withDeploymentGroupName(deploymentGroupName)
                            .withCreateTimeRange(new TimeRange().withStart(startDate).withEnd(endDate))
                            .withIncludeOnlyStatuses(includeOnlyStatuses)
                            .withNextToken(page)
            );
            response.setDeploymentIds(result.getDeployments());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "deployments.list",
            path = "{region}/deployments"
    )
    public DeploymentsResponse deploymentsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("deploymentIds") @Nullable final List<String> deploymentIds
    ) throws AmazonUnparsedException {
        return CodeDeployCaller.get(BatchGetDeploymentsRequest.class, DeploymentsResponse.class, credentials, region).execute((client, request, response) -> {
            final BatchGetDeploymentsResult result = client.batchGetDeployments(request.withDeploymentIds(deploymentIds));
            response.setDeployments(result.getDeploymentsInfo());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "deploymentConfigs.list",
            path = "{region}/deployment-configs"
    )
    public DeploymentConfigsResponse deploymentConfigsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodeDeployCaller.get(ListDeploymentConfigsRequest.class, DeploymentConfigsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListDeploymentConfigsResult result = client.listDeploymentConfigs(request.withNextToken(page));
            response.setDeploymentConfigs(result.getDeploymentConfigsList());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "deploymentConfig.get",
            path = "{region}/deployment-configs/{deploymentConfigName}"
    )
    public DeploymentConfigResponse deploymentConfigGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("deploymentConfigName") final String deploymentConfigName
    ) throws AmazonUnparsedException {
        return CodeDeployCaller.get(GetDeploymentConfigRequest.class, DeploymentConfigResponse.class, credentials, region).execute((client, request, response) -> {
            final GetDeploymentConfigResult result = client.getDeploymentConfig(request.withDeploymentConfigName(deploymentConfigName));
            response.setDeploymentConfig(result.getDeploymentConfigInfo());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "deployments.deploymentInstanceIds.list",
            path = "{region}/deployments/{deploymentId}/deployment-instance-ids"
    )
    public DeploymentInstanceIdsResponse deploymentsDeploymentInstanceIdsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("deploymentId") final String deploymentId,
            @Named("instanceStatusFilter") @Nullable final List<String> instanceStatusFilter,
            @Named("instanceTypeFilter") @Nullable final List<String> instanceTypeFilter,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodeDeployCaller.get(ListDeploymentInstancesRequest.class, DeploymentInstanceIdsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListDeploymentInstancesResult result = client.listDeploymentInstances(
                    request
                            .withDeploymentId(deploymentId)
                            .withInstanceStatusFilter(instanceStatusFilter)
                            .withInstanceTypeFilter(instanceTypeFilter)
                            .withNextToken(page)
            );
            response.setDeploymentInstanceIds(result.getInstancesList());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "deployments.deploymentInstances.list",
            path = "{region}/deployments/{deploymentId}/deployment-instances"
    )
    public DeploymentInstancesResponse deploymentsDeploymentInstancesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("deploymentId") final String deploymentId,
            @Named("deploymentInstanceIds") final List<String> deploymentInstanceIds
    ) throws AmazonUnparsedException {
        return CodeDeployCaller.get(BatchGetDeploymentInstancesRequest.class, DeploymentInstancesResponse.class, credentials, region).execute((client, request, response) -> {
            final BatchGetDeploymentInstancesResult result = client.batchGetDeploymentInstances(
                    request
                            .withDeploymentId(deploymentId)
                            .withInstanceIds(deploymentInstanceIds)
            );
            response.setInstances(result.getInstancesSummary());
            response.setErrorMessage(result.getErrorMessage());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "onPremisesInstanceNames.list",
            path = "{region}/on-premises-instance-names"
    )
    public OnPremisesInstanceNamesResponse onPremisesInstanceNamesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page,
            @Nullable final OnPremisesInstanceNamesRequest onPremisesInstanceNamesRequest
    ) throws AmazonUnparsedException {
        return CodeDeployCaller.get(ListOnPremisesInstancesRequest.class, OnPremisesInstanceNamesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListOnPremisesInstancesResult result = client.listOnPremisesInstances(
                    request
                            .withRegistrationStatus(onPremisesInstanceNamesRequest.getRegistrationStatus())
                            .withTagFilters(onPremisesInstanceNamesRequest.getTagFilters())
                            .withNextToken(page)
            );
            response.setInstanceNames(result.getInstanceNames());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "onPremisesInstances.list",
            path = "{region}/on-premises-instances"
    )
    public OnPremisesInstancesResponse onPremisesInstancesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("instanceNames") @Nullable final List<String> instanceNames
    ) throws AmazonUnparsedException {
        return CodeDeployCaller.get(BatchGetOnPremisesInstancesRequest.class, OnPremisesInstancesResponse.class, credentials, region).execute((client, request, response) -> {
            final BatchGetOnPremisesInstancesResult result = client.batchGetOnPremisesInstances(request.withInstanceNames(instanceNames));
            response.setInstances(result.getInstanceInfos());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "onPremisesInstances.tags.create",
            path = "{region}/on-premises-instances/tags/create"
    )
    public AmazonResponse onPremisesInstancesTagsCreate(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest tagsRequest
    ) throws AmazonUnparsedException {
        return CodeDeployCaller.get(AddTagsToOnPremisesInstancesRequest.class, AmazonResponse.class, credentials, region).execute((client, request, response) -> {
            final List<Tag> tags = Lists.newArrayList();
            for (final String key : tagsRequest.getTags().keySet()) {
                final Tag tag = new Tag();
                tag.setKey(key);
                tag.setValue(tagsRequest.getTags().get(key));
                tags.add(tag);
            }
            client.addTagsToOnPremisesInstances(request.withInstanceNames(tagsRequest.getInstanceNames()).withTags(tags));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "onPremisesInstances.tags.delete",
            path = "{region}/on-premises-instances/tags/delete"
    )
    public AmazonResponse onPremisesInstancesTagsDelete(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final TagsRequest tagsRequest
    ) throws AmazonUnparsedException {
        return CodeDeployCaller.get(RemoveTagsFromOnPremisesInstancesRequest.class, AmazonResponse.class, credentials, region).execute((client, request, response) -> {
            final List<Tag> tags = Lists.newArrayList();
            for (final String key : tagsRequest.getTags().keySet()) {
                final Tag tag = new Tag();
                tag.setKey(key);
                tag.setValue(tagsRequest.getTags().get(key));
                tags.add(tag);
            }
            client.removeTagsFromOnPremisesInstances(request.withInstanceNames(tagsRequest.getInstanceNames()).withTags(tags));
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "githubAccountTokenNames.list",
            path = "{region}/github-account-token-names"
    )
    public GithubAccountTokenNamesResponse githubAccountTokenNamesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodeDeployCaller.get(ListGitHubAccountTokenNamesRequest.class, GithubAccountTokenNamesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListGitHubAccountTokenNamesResult result = client.listGitHubAccountTokenNames(request.withNextToken(page));
            response.setTokenNames(result.getTokenNameList());
            response.setNextPage(result.getNextToken());
        });
    }
}
