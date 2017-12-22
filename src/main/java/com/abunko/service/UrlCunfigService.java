package com.abunko.service;

import com.abunko.model.UrlConfig;
import java.util.List;

public interface UrlCunfigService {
    void addUrlConfig(UrlConfig urlConfig);
    List<UrlConfig> listUrlConfigs();
    long count();
}
