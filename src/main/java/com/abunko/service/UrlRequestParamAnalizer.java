package com.abunko.service;

import com.abunko.model.*;
import org.springframework.stereotype.Service;

@Service
public class UrlRequestParamAnalizer implements UrlAnalizer{

    private UrlRequestParam requestParam;
    private UrlConfig config;

//    public UrlRequestParamAnalizer(UrlConfig config, UrlRequestParam requestParam) {
//        this.config = config;
//        this.requestParam = requestParam;
//    }

    @Override
    public ResultOfAnalysisUrl analize() {
        ResultOfAnalysisUrl result = new ResultOfAnalysisUrl(config.getUrl(), Status.OK, "all ok");
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
}  //Getting the response substring
//        answer.setSubstringEntry(response.toString().contains(question.getSubstring()) ? Status.OK : Status.CRITICAL);
