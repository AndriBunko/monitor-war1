package com.abunko.service;

import com.abunko.model.*;
import org.springframework.stereotype.Service;

@Service
 class UrlRequestParamAnalizer implements UrlAnalizer{

    private UrlRequestParam requestParam;
    private UrlConfig config;

    public UrlRequestParamAnalizer() {
    }

    @Override
    public UrlConfig analize( ) {
        String description;
        while (true) {
            if (responseCodeAnalizer() == Status.CRITICAL) {
                config.setStatus(Status.CRITICAL);
                config.setDescription("not expected response code");
                break;
            }
            if (responseLengthAnalizer() == Status.CRITICAL) {
                config.setStatus(Status.CRITICAL);
                config.setDescription("critical respons length");
                break;
            }
            if (responsTimeAnalizer() == Status.CRITICAL) {
                config.setStatus(Status.CRITICAL);
                config.setDescription("critical respons time");
                break;
            }
            if (responsTimeAnalizer() == Status.WARNING) {
                config.setStatus(Status.WARNING);
                config.setDescription("big respons time");
                break;
            }
            if (substringAnalizer() == Status.WARNING) {
                config.setStatus(Status.WARNING );
                config.setDescription("does not contain substrings");
                break;
            }
            break;
        }
        return config;
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

    public void setUrlRequestParam(UrlRequestParam requestParam) {
        this.requestParam = requestParam;
    }

    public void setUrlConfig(UrlConfig config) {
        this.config = config;
    }

}
