package com.cloudaware.cloudmine.amazon.elasticbeanstalk;

import com.amazonaws.services.elasticbeanstalk.model.DescribeApplicationVersionsRequest;
import com.amazonaws.services.elasticbeanstalk.model.DescribeApplicationVersionsResult;
import com.amazonaws.services.elasticbeanstalk.model.DescribeApplicationsRequest;
import com.amazonaws.services.elasticbeanstalk.model.DescribeApplicationsResult;
import com.amazonaws.services.elasticbeanstalk.model.DescribeConfigurationOptionsRequest;
import com.amazonaws.services.elasticbeanstalk.model.DescribeConfigurationOptionsResult;
import com.amazonaws.services.elasticbeanstalk.model.DescribeConfigurationSettingsRequest;
import com.amazonaws.services.elasticbeanstalk.model.DescribeConfigurationSettingsResult;
import com.amazonaws.services.elasticbeanstalk.model.DescribeEnvironmentResourcesRequest;
import com.amazonaws.services.elasticbeanstalk.model.DescribeEnvironmentResourcesResult;
import com.amazonaws.services.elasticbeanstalk.model.DescribeEnvironmentsRequest;
import com.amazonaws.services.elasticbeanstalk.model.DescribeEnvironmentsResult;
import com.amazonaws.services.elasticbeanstalk.model.DescribeEventsRequest;
import com.amazonaws.services.elasticbeanstalk.model.DescribeEventsResult;
import com.amazonaws.services.elasticbeanstalk.model.ListAvailableSolutionStacksRequest;
import com.amazonaws.services.elasticbeanstalk.model.ListAvailableSolutionStacksResult;
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
        name = "elasticbeanstalk",
        canonicalName = "ElasticBeanstalk",
        title = "AWS Elastic Beanstalk",
        description = "Run and Manage Web Apps",
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
public final class ElasticBeanstalkApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "applications.list",
            path = "{region}/applications"
    )
    public ApplicationsResponse applicationsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return ElasticBeanstalkCaller.get(DescribeApplicationsRequest.class, ApplicationsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeApplicationsResult result = client.describeApplications(request);
            response.setApplications(result.getApplications());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "applicationVersions.list",
            path = "{region}/application-versions"
    )
    public ApplicationVersionsResponse applicationVersionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ElasticBeanstalkCaller.get(DescribeApplicationVersionsRequest.class, ApplicationVersionsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeApplicationVersionsResult result = client.describeApplicationVersions(request.withNextToken(page));
            response.setApplicationVersions(result.getApplicationVersions());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "configurationOptions.list",
            path = "{region}/configuration-options"
    )
    public ConfigurationOptionsResponse configurationOptionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return ElasticBeanstalkCaller.get(DescribeConfigurationOptionsRequest.class, ConfigurationOptionsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeConfigurationOptionsResult result = client.describeConfigurationOptions(request);
            response.setConfigurationOptions(result.getOptions());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "applications.configurationSettings.get",
            path = "{region}/applications/APPLICATION_NAME/configuration-settings"
    )
    public ConfigurationSettingsResponse applicationsConfigurationSettingsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("applicationName") final String applicationName,
            @Named("templateName") @Nullable final String templateName,
            @Named("environmentName") @Nullable final String environmentName
    ) throws AmazonUnparsedException {
        return ElasticBeanstalkCaller.get(DescribeConfigurationSettingsRequest.class, ConfigurationSettingsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeConfigurationSettingsResult result = client.describeConfigurationSettings(
                    request
                            .withApplicationName(applicationName)
                            .withTemplateName(templateName)
                            .withEnvironmentName(environmentName)
            );
            response.setConfigurationSettings(result.getConfigurationSettings());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "environments.list",
            path = "{region}/environments"
    )
    public EnvironmentsResponse environmentsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return ElasticBeanstalkCaller.get(DescribeEnvironmentsRequest.class, EnvironmentsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeEnvironmentsResult result = client.describeEnvironments(request);
            response.setEnvironments(result.getEnvironments());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "environments.resources.list",
            path = "{region}/environment/{environmentId}/resources"
    )
    public EnvironmentResourcesResponse environmentsResourcesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("environmentId") final String environmentId
    ) throws AmazonUnparsedException {
        return ElasticBeanstalkCaller.get(DescribeEnvironmentResourcesRequest.class, EnvironmentResourcesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeEnvironmentResourcesResult result = client.describeEnvironmentResources(request.withEnvironmentId(environmentId));
            response.setEnvironmentResources(result.getEnvironmentResources());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "events.list",
            path = "{region}/events"
    )
    public EventsResponse eventsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ElasticBeanstalkCaller.get(DescribeEventsRequest.class, EventsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeEventsResult result = client.describeEvents(request.withNextToken(page));
            response.setEvents(result.getEvents());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "solutionStacks.list",
            path = "{region}/solution-stacks"
    )
    public SolutionStacksResponse solutionStacksList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return ElasticBeanstalkCaller.get(ListAvailableSolutionStacksRequest.class, SolutionStacksResponse.class, credentials, region).execute((client, request, response) -> {
            final ListAvailableSolutionStacksResult result = client.listAvailableSolutionStacks(request);
            response.setSolutionStacks(result.getSolutionStackDetails());
        });
    }
}
