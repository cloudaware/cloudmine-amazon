package com.cloudaware.cloudmine.amazon.ecs;

import com.amazonaws.services.ecs.model.DescribeClustersRequest;
import com.amazonaws.services.ecs.model.DescribeClustersResult;
import com.amazonaws.services.ecs.model.DescribeContainerInstancesRequest;
import com.amazonaws.services.ecs.model.DescribeContainerInstancesResult;
import com.amazonaws.services.ecs.model.DescribeServicesRequest;
import com.amazonaws.services.ecs.model.DescribeServicesResult;
import com.amazonaws.services.ecs.model.DescribeTaskDefinitionRequest;
import com.amazonaws.services.ecs.model.DescribeTaskDefinitionResult;
import com.amazonaws.services.ecs.model.DescribeTasksRequest;
import com.amazonaws.services.ecs.model.DescribeTasksResult;
import com.amazonaws.services.ecs.model.ListClustersRequest;
import com.amazonaws.services.ecs.model.ListClustersResult;
import com.amazonaws.services.ecs.model.ListContainerInstancesRequest;
import com.amazonaws.services.ecs.model.ListContainerInstancesResult;
import com.amazonaws.services.ecs.model.ListServicesRequest;
import com.amazonaws.services.ecs.model.ListServicesResult;
import com.amazonaws.services.ecs.model.ListTaskDefinitionsRequest;
import com.amazonaws.services.ecs.model.ListTaskDefinitionsResult;
import com.amazonaws.services.ecs.model.ListTasksRequest;
import com.amazonaws.services.ecs.model.ListTasksResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "ecs",
        canonicalName = "Ecs",
        title = "Amazon EC2 Container Service",
        description = "Run and Manage Docker Containers",
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
public final class EcsApi {
    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clustersArns.list",
            path = "{region}/cluster-arns"
    )
    public ArnsResponse clustersArnsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return EcsCaller.get(ListClustersRequest.class, ArnsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListClustersResult result = client.listClusters(
                    request.withNextToken(page)
            );
            response.setArns(result.getClusterArns());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.list",
            path = "{region}/clusters"
    )
    public ClustersResponse clustersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("arn") final List<String> arns
    ) throws AmazonUnparsedException {
        return EcsCaller.get(DescribeClustersRequest.class, ClustersResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeClustersResult result = client.describeClusters(
                    request.withClusters(arns)
            );
            response.setClusters(result.getClusters());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.containerInstances.list",
            path = "{region}/clusters/CLUSTER_ARN/container-instances"
    )
    public ContainerInstancesResponse clustersContainerInstancesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterArn") final String clusterArn,
            @Named("arn") final List<String> arns
    ) throws AmazonUnparsedException {
        return EcsCaller.get(DescribeContainerInstancesRequest.class, ContainerInstancesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeContainerInstancesResult result = client.describeContainerInstances(
                    request.withCluster(clusterArn)
                            .withContainerInstances(arns)
            );
            response.setContainerInstances(result.getContainerInstances());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.containerInstanceArns.list",
            path = "{region}/clusters/CLUSTER_ARN/container-instance-arns"
    )
    public ArnsResponse clustersContainerInstanceArnsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterArn") final String clusterArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return EcsCaller.get(ListContainerInstancesRequest.class, ArnsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListContainerInstancesResult result = client.listContainerInstances(
                    request
                            .withCluster(clusterArn)
                            .withNextToken(page)
            );
            response.setArns(result.getContainerInstanceArns());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.serviceArns.list",
            path = "{region}/clusters/CLUSTER_ARN/service-arns"
    )
    public ArnsResponse clustersServiceArnsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterArn") final String clusterArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return EcsCaller.get(ListServicesRequest.class, ArnsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListServicesResult result = client.listServices(
                    request
                            .withCluster(clusterArn)
                            .withNextToken(page)
            );
            response.setArns(result.getServiceArns());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.services.list",
            path = "{region}/clusters/CLUSTER_ARN/services"
    )
    public ServicesResponse clustersServicesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterArn") final String clusterArn,
            @Named("arn") final List<String> arns
    ) throws AmazonUnparsedException {
        return EcsCaller.get(DescribeServicesRequest.class, ServicesResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeServicesResult result = client.describeServices(
                    request
                            .withCluster(clusterArn)
                            .withServices(arns)
            );
            response.setServices(result.getServices());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.taskArns.list",
            path = "{region}/clusters/CLUSTER_ARN/task-arns"
    )
    public ArnsResponse clustersTaskArnsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterArn") final String clusterArn,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return EcsCaller.get(ListTasksRequest.class, ArnsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTasksResult result = client.listTasks(
                    request
                            .withCluster(clusterArn)
                            .withNextToken(page)
            );
            response.setArns(result.getTaskArns());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "clusters.tasks.list",
            path = "{region}/clusters/CLUSTER_ARN/tasks"
    )
    public TasksResponse getTasks(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("clusterArn") final String clusterArn,
            @Named("arn") final List<String> arns
    ) throws AmazonUnparsedException {
        return EcsCaller.get(DescribeTasksRequest.class, TasksResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeTasksResult result = client.describeTasks(request.withCluster(clusterArn).withTasks(arns));
            response.setTasks(result.getTasks());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "taskDefinitionArns.list",
            path = "{region}/task-definition-arns"
    )
    public ArnsResponse taskDefinitionArnsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return EcsCaller.get(ListTaskDefinitionsRequest.class, ArnsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTaskDefinitionsResult result = client.listTaskDefinitions(request.withNextToken(page));
            response.setArns(result.getTaskDefinitionArns());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "taskDefinitions.get",
            path = "{region}/task-definition/ARN"
    )
    public TaskDefinitionResponse taskDefinitionsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("arn") final String arn
    ) throws AmazonUnparsedException {
        return EcsCaller.get(DescribeTaskDefinitionRequest.class, TaskDefinitionResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeTaskDefinitionResult result = client.describeTaskDefinition(request.withTaskDefinition(arn));
            response.setTaskDefinition(result.getTaskDefinition());
        });
    }
}
