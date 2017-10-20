package com.cloudaware.cloudmine.amazon;

import com.amazonaws.AmazonWebServiceClient;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.regions.Regions;
import com.amazonaws.retry.PredefinedRetryPolicies;
import com.amazonaws.services.athena.AmazonAthena;
import com.amazonaws.services.athena.AmazonAthenaClient;
import com.amazonaws.services.autoscaling.AmazonAutoScaling;
import com.amazonaws.services.autoscaling.AmazonAutoScalingClient;
import com.amazonaws.services.cloudformation.AmazonCloudFormation;
import com.amazonaws.services.cloudformation.AmazonCloudFormationClient;
import com.amazonaws.services.cloudfront.AmazonCloudFront;
import com.amazonaws.services.cloudfront.AmazonCloudFrontClient;
import com.amazonaws.services.cloudsearchv2.AmazonCloudSearch;
import com.amazonaws.services.cloudsearchv2.AmazonCloudSearchClient;
import com.amazonaws.services.cloudtrail.AWSCloudTrail;
import com.amazonaws.services.cloudtrail.AWSCloudTrailClient;
import com.amazonaws.services.cloudwatch.AmazonCloudWatch;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchClient;
import com.amazonaws.services.codebuild.AWSCodeBuild;
import com.amazonaws.services.codebuild.AWSCodeBuildClient;
import com.amazonaws.services.codecommit.AWSCodeCommit;
import com.amazonaws.services.codecommit.AWSCodeCommitClient;
import com.amazonaws.services.codedeploy.AmazonCodeDeploy;
import com.amazonaws.services.codedeploy.AmazonCodeDeployClient;
import com.amazonaws.services.codepipeline.AWSCodePipeline;
import com.amazonaws.services.codepipeline.AWSCodePipelineClient;
import com.amazonaws.services.codestar.AWSCodeStar;
import com.amazonaws.services.codestar.AWSCodeStarClient;
import com.amazonaws.services.config.AmazonConfig;
import com.amazonaws.services.config.AmazonConfigClient;
import com.amazonaws.services.datapipeline.DataPipeline;
import com.amazonaws.services.datapipeline.DataPipelineClient;
import com.amazonaws.services.dax.AmazonDax;
import com.amazonaws.services.dax.AmazonDaxClient;
import com.amazonaws.services.directconnect.AmazonDirectConnect;
import com.amazonaws.services.directconnect.AmazonDirectConnectClient;
import com.amazonaws.services.directory.AWSDirectoryService;
import com.amazonaws.services.directory.AWSDirectoryServiceClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreams;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBStreamsClient;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ecr.AmazonECR;
import com.amazonaws.services.ecr.AmazonECRClient;
import com.amazonaws.services.ecs.AmazonECS;
import com.amazonaws.services.ecs.AmazonECSClient;
import com.amazonaws.services.elasticache.AmazonElastiCache;
import com.amazonaws.services.elasticache.AmazonElastiCacheClient;
import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalk;
import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalkClient;
import com.amazonaws.services.elasticfilesystem.AmazonElasticFileSystem;
import com.amazonaws.services.elasticfilesystem.AmazonElasticFileSystemClient;
import com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancing;
import com.amazonaws.services.elasticloadbalancing.AmazonElasticLoadBalancingClient;
import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduce;
import com.amazonaws.services.elasticmapreduce.AmazonElasticMapReduceClient;
import com.amazonaws.services.elasticsearch.AWSElasticsearch;
import com.amazonaws.services.elasticsearch.AWSElasticsearchClient;
import com.amazonaws.services.elastictranscoder.AmazonElasticTranscoder;
import com.amazonaws.services.elastictranscoder.AmazonElasticTranscoderClient;
import com.amazonaws.services.glacier.AmazonGlacier;
import com.amazonaws.services.glacier.AmazonGlacierClient;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagement;
import com.amazonaws.services.identitymanagement.AmazonIdentityManagementClient;
import com.amazonaws.services.iot.AWSIot;
import com.amazonaws.services.iot.AWSIotClient;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClient;
import com.amazonaws.services.kinesisanalytics.AmazonKinesisAnalytics;
import com.amazonaws.services.kinesisanalytics.AmazonKinesisAnalyticsClient;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClient;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClient;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClient;
import com.amazonaws.services.organizations.AWSOrganizations;
import com.amazonaws.services.organizations.AWSOrganizationsClient;
import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.AmazonRDSClient;
import com.amazonaws.services.redshift.AmazonRedshift;
import com.amazonaws.services.redshift.AmazonRedshiftClient;
import com.amazonaws.services.route53.AmazonRoute53;
import com.amazonaws.services.route53.AmazonRoute53Client;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.securitytoken.AWSSecurityTokenService;
import com.amazonaws.services.securitytoken.AWSSecurityTokenServiceClient;
import com.amazonaws.services.servicecatalog.AWSServiceCatalog;
import com.amazonaws.services.servicecatalog.AWSServiceCatalogClient;
import com.amazonaws.services.shield.AWSShield;
import com.amazonaws.services.shield.AWSShieldClient;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagement;
import com.amazonaws.services.simplesystemsmanagement.AWSSimpleSystemsManagementClient;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.storagegateway.AWSStorageGateway;
import com.amazonaws.services.storagegateway.AWSStorageGatewayClient;
import com.amazonaws.services.support.AWSSupport;
import com.amazonaws.services.support.AWSSupportClient;
import com.amazonaws.services.waf.AWSWAF;
import com.amazonaws.services.waf.AWSWAFClient;
import com.amazonaws.services.waf.AWSWAFRegional;
import com.amazonaws.services.waf.AWSWAFRegionalClient;
import com.amazonaws.services.workspaces.AmazonWorkspaces;
import com.amazonaws.services.workspaces.AmazonWorkspacesClient;

