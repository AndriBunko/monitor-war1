package com.abunko.service;

import com.abunko.dao.RequesParamDao;
import com.abunko.dao.UrlConfigDao;
import com.abunko.model.UrlConfig;
import com.abunko.model.UrlRequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Service
public class UrlConfigAnaliseServiceImpl implements UrlCunfigAnaliseService {
    @Autowired
    private UrlConfigDao urlConfigDao;
    @Autowired
    private ScheduledExecutorService scheduler;
    @Autowired
    private RequesParamDao requesParamDao;
    @Autowired
    private UrlAnalizer urlAnalizer;
    @Autowired
    private Map<Long, ScheduledFuture<?>> runnableTasks;

    @Transactional
    public List<UrlConfig> listUrlConfigs() {
        return urlConfigDao.getAll();
    }

    @Transactional
    public void delete(long [] ids) {
        for (long id: ids) {
            try {
                stop(id);
                runnableTasks.remove(id);
                urlConfigDao.delete(id);
            } catch (NullPointerException e) {
                e.printStackTrace();
                runnableTasks.remove(id);
                urlConfigDao.delete(id);
            }
        }
    }

    public Long count() {
        return urlConfigDao.count();
    }

    public void addResultUrlConfig(UrlConfig urlConfig){
        ScheduledFuture<?> schedule = scheduler.scheduleAtFixedRate(() -> doAddAndAnaliseUrlConfig(urlConfig), 0, urlConfig.getRepeatFrequency(), TimeUnit.SECONDS);
        runnableTasks.put(urlConfig.getId(), schedule);
        System.out.println(runnableTasks.keySet());
    }

    public void addUrlConfig(UrlConfig urlConfig) {
        urlConfigDao.add(urlConfig);
    }

    public void stop(long id) {
        ScheduledFuture<?> scheduledFuture = Objects.requireNonNull(runnableTasks.remove(id), () ->  " is not running");
        if (!scheduledFuture.isDone()) {
            scheduledFuture.cancel(false);
        }
    }

    public void run(long id) {
        if(runnableTasks.get(id) == null){
            addResultUrlConfig(urlConfigDao.getById(id));
        }
    }

    private void doAddAndAnaliseUrlConfig(UrlConfig urlConfig) {
        UrlRequestParam httpParam = requesParamDao.getHttpParam(urlConfig);
        urlAnalizer.setUrlConfig(urlConfig);
        urlAnalizer.setUrlRequestParam(httpParam);
        urlConfig = urlAnalizer.analize();
        System.out.println(urlConfig);
        urlConfigDao.update(urlConfig);
    }
}
