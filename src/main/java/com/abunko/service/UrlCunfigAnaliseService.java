package com.abunko.service;

import com.abunko.model.ResultOfAnalysisUrl;
import com.abunko.model.UrlConfig;
import java.util.List;
import java.util.concurrent.ExecutionException;

 public interface UrlCunfigAnaliseService {
    void addResultUrlConfig(UrlConfig urlConfig) throws ExecutionException, InterruptedException;
    void addUrlConfig(UrlConfig urlConfig);
    List<UrlConfig> listUrlConfigs();
    List<ResultOfAnalysisUrl> listResultOfAnalysis();
    void delete(String [] ids);
    Long count();
    void stop(String url);
    void run(String url) ;

}
