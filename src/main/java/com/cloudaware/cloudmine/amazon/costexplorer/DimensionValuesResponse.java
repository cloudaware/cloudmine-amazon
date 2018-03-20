package com.cloudaware.cloudmine.amazon.costexplorer;

import com.amazonaws.services.costexplorer.model.DimensionValuesWithAttributes;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class DimensionValuesResponse extends AmazonResponse {
    private List<DimensionValuesWithAttributes> dimensionValues;

    public List<DimensionValuesWithAttributes> getDimensionValues() {
        return dimensionValues;
    }

    public void setDimensionValues(final List<DimensionValuesWithAttributes> dimensionValues) {
        this.dimensionValues = dimensionValues;
    }
}
