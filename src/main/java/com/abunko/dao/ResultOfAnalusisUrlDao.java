package com.abunko.dao;

import com.abunko.model.ResultOfAnalysisUrl;

import java.util.List;

public interface ResultOfAnalusisUrlDao {
    List<ResultOfAnalysisUrl> list();
    ResultOfAnalysisUrl getByUrl(String url);
    void delete(String url);
    void update(String url);
    Long count();
}
