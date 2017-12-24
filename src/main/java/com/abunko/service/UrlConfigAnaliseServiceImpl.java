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
public class UrlConfigAnaliseServiceImpl implements UrlCunfigAnaliseService {
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

    @Transactional(readOnly = true)
    public List<UrlConfig> listUrlConfigs() {
        return urlConfigDao.getAll();
    }

    @Transactional
    public List<ResultOfAnalysisUrl> listResultOfAnalysis() {
        return analusisUrlDao.list();
    }

    @Transactional
    public void delete(String [] urls) {
        for (String url: urls) {
            try {
                System.out.println("try");
                stop(url);
                System.out.println(1);
                runnableTasks.remove(url);
                System.out.println(2);
                analusisUrlDao.delete(url);
                System.out.println("2.1");
                urlConfigDao.delete(url);
            } catch (NullPointerException e) {
                e.printStackTrace();
                analusisUrlDao.delete(url);
                runnableTasks.remove(url);
                System.out.println(4);
                urlConfigDao.delete(url);
                System.out.println(5);
            }
        }
    }

    @Override
    public Long count() {
        return analusisUrlDao.count();
    }



    public void addResultUrlConfig(UrlConfig urlConfig) throws ExecutionException, InterruptedException {
        System.out.println("in add");
        ScheduledFuture<?> schedule = scheduler.scheduleAtFixedRate(() -> doAddAndAnaliseUrlConfig(urlConfig), 0, urlConfig.getRepeatFrequency(), TimeUnit.SECONDS);
        System.out.println("after add");
        runnableTasks.put(urlConfig.getUrl(), schedule);
        System.out.println(runnableTasks.keySet());
    }

    @Override
    public void addUrlConfig(UrlConfig urlConfig) {
        urlConfigDao.add(urlConfig);
    }

    public void stop(String url) {
        ScheduledFuture<?> scheduledFuture = Objects.requireNonNull(runnableTasks.get(url), () -> url + " is not running");
        if (!scheduledFuture.isDone()) {
            scheduledFuture.cancel(false);
        }
    }

    public void run(String url) {
        ScheduledFuture<?> scheduledFuture = runnableTasks.get(url);
        try {
            if(scheduledFuture.isDone()){
                scheduledFuture.get();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void doAddAndAnaliseUrlConfig(UrlConfig urlConfig) {
        ResultOfAnalysisUrl analize = new ResultOfAnalysisUrl();
        try {
            UrlRequestParam httpParam = http.getHttpParam(urlConfig);
            urlAnalizer.setUrlConfig(urlConfig);
            urlAnalizer.setUrlRequestParam(httpParam);
            analize = urlAnalizer.analize();
            System.out.println(analize);
            analusisUrlDao.update(analize);
//            if(analusisUrlDao.getByUrl(analize.getUrl()) == null || !analusisUrlDao.getByUrl(analize.getUrl()).equals(analize)){
//                analusisUrlDao.add(analize);}
//            else{
//                analusisUrlDao.update(analize);}
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
