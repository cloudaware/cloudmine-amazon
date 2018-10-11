package com.cloudaware.cloudmine.amazon.s3;

import com.amazonaws.services.s3.model.MultipartUpload;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class MultipartUploadsResponse extends AmazonResponse {
    private List<MultipartUpload> multipartUploads;

    MultipartUploadsResponse(final AmazonException exception) {
        super(exception);
    }

    MultipartUploadsResponse(final List<MultipartUpload> uploads, final String nextPage) {
        super(nextPage);
        this.multipartUploads = uploads;
    }

    public List<MultipartUpload> getMultipartUploads() {
        return multipartUploads;
    }

    public void setMultipartUploads(final List<MultipartUpload> multipartUploads) {
        this.multipartUploads = multipartUploads;
    }
}
