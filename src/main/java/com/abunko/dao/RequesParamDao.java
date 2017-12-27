package com.abunko.dao;

import com.abunko.model.UrlConfig;
import com.abunko.model.UrlRequestParam;

public interface RequesParamDao {
    public UrlRequestParam getHttpParam(UrlConfig urlConfig);
}
