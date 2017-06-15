package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.ImageAttribute;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class ImageAttributeResponse extends AmazonResponse {

    private ImageAttribute imageAttribute;

    public ImageAttribute getImageAttribute() {
        return imageAttribute;
    }

    public void setImageAttribute(final ImageAttribute imageAttribute) {
        this.imageAttribute = imageAttribute;
    }
}
