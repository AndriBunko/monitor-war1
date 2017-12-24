package com.abunko.dao;

import com.abunko.model.ResultOfAnalysisUrl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
@Repository
public class ResultOfAnalysisUrlDaoImpl implements ResultOfAnalysisUrlDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void add(ResultOfAnalysisUrl analysisUrl){
        entityManager.persist(analysisUrl);
    }


    @Override
    public List<ResultOfAnalysisUrl> list() {
        TypedQuery<ResultOfAnalysisUrl> query = entityManager.createQuery("FROM ResultOfAnalysisUrl c", ResultOfAnalysisUrl.class);
        return query.getResultList();
    }

    @Transactional
    public ResultOfAnalysisUrl getByUrl(String url) {
        TypedQuery<ResultOfAnalysisUrl> query = entityManager.createQuery("SELECT c FROM ResultOfAnalysisUrl c WHERE c.url LIKE :url", ResultOfAnalysisUrl.class);
        return query.getSingleResult();
    }

    public void delete(String url) {
            ResultOfAnalysisUrl result = entityManager.getReference(ResultOfAnalysisUrl.class, url);
            entityManager.remove(result);
    }

    @Override
    public Long count() {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(c) FROM UrlConfig c", Long.class);
        return query.getSingleResult();
    }


    @Transactional
    public void update(ResultOfAnalysisUrl analysisUrl) {
        entityManager.merge(analysisUrl);
    }
}
