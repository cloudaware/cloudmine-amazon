package com.cloudaware.cloudmine.amazon.ecr;

import com.amazonaws.services.ecr.model.Image;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class ImagesResponse extends AmazonResponse {
    private List<Image> images;

    public List<Image> getImages() {
        return images;
    }

    public void setImages(final List<Image> images) {
        this.images = images;
    }
}
