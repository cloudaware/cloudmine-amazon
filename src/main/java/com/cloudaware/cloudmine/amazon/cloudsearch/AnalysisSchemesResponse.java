package com.cloudaware.cloudmine.amazon.cloudsearch;

import com.amazonaws.services.cloudsearchv2.model.AnalysisSchemeStatus;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 16:51
 */
public final class AnalysisSchemesResponse extends AmazonResponse {
    private List<AnalysisSchemeStatus> analysisSchemes;

    public List<AnalysisSchemeStatus> getAnalysisSchemes() {
        return analysisSchemes;
    }

    public void setAnalysisSchemes(final List<AnalysisSchemeStatus> analysisSchemes) {
        this.analysisSchemes = analysisSchemes;
    }
}