import java.util.Collection;

/**
 * User: urmuzov
 * Date: 10/23/11
 * Time: 10:20 PM
 */
public final class AmazonClientHelper {
    private static final int CONNECTION_TIMEOUT = 5 * 1000;
    private static final int SOCKET_TIMEOUT = 20 * 1000;
    private static final int REQUEST_TIMEOUT = 40 * 1000;
    private static final int CLIENT_EXECUTION_TIMEOUT = 40 * 1000;

    private final AWSCredentials credentials;
    private final ClientConfiguration config;

    public AmazonClientHelper(final String credentials) {
        this.credentials = AmazonCredentials.parse(credentials);
        config = createConfig();
    }

    public static ClientConfiguration createConfig() {
        final ClientConfiguration config = new ClientConfiguration();
        config.setUseThrottleRetries(false);
        config.setRetryPolicy(PredefinedRetryPolicies.NO_RETRY_POLICY);
        config.setConnectionTimeout(CONNECTION_TIMEOUT);
        config.setSocketTimeout(SOCKET_TIMEOUT);
        config.setRequestTimeout(REQUEST_TIMEOUT);
        config.setClientExecutionTimeout(CLIENT_EXECUTION_TIMEOUT);
        config.setMaxConnections(1);
        return config;
    }

    public static void checkRegion(final String region) {
        if (RegionUtils.getRegion(region) == null) {
            throw new IllegalArgumentException("Unknown AWS Region '" + region + "'");
        }
    }

    public static void checkEndpoint(final String region, final AmazonWebServiceClient client) {
        final Collection<String> availableEndpoints = RegionUtils.getRegion(region).getAvailableEndpoints();
        final String endpointPrefix = client.getEndpointPrefix();
        if ("ds".equals(endpointPrefix) && ("us-east-2".equals(region) || "ca-central-1".equals(region) || "ap-northeast-2".equals(region) || "eu-west-2".equals(region))) {
            // These regions don't have "ds" in available endpoints, but they have the service
            return;
        }
        if ("ssm".equals(endpointPrefix) && ("ca-central-1".equals(region) || "ap-south-1".equals(region) || "eu-west-2".equals(region))) {
            // These regions don't have "ssm" in available endpoints, but they have the service
            return;
        }
        if ("codestar".equals(endpointPrefix) && ("ap-southeast-1".equals(region) || "ap-southeast-2".equals(region) || "eu-central-1".equals(region))) {
            // These regions don't have "codestar" in available endpoints, but they have the service
            return;
        }
        if ("firehose".equals(endpointPrefix) && ("ap-northeast-1".equals(region) || "eu-central-1".equals(region) || "us-east-2".equals(region))) {
            // These regions don't have "firehose" in available endpoints, but they have the service
            return;
        }
        if ("dax".equals(endpointPrefix) && ("us-east-1".equals(region) || "us-west-1".equals(region) || "us-west-2".equals(region) || "eu-west-1".equals(region) || "ap-northeast-1".equals(region))) {
            // These regions don't have "dax" in available endpoints, but they have the service
            return;
        }
        if ("athena".equals(endpointPrefix) && "ap-southeast-2".equals(region)) {
            // These regions don't have "athena" in available endpoints, but they have the service
            return;
        }
        boolean found = false;
        for (final String availableEndpoint : availableEndpoints) {
            if (availableEndpoint.startsWith(endpointPrefix + ".") || availableEndpoint.startsWith(endpointPrefix + "-" + region)) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Unsupported AWS Region '" + region + "' for service '" + endpointPrefix + "'");
        }
    }

