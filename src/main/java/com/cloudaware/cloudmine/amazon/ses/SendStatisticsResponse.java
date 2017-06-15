package com.cloudaware.cloudmine.amazon.ses;

import com.amazonaws.services.simpleemail.model.SendDataPoint;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 00:42
 */
public final class SendStatisticsResponse extends AmazonResponse {
    private List<SendDataPoint> sendDataPoints;

    public List<SendDataPoint> getSendDataPoints() {
        return sendDataPoints;
    }

    public void setSendDataPoints(final List<SendDataPoint> sendDataPoints) {
        this.sendDataPoints = sendDataPoints;
    }
}
