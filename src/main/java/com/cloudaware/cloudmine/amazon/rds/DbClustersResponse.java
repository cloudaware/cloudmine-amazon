package com.cloudaware.cloudmine.amazon.rds;

import com.amazonaws.services.rds.model.DBCluster;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 19:23
 */
public final class DbClustersResponse extends AmazonResponse {
    private List<DBCluster> dbClusters;

    public List<DBCluster> getDbClusters() {
        return dbClusters;
    }

    public void setDbClusters(final List<DBCluster> dbClusters) {
        this.dbClusters = dbClusters;
    }
}
