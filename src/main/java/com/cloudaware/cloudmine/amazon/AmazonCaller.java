package com.cloudaware.cloudmine.amazon;

import com.amazonaws.AmazonWebServiceRequest;

public class AmazonCaller<T, RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> {

    private final Class<RqT> requestClass;
    private final Class<RsT> responseClass;
    private final String endpointPrefix;
    private final AmazonClientCreator<T> clientCreator;

    public AmazonCaller(final Class<RqT> requestClass, final Class<RsT> responseClass, final String endpointPrefix, final AmazonClientCreator<T> clientCreator) {
        this.requestClass = requestClass;
        this.responseClass = responseClass;
        this.endpointPrefix = endpointPrefix;
        this.clientCreator = clientCreator;
    }

    private static String convertToAction(final Class<?> requestClass) {
        final String apiCallName;
        if (requestClass.getSimpleName().endsWith("Request")) {
            apiCallName = requestClass.getSimpleName().substring(0, requestClass.getSimpleName().length() - "Request".length());
        } else {
            apiCallName = "Unknown";
        }

        return apiCallName;
    }

    public final RsT execute(
            final AmazonCallable<T, RqT, RsT> callable
    ) throws AmazonUnparsedException {
        final RsT response;
        try {
            response = responseClass.newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {
            throw new AmazonUnparsedException(ex);
        }
        try (ClientWrapper<T> clientWrapper = clientCreator.create()) {
            callable.call(clientWrapper.getClient(), requestClass.newInstance(), response);
        } catch (Throwable t) {
            response.setException(AmazonResponse.parse(t, endpointPrefix + ":" + convertToAction(requestClass)));
        }

        return response;
    }
}
