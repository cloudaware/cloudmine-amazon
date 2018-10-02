package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.CORSRule;

import java.io.Serializable;
import java.util.List;

public final class CorsRule implements Serializable {
    private String id;
    private List<CORSRule.AllowedMethods> allowedMethods;
    private List<String> allowedOrigins;
    private int maxAgeSeconds;
    private List<String> exposedHeaders;
    private List<String> allowedHeaders;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<CORSRule.AllowedMethods> getAllowedMethods() {
        return allowedMethods;
    }

    public void setAllowedMethods(final List<CORSRule.AllowedMethods> allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(final List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    public int getMaxAgeSeconds() {
        return maxAgeSeconds;
    }

    public void setMaxAgeSeconds(final int maxAgeSeconds) {
        this.maxAgeSeconds = maxAgeSeconds;
    }

    public List<String> getExposedHeaders() {
        return exposedHeaders;
    }

    public void setExposedHeaders(final List<String> exposedHeaders) {
        this.exposedHeaders = exposedHeaders;
    }

    public List<String> getAllowedHeaders() {
        return allowedHeaders;
    }

    public void setAllowedHeaders(final List<String> allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }
}
