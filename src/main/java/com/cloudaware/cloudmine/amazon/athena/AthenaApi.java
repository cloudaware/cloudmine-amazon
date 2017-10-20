package com.cloudaware.cloudmine.amazon.athena;

import com.amazonaws.services.athena.model.BatchGetNamedQueryRequest;
import com.amazonaws.services.athena.model.BatchGetNamedQueryResult;
import com.amazonaws.services.athena.model.BatchGetQueryExecutionRequest;
import com.amazonaws.services.athena.model.BatchGetQueryExecutionResult;
import com.amazonaws.services.athena.model.ListNamedQueriesRequest;
import com.amazonaws.services.athena.model.ListNamedQueriesResult;
import com.amazonaws.services.athena.model.ListQueryExecutionsRequest;
import com.amazonaws.services.athena.model.ListQueryExecutionsResult;
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
        name = "athena",
        canonicalName = "Athena",
        title = "Amazon Athena",
        description = "Start querying data instantly. Get results in seconds. Pay only for the queries you run.",
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
public final class AthenaApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "namedQueryIds.list",
            path = "{region}/named-query-ids"
    )
    public NamedQueryIdsResponse namedQueryIdsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AthenaCaller.get(ListNamedQueriesRequest.class, NamedQueryIdsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListNamedQueriesResult result = client.listNamedQueries(request.withNextToken(page));
            response.setNamedQueryIds(result.getNamedQueryIds());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "namedQueries.list",
            path = "{region}/named-queries"
    )
    public NamedQueriesResponse namedQueriesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("namedQueryId") final List<String> namedQueryIds
    ) throws AmazonUnparsedException {
        return AthenaCaller.get(BatchGetNamedQueryRequest.class, NamedQueriesResponse.class, credentials, region).execute((client, request, response) -> {
            final BatchGetNamedQueryResult result = client.batchGetNamedQuery(request.withNamedQueryIds(namedQueryIds));
            response.setNamedQueries(result.getNamedQueries());
            response.setUnprocessedNamedQueryIds(result.getUnprocessedNamedQueryIds());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "queryExecutionIds.list",
            path = "{region}/query-execution-ids"
    )
    public QueryExecutionIdsResponse queryExecutionIdsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return AthenaCaller.get(ListQueryExecutionsRequest.class, QueryExecutionIdsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListQueryExecutionsResult result = client.listQueryExecutions(request.withNextToken(page));
            response.setQueryExecutionIds(result.getQueryExecutionIds());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "queryExecutions.list",
            path = "{region}/query-executions"
    )
    public QueryExecutionsResponse queryExecutionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("queryExecutionId") final List<String> queryExecutionIds
    ) throws AmazonUnparsedException {
        return AthenaCaller.get(BatchGetQueryExecutionRequest.class, QueryExecutionsResponse.class, credentials, region).execute((client, request, response) -> {
            final BatchGetQueryExecutionResult result = client.batchGetQueryExecution(request.withQueryExecutionIds(queryExecutionIds));
            response.setQueryExecutions(result.getQueryExecutions());
            response.setUnprocessedQueryExecutionIds(result.getUnprocessedQueryExecutionIds());
        });
    }
}
