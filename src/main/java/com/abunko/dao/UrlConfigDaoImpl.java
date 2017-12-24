package com.abunko.dao;

import com.abunko.model.ResultOfAnalysisUrl;
import com.abunko.model.UrlConfig;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UrlConfigDaoImpl implements UrlConfigDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void add(UrlConfig urlConfig) {
        entityManager.persist(urlConfig);
    }

    @Override
    public List<UrlConfig> getAll() {
        TypedQuery<UrlConfig> query = entityManager.createQuery("FROM UrlConfig c", UrlConfig.class);
        return query.getResultList();
    }

    @Transactional
    public UrlConfig getByUrl(String url) {
        TypedQuery<UrlConfig> query = entityManager.createQuery("SELECT c FROM UrlConfig c WHERE c.url LIKE :url", UrlConfig.class);
        return query.getSingleResult();
    }


    public void delete(String url) {
        UrlConfig config = entityManager.getReference(UrlConfig.class, url);
            entityManager.remove(config);
        }
    }

