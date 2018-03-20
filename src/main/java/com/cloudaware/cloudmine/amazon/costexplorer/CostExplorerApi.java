package com.cloudaware.cloudmine.amazon.costexplorer;

import com.amazonaws.services.costexplorer.model.DateInterval;
import com.amazonaws.services.costexplorer.model.GetCostAndUsageRequest;
import com.amazonaws.services.costexplorer.model.GetCostAndUsageResult;
import com.amazonaws.services.costexplorer.model.GetDimensionValuesRequest;
import com.amazonaws.services.costexplorer.model.GetDimensionValuesResult;
import com.amazonaws.services.costexplorer.model.GetReservationCoverageRequest;
import com.amazonaws.services.costexplorer.model.GetReservationCoverageResult;
import com.amazonaws.services.costexplorer.model.GetReservationUtilizationRequest;
import com.amazonaws.services.costexplorer.model.GetReservationUtilizationResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Description;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.common.collect.ImmutableSet;

import java.util.Set;

@Api(
        name = "costexplorer",
        canonicalName = "CostExplorer",
        title = "AWS Cost Explorer",
        description = "The Cost Explorer API allows you to programmatically query your cost and usage data",
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
public final class CostExplorerApi {

    private static final Set<String> DEFAULT_COST_METRICS = ImmutableSet.<String>builder()
            .add("AmortizedCost")
            .add("BlendedCost")
            .add("UnblendedCost")
            .add("UsageQuantity")
            .build();

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "dimensions.values.list",
            path = "dimensions/values"
    )
    public DimensionValuesResponse dimensionValuesList(
            @Named("credentials") final String credentials,
            @Named("dateStart") final String dateStart,
            @Named("dateEnd") final String dateEnd,
            @Named("context") @Description("COST_AND_USAGE, RESERVATIONS") final String context,
            @Named("dimension") final String dimension,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return CostExplorerCaller.get(GetDimensionValuesRequest.class, DimensionValuesResponse.class, credentials).execute((client, request, response) -> {
            request.setTimePeriod(new DateInterval().withStart(dateStart).withEnd(dateEnd));
            request.setDimension(dimension);
            request.setContext(context);
            final GetDimensionValuesResult result = client.getDimensionValues(request.withNextPageToken(page));
            response.setDimensionValues(result.getDimensionValues());
            response.setNextPage(result.getNextPageToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "costandusage.list",
            path = "costandusage"
    )
    public ResultsByTimeResponse costAndUsageList(
            @Named("credentials") final String credentials,
            @Named("dateStart") @Description("Format yyyy-MM-dd") final String dateStart,
            @Named("dateEnd") @Description("Format yyyy-MM-dd") final String dateEnd,
            @Named("granularity") @Description("DAILY | MONTHLY") final String granularity,
            @Named("page") @Nullable final String page,
            @Named("metric") @Nullable @Description("AmortizedCost, BlendedCost, UnblendedCost, and UsageQuantity") final Set<String> metrics,
            final CostAndUsageRequest costAndUsageRequest
    ) throws AmazonUnparsedException {
        return CostExplorerCaller.get(GetCostAndUsageRequest.class, ResultsByTimeResponse.class, credentials).execute((client, request, response) -> {
            request.setTimePeriod(new DateInterval().withStart(dateStart).withEnd(dateEnd));
            request.setGranularity(granularity);
            request.setMetrics(metrics == null || metrics.isEmpty() ? DEFAULT_COST_METRICS : metrics);
            if (costAndUsageRequest != null) {
                if (costAndUsageRequest.getGroupDefinitions() != null) {
                    request.setGroupBy(costAndUsageRequest.getGroupDefinitions());
                }
                if (costAndUsageRequest.getFilter() != null) {
                    request.setFilter(costAndUsageRequest.getFilter());
                }
            }
            final GetCostAndUsageResult result = client.getCostAndUsage(request.withNextPageToken(page));
            response.setResultsByTime(result.getResultsByTime());
            response.setNextPage(result.getNextPageToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "reservations.utilization.list",
            path = "reservations/utilization"
    )
    public UtilizationsByTimeResponse reservationList(
            @Named("credentials") final String credentials,
            @Named("dateStart") @Description("Format yyyy-MM-dd") final String dateStart,
            @Named("dateEnd") @Description("Format yyyy-MM-dd") final String dateEnd,
            @Named("page") @Nullable final String page,
            final ReservationUtilizationRequest reservationUtilizationRequest
    ) throws AmazonUnparsedException {
        return CostExplorerCaller.get(GetReservationUtilizationRequest.class, UtilizationsByTimeResponse.class, credentials).execute((client, request, response) -> {
            request.setTimePeriod(new DateInterval().withStart(dateStart).withEnd(dateEnd));
            if (reservationUtilizationRequest != null) {
                request.setGranularity(reservationUtilizationRequest.getGranularity());
                if (reservationUtilizationRequest.getGroupDefinitions() != null) {
                    request.setGroupBy(reservationUtilizationRequest.getGroupDefinitions());
                }
                if (reservationUtilizationRequest.getFilter() != null) {
                    request.setFilter(reservationUtilizationRequest.getFilter());
                }
            }
            final GetReservationUtilizationResult result = client.getReservationUtilization(request.withNextPageToken(page));
            response.setUtilizationsByTime(result.getUtilizationsByTime());
            response.setNextPage(result.getNextPageToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "reservations.coverage.list",
            path = "reservations/coverage"
    )
    public CoveragesByTimeResponse reservationCoverageList(
            @Named("credentials") final String credentials,
            @Named("dateStart") @Description("Format yyyy-MM-dd") final String dateStart,
            @Named("dateEnd") @Description("Format yyyy-MM-dd") final String dateEnd,
            @Named("page") @Nullable final String page,
            final ReservationCoverageRequest reservationCoverageRequest
    ) throws AmazonUnparsedException {
        return CostExplorerCaller.get(GetReservationCoverageRequest.class, CoveragesByTimeResponse.class, credentials).execute((client, request, response) -> {
            request.setTimePeriod(new DateInterval().withStart(dateStart).withEnd(dateEnd));
            if (reservationCoverageRequest != null) {
                request.setGranularity(reservationCoverageRequest.getGranularity());
                if (reservationCoverageRequest.getGroupDefinitions() != null) {
                    request.setGroupBy(reservationCoverageRequest.getGroupDefinitions());
                }
                if (reservationCoverageRequest.getFilter() != null) {
                    request.setFilter(reservationCoverageRequest.getFilter());
                }
            }
            final GetReservationCoverageResult result = client.getReservationCoverage(request.withNextPageToken(page));
            response.setCoverageByTime(result.getCoveragesByTime());
            response.setNextPage(result.getNextPageToken());
        });
    }
}
