package com.abunko.service;

import com.abunko.model.UrlConfig;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface UrlCunfigService {
    void addUrlConfig(UrlConfig urlConfig) throws ExecutionException, InterruptedException;
    List<UrlConfig> listUrlConfigs();
    //long count();
}
