package com.cloudaware.cloudmine.amazon.cloudwatch;

import com.amazonaws.services.cloudwatch.model.MetricAlarm;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 17:12
 */
public final class MetricAlarmsResponse extends AmazonResponse {
    private List<MetricAlarm> metricAlarms;

    public List<MetricAlarm> getMetricAlarms() {
        return metricAlarms;
    }

    public void setMetricAlarms(final List<MetricAlarm> metricAlarms) {
        this.metricAlarms = metricAlarms;
    }
}
