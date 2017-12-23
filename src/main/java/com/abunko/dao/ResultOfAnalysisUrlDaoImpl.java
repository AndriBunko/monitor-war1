package com.abunko.dao;

import com.abunko.model.ResultOfAnalysisUrl;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class ResultOfAnalysisUrlDaoImpl implements ResultOfAnalysisUrlDao {

    @PersistenceContext
    EntityManager entityManager;

    public void add(ResultOfAnalysisUrl analysisUrl){
//        Query query = entityManager.createNativeQuery("INSERT INTO ResultOfAnalysisUrl (url, description, status)  VALUES ( '" + analysisUrl.getUrl() +
//                "', '"+ analysisUrl.getDescription() + "', '" + analysisUrl.getStatus() + "')");
//                //  query.executeUpdate();
        entityManager.merge(analysisUrl);
    }

    @Override
    public List<ResultOfAnalysisUrl> list() {
        TypedQuery<ResultOfAnalysisUrl> query = entityManager.createQuery("FROM ResultOfAnalysisUrl c", ResultOfAnalysisUrl.class);
        return query.getResultList();
    }

    @Override
    public ResultOfAnalysisUrl getByUrl(String url) {
        TypedQuery<ResultOfAnalysisUrl> query = entityManager.createQuery("SELECT c FROM ResultOfAnalysisUrl c WHERE c.url LIKE :url", ResultOfAnalysisUrl.class);
        return null;
    }

    @Override
    public void delete(String url) {

    }

    @Override
    public Long count() {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(c) FROM UrlConfig c", Long.class);
        return query.getSingleResult();
    }
}
