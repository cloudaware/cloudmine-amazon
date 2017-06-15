package com.cloudaware.cloudmine.amazon.cloudsearch;

import com.amazonaws.services.cloudsearchv2.model.IndexFieldStatus;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 16:52
 */
public final class IndexFieldsResponse extends AmazonResponse {
    private List<IndexFieldStatus> indexFields;

    public List<IndexFieldStatus> getIndexFields() {
        return indexFields;
    }

    public void setIndexFields(final List<IndexFieldStatus> indexFields) {
        this.indexFields = indexFields;
    }
}