    public ClientWrapper<AmazonEC2> getEc2(final String region) {
        checkRegion(region);
        final AmazonEC2 client = AmazonEC2Client.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonCloudWatch> getCw(final String region) {
        checkRegion(region);
        final AmazonCloudWatch client = AmazonCloudWatchClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonRDS> getRds(final String region) {
        checkRegion(region);
        final AmazonRDS client = AmazonRDSClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonElasticLoadBalancing> getElb(final String region) {
        checkRegion(region);
        final AmazonElasticLoadBalancing client = AmazonElasticLoadBalancingClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<com.amazonaws.services.elasticloadbalancingv2.AmazonElasticLoadBalancing> getElbV2(final String region) {
        checkRegion(region);
        final com.amazonaws.services.elasticloadbalancingv2.AmazonElasticLoadBalancing client = com.amazonaws.services.elasticloadbalancingv2.AmazonElasticLoadBalancingClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonSimpleEmailService> getSes(final String region) {
        checkRegion(region);
        final AmazonSimpleEmailService client = AmazonSimpleEmailServiceClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonS3> getS3(final String region) {
        checkRegion(region);
        final AmazonS3 client = AmazonS3Client.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                /*
                 * The option below enables virtual-host-style URL http://docs.aws.amazon.com/AmazonS3/latest/dev/UsingBucket.html#access-bucket-intro
                 * Which uses following format http://bucket.s3.amazonaws.com it doesn't matter in which region the bucket actually exists
                 * It gives ability to make several API calls to any region
                 */
                .withForceGlobalBucketAccessEnabled(true)
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonRoute53> getRoute53() {
        final AmazonRoute53 client = AmazonRoute53Client.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonIdentityManagement> getIdentityManagement(final String partition) {
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = null;
        if ("aws".equals(partition)) {
            endpointConfiguration = new AwsClientBuilder.EndpointConfiguration("iam.amazonaws.com", "us-east-1");
        } else if ("aws-us-gov".equals(partition)) {
            endpointConfiguration = new AwsClientBuilder.EndpointConfiguration("iam.us-gov.amazonaws.com", "us-gov-west-1");
        } else {
            throw new IllegalArgumentException("Unsupported AWS Partition '" + partition + "'");
        }
        final AmazonIdentityManagement client = AmazonIdentityManagementClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(endpointConfiguration)
                .build();
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonCloudFormation> getCloudFormation(final String region) {
        checkRegion(region);
        final AmazonCloudFormation client = AmazonCloudFormationClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonElastiCache> getElastiCache(final String region) {
        checkRegion(region);
        final AmazonElastiCache client = AmazonElastiCacheClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSSupport> getAwsSupport() {
        final AWSSupport client = AWSSupportClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1) // if you don't explicitly define the region it doesn't work
                .build();
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonRedshift> getRedshift(final String region) {
        checkRegion(region);
        final AmazonRedshift client = AmazonRedshiftClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonGlacier> getGlacier(final String region) {
        checkRegion(region);
        final AmazonGlacier client = AmazonGlacierClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSKMS> getKms(final String region) {
        checkRegion(region);
        final AWSKMS client = AWSKMSClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonDynamoDB> getDynamoDb(final String region) {
        checkRegion(region);
        final AmazonDynamoDB client = AmazonDynamoDBClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonDynamoDBStreams> getDynamoDbStreams(final String region) {
        checkRegion(region);
        final AmazonDynamoDBStreams client = AmazonDynamoDBStreamsClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonDax> getDynamoDbAccelerator(final String region) {
        checkRegion(region);
        final AmazonDax client = AmazonDaxClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonCloudFront> getCloudFront() {
        final AmazonCloudFront client = AmazonCloudFrontClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonSQS> getSqs(final String region) {
        checkRegion(region);
        final AmazonSQS client = AmazonSQSClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonSNS> getSns(final String region) {
        checkRegion(region);
        final AmazonSNS client = AmazonSNSClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonAutoScaling> getAutoScaling(final String region) {
        checkRegion(region);
        final AmazonAutoScaling client = AmazonAutoScalingClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSCloudTrail> getCloudTrail(final String region) {
        checkRegion(region);
        final AWSCloudTrail client = AWSCloudTrailClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonElasticMapReduce> getEmr(final String region) {
        checkRegion(region);
        final AmazonElasticMapReduce client = AmazonElasticMapReduceClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSElasticBeanstalk> getElasticBeanstalk(final String region) {
        checkRegion(region);
        final AWSElasticBeanstalk client = AWSElasticBeanstalkClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonDirectConnect> getDirectConnect(final String region) {
        checkRegion(region);
        final AmazonDirectConnect client = AmazonDirectConnectClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonWorkspaces> getWorkspaces(final String region) {
        checkRegion(region);
        final AmazonWorkspaces client = AmazonWorkspacesClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSStorageGateway> getStorageGateway(final String region) {
        checkRegion(region);
        final AWSStorageGateway client = AWSStorageGatewayClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonECS> getEcs(final String region) {
        checkRegion(region);
        final AmazonECS client = AmazonECSClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSLambda> getLambda(final String region) {
        checkRegion(region);
        final AWSLambda client = AWSLambdaClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonCloudSearch> getCloudSearch(final String region) {
        checkRegion(region);
        final AmazonCloudSearch client = AmazonCloudSearchClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSOrganizations> getOrganizations() {
        final AWSOrganizations client = AWSOrganizationsClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSSecurityTokenService> getSts(final String partition) {
        AwsClientBuilder.EndpointConfiguration endpointConfiguration = null;
        if ("aws".equals(partition)) {
            endpointConfiguration = new AwsClientBuilder.EndpointConfiguration("sts.amazonaws.com", "us-east-1");
        } else if ("aws-us-gov".equals(partition)) {
            endpointConfiguration = new AwsClientBuilder.EndpointConfiguration("sts.us-gov-west-1.amazonaws.com", "us-gov-west-1");
        } else {
            throw new IllegalArgumentException("Unsupported AWS Partition '" + partition + "'");
        }
        final AWSSecurityTokenService client = AWSSecurityTokenServiceClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(endpointConfiguration)
                .build();
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSDirectoryService> getDirectoryService(final String region) {
        checkRegion(region);
        final AWSDirectoryService client = AWSDirectoryServiceClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSSimpleSystemsManagement> getSsm(final String region) {
        checkRegion(region);
        final AWSSimpleSystemsManagement client = AWSSimpleSystemsManagementClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonElasticFileSystem> getEfs(final String region) {
        checkRegion(region);
        final AmazonElasticFileSystem client = AmazonElasticFileSystemClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSElasticsearch> getElasticsearch(final String region) {
        checkRegion(region);
        final AWSElasticsearch client = AWSElasticsearchClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSCodeStar> getCodeStar(final String region) {
        checkRegion(region);
        final AWSCodeStar client = AWSCodeStarClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSCodeBuild> getCodeBuild(final String region) {
        checkRegion(region);
        final AWSCodeBuild client = AWSCodeBuildClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSCodeCommit> getCodeCommit(final String region) {
        checkRegion(region);
        final AWSCodeCommit client = AWSCodeCommitClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonConfig> getConfig(final String region) {
        checkRegion(region);
        final AmazonConfig client = AmazonConfigClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSServiceCatalog> getServiceCatalog(final String region) {
        checkRegion(region);
        final AWSServiceCatalog client = AWSServiceCatalogClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonKinesis> getKinesisStreams(final String region) {
        checkRegion(region);
        final AmazonKinesis client = AmazonKinesisClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonKinesisFirehose> getKinesisFirehose(final String region) {
        checkRegion(region);
        final AmazonKinesisFirehose client = AmazonKinesisFirehoseClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonKinesisAnalytics> getKinesisAnalytics(final String region) {
        checkRegion(region);
        final AmazonKinesisAnalytics client = AmazonKinesisAnalyticsClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSWAF> getWaf() {
        final AWSWAF client = AWSWAFClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("waf.amazonaws.com", "us-east-1"))
                .build();
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSWAFRegional> getWafRegional(final String region) {
        checkRegion(region);
        final AWSWAFRegional client = AWSWAFRegionalClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSShield> getShield() {
        final String region = "us-east-1";
        checkRegion(region);
        final AWSShield client = AWSShieldClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonCodeDeploy> getCodeDeploy(final String region) {
        checkRegion(region);
        final AmazonCodeDeploy client = AmazonCodeDeployClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<DataPipeline> getDataPipeline(final String region) {
        checkRegion(region);
        final DataPipeline client = DataPipelineClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonElasticTranscoder> getElasticTranscoder(final String region) {
        checkRegion(region);
        final AmazonElasticTranscoder client = AmazonElasticTranscoderClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSIot> getIot(final String region) {
        checkRegion(region);
        final AWSIot client = AWSIotClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AWSCodePipeline> getCodePipeline(final String region) {
        checkRegion(region);
        final AWSCodePipeline client = AWSCodePipelineClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonECR> getEcr(final String region) {
        checkRegion(region);
        final AmazonECR client = AmazonECRClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }

    public ClientWrapper<AmazonAthena> getAthena(final String region) {
        checkRegion(region);
        final AmazonAthena client = AmazonAthenaClient.builder()
                .withClientConfiguration(config)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        checkEndpoint(region, (AmazonWebServiceClient) client);
        return new ClientWrapper<>(client);
    }
}
