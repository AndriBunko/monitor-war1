package com.abunko.service;

import com.abunko.model.UrlConfig;
import java.util.List;
import java.util.concurrent.ExecutionException;

 public interface UrlCunfigAnaliseService {
    void addResultUrlConfig(UrlConfig urlConfig);
    void addUrlConfig(UrlConfig urlConfig);
    List<UrlConfig> listUrlConfigs();
    void delete(long [] ids);
    Long count();
    void stop(long id);
    void run(long id) ;

}
