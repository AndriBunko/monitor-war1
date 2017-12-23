package com.abunko.service;

import com.abunko.model.ResultOfAnalysisUrl;
import com.abunko.model.UrlConfig;
import com.abunko.model.UrlRequestParam;

public interface UrlAnalizer {
       ResultOfAnalysisUrl analize();
       void setUrlConfig(UrlConfig urlConfig);
       void setUrlRequestParam(UrlRequestParam urlRequestParam);
}
