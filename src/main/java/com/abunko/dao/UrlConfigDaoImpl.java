package com.abunko.dao;

import com.abunko.model.UrlConfig;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UrlConfigDaoImpl implements UrlConfigDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void add(UrlConfig urlConfig) {
        entityManager.merge(urlConfig);
    }

    @Override
    public List<UrlConfig> getAll() {
        TypedQuery<UrlConfig> query = entityManager.createQuery("FROM UrlConfig c", UrlConfig.class);
        return query.getResultList();
    }

    @Override
    public Long count() {
           TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(c) FROM UrlConfig c", Long.class);
            return query.getSingleResult();
        }
}
