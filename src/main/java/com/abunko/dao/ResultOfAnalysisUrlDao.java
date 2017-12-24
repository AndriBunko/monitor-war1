package com.abunko.dao;

import com.abunko.model.ResultOfAnalysisUrl;

import java.util.List;

public interface ResultOfAnalysisUrlDao {
    void add(ResultOfAnalysisUrl analusisUrl);
    List<ResultOfAnalysisUrl> list();
    ResultOfAnalysisUrl getByUrl(String url);
    void delete(String url);
    Long count();
    void update(ResultOfAnalysisUrl analysisUrl);
}
