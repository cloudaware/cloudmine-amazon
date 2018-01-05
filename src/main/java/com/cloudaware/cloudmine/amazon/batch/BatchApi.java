package com.cloudaware.cloudmine.amazon.batch;

import com.amazonaws.services.batch.model.DescribeComputeEnvironmentsRequest;
import com.amazonaws.services.batch.model.DescribeComputeEnvironmentsResult;
import com.amazonaws.services.batch.model.DescribeJobDefinitionsRequest;
import com.amazonaws.services.batch.model.DescribeJobDefinitionsResult;
import com.amazonaws.services.batch.model.DescribeJobQueuesRequest;
import com.amazonaws.services.batch.model.DescribeJobQueuesResult;
import com.amazonaws.services.batch.model.DescribeJobsRequest;
import com.amazonaws.services.batch.model.DescribeJobsResult;
import com.amazonaws.services.batch.model.ListJobsRequest;
import com.amazonaws.services.batch.model.ListJobsResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

import java.util.List;

@Api(
        name = "batch",
        canonicalName = "Batch",
        title = "AWS Batch",
        description = "Fully Managed Batch Processing at Any Scale",
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
public final class BatchApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "jobs.list",
            path = "{region}/jobs"
    )
    public JobsResponse jobsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("jobQueue") final String jobQueue,
            @Named("jobStatus") @Nullable final String jobStatus,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return BatchCaller.get(ListJobsRequest.class, JobsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListJobsResult result = client.listJobs(request.withJobStatus(jobStatus).withJobQueue(jobQueue).withNextToken(page));
            response.setJobs(result.getJobSummaryList());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "jobDetails.list",
            path = "{region}/job-details"
    )
    public JobDetailsResponse jobDetailsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("jobId") final List<String> jobIds
    ) throws AmazonUnparsedException {
        return BatchCaller.get(DescribeJobsRequest.class, JobDetailsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeJobsResult result = client.describeJobs(request.withJobs(jobIds));
            response.setJobDetails(result.getJobs());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "jobQueues.list",
            path = "{region}/job-queues"
    )
    public JobQueuesResponse jobQueuesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("jobQueueArn") @Nullable final List<String> jobQueueArns,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return BatchCaller.get(DescribeJobQueuesRequest.class, JobQueuesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeJobQueuesResult result = client.describeJobQueues(
                    request
                            .withJobQueues(jobQueueArns)
                            .withNextToken(page)
            );
            response.setJobQueues(result.getJobQueues());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "jobDefinitions.list",
            path = "{region}/job-definitions"
    )
    public JobDefinitionsResponse jobDefinitionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("jobDefinitionArn") @Nullable final List<String> jobDefinitionArns,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return BatchCaller.get(DescribeJobDefinitionsRequest.class, JobDefinitionsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeJobDefinitionsResult result = client.describeJobDefinitions(
                    request
                            .withJobDefinitions(jobDefinitionArns)
                            .withNextToken(page)
            );
            response.setJobDefinitions(result.getJobDefinitions());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "computeEnvironments.list",
            path = "{region}/compute-environments"
    )
    public ComputeEnvironmentsResponse computeEnvironmentsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("computeEnvironmentArn") @Nullable final List<String> computeEnvironmentArns,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return BatchCaller.get(DescribeComputeEnvironmentsRequest.class, ComputeEnvironmentsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeComputeEnvironmentsResult result = client.describeComputeEnvironments(
                    request
                            .withComputeEnvironments(computeEnvironmentArns)
                            .withNextToken(page)
            );
            response.setComputeEnvironments(result.getComputeEnvironments());
            response.setNextPage(result.getNextToken());
        });
    }
}
