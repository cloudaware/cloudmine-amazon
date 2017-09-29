package com.cloudaware.cloudmine.amazon.ecr;

import com.amazonaws.services.ecr.model.ImageIdentifier;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ImageIdentifiersResponse extends AmazonResponse {
    private List<ImageIdentifier> imageIdentifiers;

    public List<ImageIdentifier> getImageIdentifiers() {
        return imageIdentifiers;
    }

    public void setImageIdentifiers(final List<ImageIdentifier> imageIdentifiers) {
        this.imageIdentifiers = imageIdentifiers;
    }
}
