package com.abunko.dao;

import com.abunko.model.UrlConfig;

import java.util.List;

public interface UrlConfigDao {
    public void add(UrlConfig urlConfig);
    public List<UrlConfig> getAll();
    void delete(String  url);
}
