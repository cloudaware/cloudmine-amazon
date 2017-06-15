package com.cloudaware.cloudmine.amazon.cloudwatch;

import com.amazonaws.services.cloudwatch.model.DescribeAlarmsRequest;
import com.amazonaws.services.cloudwatch.model.DescribeAlarmsResult;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsRequest;
import com.amazonaws.services.cloudwatch.model.GetMetricStatisticsResult;
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
        name = "cloudwatch",
        canonicalName = "CloudWatch",
        title = "Amazon CloudWatch",
        description = "Monitor Resources and Applications",
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
public final class CloudWatchApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "datapoints.metricStatistics",
            path = "{region}/datapoints/metric-statistics"
    )
    public DatapointsResponse datapointsMetricStatistics(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final MetricStatisticsRequest request
    ) throws AmazonUnparsedException {
        return CloudWatchCaller.get(GetMetricStatisticsRequest.class, DatapointsResponse.class, credentials, region).execute((client, r, response) -> {
            final GetMetricStatisticsResult result = client.getMetricStatistics(
                    r.withNamespace(request.getNamespace())
                            .withMetricName(request.getMetric())
                            .withStatistics(request.getStatistics())
                            .withStartTime(request.getStartDate())
                            .withEndTime(request.getEndDate())
                            .withPeriod(request.getPeriod())
                            .withDimensions(request.getDimensions())
            );
            response.setDatapoints(result.getDatapoints());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "metricAlarms.list",
            path = "{region}/metric-alarms"
    )
    public MetricAlarmsResponse metricAlarmsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CloudWatchCaller.get(DescribeAlarmsRequest.class, MetricAlarmsResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeAlarmsResult result = client.describeAlarms(
                    request.withNextToken(page)
            );
            response.setMetricAlarms(result.getMetricAlarms());
            response.setNextPage(result.getNextToken());
        });
    }
}
