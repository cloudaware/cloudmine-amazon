package com.cloudaware.cloudmine.amazon.cloudhsmv2;

import com.amazonaws.services.cloudhsmv2.model.Backup;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class BackupsListResponse extends AmazonResponse {
    private List<Backup> backups;

    public List<Backup> getBackups() {
        return backups;
    }

    public void setBackups(final List<Backup> backups) {
        this.backups = backups;
    }
}
