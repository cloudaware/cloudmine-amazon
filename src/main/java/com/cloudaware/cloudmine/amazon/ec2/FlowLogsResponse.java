package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.FlowLog;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 17:23
 */
public final class FlowLogsResponse extends AmazonResponse {

    private List<FlowLog> flowLogs;

    public List<FlowLog> getFlowLogs() {
        return flowLogs;
    }

    public void setFlowLogs(final List<FlowLog> flowLogs) {
        this.flowLogs = flowLogs;
    }
}
