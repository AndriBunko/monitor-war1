package com.abunko.dao;

import com.abunko.model.UrlConfig;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @Transactional
    public List<UrlConfig> getAll() {
        TypedQuery<UrlConfig> query = entityManager.createQuery("FROM UrlConfig c", UrlConfig.class);
        return query.getResultList();
    }
    @Transactional
    public void update(UrlConfig urlConfig) {
        entityManager.merge(urlConfig);
    }

    public long count() {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(c) FROM UrlConfig c", Long.class);
        return query.getSingleResult();
    }

    @Transactional
    public UrlConfig getById(long id) {
        TypedQuery<UrlConfig> query = entityManager.createQuery("SELECT c FROM UrlConfig c WHERE c.id LIKE :id", UrlConfig.class);
        System.out.println(query.getSingleResult());
        return query.getSingleResult();
    }

    @Transactional
    public UrlConfig getByUrl(String url) {
        TypedQuery<UrlConfig> query = entityManager.createQuery("SELECT c FROM UrlConfig c WHERE c.url LIKE :url", UrlConfig.class);
        return query.getSingleResult();
    }

    @Transactional
    public void delete(long id) {
        UrlConfig config = entityManager.getReference(UrlConfig.class, id);
            entityManager.remove(config);
        }
    }

