package com.cloudaware.cloudmine.amazon.lambda;

import com.amazonaws.services.lambda.model.FunctionConfiguration;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 17:40
 */
public final class FunctionsResponse extends AmazonResponse {
    private List<FunctionConfiguration> functions;

    public List<FunctionConfiguration> getFunctions() {
        return functions;
    }

    public void setFunctions(final List<FunctionConfiguration> functions) {
        this.functions = functions;
    }
}
