package com.abunko.service;

import com.abunko.model.*;
import org.springframework.stereotype.Service;

@Service
public class UrlRequestParamAnalizer implements UrlAnalizer{

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
        ResultOfAnalysisUrl result = new ResultOfAnalysisUrl(config.getUrl(), "all ok", Status.OK.toString());
        String description;
        while (true) {
            if (responseCodeAnalizer() == Status.CRITICAL) {
                result.setStatus(Status.CRITICAL.toString());
                result.setDescription("not expected response code");
                break;
            }
            if (responseLengthAnalizer() == Status.CRITICAL) {
                result.setStatus(Status.CRITICAL.toString());
                result.setDescription("critical respons length");
                break;
            }
            if (responsTimeAnalizer() == Status.CRITICAL) {
                result.setStatus(Status.CRITICAL.toString());
                result.setDescription("critical respons time");
                break;
            }
            if (responsTimeAnalizer() == Status.WARNING) {
                result.setStatus(Status.WARNING.toString());
                result.setDescription("big respons time");
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
}  //Getting the response substring
//        answer.setSubstringEntry(response.toString().contains(question.getSubstring()) ? Status.OK : Status.CRITICAL);
