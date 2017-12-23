package com.abunko.service;

import com.abunko.Response;
import com.abunko.dao.ResultOfAnalysisUrlDao;
import com.abunko.dao.UrlConfigDao;
import com.abunko.model.ResultOfAnalysisUrl;
import com.abunko.model.UrlConfig;
import com.abunko.model.UrlRequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class UrlConfigServiceImpl implements UrlCunfigService {
    @Autowired
    private UrlConfigDao urlConfigDao;
    @Autowired
    ResultOfAnalysisUrlDao analusisUrlDao;
    @Autowired
    private ScheduledExecutorService scheduler;
    @Autowired
    private Response http;
    @Autowired
    private UrlAnalizer urlAnalizer;
    @Autowired
    private Map<String, ScheduledFuture<?>> runnableTasks;


//    @Transactional
//    public void addUrlConfig(UrlConfig urlConfig) {
//        urlConfigDao.add(urlConfig);
//    }

    @Transactional(readOnly = true)
    public List<UrlConfig> listUrlConfigs() {
        return urlConfigDao.getAll();
    }

//    @Transactional(readOnly = true)
//    public long count() {
//        return urlConfigDao.count();
//    }

    @Transactional
    public void addUrlConfig(UrlConfig urlConfig) throws ExecutionException, InterruptedException {
        System.out.println("in add");
        ScheduledFuture<?> schedule = scheduler.scheduleAtFixedRate(() -> doAddUrlConfig(urlConfig), 5, 5/*urlConfig.getCooldown()*/, TimeUnit.SECONDS);
        System.out.println("after add");
        runnableTasks.put(urlConfig.getUrl(), schedule);
        System.out.println(runnableTasks.keySet());
    }
    @Transactional
    public void stop(String id) {
        ScheduledFuture<?> scheduledFuture = Objects.requireNonNull(runnableTasks.remove(id), () -> id + " is not running");
        if (!scheduledFuture.isDone()) {
            scheduledFuture.cancel(false);
        }
    }

    private void doAddUrlConfig(UrlConfig urlConfig) {
        ResultOfAnalysisUrl analize = new ResultOfAnalysisUrl();
        try {
            urlConfigDao.getAll();
            UrlRequestParam httpParam = http.getHttpParam(urlConfig);
            urlAnalizer.setUrlConfig(urlConfig);
            urlAnalizer.setUrlRequestParam(httpParam);
            analize = urlAnalizer.analize();
            System.out.println(analize);
            urlConfigDao.add(urlConfig);
            System.out.println(analize);
            analusisUrlDao.add(analize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
