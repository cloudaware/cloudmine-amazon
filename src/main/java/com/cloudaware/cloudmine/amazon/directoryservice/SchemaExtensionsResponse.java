package com.cloudaware.cloudmine.amazon.directoryservice;

import com.amazonaws.services.directory.model.SchemaExtensionInfo;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class SchemaExtensionsResponse extends AmazonResponse {
    private List<SchemaExtensionInfo> schemaExtensions;

    public List<SchemaExtensionInfo> getSchemaExtensions() {
        return schemaExtensions;
    }

    public void setSchemaExtensions(final List<SchemaExtensionInfo> schemaExtensions) {
        this.schemaExtensions = schemaExtensions;
    }
}
