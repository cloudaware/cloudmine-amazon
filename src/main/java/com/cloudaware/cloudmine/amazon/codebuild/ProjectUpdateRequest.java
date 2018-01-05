package com.cloudaware.cloudmine.amazon.codebuild;

import com.amazonaws.services.codebuild.model.ProjectArtifacts;
import com.amazonaws.services.codebuild.model.ProjectCache;
import com.amazonaws.services.codebuild.model.ProjectEnvironment;
import com.amazonaws.services.codebuild.model.ProjectSource;
import com.amazonaws.services.codebuild.model.Tag;
import com.amazonaws.services.codebuild.model.VpcConfig;

import java.util.List;

/**
 * User: tolstikov
 * Date: 2/18/16
 */
public final class ProjectUpdateRequest {

    private String projectName;
    private ProjectArtifacts artifacts;
    private String description;
    private String encryptionKey;
    private ProjectEnvironment environment;
    private String serviceRole;
    private ProjectSource source;
    private Integer timeoutInMinutes;
    private ProjectCache cache;
    private VpcConfig vpcConfig;
    private Boolean badgeEnabled;

    private List<Tag> tags;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(final String projectName) {
        this.projectName = projectName;
    }

    public ProjectArtifacts getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(final ProjectArtifacts artifacts) {
        this.artifacts = artifacts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(final String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public ProjectEnvironment getEnvironment() {
        return environment;
    }

    public void setEnvironment(final ProjectEnvironment environment) {
        this.environment = environment;
    }

    public String getServiceRole() {
        return serviceRole;
    }

    public void setServiceRole(final String serviceRole) {
        this.serviceRole = serviceRole;
    }

    public ProjectSource getSource() {
        return source;
    }

    public void setSource(final ProjectSource source) {
        this.source = source;
    }

    public Integer getTimeoutInMinutes() {
        return timeoutInMinutes;
    }

    public void setTimeoutInMinutes(final Integer timeoutInMinutes) {
        this.timeoutInMinutes = timeoutInMinutes;
    }

    public Boolean getBadgeEnabled() {
        return badgeEnabled;
    }

    public void setBadgeEnabled(final Boolean badgeEnabled) {
        this.badgeEnabled = badgeEnabled;
    }

    public VpcConfig getVpcConfig() {
        return vpcConfig;
    }

    public void setVpcConfig(final VpcConfig vpcConfig) {
        this.vpcConfig = vpcConfig;
    }

    public ProjectCache getCache() {
        return cache;
    }

    public void setCache(final ProjectCache cache) {
        this.cache = cache;
    }
}
