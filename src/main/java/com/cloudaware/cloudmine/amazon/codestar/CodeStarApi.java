package com.cloudaware.cloudmine.amazon.codestar;

import com.amazonaws.services.codestar.model.DescribeProjectRequest;
import com.amazonaws.services.codestar.model.DescribeProjectResult;
import com.amazonaws.services.codestar.model.DescribeUserProfileRequest;
import com.amazonaws.services.codestar.model.DescribeUserProfileResult;
import com.amazonaws.services.codestar.model.ListProjectsRequest;
import com.amazonaws.services.codestar.model.ListProjectsResult;
import com.amazonaws.services.codestar.model.ListResourcesRequest;
import com.amazonaws.services.codestar.model.ListResourcesResult;
import com.amazonaws.services.codestar.model.ListTeamMembersRequest;
import com.amazonaws.services.codestar.model.ListTeamMembersResult;
import com.amazonaws.services.codestar.model.ListUserProfilesRequest;
import com.amazonaws.services.codestar.model.ListUserProfilesResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "codestar",
        canonicalName = "CodeStar",
        title = "AWS CodeStar",
        description = "Quickly develop, build, and deploy applications on AWS",
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
public final class CodeStarApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "projects.list",
            path = "{region}/projects"
    )
    public ProjectsResponse projectsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodeStarCaller.get(ListProjectsRequest.class, ProjectsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListProjectsResult result = client.listProjects(request.withNextToken(page));
            response.setProjects(result.getProjects());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "projects.get",
            path = "{region}/projects/{projectId}"
    )
    public ProjectResponse projectsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("projectId") final String projectId
    ) throws AmazonUnparsedException {
        return CodeStarCaller.get(DescribeProjectRequest.class, ProjectResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeProjectResult result = client.describeProject(request.withId(projectId));
            response.setArn(result.getArn());
            response.setClientRequestToken(result.getClientRequestToken());
            response.setCreatedTimeStamp(result.getCreatedTimeStamp());
            response.setDescription(result.getDescription());
            response.setId(result.getId());
            response.setName(result.getName());
            response.setProjectTemplateId(result.getProjectTemplateId());
            response.setStackId(result.getStackId());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "projects.resources.list",
            path = "{region}/projects/{projectId}/resources"
    )
    public ResourcesResponse projectsResourcesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("projectId") final String projectId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodeStarCaller.get(ListResourcesRequest.class, ResourcesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListResourcesResult result = client.listResources(
                    request
                            .withProjectId(projectId)
                            .withNextToken(page)
            );
            response.setResources(result.getResources());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "projects.teamMembers.list",
            path = "{region}/projects/{projectId}/team-members"
    )
    public TeamMembersResponse projectsTeamMembersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("projectId") final String projectId,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodeStarCaller.get(ListTeamMembersRequest.class, TeamMembersResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTeamMembersResult result = client.listTeamMembers(
                    request
                            .withProjectId(projectId)
                            .withNextToken(page)
            );
            response.setTeamMembers(result.getTeamMembers());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "userProfiles.list",
            path = "{region}/user-profiles"
    )
    public UserProfilesResponse userProfilesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CodeStarCaller.get(ListUserProfilesRequest.class, UserProfilesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListUserProfilesResult result = client.listUserProfiles(request.withNextToken(page));
            response.setUserProfiles(result.getUserProfiles());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "userProfiles.get",
            path = "{region}/user-profiles/{userArn}"
    )
    public UserProfileResponse userProfilesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("userArn") final String userArn
    ) throws AmazonUnparsedException {
        return CodeStarCaller.get(DescribeUserProfileRequest.class, UserProfileResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeUserProfileResult result = client.describeUserProfile(request.withUserArn(userArn));
            response.setCreatedTimestamp(result.getCreatedTimestamp());
            response.setDisplayName(result.getDisplayName());
            response.setEmailAddress(result.getEmailAddress());
            response.setLastModifiedTimestamp(result.getLastModifiedTimestamp());
            response.setSshPublicKey(result.getSshPublicKey());
            response.setUserArn(result.getUserArn());
        });
    }
}
