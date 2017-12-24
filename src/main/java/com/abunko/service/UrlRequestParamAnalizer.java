package com.abunko.service;

import com.abunko.model.*;
import org.springframework.stereotype.Service;

@Service
 class UrlRequestParamAnalizer implements UrlAnalizer{

    private UrlRequestParam requestParam;
    private UrlConfig config;

    public UrlRequestParamAnalizer() {
    }

    public UrlRequestParamAnalizer(UrlConfig config, UrlRequestParam requestParam) {
        this.config = config;
        this.requestParam = requestParam;
    }

    @Override
    public ResultOfAnalysisUrl analize( ) {
        ResultOfAnalysisUrl result = new ResultOfAnalysisUrl();
        result.setDescription("all ok");
        result.setUrl(config.getUrl());
        result.setStatus(Status.OK);
        String description;
        while (true) {
            if (responseCodeAnalizer() == Status.CRITICAL) {
                result.setStatus(Status.CRITICAL);
                result.setDescription("not expected response code");
                break;
            }
            if (responseLengthAnalizer() == Status.CRITICAL) {
                result.setStatus(Status.CRITICAL);
                result.setDescription("critical respons length");
                break;
            }
            if (responsTimeAnalizer() == Status.CRITICAL) {
                result.setStatus(Status.CRITICAL);
                result.setDescription("critical respons time");
                break;
            }
            if (responsTimeAnalizer() == Status.WARNING) {
                result.setStatus(Status.WARNING);
                result.setDescription("big respons time");
                break;
            }
            if (substringAnalizer() == Status.WARNING) {
                result.setStatus(Status.WARNING );
                result.setDescription("does not contain substrings");
                break;
            }
            break;
        }
        return result;
    }

    private Status responseLengthAnalizer() {
        Status status = requestParam.getResponseLength() < config.getMinResponseLength() ||
                requestParam.getResponseLength() > config.getMaxResponseLength()  ? Status.CRITICAL : Status.OK;
        return status;
    }

    private Status responseCodeAnalizer() {
        Status status = config.getExpectedResponseCode() == requestParam.getResponseCode()  ? Status.OK : Status.CRITICAL;
        return status;
    }

    private Status responsTimeAnalizer() {
        Status status = requestParam.getResponseTime() < config.getResponseTimeOK() ? Status.OK :
                requestParam.getResponseTime() < config.getResponseTimeWARNING() ? Status.WARNING : Status.CRITICAL;
        return status;
    }

    private Status substringAnalizer(){
        Status status =  requestParam.getStr().contains(config.getSubstring()) ? Status.OK : Status.CRITICAL;
        return status;
    }

    public UrlRequestParam getRequestParam() {
        return requestParam;
    }

    public void setUrlRequestParam(UrlRequestParam requestParam) {
        this.requestParam = requestParam;
    }

    public UrlConfig getConfig() {
        return config;
    }

    public void setUrlConfig(UrlConfig config) {
        this.config = config;
    }

}
