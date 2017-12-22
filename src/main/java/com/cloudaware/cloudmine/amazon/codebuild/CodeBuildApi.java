package com.cloudaware.cloudmine.amazon.codebuild;

import com.amazonaws.services.codebuild.model.BatchGetBuildsRequest;
import com.amazonaws.services.codebuild.model.BatchGetBuildsResult;
import com.amazonaws.services.codebuild.model.BatchGetProjectsRequest;
import com.amazonaws.services.codebuild.model.BatchGetProjectsResult;
import com.amazonaws.services.codebuild.model.ListBuildsForProjectRequest;
import com.amazonaws.services.codebuild.model.ListBuildsForProjectResult;
import com.amazonaws.services.codebuild.model.ListBuildsRequest;
import com.amazonaws.services.codebuild.model.ListBuildsResult;
import com.amazonaws.services.codebuild.model.ListCuratedEnvironmentImagesRequest;
import com.amazonaws.services.codebuild.model.ListCuratedEnvironmentImagesResult;
import com.amazonaws.services.codebuild.model.ListProjectsRequest;
import com.amazonaws.services.codebuild.model.ListProjectsResult;
import com.amazonaws.services.codebuild.model.Tag;
import com.amazonaws.services.codebuild.model.UpdateProjectRequest;
import com.amazonaws.services.codebuild.model.UpdateProjectResult;
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
        name = "codebuild",
        canonicalName = "CodeBuild",
        title = "AWS CodeBuild",
        description = "Build and test code with continuous scaling. Only pay for the build time you use",
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
public final class CodeBuildApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "projectNames.list",
            path = "{region}/project-names"
    )
    public ProjectNamesResponse projectNamesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodeBuildCaller.get(ListProjectsRequest.class, ProjectNamesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListProjectsResult result = client.listProjects(request.withNextToken(page));
            response.setProjectNames(result.getProjects());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "projects.list",
            path = "{region}/projects"
    )
    public ProjectsResponse projectsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("projectNames") final List<String> projectNames
    ) throws AmazonUnparsedException {
        return CodeBuildCaller.get(BatchGetProjectsRequest.class, ProjectsResponse.class, credentials, region).execute((client, request, response) -> {
            final BatchGetProjectsResult result = client.batchGetProjects(request.withNames(projectNames));
            response.setProjects(result.getProjects());
            response.setProjectsNotFound(result.getProjectsNotFound());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "buildIds.list",
            path = "{region}/build-ids"
    )
    public BuildIdsResponse buildIdsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodeBuildCaller.get(ListBuildsRequest.class, BuildIdsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListBuildsResult result = client.listBuilds(request.withNextToken(page));
            response.setBuildIds(result.getIds());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "builds.list",
            path = "{region}/builds"
    )
    public BuildsResponse buildsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("buildIds") final List<String> buildIds
    ) throws AmazonUnparsedException {
        return CodeBuildCaller.get(BatchGetBuildsRequest.class, BuildsResponse.class, credentials, region).execute((client, request, response) -> {
            final BatchGetBuildsResult result = client.batchGetBuilds(request.withIds(buildIds));
            response.setBuilds(result.getBuilds());
            response.setBuildsNotFound(result.getBuildsNotFound());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "projects.builds.list",
            path = "{region}/projects/{projectName}/builds"
    )
    public ProjectsBuildsResponse projectsBuildsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("projectName") final String projectName,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodeBuildCaller.get(ListBuildsForProjectRequest.class, ProjectsBuildsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListBuildsForProjectResult result = client.listBuildsForProject(
                    request
                            .withProjectName(projectName)
                            .withNextToken(page)
            );
            response.setBuildIds(result.getIds());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "projects.update",
            path = "{region}/projects/update"
    )
    public ProjectUpdateResponse projectUpdate(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final ProjectUpdateRequest request
    ) throws AmazonUnparsedException {
        return CodeBuildCaller.get(UpdateProjectRequest.class, ProjectUpdateResponse.class, credentials, region).execute((client, updateProjectRequest, response) -> {
            updateProjectRequest.setName(request.getProjectName());
            updateProjectRequest.setArtifacts(request.getArtifacts());
            updateProjectRequest.setDescription(request.getDescription());
            updateProjectRequest.setEncryptionKey(request.getEncryptionKey());
            updateProjectRequest.setEnvironment(request.getEnvironment());
            updateProjectRequest.setServiceRole(request.getServiceRole());
            updateProjectRequest.setSource(request.getSource());
            updateProjectRequest.setTimeoutInMinutes(request.getTimeoutInMinutes());
            final List<Tag> tags = Lists.newArrayList();
            for (final String key : request.getTags().keySet()) {
                final Tag tag = new Tag();
                tag.setKey(key);
                tag.setValue(request.getTags().get(key));
                tags.add(tag);
            }
            updateProjectRequest.setTags(tags);
            final UpdateProjectResult result = client.updateProject(updateProjectRequest);
            response.setProject(result.getProject());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "curatedEnvironmentImages.list",
            path = "{region}/curated-environment-images"
    )
    public CuratedEnvironmentImagesResponse curatedEnvironmentImagesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return CodeBuildCaller.get(ListCuratedEnvironmentImagesRequest.class, CuratedEnvironmentImagesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListCuratedEnvironmentImagesResult result = client.listCuratedEnvironmentImages(request);
            response.setCuratedEnvironmentImages(result.getPlatforms());
        });
    }
}
