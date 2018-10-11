package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.inventory.InventoryConfiguration;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class InventoryConfigurationsResponse extends AmazonResponse {
    private List<InventoryConfiguration> inventoryConfigurations;

    InventoryConfigurationsResponse(final AmazonException exception) {
        super(exception);
    }

    InventoryConfigurationsResponse(final List<InventoryConfiguration> configurations, final String nextPage) {
        super(nextPage);
        this.inventoryConfigurations = configurations;
    }

    public List<InventoryConfiguration> getInventoryConfigurations() {
        return inventoryConfigurations;
    }

    public void setInventoryConfigurations(final List<InventoryConfiguration> inventoryConfigurations) {
        this.inventoryConfigurations = inventoryConfigurations;
    }
}
