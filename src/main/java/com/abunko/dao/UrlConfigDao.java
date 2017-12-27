package com.abunko.dao;

import com.abunko.model.UrlConfig;

import java.util.List;

public interface UrlConfigDao {

    public void add(UrlConfig urlConfig);

    public List<UrlConfig> getAll();

    void delete(long id);

    public UrlConfig getByUrl(String url);

    public UrlConfig getById(long id);

    void update(UrlConfig config);

    long count();
}
