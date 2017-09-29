package com.cloudaware.cloudmine.amazon.ecr;

import com.amazonaws.services.ecr.model.ImageDetail;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ImageDetailsResponse extends AmazonResponse {
    private List<ImageDetail> imageDetails;

    public List<ImageDetail> getImageDetails() {
        return imageDetails;
    }

    public void setImageDetails(final List<ImageDetail> imageDetails) {
        this.imageDetails = imageDetails;
    }
}
