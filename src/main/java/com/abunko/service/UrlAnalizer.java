package com.abunko.service;

import com.abunko.model.UrlConfig;
import com.abunko.model.UrlRequestParam;

 interface UrlAnalizer {
       UrlConfig analize();
       void setUrlConfig(UrlConfig urlConfig);
       void setUrlRequestParam(UrlRequestParam urlRequestParam);

}
